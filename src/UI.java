
// No simpin allowed
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UI {
	private JPanel contentPane;
	private MyPanel panel1;
	private MyPanel2 panel2;
	private MyPanel3 panel3;
	private MyPanel3 panel3_1;
	private MyPanel4 panel4;

	/**
	 * @wbp.parser.entryPoint
	 */
	void displayGUI() {
		CardLayout cardLayout = new CardLayout();
		JFrame frame = new JFrame("Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setForeground(Color.GRAY);
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
		mnNewMenu.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu);
		JMenuItem HouseItem = new JMenuItem("House List");
		mnNewMenu.add(HouseItem);
		JMenuItem DailyStatsItem = new JMenuItem("Daily Statistics");
		mnNewMenu.add(DailyStatsItem);
		JMenuItem TotalStatsItem = new JMenuItem("Total Statistics");
		mnNewMenu.add(TotalStatsItem);
		JMenuItem SettingsMenuItem = new JMenuItem("Settings");
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

		JLabel HouseLbl = new JLabel("House List");
		HouseLbl.setFont(new Font("AppleGothic", Font.PLAIN, 20));
		HouseLbl.setHorizontalAlignment(SwingConstants.CENTER);
		HouseLbl.setBounds(199, 16, 110, 25);
		panel1.add(HouseLbl);

		JButton hs1 = new JButton("House 1");
		hs1.setBounds(129, 60, 117, 29);
		panel1.add(hs1);

		JButton hs3 = new JButton("House 3");
		hs3.setBounds(129, 101, 117, 29);
		panel1.add(hs3);

		JButton hs5 = new JButton("House 5");
		hs5.setBounds(129, 142, 117, 29);
		panel1.add(hs5);

		JButton hs7 = new JButton("House 7");
		hs7.setBounds(129, 183, 117, 29);
		panel1.add(hs7);

		JButton hs2 = new JButton("House 2");
		hs2.setBounds(258, 60, 117, 29);
		panel1.add(hs2);

		JButton hs4 = new JButton("House 4");
		hs4.setBounds(258, 101, 117, 29);
		panel1.add(hs4);

		JButton hs6 = new JButton("House 6");
		hs6.setBounds(258, 142, 117, 29);
		panel1.add(hs6);

		JButton hs8 = new JButton("House 8");
		hs8.setBounds(258, 183, 117, 29);
		panel1.add(hs8);

		JButton hs9 = new JButton("House 9");
		hs9.setBounds(129, 224, 117, 29);
		panel1.add(hs9);

		JButton hs10 = new JButton("House 10");
		hs10.setBounds(258, 224, 117, 29);
		panel1.add(hs10);
		panel2.setLayout(null);
		JLabel dailyStatsLbl = new JLabel("Daily Statistics");
		dailyStatsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dailyStatsLbl.setFont(new Font("AppleGothic", Font.PLAIN, 18));
		dailyStatsLbl.setBounds(188, 18, 132, 22);
		panel2.add(dailyStatsLbl);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"House 1", "House 2", "House 3", "House 4", "House 5", "House 6", "House 7", "House 8", "House 9", "House 10"}));
		comboBox.setBounds(197, 87, 111, 27);
		panel2.add(comboBox);
		
		JLabel hsSelectLbl = new JLabel("House Select");
		hsSelectLbl.setBounds(209, 69, 84, 16);
		panel2.add(hsSelectLbl);
		
		JButton refreshDailyBtn = new JButton("Refresh");
		
		refreshDailyBtn.setBounds(403, 11, 91, 29);
		panel2.add(refreshDailyBtn);
		
		//panel3.add(refreshBtn);
		
		JLabel rateLbl = new JLabel("Survival Rate:");
		rateLbl.setBounds(156, 139, 91, 16);
		panel2.add(rateLbl);
		//panel3.add(rateLbl);
		
		JLabel rateDisplay = new JLabel("--%");
		rateDisplay.setBounds(259, 139, 61, 16);
		panel2.add(rateDisplay);
		
		JLabel deathLbl = new JLabel("Death Count:");
		deathLbl.setToolTipText("Death count measure in absolute");
		deathLbl.setBounds(156, 167, 91, 16);
		panel2.add(deathLbl);
		//panel3.add(rateLbl);
		
		JLabel rateDisplay_1 = new JLabel("999");
		rateDisplay_1.setBounds(259, 167, 61, 16);
		panel2.add(rateDisplay_1);
		
		JLabel consumptionLbl1 = new JLabel("Water Consumed:");
		consumptionLbl1.setToolTipText("Death count measure in absolute");
		consumptionLbl1.setBounds(128, 199, 111, 16);
		panel2.add(consumptionLbl1);
		//panel3.add(rateLbl);
		
		JLabel consumptionLbl2 = new JLabel("Food Consumed:");
		consumptionLbl2.setToolTipText("Death count measure in absolute");
		consumptionLbl2.setBounds(128, 227, 111, 16);
		panel2.add(consumptionLbl2);
		//panel3.add(rateLbl);
		
		JLabel rateDisplay_1_1 = new JLabel("???");
		rateDisplay_1_1.setBounds(259, 199, 61, 16);
		panel2.add(rateDisplay_1_1);
		
		JLabel rateDisplay_1_1_1 = new JLabel("???");
		rateDisplay_1_1_1.setBounds(259, 228, 61, 16);
		panel2.add(rateDisplay_1_1_1);
		panel3_1 = new MyPanel3(contentPane);
		contentPane.add(panel3_1, "Panel 3");
		panel3_1.setLayout(null);

		JLabel totStatsLbl = new JLabel("Total Statistics");
		totStatsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		totStatsLbl.setFont(new Font("AppleGothic", Font.PLAIN, 18));
		totStatsLbl.setBounds(190, 12, 119, 22);
		panel3_1.add(totStatsLbl);
		
		JLabel rateLblTot = new JLabel("Survival Rate:");
		rateLblTot.setBounds(156, 133, 91, 16);
		panel3_1.add(rateLblTot);
		
		JLabel deathLblTot = new JLabel("Death Count:");
		deathLblTot.setToolTipText("Death count measure in absolute");
		deathLblTot.setBounds(156, 161, 91, 16);
		panel3_1.add(deathLblTot);
		
		JLabel consumptionLblTot = new JLabel("Water Consumed:");
		consumptionLblTot.setToolTipText("Death count measure in absolute");
		consumptionLblTot.setBounds(128, 193, 111, 16);
		panel3_1.add(consumptionLblTot);
		
		JLabel consumptionLblTotal = new JLabel("Food Consumed:");
		consumptionLblTotal.setToolTipText("Death count measure in absolute");
		consumptionLblTotal.setBounds(128, 221, 111, 16);
		panel3_1.add(consumptionLblTotal);
		
		JButton refreshTotalBtn = new JButton("Refresh");
		refreshTotalBtn.setBounds(403, 10, 91, 29);
		panel3_1.add(refreshTotalBtn);
		
		JLabel rateDisplayTotal = new JLabel("--%");
		rateDisplayTotal.setBounds(259, 133, 61, 16);
		panel3_1.add(rateDisplayTotal);
		
		JLabel rateDisplay_1_2 = new JLabel("999");
		rateDisplay_1_2.setBounds(259, 161, 61, 16);
		panel3_1.add(rateDisplay_1_2);
		
		JLabel rateDisplay_1_1_2 = new JLabel("???");
		rateDisplay_1_1_2.setBounds(259, 193, 61, 16);
		panel3_1.add(rateDisplay_1_1_2);
		
		JLabel rateDisplay_1_1_1_1 = new JLabel("???");
		rateDisplay_1_1_1_1.setBounds(259, 222, 61, 16);
		panel3_1.add(rateDisplay_1_1_1_1);
		contentPane.add(panel4, "Panel 4");
		panel4.setLayout(null);

		JLabel sttngsLbl = new JLabel("Settings");
		sttngsLbl.setFont(new Font("AppleGothic", Font.PLAIN, 20));
		sttngsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		sttngsLbl.setBounds(180, 6, 143, 35);
		panel4.add(sttngsLbl);

		JLabel langLbl = new JLabel("Language");
		langLbl.setFont(new Font("AppleGothic", Font.PLAIN, 16));
		langLbl.setBounds(162, 103, 123, 16);
		panel4.add(langLbl);

		JSlider slider = new JSlider();
		slider.setValue(1);
		slider.setSnapToTicks(true);
		slider.setBackground(Color.LIGHT_GRAY);
		slider.setMajorTickSpacing(1);
		slider.setMaximum(2);
		slider.setBounds(152, 120, 190, 29);
		panel4.add(slider);

		JLabel lblNewLabel = new JLabel("Pусский - English - Magyar");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(152, 161, 180, 16);
		panel4.add(lblNewLabel);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// FIX THIS IMPLEMENT LANGUAGE LOCALES FILE TO UI TEXT!!!
				if (slider.getValue() == 0 && slider.getValueIsAdjusting() == false) {
					System.out.println("Changing language to Russian...");
				} else if (slider.getValue() == 2 && slider.getValueIsAdjusting() == false) {
					System.out.println("Changing language to Hungarian...");
				} else if (slider.getValue() == 1 && slider.getValueIsAdjusting() == false) {
					System.out.println("Changing language to English...");
				}
			}
		});
		JLabel timeLbl = new JLabel("Date & Time");
		timeLbl.setFont(new Font("AppleGothic", Font.PLAIN, 16));
		timeLbl.setBounds(162, 242, 123, 16);
		panel4.add(timeLbl);

		JLabel placeholderLabel = new JLabel("*stuff here*");
		placeholderLabel.setBounds(212, 270, 73, 16);
		panel4.add(placeholderLabel);

		JButton logOutBtn = new JButton("Log Out");
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
			}
		});
		TotalStatsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(contentPane, "Panel 3");
			}
		});
		hs1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 1 button pressed");
				// here anything that happens when button pressed
			}
		});
		hs2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 2 button pressed");
				// here anything that happens when button pressed
			}
		});
		hs3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 3 button pressed");
				// here anything that happens when button pressed
			}
		});
		hs4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 4 button pressed");
				// here anything that happens when button pressed
			}
		});
		hs5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 5 button pressed");
				// here anything that happens when button pressed
			}
		});
		hs6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 6 button pressed");
				// here anything that happens when button pressed
			}
		});
		hs7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 7 button pressed");
				// here anything that happens when button pressed
			}
		});
		hs8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 8 button pressed");
				// here anything that happens when button pressed
			}
		});
		hs9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 9 button pressed");
				// here anything that happens when button pressed
			}
		});
		hs10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("House 10 button pressed");
				// here anything that happens when button pressed
			}
		});
		refreshDailyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Refresh();
				
			}
		});
		refreshTotalBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Refresh();
				
			}
		});
	}
	void Refresh() {
		System.out.println("Refreshing...");
		//read data from external application im guessing?
		//do that here
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
		return (new Dimension(500, 500));
	}
}