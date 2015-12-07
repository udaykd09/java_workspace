package coolMath;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * A Swing application that uploads files to a HTTP server.
 * @author www.codejava.net
 *
 */
public class SwingFileUploadHTTP extends JFrame {
	private JLabel lab = new JLabel();
	private JFilePicker filePicker = new JFilePicker("Choose a file: ",
			"Browse");

	private JButton buttonUpload = new JButton("Upload");
	private JButton buttonLogout = new JButton("Logout");
	
	public SwingFileUploadHTTP(String s2) {
		super("Swing File Upload to HTTP server");

		// set up layout
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);

		// set up components
		filePicker.setMode(JFilePicker.MODE_OPEN);

		buttonUpload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				buttonUploadActionPerformed(event);
			}
		});

		buttonLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				buttonLogoutActionPerformed(event);
			}
		});
		
		// add components to the frame
		//constraints.gridx = 0;
		//constraints.gridy = 0;
		//constraints.anchor = GridBagConstraints.CENTER;
		lab.setHorizontalAlignment(SwingConstants.CENTER);
		lab.setForeground(Color.DARK_GRAY);
		lab.setFont(new Font("Serif", Font.BOLD, 20));
		lab.setText(s2);
		add(lab);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 0.0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.NONE;
		add(filePicker, constraints);

		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		add(buttonUpload, constraints);

		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		add(buttonLogout, constraints);

		pack();
		setLocationRelativeTo(null);	// center on screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * handle click event of the Upload button
	 */
	private void buttonUploadActionPerformed(ActionEvent event) {
		String filePath = filePicker.getSelectedFilePath();

		// validate input first

		if (filePath.equals("")) {
			JOptionPane.showMessageDialog(this,
					"Please choose a file to upload!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {

			JavaPHPClient uday = new JavaPHPClient();
			String msg = uday.httpupload(filePath);
			JOptionPane.showMessageDialog(this,msg);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
					"Error executing upload task: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	
	
	private void buttonLogoutActionPerformed(ActionEvent event) {
		
		String msg_l=logout();
		JOptionPane.showMessageDialog(this,msg_l);
		setVisible(false);
		new login();
	}
	
	public String logout() {
		try {
			String url = "http://udaykdungarwal.com/Woohoo/logout_swing.php";
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

	/**
	 * Launch the application
	 */
	public void browse(SwingFileUploadHTTP sf) {
		try {
			// set look and feel to system dependent
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

				sf.setVisible(true);
				setSize(600, 300);
	}
}