
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JCalendar;

public class UI {
	//JDBC variables for opening and managing connection
	private static Connection con;
    private static Statement stmt;
    //query to change language
    private String changeLangQuery = "update house_database.users set lang = '%s' where id=%d;";
    private String setHouseDataQuery = "insert into house_database.house_data values (%d, '%s', %d, %d, %d, %d, %d);";
    private String getDailyStatisticForHouse = "SELECT * FROM house_database.house_data where house_id = %d and date= '%s' ORDER BY date desc limit 1;";
    private String getTotalStatisticQuery = "SELECT SUM(total_birds_h - dead_birds_h)/sum(total_birds_h)*100 as 'survival_rate', SUM(dead_birds_h) as 'dead_birds', sum(weight_total)/count(weight_total) as 'weight_total', sum(food_total-food_cons) as 'food_cons'  FROM house_database.house_data where date = '%s';";
	private JPanel contentPane;
	private MyPanel panel1;
	private MyPanel2 panel2;
	private MyPanel3 panel3;
	private MyPanel3 panel3_1;
	private MyPanel4 panel4;
	//This object will store strings
	private ResourceBundle labels;
	private JFrame frame;
	private JMenu mnNewMenu;
	private JMenuItem HouseItem;
	private JMenuItem DailyStatsItem;
	private JMenuItem TotalStatsItem;
	private JMenuItem SettingsMenuItem;
	private JLabel HouseLbl;
	private JButton hs1;
	private JButton hs2;
	private JButton hs3;
	private JButton hs4;
	private JButton hs5;
	private JButton hs6;
	private JButton hs7;
	private JButton hs8;
	private JButton hs9;
	private JButton hs10;
	private JLabel dailyStatsLbl;
	private JComboBox comboBox;
	private JLabel hsSelectLbl;
	private JButton refreshDailyBtn;
	private JLabel rateLbl;
	private JLabel deathLbl;
	private JLabel consumptionLbl1;
	private JLabel consumptionLbl2;
	private JLabel totStatsLbl;
	private JLabel rateLblTot;
	private JLabel deathLblTot;
	private JLabel consumptionLblTot;
	private JLabel consumptionLblTotal;
	private JButton refreshTotalBtn;
	private JLabel sttngsLbl;
	private JLabel langLbl;
	private JButton logOutBtn;
	private JLabel insTotalBirds;
	private JLabel insDeadBirds;
	private JLabel insTotalWeight;
	private JLabel insTotalFood;
	private JLabel insConsFood;
	private JButton save;
	private JCalendar calendar2;
	private JCalendar calendar1;
	/**
	 * @wbp.parser.entryPoint
	 */
	void displayGUI(String lang, int id) {
		try {
			//Opening local connection by using username and password
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/", CreatorDB.user, CreatorDB.password);
            //Create variable for managing database
            stmt = con.createStatement();
		//Getting localized strings
		labels = ResourceBundle.getBundle("LabelsBundle", new Locale(lang));
		CardLayout cardLayout = new CardLayout();
		//Get title for window. You need to use the key "frame" to get the expected string
		frame = new JFrame(labels.getString("frame"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setForeground(Color.GRAY);
		frame.setJMenuBar(menuBar);

		mnNewMenu = new JMenu(labels.getString("mnNewMenu"));
		mnNewMenu.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu);
		HouseItem = new JMenuItem(labels.getString("HouseItem"));
		mnNewMenu.add(HouseItem);
		DailyStatsItem = new JMenuItem(labels.getString("DailyStatsItem"));
		mnNewMenu.add(DailyStatsItem);
		TotalStatsItem = new JMenuItem(labels.getString("TotalStatsItem"));
		mnNewMenu.add(TotalStatsItem);
		SettingsMenuItem = new JMenuItem(labels.getString("SettingsMenuItem"));
		mnNewMenu.add(SettingsMenuItem);
		JPanel contentPane = new JPanel();

		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout());

		panel1 = new MyPanel(contentPane);
		panel2 = new MyPanel2(contentPane);
		panel3 = new MyPanel3(contentPane);
		panel4 = new MyPanel4(contentPane);

		contentPane.add(panel1, "Panel 1");
		contentPane.add(panel2, "Panel 2");
		CardLayout layout = (CardLayout) contentPane.getLayout();
		panel1.setLayout(null);

		HouseLbl = new JLabel(labels.getString("HouseLbl"));
		HouseLbl.setFont(new Font("AppleGothic", Font.PLAIN, 20));
		HouseLbl.setHorizontalAlignment(SwingConstants.CENTER);
		HouseLbl.setBounds(199, 16, 210, 25);
		panel1.add(HouseLbl);

		hs1 = new JButton(labels.getString("hs1"));
		hs1.setBounds(179, 60, 117, 29);
		panel1.add(hs1);

		hs3 = new JButton(labels.getString("hs3"));
		hs3.setBounds(179, 101, 117, 29);
		panel1.add(hs3);

		hs5 = new JButton(labels.getString("hs5"));
		hs5.setBounds(179, 142, 117, 29);
		panel1.add(hs5);

		hs7 = new JButton(labels.getString("hs7"));
		hs7.setBounds(179, 183, 117, 29);
		panel1.add(hs7);

		hs2 = new JButton(labels.getString("hs2"));
		hs2.setBounds(308, 60, 117, 29);
		panel1.add(hs2);

		hs4 = new JButton(labels.getString("hs4"));
		hs4.setBounds(308, 101, 117, 29);
		panel1.add(hs4);

		hs6 = new JButton(labels.getString("hs6"));
		hs6.setBounds(308, 142, 117, 29);
		panel1.add(hs6);

		hs8 = new JButton(labels.getString("hs8"));
		hs8.setBounds(308, 183, 117, 29);
		panel1.add(hs8);

		hs9 = new JButton(labels.getString("hs9"));
		hs9.setBounds(179, 224, 117, 29);
		panel1.add(hs9);

		hs10 = new JButton(labels.getString("hs10"));
		hs10.setBounds(308, 224, 117, 29);
		panel1.add(hs10);
		panel2.setLayout(null);
		dailyStatsLbl = new JLabel(labels.getString("dailyStatsLbl"));
		dailyStatsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dailyStatsLbl.setFont(new Font("AppleGothic", Font.PLAIN, 18));
		dailyStatsLbl.setBounds(88, 18, 232, 22);
		panel2.add(dailyStatsLbl);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {labels.getString("hs1"), labels.getString("hs2"), labels.getString("hs3"), labels.getString("hs4"),
				labels.getString("hs5"), labels.getString("hs6"), labels.getString("hs7"), labels.getString("hs8"), labels.getString("hs9"), labels.getString("hs10")}));
		comboBox.setBounds(107, 87, 111, 27);
		panel2.add(comboBox);

