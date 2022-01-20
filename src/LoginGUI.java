
//FriedmanHasGreasyHairProductions Inc.
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {
    //JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	public static boolean credentialVerified;
	//Query to search user with username and password
	private String checkQuery = "SELECT * FROM house_database.users where username='%s' and password='%s'";
	static LoginGUI app1 = new LoginGUI();

	public LoginGUI() {
		super("Simple Example");
		
		try {
			//Opening local connection by using username and password
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/", CreatorDB.user, CreatorDB.password);
            //Create variable for managing database
            stmt = con.createStatement();
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
		        ResultSet rs;
				try {
					//execute query
					rs = stmt.executeQuery(String.format(checkQuery, txtUsername.getText(), decodedPass()));
					//if it has result it means that username and password is correct
			        if (rs.next())
			        {
			        	//saving id and language
			        	int id = rs.getInt("id");
			            String lang = rs.getString("lang");
			            //getting access level but don't use it
			            int access_level = rs.getInt("access_level");
			            app1.dispose();
			            SwingUtilities.invokeLater(new Runnable() {
			                public void run() {
			                	//Now UI now user id and lang
			                    new UI().displayGUI(lang, id);
			                }
			            });
			            try {
			                //Close connection
			                con.close();
			            } catch (SQLException se) {
			                //Catching exceptions and print it into console
			                se.printStackTrace();
			            }
			            try {
			                //Close manager
			                stmt.close();
			            } catch (SQLException se) {
			                //Catching exceptions and print it into console
			                se.printStackTrace();
			            }
			        }
			        else
			            System.err.println("Credentials invalid!");
				} catch (SQLException e1) {
					//Catching exceptions and print it into console
					e1.printStackTrace();
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
		databaseControll();
		}catch(SQLException sqlEx) {
			//Catching exceptions and print it into console
			sqlEx.printStackTrace();
		}
	}
	
	private void databaseControll() {
		JButton create = new JButton("Create Database");
		JButton drop = new JButton("Drop Database");
		create.setBounds(10, 10, 140, 20);
		drop.setBounds(160, 10, 120, 20);
		JButton addUser = new JButton("Add user un='sop' p='sop' ");
		addUser.setBounds(10, 40, 300, 20);
		
		addUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreatorDB.addUser();
			}
			
		});
		
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreatorDB.createDB();
			}
			
		});
		
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreatorDB.dropDB();
			}
			
		});
		add(create);
		add(drop);
		add(addUser);
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
