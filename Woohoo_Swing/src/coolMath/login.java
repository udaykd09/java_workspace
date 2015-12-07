package coolMath;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {
	JLabel l0, l1, l2, l3;
	JTextField tf1;
	JButton btn1;
	JPasswordField p1;

	public login() {
		setTitle("Login Form in Windows Form");
		setVisible(true);
		setSize(600, 300);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		l1 = new JLabel("Woohoo");
		l1.setForeground(Color.black);
		l1.setFont(new Font("Serif", Font.BOLD, 40));
		l1.setHorizontalAlignment(SwingConstants.CENTER);

		l2 = new JLabel("Enter Email:");
		l3 = new JLabel("Enter Password:");
		tf1 = new JTextField();
		p1 = new JPasswordField();
		btn1 = new JButton("Submit");

		l1.setBounds(100, 20, 400, 30);
		l2.setBounds(80, 70, 200, 30);
		l3.setBounds(80, 110, 200, 30);
		tf1.setBounds(300, 70, 200, 30);
		p1.setBounds(300, 110, 200, 30);
		btn1.setBounds(150, 160, 100, 30);
		//btn1.setHorizontalAlignment(SwingConstants.CENTER);

		add(l1);
		add(l2);
		add(tf1);
		add(l3);
		add(p1);
		add(btn1);
		btn1.addActionListener(this);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		showData();
	}

	public void showData() {

		String str1 = tf1.getText();
		char[] p = p1.getPassword();
		String str2 = new String(p);
		try {

			String s2 = isValid(str1, str2);
			if (s2 != null) {

				uploadFile(s2);
				setVisible(false);
			} else {
				JOptionPane
						.showMessageDialog(null,
								"Incorrect email-Id or password..Try Again with correct detail");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private void uploadFile(String s2) {
		SwingFileUploadHTTP home = new SwingFileUploadHTTP(s2);
		home.browse(home);

	}

	public static void main(String arr[]) {
		new login();
	}

	public String isValid(String str1, String str2) {
		try {
			String url = "http://udaykdungarwal.com/Woohoo/login_swing.php?mail="
					+ str1 + "&password=" + str2;
			URL serverURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) serverURL
					.openConnection();// Opens a HTTPURLConnection
			conn.setDoOutput(true);// Sets the connection to for Output
			conn.setDoInput(true);// Sets the connection to for Output
			conn.setRequestMethod("POST");// Sets the request method to Post
			PrintStream wr = new PrintStream(conn.getOutputStream(), true);
			BufferedReader bget = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));// To read response
			String s1 = null;
			String s = null;
			while ((s = bget.readLine()) != null) {
				s1 = s;
			}
			bget.close();
			return s1;
		} catch (Exception e) {
			return "ERROR";
		}
	}
}
