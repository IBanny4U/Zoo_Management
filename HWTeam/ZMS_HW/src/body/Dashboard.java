package body;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import body.Visitor_Management.DbConnection;
import welcome.SignIn_Form;
import welcome.User;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    LocalDateTime localDateTime;
    DateTimeFormatter dateTimeFormatter;
    String formattedDateTime;
    private JTable day_shift;
    private JTable night_shift;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Dashboard() throws SQLException {
	 //get Visitor count & sales revenue from visitor_Management
		Visitor_Management VM=new Visitor_Management();
		Visitor_Management.DbConnection vdbConnection = VM.new DbConnection();
		
		try {
			vdbConnection.showDb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//get animal count from animal_Management
		 Animal_Management AM=new Animal_Management();
		 Animal_Management.DbConnectoion adbConnection = AM.new DbConnectoion();
			try {
					adbConnection.AnimalCount();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	//get staff count from staff_Management
			Staff_Management SM=new Staff_Management();
			 Staff_Management.Staffdbconnection sdbConnection = SM.new Staffdbconnection();
				sdbConnection.getDataLength ();
			
			
			
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/icon/zoo_logo_nobg.png")));
		setForeground(new Color(0, 50, 35));
		setBackground(new Color(0, 50, 35));
		setTitle("Zoo Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(120, 50, 1132, 654);
		contentPane = new JPanel();
		contentPane.setDoubleBuffered(true);
		contentPane.setBackground(new Color(0, 50, 35));
		contentPane.setForeground(new Color(239, 239, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel siderBar = new JPanel();
		siderBar.setBorder(null);
		siderBar.setBackground(new Color(0, 50, 35));
		siderBar.setPreferredSize(new Dimension(85, 120));
		contentPane.add(siderBar, BorderLayout.WEST);
		siderBar.setLayout(null);
		
		JPanel dashboardMenu = new JPanel();
		dashboardMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard db;
				try {
					db = new Dashboard();
					int extendedValue=getExtendedState();	
				db.setExtendedState(extendedValue);
				db.setVisible(true);
				setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		dashboardMenu.setBackground(new Color(255, 253, 233));
		dashboardMenu.setBounds(5, 75, 80, 56);
		siderBar.add(dashboardMenu);
		dashboardMenu.setLayout(null);
		
		JLabel dashboardIcon = new JLabel("");
		dashboardIcon.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/dashboardSidebarIcon.png")));
		dashboardIcon.setBackground(new Color(255, 100, 0));
		dashboardIcon.setBounds(-2, 2, 80, 32);
		dashboardIcon.setForeground(new Color(255, 100, 0));
		dashboardMenu.add(dashboardIcon);
		
		JLabel lblMenuDashboard = new JLabel("Dashboard");
		lblMenuDashboard.setBounds(8, 35, 72, 16);
		lblMenuDashboard.setForeground(new Color(0, 50, 35));
		lblMenuDashboard.setFont(new Font("Arial", Font.BOLD, 12));
		dashboardMenu.add(lblMenuDashboard);
		
		JPanel logo = new JPanel();
		logo.setBackground(new Color(0, 50, 35));
		logo.setBounds(0, 0, 85, 56);
		siderBar.add(logo);
		logo.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("");
		lblNewLabel_1_1_1_2.setBounds(0, 10, 85, 40);
		lblNewLabel_1_1_1_2.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/resized_GreenBg_ZooLogo2.png")));
		lblNewLabel_1_1_1_2.setPreferredSize(new Dimension(15, 15));
		lblNewLabel_1_1_1_2.setForeground(new Color(255, 100, 0));
		logo.add(lblNewLabel_1_1_1_2);
		
		JPanel animalMenu = new JPanel();
		animalMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Animal_Management an=new Animal_Management();
				int extendedValue=getExtendedState();	
				an.setExtendedState(extendedValue);
				an.setVisible(true);
				setVisible(false);
			}
		});
		animalMenu.setLayout(null);
		animalMenu.setBackground(new Color(0, 50, 35));
		animalMenu.setBounds(5, 148, 80, 56);
		siderBar.add(animalMenu);
		
		JLabel animalIcon = new JLabel("");
		animalIcon.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/animalSidebarIcon_bgGreen.png")));
		animalIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Animal_Management an=new Animal_Management();
				int extendedValue=getExtendedState();	
				an.setExtendedState(extendedValue);
				an.setVisible(true);
				setVisible(false);
			}
		});
		animalIcon.setForeground(new Color(255, 100, 0));
		animalIcon.setBounds(-2, 2, 80, 32);
		animalMenu.add(animalIcon);
		
		JLabel lblMenuAnimal = new JLabel("Animal");
		lblMenuAnimal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Animal_Management an=new Animal_Management();
				int extendedValue=getExtendedState();	
				an.setExtendedState(extendedValue);
				an.setVisible(true);
				setVisible(false);
			}
		});
		lblMenuAnimal.setForeground(new Color(255, 253, 233));
		lblMenuAnimal.setFont(new Font("Arial", Font.BOLD, 12));
		lblMenuAnimal.setBounds(17, 35, 60, 16);
		animalMenu.add(lblMenuAnimal);
		
		JPanel staffMenu = new JPanel();
		staffMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			if(SignIn_Form.signInAccRole.equals("Manager") || SignIn_Form.signInAccRole.equals("CEO")) {
				Staff_Management sf=new Staff_Management();
				int extendedValue=getExtendedState();	
				sf.setExtendedState(extendedValue);
				sf.setVisible(true);
				setVisible(false);
			   }else {
			
				   JOptionPane.showMessageDialog(null, "This page is for administrators only.", "Access Denied", JOptionPane.INFORMATION_MESSAGE);
			   }
			
			}
		});
		staffMenu.setLayout(null);
		staffMenu.setBackground(new Color(0, 50, 35));
		staffMenu.setBounds(5, 220, 80, 56);
		siderBar.add(staffMenu);
		
		JLabel staffIcon = new JLabel("");
		staffIcon.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/staffSidebaricon_bgGreen.png")));
		staffIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SignIn_Form.signInAccRole.equals("Manager") || SignIn_Form.signInAccRole.equals("CEO")) {
					Staff_Management sf=new Staff_Management();
					int extendedValue=getExtendedState();	
					sf.setExtendedState(extendedValue);
					sf.setVisible(true);
					setVisible(false);
				   }else {
				
					   JOptionPane.showMessageDialog(null, "This page is for administrators only.", "Access Denied", JOptionPane.INFORMATION_MESSAGE);
				   }
			}
		});
		staffIcon.setForeground(new Color(255, 100, 0));
		staffIcon.setBounds(-2, 2, 80, 32);
		staffMenu.add(staffIcon);
		
		JLabel lblMenuStaff = new JLabel("Staff");
		lblMenuStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SignIn_Form.signInAccRole.equals("Manager") || SignIn_Form.signInAccRole.equals("CEO")) {
					Staff_Management sf=new Staff_Management();
					int extendedValue=getExtendedState();	
					sf.setExtendedState(extendedValue);
					sf.setVisible(true);
					setVisible(false);
				   }else {
					   JOptionPane.showMessageDialog(null, "This page is for administrators only.", "Access Denied", JOptionPane.INFORMATION_MESSAGE);
				   }
			}
		});
		lblMenuStaff.setForeground(new Color(255, 253, 233));
		lblMenuStaff.setFont(new Font("Arial", Font.BOLD, 12));
		lblMenuStaff.setBounds(22, 35, 52, 16);
		staffMenu.add(lblMenuStaff);
		
		JPanel visitorMenu = new JPanel();
		visitorMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Visitor_Management vs;
				try {
					vs = new Visitor_Management();
					int extendedValue=getExtendedState();	
				vs.setExtendedState(extendedValue);
				vs.setVisible(true);
				setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		visitorMenu.setLayout(null);
		visitorMenu.setBackground(new Color(0, 50, 35));
		visitorMenu.setBounds(5, 292, 80, 56);
		siderBar.add(visitorMenu);
		
		JLabel visitorIcon = new JLabel("");
		visitorIcon.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/visitorSidebarIcon_bgGreen.png")));
		visitorIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Visitor_Management vs;
				try {
					vs = new Visitor_Management();
					int extendedValue=getExtendedState();	
				vs.setExtendedState(extendedValue);
				vs.setVisible(true);
				setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		visitorIcon.setForeground(new Color(255, 100, 0));
		visitorIcon.setBounds(-2, 2, 80, 32);
		visitorMenu.add(visitorIcon);
		
		JLabel lblMenuVisitor = new JLabel("Visitor");
		lblMenuVisitor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Visitor_Management vs;
				try {
					vs = new Visitor_Management();
					int extendedValue=getExtendedState();	
				vs.setExtendedState(extendedValue);
				vs.setVisible(true);
				setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		lblMenuVisitor.setForeground(new Color(255, 253, 233));
		lblMenuVisitor.setFont(new Font("Arial", Font.BOLD, 12));
		lblMenuVisitor.setBounds(18, 35, 57, 16);
		visitorMenu.add(lblMenuVisitor);
		
		JPanel inventoryMenu = new JPanel();
		inventoryMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Inventory_Management in = null;
				try {
					in = new Inventory_Management();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int extendedValue=getExtendedState();	
				in.setExtendedState(extendedValue);
				in.setVisible(true);
				setVisible(false);
			}
		});
		inventoryMenu.setLayout(null);
		inventoryMenu.setBackground(new Color(0, 50, 35));
		inventoryMenu.setBounds(5, 364, 80, 56);
		siderBar.add(inventoryMenu);
		
		JLabel inventoryIcon = new JLabel("");
		inventoryIcon.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/inventorySidebarIcon_bgGreen.png")));
		inventoryIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			  if(SignIn_Form.signInAccRole.equals("Manager") || SignIn_Form.signInAccRole.equals("CEO")) {
					Inventory_Management in1 = null;
					try {
						in1 = new Inventory_Management();
						int extendedValue1=getExtendedState();	
					in1.setExtendedState(extendedValue1);
					in1.setVisible(true);
					setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}else {
				
					   JOptionPane.showMessageDialog(null, "This page is for administrators only.", "Access Denied", JOptionPane.INFORMATION_MESSAGE);
				   }
			}
		});
		inventoryIcon.setForeground(new Color(255, 100, 0));
		inventoryIcon.setBounds(-2, 2, 80, 32);
		inventoryMenu.add(inventoryIcon);
		
		JLabel lblMenuInventory = new JLabel("Inventory");
		lblMenuInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SignIn_Form.signInAccRole.equals("Manager") || SignIn_Form.signInAccRole.equals("CEO")) {
					Inventory_Management in1 = null;
					try {
						in1 = new Inventory_Management();
						int extendedValue1=getExtendedState();	
					in1.setExtendedState(extendedValue1);
					in1.setVisible(true);
					setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}else {
				
					   JOptionPane.showMessageDialog(null, "This page is for administrators only.", "Access Denied", JOptionPane.INFORMATION_MESSAGE);
				   }
			}
		});
		lblMenuInventory.setForeground(new Color(255, 253, 233));
		lblMenuInventory.setFont(new Font("Arial", Font.BOLD, 12));
		lblMenuInventory.setBounds(13, 35, 67, 16);
		inventoryMenu.add(lblMenuInventory);
		
		JPanel body = new JPanel();
		body.setBorder(null);
		body.setBackground(new Color(255, 253, 233));
		body.setPreferredSize(new Dimension(100, 65));
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BorderLayout(0, 0));
		
		JPanel header = new JPanel();
		header.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(108, 108, 108)));
		header.setBackground(new Color(45, 100, 43));
		header.setPreferredSize(new Dimension(100, 60));
		body.add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
		JPanel headerLeftPanel = new JPanel();
		headerLeftPanel.setPreferredSize(new Dimension(220, 10));
		headerLeftPanel.setBackground(new Color(12, 59, 46));
		header.add(headerLeftPanel, BorderLayout.WEST);
		headerLeftPanel.setLayout(null);
		
		JLabel lblPageNameDashboard = new JLabel("Dashboard");
		lblPageNameDashboard.setPreferredSize(new Dimension(48, 14));
		lblPageNameDashboard.setForeground(new Color(206, 241, 123));
		lblPageNameDashboard.setBackground(new Color(255, 100, 0));
		lblPageNameDashboard.setBounds(20, 13, 190, 26);
		lblPageNameDashboard.setName(" ");
		lblPageNameDashboard.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 30));
		headerLeftPanel.add(lblPageNameDashboard);
		
		JPanel headerRightPanel = new JPanel();
		headerRightPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(108, 108, 108)));
		headerRightPanel.setBackground(new Color(12, 59, 46));
		headerRightPanel.setPreferredSize(new Dimension(200, 10));
		header.add(headerRightPanel, BorderLayout.EAST);
		headerRightPanel.setLayout(null);
		
		JLabel lblHeaderUsername = new JLabel("Username");
		lblHeaderUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderUsername.setForeground(new Color(255, 255, 255));
		lblHeaderUsername.setFont(new Font("Arial", Font.BOLD, 16));
		lblHeaderUsername.setBounds(26, 14, 96, 14);
		headerRightPanel.add(lblHeaderUsername);
		
		JLabel lblHeaderRole = new JLabel("Admin");
		lblHeaderRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderRole.setForeground(new Color(206, 241, 123));
		lblHeaderRole.setFont(new Font("Arial", Font.BOLD, 12));
		lblHeaderRole.setBounds(26, 31, 96, 14);
		headerRightPanel.add(lblHeaderRole);
		
		lblHeaderUsername.setText(SignIn_Form.signInAccUsername);
		lblHeaderRole.setText(SignIn_Form.signInAccRole);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/userProfileIconGreenGreen.png")));
		lblNewLabel_2.setForeground(new Color(255, 100, 0));
		lblNewLabel_2.setBackground(new Color(255, 100, 0));
		lblNewLabel_2.setBounds(132, 6, 40, 44);
		headerRightPanel.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 253, 233));
		header.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(12, 59, 46));
		panel_1.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(108, 108, 108)));
		panel_1.setPreferredSize(new Dimension(150, 10));
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(null);
		
		JLabel lblToday = new JLabel("Today ");
		lblToday.setBounds(15, 12, 42, 14);
		lblToday.setPreferredSize(new Dimension(42, 20));
		lblToday.setForeground(new Color(255, 253, 233));
		lblToday.setFont(new Font("Arial", Font.BOLD, 12));
		panel_1.add(lblToday);
		
		JLabel lblHeaderLocalDateTime = new JLabel("");
		 localDateTime=LocalDateTime.now();
	     dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
	     formattedDateTime=localDateTime.format(dateTimeFormatter); 
	     lblHeaderLocalDateTime.setText(formattedDateTime);
		lblHeaderLocalDateTime.setBackground(new Color(206, 241, 123));
		lblHeaderLocalDateTime.setPreferredSize(new Dimension(80, 17));
		lblHeaderLocalDateTime.setForeground(new Color(206, 241, 123));
		lblHeaderLocalDateTime.setFont(new Font("Arial", Font.BOLD, 16));
		lblHeaderLocalDateTime.setBounds(15, 28, 125, 16);
		panel_1.add(lblHeaderLocalDateTime);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(12, 59, 46));
		panel.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 253, 233));
		body.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(90, 160));
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel leftBorderPanel = new JPanel();
		leftBorderPanel.setBackground(new Color(255, 253, 233));
		leftBorderPanel.setPreferredSize(new Dimension(15, 10));
		panel_3.add(leftBorderPanel, BorderLayout.WEST);
		
		JPanel rightBorderPanel = new JPanel();
		rightBorderPanel.setBackground(new Color(255, 253, 233));
		rightBorderPanel.setPreferredSize(new Dimension(15, 10));
		panel_3.add(rightBorderPanel, BorderLayout.EAST);
		
		JPanel topBorderPanel = new JPanel();
		topBorderPanel.setBackground(new Color(255, 253, 233));
		panel_3.add(topBorderPanel, BorderLayout.NORTH);
		
		JPanel centerBoxLayoutPanel = new JPanel();
		centerBoxLayoutPanel.setBackground(new Color(12, 59, 46));
		panel_3.add(centerBoxLayoutPanel, BorderLayout.CENTER);
		centerBoxLayoutPanel.setLayout(new BoxLayout(centerBoxLayoutPanel, BoxLayout.X_AXIS));
		
		JPanel centerBox1 = new JPanel();
		centerBox1.setBackground(new Color(12, 59, 46));
		centerBox1.setPreferredSize(new Dimension(190, 120));
		centerBoxLayoutPanel.add(centerBox1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 10, 1, 1, (Color) new Color(140, 200, 80)));
		panel_4.setBackground(new Color(216, 236, 196));
		panel_4.setPreferredSize(new Dimension(270, 140));
		centerBox1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblTotalStaff = new JLabel("TOTAL ANIMALS");
		lblTotalStaff.setBackground(new Color(45, 100, 43));
		lblTotalStaff.setForeground(new Color(12, 59, 46));
		lblTotalStaff.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 16));
		lblTotalStaff.setBounds(32, 11, 140, 37);
		panel_4.add(lblTotalStaff);
		
		JPanel panel_5 = new JPanel();
		panel_5.setForeground(new Color(239, 247, 232));
		panel_5.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(255, 253, 233)));
		panel_5.setBackground(new Color(216, 236, 196));
		panel_5.setPreferredSize(new Dimension(266, 100));
		panel_5.setBounds(35, 59, 210, 77);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel dashboardTotalAnimallbl = new JLabel("0");
		dashboardTotalAnimallbl.setText(String.valueOf(Animal_Management.totalAnimalCount));
		dashboardTotalAnimallbl.setBounds(32, 21, 135, 32);
		dashboardTotalAnimallbl.setHorizontalAlignment(SwingConstants.CENTER);
		dashboardTotalAnimallbl.setForeground(new Color(12, 59, 46));
		dashboardTotalAnimallbl.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
		panel_5.add(dashboardTotalAnimallbl);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/DashTotalAnimalBg.png")));
		lblNewLabel.setBounds(196, 11, 46, 40);
		panel_4.add(lblNewLabel);
		
		JPanel centerBox2 = new JPanel();
		centerBox2.setPreferredSize(new Dimension(190, 120));
		centerBox2.setBackground(new Color(12, 59, 46));
		centerBoxLayoutPanel.add(centerBox2);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBorder(new MatteBorder(1, 10, 1, 1, (Color) new Color(140, 200, 80)));
		panel_4_1.setBackground(new Color(216, 236, 196));
		panel_4_1.setPreferredSize(new Dimension(280, 140));
		centerBox2.add(panel_4_1);
		panel_4_1.setLayout(null);
		
		JLabel lblTotalStaff_1 = new JLabel("TOTAL STAFF");
		lblTotalStaff_1.setBackground(new Color(234, 244, 223));
		lblTotalStaff_1.setForeground(new Color(12, 59, 46));
		lblTotalStaff_1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 16));
		lblTotalStaff_1.setBounds(32, 11, 140, 37);
		panel_4_1.add(lblTotalStaff_1);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(255, 253, 233)));
		panel_5_1.setPreferredSize(new Dimension(266, 100));
		panel_5_1.setBackground(new Color(216, 236, 196));
		panel_5_1.setBounds(35, 59, 210, 77);
		panel_4_1.add(panel_5_1);
		panel_5_1.setLayout(null);
		
		JLabel dashboardTotalAnimallbl_1 = new JLabel("0");
		dashboardTotalAnimallbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		dashboardTotalAnimallbl_1.setForeground(new Color(12, 59, 46));
		dashboardTotalAnimallbl_1.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
		dashboardTotalAnimallbl_1.setBounds(35, 21, 135, 32);
		panel_5_1.add(dashboardTotalAnimallbl_1);
		dashboardTotalAnimallbl_1.setText(String.valueOf(Staff_Management.totalStaffCount));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/DashTotalStaffWbg.png")));
		lblNewLabel_1.setBounds(199, 11, 46, 40);
		panel_4_1.add(lblNewLabel_1);
		
		JPanel centerBox3 = new JPanel();
		centerBox3.setPreferredSize(new Dimension(190, 120));
		centerBox3.setBackground(new Color(12, 59, 46));
		centerBoxLayoutPanel.add(centerBox3);
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setBorder(new MatteBorder(1, 10, 1, 1, (Color) new Color(140, 200, 80)));
		panel_4_1_1.setBackground(new Color(216, 236, 196));
		panel_4_1_1.setPreferredSize(new Dimension(280, 140));
		centerBox3.add(panel_4_1_1);
		panel_4_1_1.setLayout(null);
		
		JLabel lblTotalStaff_1_1 = new JLabel("VISITOR COUNT");
		lblTotalStaff_1_1.setBorder(null);
		lblTotalStaff_1_1.setForeground(new Color(12, 59, 46));
		lblTotalStaff_1_1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 16));
		lblTotalStaff_1_1.setBounds(32, 11, 140, 37);
		panel_4_1_1.add(lblTotalStaff_1_1);
		
		JPanel panel_5_1_1 = new JPanel();
		panel_5_1_1.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(255, 253, 233)));
		panel_5_1_1.setPreferredSize(new Dimension(278, 100));
		panel_5_1_1.setBackground(new Color(216, 236, 196));
		panel_5_1_1.setBounds(35, 59, 210, 77);
		panel_4_1_1.add(panel_5_1_1);
		panel_5_1_1.setLayout(null);
		
		JLabel dashboardTotalVisitor = new JLabel("0");
		dashboardTotalVisitor.setHorizontalAlignment(SwingConstants.CENTER);
		dashboardTotalVisitor.setForeground(new Color(12, 59, 46));
		dashboardTotalVisitor.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
		dashboardTotalVisitor.setBounds(35, 21, 135, 32);
		panel_5_1_1.add(dashboardTotalVisitor);
		
		dashboardTotalVisitor.setText(String.valueOf(Visitor_Management.totalCustomerCount));
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/DashTotalVisitorBg.png")));
		lblNewLabel_1_2.setBounds(199, 11, 46, 40);
		panel_4_1_1.add(lblNewLabel_1_2);
		
		JPanel centerBox4 = new JPanel();
		centerBox4.setPreferredSize(new Dimension(190, 120));
		centerBox4.setBackground(new Color(12, 59, 46));
		centerBoxLayoutPanel.add(centerBox4);
		
		JPanel panel_4_1_1_1 = new JPanel();
		panel_4_1_1_1.setBorder(new MatteBorder(1, 10, 1, 1, (Color) new Color(140, 200, 80)));
		panel_4_1_1_1.setBackground(new Color(216, 236, 196));
		panel_4_1_1_1.setPreferredSize(new Dimension(280, 140));
		centerBox4.add(panel_4_1_1_1);
		panel_4_1_1_1.setLayout(null);
		
		JLabel lblTotalStaff_1_1_1 = new JLabel("SALES REVENUE");
		lblTotalStaff_1_1_1.setForeground(new Color(12, 59, 46));
		lblTotalStaff_1_1_1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 16));
		lblTotalStaff_1_1_1.setBounds(32, 11, 140, 37);
		panel_4_1_1_1.add(lblTotalStaff_1_1_1);
		
		JPanel panel_5_1_1_1 = new JPanel();
		panel_5_1_1_1.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(255, 253, 233)));
		panel_5_1_1_1.setPreferredSize(new Dimension(278, 100));
		panel_5_1_1_1.setBackground(new Color(216, 236, 196));
		panel_5_1_1_1.setBounds(35, 59, 210, 77);
		panel_4_1_1_1.add(panel_5_1_1_1);
		panel_5_1_1_1.setLayout(null);
		
		JLabel dashboardTotalSales = new JLabel("0.00");
		dashboardTotalSales.setHorizontalAlignment(SwingConstants.RIGHT);
		dashboardTotalSales.setForeground(new Color(12, 59, 46));
		dashboardTotalSales.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
		dashboardTotalSales.setBounds(0, 21, 155, 32);
		panel_5_1_1_1.add(dashboardTotalSales);
		
		dashboardTotalSales.setText(String.valueOf(Visitor_Management.totalTicketSales));
		
		JLabel dashboardTotalAnimallbl_1_1_1_1 = new JLabel("Ks");
		dashboardTotalAnimallbl_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		dashboardTotalAnimallbl_1_1_1_1.setForeground(new Color(12, 59, 46));
		dashboardTotalAnimallbl_1_1_1_1.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
		dashboardTotalAnimallbl_1_1_1_1.setBounds(165, 21, 45, 32);
		panel_5_1_1_1.add(dashboardTotalAnimallbl_1_1_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/DashTotalRevenue.png")));
		lblNewLabel_1_1.setBounds(199, 11, 46, 40);
		panel_4_1_1_1.add(lblNewLabel_1_1);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(8, 71, 52)));
		panel_7.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
		panel_12.setBackground(new Color(12, 59, 46));
		panel_12.setPreferredSize(new Dimension(10, 50));
		panel_8.add(panel_12, BorderLayout.NORTH);
		
		JLabel lblNewLabel_3 = new JLabel("DAILY SCHEDULE");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setPreferredSize(new Dimension(280, 40));
		lblNewLabel_3.setForeground(new Color(206, 241, 123));
		lblNewLabel_3.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 25));
		panel_12.add(lblNewLabel_3);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(new Color(0, 50, 35));
		panel_8.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_14.setPreferredSize(new Dimension(10, 30));
		panel_13.add(panel_14, BorderLayout.NORTH);
		panel_14.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel datshiftlb = new JPanel();
		datshiftlb.setPreferredSize(new Dimension(100, 10));
		datshiftlb.setBackground(new Color(255, 100, 0));
		panel_14.add(datshiftlb);
		
		JLabel lblNewLabel_5 = new JLabel("Day Shift");
		lblNewLabel_5.setForeground(new Color(255, 253, 233));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBackground(new Color(255, 253, 233));
		datshiftlb.add(lblNewLabel_5);
		
		JPanel nightshiftlb = new JPanel();
		nightshiftlb.setForeground(new Color(12, 59, 46));
		nightshiftlb.setBackground(new Color(206, 241, 123));
		panel_14.add(nightshiftlb);
		
		JLabel lblNewLabel_5_1 = new JLabel("Night Shift");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5_1.setBackground(new Color(255, 253, 233));
		nightshiftlb.add(lblNewLabel_5_1);
		
		JPanel panel_31 = new JPanel();
		panel_31.setBackground(new Color(12, 59, 46));
		panel_31.setBorder(null);
		panel_31.setPreferredSize(new Dimension(450, 10));
		panel_13.add(panel_31, BorderLayout.CENTER);
		panel_31.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(206, 241, 123)));
		panel_31.add(scrollPane_1);
		
		
		
		night_shift = new JTable();
		night_shift.setBorder(null);
		night_shift.setPreferredScrollableViewportSize(new Dimension(450, 360));
		night_shift.setGridColor(new Color(226, 226, 226));
		night_shift.setFont(new Font("Arial", Font.PLAIN, 14));
		night_shift.setRowHeight(40);
		night_shift.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"Name", "Department", "Time"
			}
		));
		sdbConnection.NightShiftAdd(night_shift);
		scrollPane_1.setViewportView(night_shift);
		
		JPanel panel_29_1 = new JPanel();
		panel_29_1.setBackground(new Color(12, 59, 46));
		panel_29_1.setBorder(null);
		panel_29_1.setPreferredSize(new Dimension(460, 10));
		panel_13.add(panel_29_1, BorderLayout.WEST);
		panel_29_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(255, 100, 0)));
		panel_29_1.add(scrollPane);
		
		day_shift = new JTable();
		day_shift.setPreferredScrollableViewportSize(new Dimension(450, 360));
		day_shift.setFont(new Font("Arial", Font.PLAIN, 14));
		day_shift.setGridColor(new Color(226, 226, 226));
		day_shift.setRowHeight(40);
		day_shift.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"Name", "Department", "Time"
			}
		));
		sdbConnection.DayShiftAdd(day_shift);
		scrollPane.setViewportView(day_shift);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 253, 233));
		panel_7.add(panel_9, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		panel_10.setPreferredSize(new Dimension(10, 5));
		panel_10.setBackground(new Color(255, 253, 233));
		panel_7.add(panel_10, BorderLayout.SOUTH);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(255, 253, 233));
		panel_11.setPreferredSize(new Dimension(15, 10));
		panel_7.add(panel_11, BorderLayout.WEST);
		
		JPanel side_box_panel = new JPanel();
		side_box_panel.setBackground(new Color(12, 59, 46));
		side_box_panel.setPreferredSize(new Dimension(335, 10));
		panel_7.add(side_box_panel, BorderLayout.EAST);
		side_box_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(new Color(255, 253, 233));
		panel_15.setPreferredSize(new Dimension(15, 10));
		side_box_panel.add(panel_15, BorderLayout.EAST);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(new Color(12, 59, 46));
		side_box_panel.add(panel_16, BorderLayout.CENTER);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Dashboard.class.getResource("/icon/Welcome (1).png")));
		panel_16.add(lblNewLabel_4);

	}
}