		hsSelectLbl = new JLabel(labels.getString("hsSelectLbl"));
		hsSelectLbl.setBounds(109, 69, 134, 16);
		panel2.add(hsSelectLbl);

		refreshDailyBtn = new JButton(labels.getString("refreshDailyBtn"));

		refreshDailyBtn.setBounds(403, 11, 91, 29);
		panel2.add(refreshDailyBtn);

		//panel3.add(refreshBtn);

		rateLbl = new JLabel(labels.getString("rateLbl"));
		rateLbl.setBounds(10, 139, 191, 16);
		panel2.add(rateLbl);
		//panel3.add(rateLbl);

		final JLabel rateDisplay = new JLabel("--%");
		rateDisplay.setBounds(209, 139, 100, 16);
		panel2.add(rateDisplay);

		deathLbl = new JLabel(labels.getString("deathLbl"));
		deathLbl.setToolTipText("Death count measure in absolute");
		deathLbl.setBounds(10, 167, 91, 16);
		panel2.add(deathLbl);
		//panel3.add(rateLbl);

		final JLabel rateDisplay_1 = new JLabel("999");
		rateDisplay_1.setBounds(209, 167, 100, 16);
		panel2.add(rateDisplay_1);

		consumptionLbl1 = new JLabel(labels.getString("consumptionLbl1"));
		consumptionLbl1.setToolTipText("Death count measure in absolute");
		consumptionLbl1.setBounds(10, 199, 111, 16);
		panel2.add(consumptionLbl1);
		//panel3.add(rateLbl);

		consumptionLbl2 = new JLabel(labels.getString("consumptionLbl2"));
		consumptionLbl2.setToolTipText("Death count measure in absolute");
		consumptionLbl2.setBounds(10, 227, 159, 16);
		panel2.add(consumptionLbl2);
		//panel3.add(rateLbl);

		final JLabel rateDisplay_1_1 = new JLabel("???");
		rateDisplay_1_1.setBounds(209, 199, 100, 16);
		panel2.add(rateDisplay_1_1);

		final JLabel rateDisplay_1_1_1 = new JLabel("???");
		rateDisplay_1_1_1.setBounds(209, 228, 100, 16);
		panel2.add(rateDisplay_1_1_1);
		
		insTotalBirds = new JLabel(labels.getString("insTotalBirds"));
		insTotalBirds.setBounds(10, 258, 240, 16);
		panel2.add(insTotalBirds);
		
