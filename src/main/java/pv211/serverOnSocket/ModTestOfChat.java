package pv211.serverOnSocket;
import org.junit.jupiter.api
.BeforeAll;
import org.junit.jupiter.api
.Test;
import static org.junit.jupiter.api
.Assertions.assertTrue;
public class ModTestOfChat {
	ModTestOfChat() { }
	@BeforeAll
	public static void begin() {
		App app = new App();//program
		app.start(null);
	}
	@Test
	public void testJButton() {
		boolean jButtIsCont = false;//check panel 1
		if (App.jPanel1.getComponent(0) == App.jButton) jButtIsCont = true;//condition of positive result
		assertTrue(jButtIsCont);
	}
	@Test
	public void testJLab() {
		boolean jLabIsCont = false;//check panel 2
		if (App.jPanel2.getComponent(0) == App.jLabel1) jLabIsCont = true;//condition of positive result
		assertTrue(jLabIsCont);
	}
	@Test
	public void testJTextAr() {
		boolean jTextArIsCont = false;//check panel 3
		if (App.jPanel3.getComponent(0) == App.jTextArea) jTextArIsCont = true;//condition of positive result
		assertTrue(jTextArIsCont);
	}
}