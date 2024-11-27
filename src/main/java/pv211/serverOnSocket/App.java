package pv211.serverOnSocket;

import javafx.application.Application;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.Socket;
import java.io.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.BorderLayout;
/**
 * JavaFX App
 */
public class App extends Application {
	static JFrame jFrame = new JFrame();//frame with limit
    static JPanel jPanel1 = new JPanel(), jPanel2 = new JPanel(), jPanel3 = new JPanel();//swing's lightweight container which's used to group set of components together
    static JButton jButton = new JButton("отправить сообщение");//implementation of push button
    static JLabel jLabel1 = new JLabel(), jLabel2 = new JLabel();//display area for short text string or image
    static JTextArea jTextArea = new JTextArea();//text field of many lines
    @Override
    public void start(Stage stage) {
        jTextArea.setLineWrap(true);
        jTextArea.setPreferredSize(new Dimension(210, 210));
        ArrayList<String> messages = new ArrayList<String>();//message list
        jButton.addActionListener(new ActionListener() {
        	String str = new String();//text that has to be received
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		try {
        			InetAddress addr = InetAddress.getByName("192.168.1.146");//socket creature
        			Socket socket = new Socket(addr, 8080);//socket object
        			try {
        				jLabel1.setText("<html>");
        				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//object of buffered reader
        				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);//object of print writer
        				out.println(jTextArea.getText());
        				jTextArea.setText(null);
        				this.str = in.readLine();//receiving string value from server
        				if (messages.toArray().length == 10) messages.remove(0);
        				messages.add(this.str);
        				Object[] msgs = messages.toArray();//message array
        				for (int i = 0; i < msgs.length; i++) jLabel1.setText(jLabel1.getText() + "<div>" + msgs[i] + "</div>");
        				jLabel1.setText(jLabel1.getText() + "</html>");
        			}
        			finally { socket.close(); }
        		}
        		catch(IOException iOException) { }
        	}
        });
        int maxCountOfSymb = 377;//symbol limit
        jTextArea.getDocument().addDocumentListener(new DocumentListener() {
        	String text = new String();//message inside text area
        	@Override
        	public void removeUpdate(DocumentEvent e) { this.clean(); }
        	@Override
        	public void insertUpdate(DocumentEvent e) { this.clean(); }
        	@Override
        	public void changedUpdate(DocumentEvent e) { }
        	void clean() {
        		int textLength = jTextArea.getText().length();//message length
        		if (textLength <= maxCountOfSymb) this.text = jTextArea.getText();//text reset 
        		if (textLength > maxCountOfSymb) jTextArea.setText(this.text);
        		jLabel2.setText(jTextArea.getText().length() + "/" + maxCountOfSymb);
        	}
        });
        jFrame.setLayout(new BorderLayout());
        jFrame.add(jPanel1, BorderLayout.WEST);
        jFrame.add(jPanel2, BorderLayout.CENTER);
        jFrame.add(jPanel3, BorderLayout.EAST);
        jPanel1.add(jButton);
        jPanel2.add(jLabel1);
        addComp(jPanel3, jTextArea, jLabel2);
        jFrame.pack();
        jFrame.setSize(600, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
    static void addComp(JPanel panel, JTextArea textArea, JLabel label) {
    	panel.add(textArea);
    	panel.add(label);
    }
    public static void main(String[] args) {
        launch();
    }
}