		final JTextField setTotalBirds = new JTextField();
		setTotalBirds.setBounds(276, 258, 100, 16);
		panel2.add(setTotalBirds);
		
		insDeadBirds = new JLabel(labels.getString("insDeadBirds"));
		insDeadBirds.setBounds(10, 288, 240, 16);
		panel2.add(insDeadBirds);
		
		final JTextField setDeadBirds = new JTextField();
		setDeadBirds.setBounds(276, 288, 100, 16);
		panel2.add(setDeadBirds);
		
		insTotalWeight = new JLabel(labels.getString("insTotalWeight"));
		insTotalWeight.setBounds(10, 318, 240, 16);
		panel2.add(insTotalWeight);
		
		final JTextField setTotalWeight = new JTextField();
		setTotalWeight.setBounds(276, 318, 100, 16);
		panel2.add(setTotalWeight);
		
		insTotalFood = new JLabel(labels.getString("insTotalFood"));
		insTotalFood.setBounds(10, 348, 240, 16);
		panel2.add(insTotalFood);
		
		final JTextField setTotalFood = new JTextField();
		setTotalFood.setBounds(276, 348, 100, 16);
		panel2.add(setTotalFood);
		
		insConsFood = new JLabel(labels.getString("insConsFood"));
		insConsFood.setBounds(10, 378, 240, 16);
		panel2.add(insConsFood);
		
		final JTextField setConsFood = new JTextField();
		setConsFood.setBounds(276, 378, 100, 16);
		panel2.add(setConsFood);
		
		calendar1 = new JCalendar();
		calendar1.setLocale(new Locale(lang));
		calendar1.setBounds(343, 50, 250, 200);
		panel2.add(calendar1);
		
		save = new JButton(labels.getString("save"));
		save.setBounds(250, 458, 100, 30);
		panel2.add(save);
		
		panel3_1 = new MyPanel3(contentPane);
		contentPane.add(panel3_1, "Panel 3");
		panel3_1.setLayout(null);
		
		
		calendar2 = new JCalendar();
		calendar2.setLocale(new Locale(lang));
		calendar2.setBounds(343, 50, 250, 200);
		panel3_1.add(calendar2);
		
		totStatsLbl = new JLabel(labels.getString("totStatsLbl"));
		totStatsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		totStatsLbl.setFont(new Font("AppleGothic", Font.PLAIN, 18));
		totStatsLbl.setBounds(88, 18, 232, 22);
		panel3_1.add(totStatsLbl);

		rateLblTot = new JLabel(labels.getString("rateLblTot"));
		rateLblTot.setBounds(10, 139, 191, 16);
		panel3_1.add(rateLblTot);

		deathLblTot = new JLabel(labels.getString("deathLblTot"));
		deathLblTot.setToolTipText("Death count measure in absolute");
		deathLblTot.setBounds(10, 161, 91, 16);
		panel3_1.add(deathLblTot);

		consumptionLblTot = new JLabel(labels.getString("consumptionLblTot"));
		consumptionLblTot.setToolTipText("Death count measure in absolute");
		consumptionLblTot.setBounds(10, 193, 111, 16);
		panel3_1.add(consumptionLblTot);

		consumptionLblTotal = new JLabel(labels.getString("consumptionLblTotal"));
		consumptionLblTotal.setToolTipText("Death count measure in absolute");
		consumptionLblTotal.setBounds(10, 227, 159, 16);
		panel3_1.add(consumptionLblTotal);

		refreshTotalBtn = new JButton(labels.getString("refreshTotalBtn"));
		refreshTotalBtn.setBounds(403, 10, 91, 29);
		panel3_1.add(refreshTotalBtn);

		final JLabel rateDisplayTotal = new JLabel("--%");
		rateDisplayTotal.setBounds(259, 133, 61, 16);
		panel3_1.add(rateDisplayTotal);

		final JLabel rateDisplay_1_2 = new JLabel("999");
		rateDisplay_1_2.setBounds(259, 161, 61, 16);
		panel3_1.add(rateDisplay_1_2);

		final JLabel rateDisplay_1_1_2 = new JLabel("???");
		rateDisplay_1_1_2.setBounds(259, 193, 61, 16);
		panel3_1.add(rateDisplay_1_1_2);

		final JLabel rateDisplay_1_1_1_1 = new JLabel("???");
		rateDisplay_1_1_1_1.setBounds(259, 222, 61, 16);
		panel3_1.add(rateDisplay_1_1_1_1);
		contentPane.add(panel4, "Panel 4");
		panel4.setLayout(null);

