
//FriedmanHasGreasyHairProductions Inc.
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {
	private JTextField txtUsername;
	private JPasswordField passwordField;
	public static boolean credentialVerified;
	static LoginGUI app1 = new LoginGUI();

	public LoginGUI() {
		super("Simple Example");
		getContentPane().setBackground(SystemColor.window);
		this.setBounds(100, 100, 440, 340);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		String username = "sop"; // change these
		String password = "sop"; // change these
		setLayout(null);
		JLabel psdicn = new JLabel("");
		psdicn.setIcon(new ImageIcon("psw.png"));
		psdicn.setBounds(65, 152, 21, 23);
		add(psdicn);

		JLabel usricn = new JLabel("");
		usricn.setIcon(new ImageIcon("usr.png"));
		usricn.setBounds(65, 105, 21, 35);
		add(usricn);

		JButton procBttn = new JButton("PROCEED");
		procBttn.setFont(new Font("AppleGothic", Font.PLAIN, 13));
		procBttn.setHorizontalAlignment(SwingConstants.RIGHT);
		procBttn.setBounds(50, 190, 335, 42);
		add(procBttn);
		procBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((txtUsername.getText().equals(username)) && (decodedPass().equals(password))) {
					app1.dispose();
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							new UI().displayGUI();
						}
					});
				} else {
					System.err.println("Credentials invalid!");
				}
			}
		});
		JLabel loginLbl = new JLabel("Log In");
		loginLbl.setFont(new Font("AppleGothic", Font.PLAIN, 19));
		loginLbl.setBounds(55, 58, 83, 42);
		add(loginLbl);

		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setFont(new Font("AppleGothic", Font.PLAIN, 15));
		txtUsername.setBounds(50, 96, 335, 47);
		add(txtUsername);
		txtUsername.setColumns(1);

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		passwordField.setEchoChar('☻'); // lil boops
		passwordField.setBounds(50, 140, 335, 47);
		add(passwordField);
	}

	private String decodedPass() {
		String decoded = "";
		for (int i = 0; i < passwordField.getPassword().length; i++) {
			decoded = decoded + passwordField.getPassword()[i];
		}
		return decoded;
	}

	public static void main(String[] args) throws Exception {
		app1.setVisible(true);
	}
}