		sttngsLbl = new JLabel(labels.getString("sttngsLbl"));
		sttngsLbl.setFont(new Font("AppleGothic", Font.PLAIN, 20));
		sttngsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		sttngsLbl.setBounds(180, 6, 143, 35);
		panel4.add(sttngsLbl);

		//Get string for langLbl. Now you need use another key to get expected string
		langLbl = new JLabel(labels.getString("langLbl"));
		langLbl.setFont(new Font("AppleGothic", Font.PLAIN, 16));
		langLbl.setBounds(162, 103, 123, 16);
		panel4.add(langLbl);

		JSlider slider = new JSlider();
		//set locale 
		if(lang.equals("en"))
			slider.setValue(1);
		else if(lang.equals("ru"))
			slider.setValue(0);
		else 
			slider.setValue(2);
		slider.setSnapToTicks(true);
		slider.setBackground(Color.LIGHT_GRAY);
		slider.setMajorTickSpacing(1);
		slider.setMaximum(2);
		slider.setBounds(152, 120, 190, 29);
		panel4.add(slider);

		JLabel lblNewLabel = new JLabel("Русский - English - Magyar");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(152, 161, 180, 16);
		panel4.add(lblNewLabel);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// FIX THIS IMPLEMENT LANGUAGE LOCALES FILE TO UI TEXT!!!
				if (slider.getValue() == 0 && slider.getValueIsAdjusting() == false) {
					System.out.println("Changing language to Russian...");
					//Change string resources. Getting string again, but use another locale
					labels = ResourceBundle.getBundle("LabelsBundle", new Locale("ru"));
					//Change labels, buttons and ect. text
					ChangeLocale("ru");
					try {
						//Execute query to change language
						stmt.execute(String.format(changeLangQuery, "ru", id));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else if (slider.getValue() == 2 && slider.getValueIsAdjusting() == false) {
					System.out.println("Changing language to Hungarian...");
					//Change string resources. Getting string again, but use another locale
					labels = ResourceBundle.getBundle("LabelsBundle", new Locale("hu"));
					//Change labels, buttons and ect. text
					ChangeLocale("hu");
					try {
						//Execute query to change language
						stmt.execute(String.format(changeLangQuery, "hu", id));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else if (slider.getValue() == 1 && slider.getValueIsAdjusting() == false) {
					System.out.println("Changing language to English...");
					//Change string resources. Getting string again, but use another locale
					labels = ResourceBundle.getBundle("LabelsBundle", Locale.ENGLISH);
					//Change labels, buttons and ect. text
					ChangeLocale("en");
					try {
						//Execute query to change language
						stmt.execute(String.format(changeLangQuery, "en", id));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		logOutBtn = new JButton(labels.getString("logOutBtn"));
		logOutBtn.setBounds(195, 448, 117, 29);
		panel4.add(logOutBtn);
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

		logOutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Logging out...");
				// here log out happens.
				LoginGUI app1 = new LoginGUI();
				frame.dispose();
				app1.setVisible(true);
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
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox.getSelectedItem();
                if(item.equals(labels.getString("hs1"))) {
                	try {
    					//Execute query to add daily info
    					stmt.execute(String.format(setHouseDataQuery, 1, calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay(), Integer.parseInt(setTotalBirds.getText()),Integer.parseInt(setDeadBirds.getText()),Integer.parseInt(setTotalFood.getText()),Integer.parseInt(setConsFood.getText()),Integer.parseInt(setTotalWeight.getText())));
    					
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                } else if(item.equals(labels.getString("hs2"))) {
                	try {
                		//Execute query to add daily info
                		stmt.execute(String.format(setHouseDataQuery, 2, calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay(), Integer.parseInt(setTotalBirds.getText()),Integer.parseInt(setDeadBirds.getText()),Integer.parseInt(setTotalFood.getText()),Integer.parseInt(setConsFood.getText()),Integer.parseInt(setTotalWeight.getText())));
    					
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                }else if(item.equals(labels.getString("hs3"))) {
                	try {
                		//Execute query to add daily info
                		stmt.execute(String.format(setHouseDataQuery, 3,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay(), Integer.parseInt(setTotalBirds.getText()),Integer.parseInt(setDeadBirds.getText()),Integer.parseInt(setTotalFood.getText()),Integer.parseInt(setConsFood.getText()),Integer.parseInt(setTotalWeight.getText())));
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                }else if(item.equals(labels.getString("hs4"))) {
                	try {
                		//Execute query to add daily info
                		stmt.execute(String.format(setHouseDataQuery, 4,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay(), Integer.parseInt(setTotalBirds.getText()),Integer.parseInt(setDeadBirds.getText()),Integer.parseInt(setTotalFood.getText()),Integer.parseInt(setConsFood.getText()),Integer.parseInt(setTotalWeight.getText())));
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                }else if(item.equals(labels.getString("hs5"))) {
                	try {
                		//Execute query to add daily info
                		stmt.execute(String.format(setHouseDataQuery, 5,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay(), Integer.parseInt(setTotalBirds.getText()),Integer.parseInt(setDeadBirds.getText()),Integer.parseInt(setTotalFood.getText()),Integer.parseInt(setConsFood.getText()),Integer.parseInt(setTotalWeight.getText())));
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                }else if(item.equals(labels.getString("hs6"))) {
                	try {
                		//Execute query to add daily info
                		stmt.execute(String.format(setHouseDataQuery, 6,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay(), Integer.parseInt(setTotalBirds.getText()),Integer.parseInt(setDeadBirds.getText()),Integer.parseInt(setTotalFood.getText()),Integer.parseInt(setConsFood.getText()),Integer.parseInt(setTotalWeight.getText())));
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                }else if(item.equals(labels.getString("hs7"))) {
                	try {
                		//Execute query to add daily info
                		stmt.execute(String.format(setHouseDataQuery, 7,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay(), Integer.parseInt(setTotalBirds.getText()),Integer.parseInt(setDeadBirds.getText()),Integer.parseInt(setTotalFood.getText()),Integer.parseInt(setConsFood.getText()),Integer.parseInt(setTotalWeight.getText())));
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                }else if(item.equals(labels.getString("hs8"))) {
                	try {
                		//Execute query to add daily info
                		stmt.execute(String.format(setHouseDataQuery, 8, calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay(), Integer.parseInt(setTotalBirds.getText()),Integer.parseInt(setDeadBirds.getText()),Integer.parseInt(setTotalFood.getText()),Integer.parseInt(setConsFood.getText()),Integer.parseInt(setTotalWeight.getText())));
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                }else if(item.equals(labels.getString("hs9"))) {
                	try {
                		//Execute query to add daily info
                		stmt.execute(String.format(setHouseDataQuery, 9, calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay(), Integer.parseInt(setTotalBirds.getText()),Integer.parseInt(setDeadBirds.getText()),Integer.parseInt(setTotalFood.getText()),Integer.parseInt(setConsFood.getText()),Integer.parseInt(setTotalWeight.getText())));
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                }else if(item.equals(labels.getString("hs1"))) {
                	try {
                		//Execute query to add daily info
                		stmt.execute(String.format(setHouseDataQuery, 10, calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay(), Integer.parseInt(setTotalBirds.getText()),Integer.parseInt(setDeadBirds.getText()),Integer.parseInt(setTotalFood.getText()),Integer.parseInt(setConsFood.getText()),Integer.parseInt(setTotalWeight.getText())));
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                }
				
			}
		});
		
		SettingsMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(contentPane, "Panel 4");
			}
		});
		HouseItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(contentPane, "Panel 1");
			}
		});
		DailyStatsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(contentPane, "Panel 2"); 
				getDailyStatistic(rateDisplay,rateDisplay_1,rateDisplay_1_1,rateDisplay_1_1_1);
				
			}
		});
		TotalStatsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getTotalStatisticQuery, calendar2.getYearChooser().getYear()+"-"+(calendar2.getMonthChooser().getMonth()+1)+"-"+calendar2.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplayTotal.setText(rs.getFloat("survival_rate")+"%");
						rateDisplay_1_2.setText((rs.getInt("dead_birds"))+"");
						rateDisplay_1_1_2.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1_1.setText(rs.getFloat("food_cons")+"");
					}else {
						rateDisplayTotal.setText(labels.getString("noData"));
						rateDisplay_1_2.setText(labels.getString("noData"));
						rateDisplay_1_1_2.setText(labels.getString("noData"));
						rateDisplay_1_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				layout.show(contentPane, "Panel 3");
			}
		});
		hs1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 1 button pressed");
				//Showing second panel and switch element of JComboBox
				layout.show(contentPane, "Panel 2");
				comboBox.setSelectedIndex(0);
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 1,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
						rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
						rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
					}else {
						rateDisplay.setText(labels.getString("noData"));
						rateDisplay_1.setText(labels.getString("noData"));
						rateDisplay_1_1.setText(labels.getString("noData"));
						rateDisplay_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		hs2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 2 button pressed");
				//Showing second panel and switch element of JComboBox
				layout.show(contentPane, "Panel 2");
				comboBox.setSelectedIndex(1);
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 2,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
						rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
						rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
					}else {
						rateDisplay.setText(labels.getString("noData"));
						rateDisplay_1.setText(labels.getString("noData"));
						rateDisplay_1_1.setText(labels.getString("noData"));
						rateDisplay_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		hs3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 3 button pressed");
				//Showing second panel and switch element of JComboBox
				layout.show(contentPane, "Panel 2");
				comboBox.setSelectedIndex(2);
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 3,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
						rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
						rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
					}else {
						rateDisplay.setText(labels.getString("noData"));
						rateDisplay_1.setText(labels.getString("noData"));
						rateDisplay_1_1.setText(labels.getString("noData"));
						rateDisplay_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		hs4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 4 button pressed");
				//Showing second panel and switch element of JComboBox
				layout.show(contentPane, "Panel 2");
				comboBox.setSelectedIndex(3);
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 4,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
						rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
						rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
					}else {
						rateDisplay.setText(labels.getString("noData"));
						rateDisplay_1.setText(labels.getString("noData"));
						rateDisplay_1_1.setText(labels.getString("noData"));
						rateDisplay_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		hs5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 5 button pressed");
				//Showing second panel and switch element of JComboBox
				layout.show(contentPane, "Panel 2");
				comboBox.setSelectedIndex(4);
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 5,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
						rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
						rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
					}else {
						rateDisplay.setText(labels.getString("noData"));
						rateDisplay_1.setText(labels.getString("noData"));
						rateDisplay_1_1.setText(labels.getString("noData"));
						rateDisplay_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		hs6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 6 button pressed");
				//Showing second panel and switch element of JComboBox
				layout.show(contentPane, "Panel 2");
				comboBox.setSelectedIndex(5);
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 6,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
						rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
						rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
					}else {
						rateDisplay.setText(labels.getString("noData"));
						rateDisplay_1.setText(labels.getString("noData"));
						rateDisplay_1_1.setText(labels.getString("noData"));
						rateDisplay_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		hs7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 7 button pressed");
				//Showing second panel and switch element of JComboBox
				layout.show(contentPane, "Panel 2");
				comboBox.setSelectedIndex(6);
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 7,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
						rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
						rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
					}else {
						rateDisplay.setText(labels.getString("noData"));
						rateDisplay_1.setText(labels.getString("noData"));
						rateDisplay_1_1.setText(labels.getString("noData"));
						rateDisplay_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		hs8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 8 button pressed");
				//Showing second panel and switch element of JComboBox
				layout.show(contentPane, "Panel 2");
				comboBox.setSelectedIndex(7);
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 8,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
						rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
						rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
					}else {
						rateDisplay.setText(labels.getString("noData"));
						rateDisplay_1.setText(labels.getString("noData"));
						rateDisplay_1_1.setText(labels.getString("noData"));
						rateDisplay_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		hs9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 9 button pressed");
				//Showing second panel and switch element of JComboBox
				layout.show(contentPane, "Panel 2");
				comboBox.setSelectedIndex(8);
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 9,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
						rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
						rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
					}else {
						rateDisplay.setText(labels.getString("noData"));
						rateDisplay_1.setText(labels.getString("noData"));
						rateDisplay_1_1.setText(labels.getString("noData"));
						rateDisplay_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		hs10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 10 button pressed");
				//Showing second panel and switch element of JComboBox
				layout.show(contentPane, "Panel 2");
				comboBox.setSelectedIndex(9);
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 10,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
						rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
						rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
					}else {
						rateDisplay.setText(labels.getString("noData"));
						rateDisplay_1.setText(labels.getString("noData"));
						rateDisplay_1_1.setText(labels.getString("noData"));
						rateDisplay_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getDailyStatistic(rateDisplay,rateDisplay_1,rateDisplay_1_1,rateDisplay_1_1_1);
				
			}
			
		});
		refreshDailyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getDailyStatistic(rateDisplay,rateDisplay_1,rateDisplay_1_1,rateDisplay_1_1_1);
			}

		});
		refreshTotalBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//Execute query to change language
					ResultSet rs = stmt.executeQuery(String.format(getTotalStatisticQuery, calendar2.getYearChooser().getYear()+"-"+(calendar2.getMonthChooser().getMonth()+1)+"-"+calendar2.getDayChooser().getDay()));
					if(rs.next()) {
						rateDisplayTotal.setText(rs.getFloat("survival_rate")+"%");
						rateDisplay_1_2.setText((rs.getInt("dead_birds"))+"");
						rateDisplay_1_1_2.setText(rs.getFloat("weight_total")+"");
						rateDisplay_1_1_1_1.setText(rs.getFloat("food_cons")+"");
					}else {
						rateDisplayTotal.setText(labels.getString("noData"));
						rateDisplay_1_2.setText(labels.getString("noData"));
						rateDisplay_1_1_2.setText(labels.getString("noData"));
						rateDisplay_1_1_1_1.setText(labels.getString("noData"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		}catch(SQLException sqlEx) {
			//Catching exceptions and print it into console
			sqlEx.printStackTrace();
		}
	}
	void ChangeLocale(String lang) {
		frame.setTitle(labels.getString("frame"));
		langLbl.setText(labels.getString("langLbl"));
		calendar1.setLocale(new Locale(lang));
		calendar2.setLocale(new Locale(lang));
		HouseItem.setText(labels.getString("HouseItem"));
		DailyStatsItem.setText(labels.getString("DailyStatsItem"));
		TotalStatsItem.setText(labels.getString("TotalStatsItem"));
		SettingsMenuItem.setText(labels.getString("SettingsMenuItem"));
		HouseLbl.setText(labels.getString("HouseLbl"));
		hs1.setText(labels.getString("hs1"));
		hs2.setText(labels.getString("hs2"));
		hs3.setText(labels.getString("hs3"));
		hs4.setText(labels.getString("hs4"));
		hs5.setText(labels.getString("hs5"));
		hs6.setText(labels.getString("hs6"));
		hs7.setText(labels.getString("hs7"));
		hs8.setText(labels.getString("hs8"));
		hs9.setText(labels.getString("hs9"));
		hs10.setText(labels.getString("hs10"));
		dailyStatsLbl.setText(labels.getString("dailyStatsLbl"));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {labels.getString("hs1"), labels.getString("hs2"), labels.getString("hs3"),
				labels.getString("hs4"), labels.getString("hs5"), labels.getString("hs6"), labels.getString("hs7"), labels.getString("hs8"), labels.getString("hs9"), labels.getString("hs10")}));
		hsSelectLbl.setText(labels.getString("hsSelectLbl"));
        refreshDailyBtn.setText(labels.getString("refreshDailyBtn"));
        rateLbl.setText(labels.getString("rateLbl"));
        deathLbl.setText(labels.getString("deathLbl"));
        consumptionLbl1.setText(labels.getString("consumptionLbl1"));
        consumptionLbl2.setText(labels.getString("consumptionLbl2"));
        totStatsLbl.setText(labels.getString("totStatsLbl"));
        rateLblTot.setText(labels.getString("rateLblTot"));
        deathLblTot.setText(labels.getString("deathLblTot"));
        consumptionLblTot.setText(labels.getString("consumptionLblTot"));
        consumptionLblTotal.setText(labels.getString("consumptionLblTotal"));
        refreshTotalBtn.setText(labels.getString("refreshTotalBtn"));
        sttngsLbl.setText(labels.getString("sttngsLbl"));
        logOutBtn.setText(labels.getString("logOutBtn"));
        mnNewMenu.setText(labels.getString("mnNewMenu"));
        insTotalBirds.setText(labels.getString("insTotalBirds"));
    	insDeadBirds.setText(labels.getString("insDeadBirds"));
    	insTotalWeight.setText(labels.getString("insTotalWeight"));
    	insTotalFood.setText(labels.getString("insTotalFood"));
    	insConsFood.setText(labels.getString("insConsFood"));
    	save.setText(labels.getString("save"));
	}
	
	void getDailyStatistic(JLabel rateDisplay,JLabel rateDisplay_1,JLabel rateDisplay_1_1,JLabel rateDisplay_1_1_1) {
		String item = (String)comboBox.getSelectedItem();
        if(item.equals(labels.getString("hs1"))) {
        	try {
				//Execute query to change language
				ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 1,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
				if(rs.next()) {
					rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
					rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
					rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
					rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
				}else {
					rateDisplay.setText(labels.getString("noData"));
					rateDisplay_1.setText(labels.getString("noData"));
					rateDisplay_1_1.setText(labels.getString("noData"));
					rateDisplay_1_1_1.setText(labels.getString("noData"));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        } else if(item.equals(labels.getString("hs2"))) {
        	try {
				//Execute query to change language
				ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 2,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
				if(rs.next()) {
					rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
					rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
					rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
					rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
				}else {
					rateDisplay.setText(labels.getString("noData"));
					rateDisplay_1.setText(labels.getString("noData"));
					rateDisplay_1_1.setText(labels.getString("noData"));
					rateDisplay_1_1_1.setText(labels.getString("noData"));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }else if(item.equals(labels.getString("hs3"))) {
        	try {
				//Execute query to change language
				ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 3,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
				if(rs.next()) {
					rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
					rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
					rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
					rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
				}else {
					rateDisplay.setText(labels.getString("noData"));
					rateDisplay_1.setText(labels.getString("noData"));
					rateDisplay_1_1.setText(labels.getString("noData"));
					rateDisplay_1_1_1.setText(labels.getString("noData"));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }else if(item.equals(labels.getString("hs4"))) {
        	try {
				//Execute query to change language
				ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 4,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
				if(rs.next()) {
					rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
					rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
					rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
					rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
				}else {
					rateDisplay.setText(labels.getString("noData"));
					rateDisplay_1.setText(labels.getString("noData"));
					rateDisplay_1_1.setText(labels.getString("noData"));
					rateDisplay_1_1_1.setText(labels.getString("noData"));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }else if(item.equals(labels.getString("hs5"))) {
        	try {
				//Execute query to change language
				ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 5,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
				if(rs.next()) {
					rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
					rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
					rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
					rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
				}else {
					rateDisplay.setText(labels.getString("noData"));
					rateDisplay_1.setText(labels.getString("noData"));
					rateDisplay_1_1.setText(labels.getString("noData"));
					rateDisplay_1_1_1.setText(labels.getString("noData"));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }else if(item.equals(labels.getString("hs6"))) {
        	try {
				//Execute query to change language
				ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 6,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
				if(rs.next()) {
					rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
					rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
					rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
					rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
				}else {
					rateDisplay.setText(labels.getString("noData"));
					rateDisplay_1.setText(labels.getString("noData"));
					rateDisplay_1_1.setText(labels.getString("noData"));
					rateDisplay_1_1_1.setText(labels.getString("noData"));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }else if(item.equals(labels.getString("hs7"))) {
        	try {
				//Execute query to change language
				ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 7,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
				if(rs.next()) {
					rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
					rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
					rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
					rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
				}else {
					rateDisplay.setText(labels.getString("noData"));
					rateDisplay_1.setText(labels.getString("noData"));
					rateDisplay_1_1.setText(labels.getString("noData"));
					rateDisplay_1_1_1.setText(labels.getString("noData"));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }else if(item.equals(labels.getString("hs8"))) {
        	try {
				//Execute query to change language
				ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 8,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
				if(rs.next()) {
					rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
					rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
					rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
					rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
				}else {
					rateDisplay.setText(labels.getString("noData"));
					rateDisplay_1.setText(labels.getString("noData"));
					rateDisplay_1_1.setText(labels.getString("noData"));
					rateDisplay_1_1_1.setText(labels.getString("noData"));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }else if(item.equals(labels.getString("hs9"))) {
        	try {
				//Execute query to change language
				ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 9,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
				if(rs.next()) {
					rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
					rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
					rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
					rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
				}else {
					rateDisplay.setText(labels.getString("noData"));
					rateDisplay_1.setText(labels.getString("noData"));
					rateDisplay_1_1.setText(labels.getString("noData"));
					rateDisplay_1_1_1.setText(labels.getString("noData"));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }else if(item.equals(labels.getString("hs10"))) {
        	try {
				//Execute query to change language
				ResultSet rs = stmt.executeQuery(String.format(getDailyStatisticForHouse, 10,calendar1.getYearChooser().getYear()+"-"+(calendar1.getMonthChooser().getMonth()+1)+"-"+calendar1.getDayChooser().getDay()));
				if(rs.next()) {
					rateDisplay.setText(((rs.getInt("total_birds_h")-rs.getInt("dead_birds_h"))*100f/rs.getInt("total_birds_h"))+"%");
					rateDisplay_1.setText((rs.getInt("dead_birds_h"))+"");
					rateDisplay_1_1.setText(rs.getFloat("weight_total")+"");
					rateDisplay_1_1_1.setText((rs.getFloat("food_total")-rs.getFloat("food_cons"))+"");
				}else {
					rateDisplay.setText(labels.getString("noData"));
					rateDisplay_1.setText(labels.getString("noData"));
					rateDisplay_1_1.setText(labels.getString("noData"));
					rateDisplay_1_1_1.setText(labels.getString("noData"));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
	}
}

class MyPanel extends JPanel {
	private JPanel contentPane;

	public MyPanel(JPanel panel) {
		contentPane = panel;
		setOpaque(true);

	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}

class MyPanel2 extends JPanel {
	private JPanel contentPane;

	public MyPanel2(JPanel panel) {
		contentPane = panel;
		setOpaque(true);
		// stuff come here
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}

class MyPanel3 extends JPanel {
	private JPanel contentPane;

	public MyPanel3(JPanel panel) {
		contentPane = panel;
		setOpaque(true);

	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}

}

class MyPanel4 extends JPanel {
	private JPanel contentPane;

	public MyPanel4(JPanel panel) {
		contentPane = panel;
		setOpaque(true);

	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(600, 500));
	}
}
