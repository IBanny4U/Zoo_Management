package body;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.BorderLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import welcome.SignIn_Form;
import welcome.User;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;




public class Animal_Management extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameReq;
	private JTextField ageReq;
	private JTextField typeReq;
	private JTextField nameField;
	private JTextField ageField;
	private JTextField typeField;
	private JTextField qtytxt;
	private JTextField animaltxtf;
	private JTextField Speciestxtf;
	private JTextField Gendertxtf;
	private JTextField ShowQty;
	private JTextField productTxt;
	private JTextField manutxts;
	private JTextField Warehousetxt;
	private JTextField medtxt;
	private JTextField medID;
	private JTextField Medmanu;
	private JTextField medware;
	private JTextField namemed;
	private JTextField spmed;
	private JTextField gendermed;
	private JTextField medtotalstock;

	
	LocalDateTime localDateTime;
    DateTimeFormatter dateTimeFormatter;
    String formattedDateTime;
	static int totalAnimalCount;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Animal_Management frame = new Animal_Management();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
	
	
	
	
	
	
	
	
	
	//IDGenerator inner class and date time base
	
	    public static String generateID() {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyMdHmss");
	        return "HW" + sdf.format(new Date());
	    }
	
	

	/**
	 * Create the frame.
	 */
	public Animal_Management() {
		
			
		DbConnectoion db = new DbConnectoion();
		
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Animal_Management.class.getResource("/icon/zoo_logo_nobg.png")));
		setForeground(new Color(0, 50, 35));
		setBackground(new Color(0, 50, 35));
		setTitle("Zoo Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(120,50,1132,654);
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
		dashboardMenu.setBackground(new Color(0, 50, 35));
		dashboardMenu.setBounds(5, 75, 80, 56);
		siderBar.add(dashboardMenu);
		dashboardMenu.setLayout(null);
		
		JLabel dashboardIcon = new JLabel("");
		dashboardIcon.addMouseListener(new MouseAdapter() {
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
		dashboardIcon.setIcon(new ImageIcon(Animal_Management.class.getResource("/icon/dashboardSidebarIcon_bgGreen.png")));
		dashboardIcon.setBackground(new Color(255, 100, 0));
		dashboardIcon.setBounds(-2, 2, 80, 32);
		dashboardIcon.setForeground(new Color(255, 100, 0));
		dashboardMenu.add(dashboardIcon);
		
		JLabel lblMenuDashboard = new JLabel("Dashboard");
		lblMenuDashboard.addMouseListener(new MouseAdapter() {
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
		lblMenuDashboard.setBounds(8, 35, 72, 16);
		lblMenuDashboard.setForeground(new Color(255, 253, 233));
		lblMenuDashboard.setFont(new Font("Arial", Font.BOLD, 12));
		dashboardMenu.add(lblMenuDashboard);
		
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
		animalMenu.setBackground(new Color(255, 253, 233));
		animalMenu.setBounds(5, 148, 80, 56);
		siderBar.add(animalMenu);
		
		JLabel animalIcon = new JLabel("");
		animalIcon.setIcon(new ImageIcon(Animal_Management.class.getResource("/icon/animalSidebaricon.png")));
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
		lblMenuAnimal.setForeground(new Color(0, 50, 35));
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
		staffIcon.setIcon(new ImageIcon(Animal_Management.class.getResource("/icon/staffSidebaricon_bgGreen.png")));
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
		visitorIcon.setIcon(new ImageIcon(Animal_Management.class.getResource("/icon/visitorSidebarIcon_bgGreen.png")));
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
		inventoryMenu.setLayout(null);
		inventoryMenu.setBackground(new Color(0, 50, 35));
		inventoryMenu.setBounds(5, 364, 80, 56);
		siderBar.add(inventoryMenu);
		
		JLabel inventoryIcon = new JLabel("");
		inventoryIcon.setIcon(new ImageIcon(Animal_Management.class.getResource("/icon/inventorySidebarIcon_bgGreen.png")));
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
		
		JPanel logo = new JPanel();
		logo.setLayout(null);
		logo.setBackground(new Color(0, 50, 35));
		logo.setBounds(0, 0, 85, 56);
		siderBar.add(logo);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("");
		lblNewLabel_1_1_1_2.setPreferredSize(new Dimension(15, 15));
		lblNewLabel_1_1_1_2.setIcon(new ImageIcon(Animal_Management.class.getResource("/icon/resized_GreenBg_ZooLogo2.png")));
		lblNewLabel_1_1_1_2.setForeground(new Color(255, 100, 0));
		lblNewLabel_1_1_1_2.setBounds(0, 10, 85, 40);
		logo.add(lblNewLabel_1_1_1_2);
		
		JPanel body = new JPanel();
		body.setBorder(null);
		body.setBackground(new Color(255, 253, 233));
		body.setPreferredSize(new Dimension(100, 65));
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BorderLayout(0, 0));
		
		JPanel header = new JPanel();
		header.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(108, 108, 108)));
		header.setBackground(new Color(12, 59, 46));
		header.setPreferredSize(new Dimension(100, 60));
		body.add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
		JPanel headerLeftPanel = new JPanel();
		headerLeftPanel.setPreferredSize(new Dimension(530, 10));
		headerLeftPanel.setBackground(new Color(12, 59, 46));
		header.add(headerLeftPanel, BorderLayout.WEST);
		headerLeftPanel.setLayout(null);
		
		JLabel lblPageNameDashboard = new JLabel("Animal Management");
		lblPageNameDashboard.setForeground(new Color(206, 241, 123));
		lblPageNameDashboard.setBackground(new Color(255, 100, 0));
		lblPageNameDashboard.setBounds(20, 9, 464, 36);
		lblPageNameDashboard.setName(" ");
		lblPageNameDashboard.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 30));
		headerLeftPanel.add(lblPageNameDashboard);
		
		JPanel headerRightPanel = new JPanel();
		headerRightPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(108, 108, 108)));
		headerRightPanel.setBackground(new Color(12, 59, 46));
		headerRightPanel.setPreferredSize(new Dimension(200, 10));
		header.add(headerRightPanel, BorderLayout.EAST);
		headerRightPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\HP\\eclipse-workspace\\ZMS_HW\\icon\\acc.png"));
		lblNewLabel_2.setBackground(new	 Color(255, 100, 0));
		lblNewLabel_2.setForeground(new Color(255, 100, 0));
		lblNewLabel_2.setBounds(89, 4, 46, 47);
		headerRightPanel.add(lblNewLabel_2);
		
		JLabel lblHeaderUsername = new JLabel("Username");
		lblHeaderUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderUsername.setForeground(Color.WHITE);
		lblHeaderUsername.setFont(new Font("Arial", Font.BOLD, 16));
		lblHeaderUsername.setBounds(26, 14, 96, 14);
		headerRightPanel.add(lblHeaderUsername);
		
		JLabel lblHeaderRole = new JLabel("Admin");
		lblHeaderRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderRole.setForeground(new Color(206, 241, 123));
		lblHeaderRole.setFont(new Font("Arial", Font.BOLD, 12));
		lblHeaderRole.setBounds(26, 32, 96, 14);
		headerRightPanel.add(lblHeaderRole);
		
		lblHeaderUsername.setText(SignIn_Form.signInAccUsername);
		lblHeaderRole.setText(SignIn_Form.signInAccRole);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon(Animal_Management.class.getResource("/icon/userProfileIconGreenGreen.png")));
		lblNewLabel_2_1.setForeground(new Color(255, 100, 0));
		lblNewLabel_2_1.setBackground(new Color(255, 100, 0));
		lblNewLabel_2_1.setBounds(132, 6, 40, 44);
		headerRightPanel.add(lblNewLabel_2_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(12, 59, 46));
		header.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setPreferredSize(new Dimension(150, 10));
		panel_1.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(108, 108, 108)));
		panel_1.setBackground(new Color(12, 59, 46));
		panel.add(panel_1, BorderLayout.EAST);
		
		JLabel lblToday = new JLabel("Today ");
		lblToday.setPreferredSize(new Dimension(42, 20));
		lblToday.setForeground(new Color(255, 253, 233));
		lblToday.setFont(new Font("Arial", Font.BOLD, 12));
		lblToday.setBounds(15, 12, 42, 14);
		panel_1.add(lblToday);
		
		JLabel lblHeaderLocalDateTime = new JLabel("");
		//set date to header
		 localDateTime=LocalDateTime.now();
	     dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
	     formattedDateTime=localDateTime.format(dateTimeFormatter); 
	     lblHeaderLocalDateTime.setText(formattedDateTime);
	    //set date to header
		lblHeaderLocalDateTime.setPreferredSize(new Dimension(80, 17));
		lblHeaderLocalDateTime.setForeground(new Color(206, 241, 123));
		lblHeaderLocalDateTime.setFont(new Font("Arial", Font.BOLD, 16));
		lblHeaderLocalDateTime.setBackground(new Color(206, 241, 123));
		lblHeaderLocalDateTime.setBounds(15, 28, 125, 16);
		panel_1.add(lblHeaderLocalDateTime);
		
		JPanel BackFrame = new JPanel();
		BackFrame.setBackground(new Color(255, 253, 233));
		body.add(BackFrame, BorderLayout.CENTER);
		BackFrame.setLayout(null);
		
		JPanel mainwindow = new JPanel();
		mainwindow.setBackground(new Color(255, 255, 255));
		
		mainwindow.setBounds(10, 52, 1003, 495);
		BackFrame.add(mainwindow);
		mainwindow.setLayout(new CardLayout(0, 0));
		
		JPanel ManagemetPanel = new JPanel();
		ManagemetPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		ManagemetPanel.setBackground(new Color(0, 50, 35));
		mainwindow.add(ManagemetPanel, "name_108333485304500");
		ManagemetPanel.setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(0, 46, 1023, 449);
		ManagemetPanel.add(splitPane);
		
		/*Sizing Split Screen */
		splitPane.setDividerLocation(500);
		splitPane.setResizeWeight(0.5);
		
		
		//-----------------------
		
		
		
		
		
		
		
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(255, 128, 0));
		leftPanel.setForeground(new Color(255, 255, 255));
		splitPane.setLeftComponent(leftPanel);
		leftPanel.setLayout(new CardLayout(0, 0));
		
		JPanel AllPanel = new JPanel();
		AllPanel.setBackground(new Color(0, 128, 128));
		leftPanel.add(AllPanel, "name_87630894393700");
		
		JScrollPane allScrollPane = new JScrollPane();
		 allScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	     allScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		AllPanel.setLayout(new BoxLayout(AllPanel, BoxLayout.X_AXIS));
	
		AllPanel.add(allScrollPane);
		
		JPanel boxLayout = new JPanel();
		boxLayout.setBackground(new Color(255, 253, 233));
		
		/**/
		boxLayout.setLayout(new BoxLayout(boxLayout, BoxLayout.X_AXIS));

		
		allScrollPane.setViewportView(boxLayout);
		boxLayout.setLayout(new BoxLayout(boxLayout, BoxLayout.PAGE_AXIS));
		
	
		
		
		
		JPanel contactBox = new JPanel();
		boxLayout.add(contactBox);
		
		contactBox.setLayout(new BoxLayout(contactBox, BoxLayout.PAGE_AXIS));
		
		
		//This one will read DataBase and show in all page
		 try {
	            ResultSet rs = db.getAllAnimals();
	            while (rs.next()) {
	                String id = rs.getString("idanimal");
	                String name = rs.getString("name");
	                String type = rs.getString("type");
	                int age = rs.getInt("age");			          
	                String species = rs.getString("Species");
	                String enclosure = rs.getString("enclosure");
	                String health = rs.getString("healthstats");

	               
	                contactBox.add(cardCreation(name,age,id,enclosure,species,type));
	                
	                
	                
	                
	                
	                
	                
	            }
	        } catch (SQLException ea) {
	            ea.printStackTrace();
	        }			
		
		
		
		

		JPanel AddPanel = new JPanel();
		AddPanel.setBackground(new Color(255, 253, 233));
		leftPanel.add(AddPanel, "name_87637545811300");
		AddPanel.setLayout(null);
		
		JLabel Name = new JLabel("Name :");
		Name.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Name.setBounds(35, 78, 121, 34);
		AddPanel.add(Name);
		
		nameReq = new JTextField();
		nameReq.setBackground(new Color(255, 253, 233));
		nameReq.setFont(new Font("Tahoma", Font.PLAIN, 17));
		nameReq.setBounds(96, 79, 258, 34);
		AddPanel.add(nameReq);
		nameReq.setColumns(10);
		
		JLabel Age = new JLabel("Age :");
		Age.setBackground(new Color(255, 253, 233));
		Age.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Age.setBounds(47, 122, 121, 34);
		AddPanel.add(Age);
		
		ageReq = new JTextField();
		ageReq.setBackground(new Color(255, 253, 233));
		ageReq.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ageReq.setColumns(10);
		ageReq.setBounds(96, 123, 102, 34);
		AddPanel.add(ageReq);
		
		JLabel AddAnimalForm = new JLabel("Add Animal Information");
		AddAnimalForm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddAnimalForm.setBounds(134, 0, 355, 39);
		AddPanel.add(AddAnimalForm);
		
		JLabel IDLabel = new JLabel("ID :");
		IDLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		IDLabel.setBounds(21, 35, 121, 34);
		AddPanel.add(IDLabel);
		
		JLabel genderLabel = new JLabel("Gender :");
		genderLabel.setBackground(new Color(255, 253, 233));
		genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		genderLabel.setBounds(287, 122, 121, 34);
		AddPanel.add(genderLabel);
		
		JLabel lblType = new JLabel("Type : ");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblType.setBounds(41, 166, 121, 34);
		AddPanel.add(lblType);
		
		typeReq = new JTextField();
		typeReq.setBackground(new Color(255, 253, 233));
		typeReq.setFont(new Font("Tahoma", Font.PLAIN, 17));
		typeReq.setColumns(10);
		typeReq.setBounds(96, 167, 258, 34);
		AddPanel.add(typeReq);
		
		JLabel lblHabititDetail = new JLabel("Habitat Detail");
		lblHabititDetail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHabititDetail.setBounds(10, 210, 166, 39);
		AddPanel.add(lblHabititDetail);
		
		JLabel SpeciesLb = new JLabel(" Species :");
		SpeciesLb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		SpeciesLb.setBounds(21, 256, 121, 34);
		AddPanel.add(SpeciesLb);
		
		JLabel enclosurelb = new JLabel("Enclosure :");
		enclosurelb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		enclosurelb.setBounds(10, 300, 102, 34);
		AddPanel.add(enclosurelb);
		
		JLabel healthLb = new JLabel("Health Status");
		healthLb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		healthLb.setBounds(10, 338, 166, 39);
		AddPanel.add(healthLb);
		
		JLabel lblNewLabel_1 = new JLabel("State :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 381, 142, 39);
		AddPanel.add(lblNewLabel_1);
		
		String idCatch = generateID(); 
		JLabel idLb = new JLabel(idCatch);
		
		idLb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		idLb.setBackground(new Color(255, 255, 255));
		idLb.setBounds(86, 34, 258, 34);
		AddPanel.add(idLb);
		
		/*Gender Box*/
		String [] GenderBox = {"Male","Female","Hermaphrodites"};
		JComboBox<String> genderCombo = new JComboBox<>(GenderBox);
		genderCombo.setBackground(new Color(255, 253, 233));
		genderCombo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		genderCombo.setBounds(357, 122, 142, 34);
		AddPanel.add(genderCombo);
		
		String [] SpeciesBox = {"Carnivore","Herbivores","Omnivores","Scavengers"};
		JComboBox<String> speciesCombo = new JComboBox<>(SpeciesBox);
		speciesCombo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		speciesCombo.setBackground(new Color(255, 253, 233));
		speciesCombo.setBounds(96, 259, 378, 34);
		AddPanel.add(speciesCombo);
		
		
		String [] EnclosureBox = {"East","West","North","South","Northwest","Northeast","Southeast","Southwest","Center"};
		JComboBox <String> enclosureBox = new JComboBox<>(EnclosureBox);
		enclosureBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		enclosureBox.setBackground(new Color(255, 253, 233));
		enclosureBox.setBounds(96, 303, 378, 34);
		AddPanel.add(enclosureBox);
		
		String [] HealthBox = {"Normal","Sick","Injure","Recovering","Quarantined","Pregnant","Breeding","New Arrival","Transferred","Missing","Deceased"};
		JComboBox <String>healConditionBox = new JComboBox<>(HealthBox);
		healConditionBox.setBackground(new Color(206, 241, 123));
		healConditionBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		healConditionBox.setBounds(79, 378, 258, 39);
		AddPanel.add(healConditionBox);
		
		
		JButton Addbtn = new JButton("+ Add");
		Addbtn.setFocusable(false);
		Addbtn.setForeground(new Color(255, 253, 233));
		Addbtn.setBackground(new Color(255, 100, 0));
		
		Addbtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		Addbtn.setBounds(353, 378, 136, 42);
		AddPanel.add(Addbtn);
		
		
		// Center the text inside the combo box
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		genderCombo.setRenderer(renderer);
		
		
		
	//Move to Center
		healConditionBox.setRenderer(renderer);
		
		
		
		
		JPanel removePanel = new JPanel();
		removePanel.setBackground(new Color(255, 253, 233));
		leftPanel.add(removePanel, "name_87641017106300");
		removePanel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Remove Animal Data");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(129, 10, 182, 35);
		removePanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ID :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(54, 46, 68, 40);
		removePanel.add(lblNewLabel_4);
		
		
		//This Section is Choosing Primary Key  ( ID ) CombboBox for Removing Data
		 JComboBox <String> choose2RemoveCombo  = new JComboBox<>();
		 choose2RemoveCombo.setBackground(new Color(255, 253, 233));
		 choose2RemoveCombo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 choose2RemoveCombo.setBounds(10, 86, 166, 32);
		
		
		 try {
	            ResultSet rs = db.getAllAnimals();
	            while (rs.next()) {       
	                db.Refresh(choose2RemoveCombo);	                
	                //This refresh Box is for update	                
	            	choose2RemoveCombo.setBounds(10, 87, 212, 35);
	        		removePanel.add(choose2RemoveCombo);
	        	
	        		
	        		
	        		
	        		
	        		
	        		
	            }
	        } catch (SQLException ea) {
	            ea.printStackTrace();
	        }
		 removePanel.add(choose2RemoveCombo);
		
		
		
		 
		 
		 
		 
		 
		
		JButton removebtn = new JButton("Remove");
		removebtn.setFocusable(false);
		removebtn.setForeground(new Color(255, 253, 233));
		removebtn.setBackground(new Color(255, 100, 0));
		
		removebtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		removebtn.setBounds(311, 385, 163, 40);
		removePanel.add(removebtn);
		
		JLabel namelb = new JLabel("Name :     ");
		namelb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		namelb.setBounds(264, 70, 225, 33);
		removePanel.add(namelb);
		
		JLabel agelb = new JLabel("Age :      ");
		agelb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		agelb.setBounds(278, 130, 196, 26);
		removePanel.add(agelb);
		
		JLabel lbenclosure = new JLabel("Enclosure :     ");
		lbenclosure.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbenclosure.setBounds(234, 190, 240, 26);
		removePanel.add(lbenclosure);
		
		JLabel lbSpecies = new JLabel(" Species :     ");
		lbSpecies.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbSpecies.setBounds(251, 297, 223, 35);
		removePanel.add(lbSpecies);
		
		JLabel lbType = new JLabel("Type :     ");
		lbType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbType.setBounds(270, 237, 204, 33);
		removePanel.add(lbType);
		
		
		//This one read data from database to show in remove box
		choose2RemoveCombo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedID = (String) choose2RemoveCombo.getSelectedItem();
		        if (selectedID != null) {
		            try {
		                ResultSet rs = db.getAnimalById(selectedID);
		                if (rs != null && rs.next()) {
		                	namelb.setText("Name:    " + rs.getString("name"));
		                	agelb.setText("Age:    " + rs.getInt("age"));
		                	lbenclosure.setText("Enclosure:    " + rs.getString("enclosure"));
		                    lbType.setText("Type:    " + rs.getString("type"));
		                    lbSpecies.setText("Species:    " + rs.getString("Species"));
		                   
		                    
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});

		
		JPanel editPanel = new JPanel();
		editPanel.setBackground(new Color(255, 253, 233));
		leftPanel.add(editPanel, "name_90080736840600");
		editPanel.setLayout(null);
		
		JLabel editlb = new JLabel("Edit Animal Data");
		editlb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		editlb.setBounds(174, 5, 315, 46);
		editPanel.add(editlb);
		
		JLabel idlb = new JLabel("ID :");
		idlb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		idlb.setBounds(10, 51, 70, 39);
		editPanel.add(idlb);
		
	
		
		
		 JLabel Namelb = new JLabel("Name :");
		 Namelb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 Namelb.setBounds(258, 61, 70, 29);
		 editPanel.add(Namelb);
		 
		 nameField = new JTextField();
		 nameField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 nameField.setBounds(259, 91, 217, 29);
		 editPanel.add(nameField);
		 nameField.setColumns(10);
		 
		 JLabel Agelb = new JLabel("Age :");
		 Agelb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 Agelb.setBounds(258, 120, 70, 29);
		 editPanel.add(Agelb);
		 
		 ageField = new JTextField();
		 ageField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 ageField.setColumns(10);
		 ageField.setBounds(258, 150, 76, 29);
		 editPanel.add(ageField);
		 
		 JLabel genderlb = new JLabel("Gender :");
		 genderlb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 genderlb.setBounds(362, 120, 70, 29);
		 editPanel.add(genderlb);
		 
		 JComboBox <String >genderBox = new JComboBox(GenderBox);
		 genderBox.setBackground(new Color(255, 253, 233));
		 genderBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 genderBox.setBounds(344, 150, 132, 29);
		 editPanel.add(genderBox);
		 
		 JLabel typelb = new JLabel("Type :");
		 typelb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 typelb.setBounds(258, 177, 70, 29);
		 editPanel.add(typelb);
		 
		 typeField = new JTextField();
		 typeField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 typeField.setColumns(10);
		 typeField.setBounds(258, 209, 217, 29);
		 editPanel.add(typeField);
		 
		 JLabel specieslb = new JLabel("Species :");
		 specieslb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 specieslb.setBounds(258, 239, 70, 29);
		 editPanel.add(specieslb);
		 
		 JLabel Enclosurelb = new JLabel("Enclosure :");
		 Enclosurelb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 Enclosurelb.setBounds(258, 300, 110, 29);
		 editPanel.add(Enclosurelb);
		 
		 JComboBox<String> EnclouserComboBox = new JComboBox(EnclosureBox);
		 EnclouserComboBox.setBackground(new Color(255, 253, 233));
		 EnclouserComboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 EnclouserComboBox.setBounds(258, 330, 218, 29);
		 editPanel.add(EnclouserComboBox);
		 
		 JLabel statelb = new JLabel("Status :");
		 statelb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 statelb.setBounds(10, 300, 110, 29);
		 editPanel.add(statelb);
		 
		 JComboBox <String>StatusCombobox = new JComboBox(HealthBox);
		 StatusCombobox.setBackground(new Color(255, 253, 233));
		 StatusCombobox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 StatusCombobox.setBounds(10, 330, 218, 29);
		 editPanel.add(StatusCombobox);
		 
		 JComboBox <String>SpeciesField = new JComboBox(SpeciesBox);
		 SpeciesField.setBackground(new Color(255, 253, 233));
		 SpeciesField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 SpeciesField.setBounds(258, 270, 218, 29);
		 editPanel.add(SpeciesField);
		
			JPanel righPanel = new JPanel();
			righPanel.setBackground(new Color(0, 64, 0));
			righPanel.setForeground(new Color(255, 253, 233));
			splitPane.setRightComponent(righPanel);
			righPanel.setLayout(new BoxLayout(righPanel, BoxLayout.X_AXIS));
			
			JScrollPane rightScroll = new JScrollPane();
			rightScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			rightScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			rightScroll.setBounds(0, 0, 200, 400);
			righPanel.add(rightScroll);
			
			JPanel rightbox = new JPanel();
			rightScroll.setViewportView(rightbox);
			rightbox.setLayout(new BoxLayout(rightbox, BoxLayout.X_AXIS));
			
			JPanel logsContact = new JPanel();
		//	rightbox.add(logsContact);
			logsContact.setLayout(new BoxLayout(logsContact, BoxLayout.PAGE_AXIS));
			
			JPanel wrapper = new JPanel(new BorderLayout());
			wrapper.setForeground(new Color(255, 253, 233));
			wrapper.setBackground(new Color(0, 50, 35));
			wrapper.add(logsContact, BorderLayout.NORTH);

			rightbox.add(wrapper);
		
		
		
		//This Section is Choosing Primary Key  ( ID ) CombboBox for Editing Data

		 JComboBox <String> editComboBox = new JComboBox<>();
		 editComboBox.setBackground(new Color(255, 253, 233));
		 editComboBox.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		 String selectedID = (String) editComboBox.getSelectedItem();
		         if (selectedID == null || selectedID.trim().isEmpty()) {
		             return;
		         }

		         try (ResultSet rs = db.getAnimalById(selectedID)) {
		             if (rs != null && rs.next()) {
		                 // Text fields
		                 nameField.setText(rs.getString("name"));
		                 ageField.setText(String.valueOf(rs.getInt("age")));
		                 typeField.setText(rs.getString("type"));
		                
		                 
		                 

		                 // Combo boxes 
		                 selectComboBoxItem(SpeciesField, rs.getString("Species"));
		                 selectComboBoxItem(genderBox, rs.getString("gender"));
		                 selectComboBoxItem(StatusCombobox, rs.getString("healthstats"));
		                 selectComboBoxItem(EnclouserComboBox, rs.getString("enclosure"));

		             } else {
		                 // Clear fields if no record found
		                 nameField.setText("No Data or Removed");
		                 ageField.setText("No Data or Removed");
		                 typeField.setText("No Data or Removed");
		                 
		                 
		                 
		                 SpeciesField.setSelectedIndex(-1);
		                 genderCombo.setSelectedIndex(-1);
		                 StatusCombobox.setSelectedIndex(-1);
		                 EnclouserComboBox.setSelectedIndex(-1);
		             }
		         } catch (SQLException ex) {
		             ex.printStackTrace();
		         }
		       
		 		
		 				
		 	}
		 });
		 editComboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 editComboBox.setBounds(10, 86, 218, 32);
		
		 try {
	            ResultSet rs = db.getAllAnimals();
	            while (rs.next()) {       
	                db.Refresh(editComboBox);	                
	                //This refresh Box is for update	                
	                editComboBox.setBounds(10, 87, 212, 35);
	        		removePanel.add(editComboBox);
	        		
	        		String ID = (String)editComboBox.getSelectedItem();
	        		
	        		
	        		
	        		
	        		
	        		
	            }
	        } catch (SQLException ea) {
	            ea.printStackTrace();
	        }		
		 editPanel.add(editComboBox);
		 
		 JButton editBtn = new JButton("Confirm");
		 editBtn.setFocusable(false);
		 editBtn.setForeground(new Color(255, 253, 233));
		 editBtn.setBackground(new Color(255, 100, 0));
		 editBtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		 try {
		 		//Catch fill data with boolean
				String ID = (String)editComboBox.getSelectedItem();
				
				String name = nameField.getText();
				int age = Integer.parseInt(ageField.getText());
		 		String Gender = (String)genderBox.getSelectedItem();
		 			String Type = typeField.getText();
		 		String Sp = (String)SpeciesField.getSelectedItem();
		 			String en = (String)EnclouserComboBox.getSelectedItem();
		 		String hp =(String) StatusCombobox.getSelectedItem();
		 		int result = JOptionPane.showConfirmDialog(null, "Do you want to proceed?","Confirm Box", JOptionPane.OK_CANCEL_OPTION);
		 	
		 		if(result == JOptionPane.OK_OPTION) { 
		 		
		 		boolean update = db.updateAnimalRecord(ID,name,Type,age,Gender,Sp,en,hp);
		 		
		 		
				 
				 //Remove Log
            	String [] Data = db.nts(ID);
	        	
	        
            	if (Data != null) {
            	   
            														//User name is trying to fix
            		logsContact.add(logCardCreation(Data[0],Data[1],Data[2],SignIn_Form.signInAccUsername,
		SignIn_Form.signInAccRole,"Edited"));
            		 db.Refresh(editComboBox);
            		 righPanel.repaint();
            		 righPanel.revalidate();
            		 
            		
            		
            		
            	} else {
            	    System.out.println("No data found for ID: " );
            	}
            	
				
            	
            	
            	
	
		 		if(update == true ) {
		 						JOptionPane.showMessageDialog(null,"Successfully Edited!");
		 	
		 						
		 			}else{
			 			
			 			JOptionPane.showMessageDialog(null,"Fail!");
			 			
			 			
			 		}
	
		 			
		 		}else if (result == JOptionPane.CANCEL_OPTION) {
	 				
	 				JOptionPane.showMessageDialog(null,"Back To App!");
	 				
	 				
	 			};
		 		
		 		
		 		
	 			
	 			
		 		
		 		
		 		
		 		
		 		
		 		 }catch(NumberFormatException ex) {
		 			 
		 			JOptionPane.showMessageDialog(null,"Number Can't be letter !");
		 			 
		 		 };
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 	}
		 });
		 editBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		 editBtn.setBounds(304, 386, 172, 39);
		 editPanel.add(editBtn);
		 
		 
		 JComboBox PKanimalID = new JComboBox();	
		 PKanimalID.setBackground(new Color(255, 253, 233));
		 
		 
		 Addbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String name = nameReq.getText();
					
					
					String type = typeReq.getText();
					
					String ID = idLb.getText();
					
				String Gender = (String) genderCombo.getSelectedItem();
				String Species = (String) speciesCombo.getSelectedItem();
				String Enclosure = (String) enclosureBox.getSelectedItem();
				String Health = (String) healConditionBox.getSelectedItem();
				
				if (name.isEmpty()) {
				    JOptionPane.showMessageDialog(null, "Name can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
				    return;
				}
				if (type.isEmpty()) {
				    JOptionPane.showMessageDialog(null, "Need a type for Animal! Eg.Tiger ", "Warning", JOptionPane.WARNING_MESSAGE);
				    return;
				}
				
				
				
				try{
					
					int age = Integer.parseInt(ageReq.getText());
					
					

					
					int result = JOptionPane.showConfirmDialog( null,"Are you sure you want to submit?","Confirm Action",JOptionPane.OK_CANCEL_OPTION
						    
						  
						);
					
					if (result == JOptionPane.OK_OPTION) {
						contactBox.add(cardCreation(name,age,ID,Enclosure,Species,type));
						
						// Data Filling
						db.insertAnimal(ID,name,type,age,Gender,Species,Enclosure,Health);
						  
						
						
					
						
						
						JOptionPane.showMessageDialog(null, "Successfully Added!!");
						nameReq.setText("");
				        ageReq.setText("");
				        typeReq.setText("");
				        idLb.setText(generateID());
				        
				        
				        
				        
				        
				        //This one is refresh ID in RemoveIDCombo Box  and editcombo box
				        
				        db.Refresh(choose2RemoveCombo);
				        db.Refresh(editComboBox);
				        db.Refresh(PKanimalID);
				    
				   
				        
				        //This Section is for Logs
				        
				        
				        String [] lg = db.Log();	        
				        if( lg.length >= 2) {
				        	
				        	logsContact.add(logCardCreation(ID,name,type,lg[0],lg[1],"ADDED"));
		
				        }else {    	
				        System.out.print("Error in logs Section of add button");
				        }
				        
     
				    //------------------------    
				        
				        
					    
					} else if (result == JOptionPane.CANCEL_OPTION) {

					    return;  
					}
					
					
				}catch(NumberFormatException ex){
					
					JOptionPane.showMessageDialog(null, "Please enter a valid number for age.");
			    

				} catch (SQLException e1) {
					
					e1.printStackTrace();
				} 
				

			
				
				
					
				}

				
			});
		 
		
		 //Nutrion
		 
		 JPanel NutritionPanel = new JPanel();
		 NutritionPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			NutritionPanel.setBackground(new Color(0, 50, 35));
			mainwindow.add(NutritionPanel, "name_108421765895500");
			NutritionPanel.setLayout(null);
			
			JPanel WarehouseBox = new JPanel();
			WarehouseBox.setBackground(new Color(255, 253, 233));
			WarehouseBox.setBounds(10, 54, 181, 431);
			NutritionPanel.add(WarehouseBox);
			WarehouseBox.setLayout(null);
			
					//Combobox Section 
			
			JComboBox CateComboBox = new JComboBox();
			CateComboBox.setBackground(new Color(255, 253, 233));
			
			CateComboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
			CateComboBox.setToolTipText("Select");
			CateComboBox.setBounds(10, 33, 161, 40);
			WarehouseBox.add(CateComboBox);
			
			JLabel categoryLb = new JLabel("Category");
			categoryLb.setBackground(new Color(0, 50, 35));
			categoryLb.setForeground(new Color(0, 50, 35));
			categoryLb.setFont(new Font("Tahoma", Font.PLAIN, 17));
			categoryLb.setBounds(10, 0, 161, 26);
			WarehouseBox.add(categoryLb);
			categoryLb.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel Itemlb = new JLabel("Item");
			Itemlb.setBackground(new Color(0, 50, 35));
			Itemlb.setForeground(new Color(0, 50, 35));
			Itemlb.setHorizontalAlignment(SwingConstants.CENTER);
			Itemlb.setFont(new Font("Tahoma", Font.PLAIN, 17));
			Itemlb.setBounds(10, 211, 161, 26);
			WarehouseBox.add(Itemlb);
			
			JComboBox Itemcombobox = new JComboBox();
			Itemcombobox.setBackground(new Color(255, 253, 233));
			
			Itemcombobox.setFont(new Font("Tahoma", Font.PLAIN, 17));
			Itemcombobox.setToolTipText("Select");
			Itemcombobox.setBounds(10, 247, 161, 40);
			WarehouseBox.add(Itemcombobox);
			
			
			//***
			Map<String, List <String>> ChangeCatData = new HashMap<>();
			
				System.out.println(ChangeCatData);
			
			ChangeCatData = db.LoadCatbox();
			
			
			//loop and take all the Category in DataBase
			for (String category : ChangeCatData.keySet()) {
				CateComboBox.addItem(category);
				
				
	        }
			
			
			
		//	 updateItemCombo(String category , JComboBox JC, Map<String, List<String>> st )
			
			
			CateComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
							
					
					
					Map<String, List <String>> Item= new HashMap<>();
					Item =  db.LoadCatbox();
					
					String SelectCat = (String) CateComboBox.getSelectedItem();
					
					db.changeitem(SelectCat,Itemcombobox,Item);
					
					
					
					
					
					
					
					
					
					
				}
			});
			
			
			
			PKanimalID.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
			

			
			JLabel Feedinglb = new JLabel("Feeding Transition");
			Feedinglb.setForeground(new Color(255, 253, 233));
			Feedinglb.setBackground(new Color(255, 253, 233));
			Feedinglb.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 24));
			Feedinglb.setHorizontalAlignment(SwingConstants.CENTER);

			Feedinglb.setBounds(10, 10, 983, 41);
			NutritionPanel.add(Feedinglb);
			
			JPanel Contactpanel = new JPanel();
			Contactpanel.setBackground(new Color(0, 50, 35));
			Contactpanel.setBounds(204, 54, 789, 431);
			NutritionPanel.add(Contactpanel);
			Contactpanel.setLayout(null);
			
			JLabel totalStock = new JLabel("Total Stock :");
			totalStock.setForeground(new Color(255, 253, 233));
			totalStock.setFont(new Font("Tahoma", Font.PLAIN, 17));
			totalStock.setBounds(10, 13, 137, 28);
			Contactpanel.add(totalStock);
			
			qtytxt = new JTextField();
			qtytxt.setBackground(new Color(255, 253, 233));
			qtytxt.setBounds(107, 67, 123, 27);
			Contactpanel.add(qtytxt);
			qtytxt.setColumns(10);
			
			JLabel AmountLb = new JLabel("Amount");
			AmountLb.setForeground(new Color(255, 253, 233));
			AmountLb.setFont(new Font("Tahoma", Font.PLAIN, 17));
			AmountLb.setBounds(10, 63, 87, 28);
			Contactpanel.add(AmountLb);
			
			JLabel Animaldetail = new JLabel("Animal Detail");
			Animaldetail.setForeground(new Color(255, 253, 233));
			Animaldetail.setFont(new Font("Tahoma", Font.PLAIN, 17));
			Animaldetail.setBounds(308, 13, 110, 28);
			Contactpanel.add(Animaldetail);
			
			JPanel AnimalDetalPenal = new JPanel();
			AnimalDetalPenal.setBackground(new Color(255, 253, 233));
			AnimalDetalPenal.setBounds(258, 40, 212, 235);
			Contactpanel.add(AnimalDetalPenal);
			
			
			AnimalDetalPenal.add(PKanimalID);
			PKanimalID.setPreferredSize(new Dimension(200, 30));
			
			
			JLabel StockDetail = new JLabel("Stock Detail");
			StockDetail.setForeground(new Color(255, 253, 233));
			StockDetail.setFont(new Font("Tahoma", Font.PLAIN, 17));
			StockDetail.setBounds(592, 25, 110, 28);
			Contactpanel.add(StockDetail);
			
			JPanel stockdetailpanel = new JPanel();
			stockdetailpanel.setBackground(new Color(255, 100, 0));
			stockdetailpanel.setBounds(516, 63, 263, 344);
			Contactpanel.add(stockdetailpanel);
			
			JLabel productID = new JLabel("Product ID");
			productID.setFont(new Font("Tahoma", Font.PLAIN, 17));
			productID.setPreferredSize(new Dimension(stockdetailpanel.getWidth(),30));
			productID .setHorizontalAlignment(JTextField.CENTER);

			stockdetailpanel.add(productID);
			
			productTxt = new JTextField();
			productTxt.setEditable(false);
			productTxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
			productTxt.setColumns(10);
			productTxt.setPreferredSize(new Dimension(stockdetailpanel.getWidth(), 40));
			stockdetailpanel.add(productTxt);
			
			JLabel manutxt = new JLabel("Manufacturer");
			manutxt.setPreferredSize(new Dimension(263, 30));
			manutxt.setHorizontalAlignment(SwingConstants.CENTER);
			manutxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
			stockdetailpanel.add(manutxt);
			
			manutxts = new JTextField();
			manutxts.setEditable(false);
			manutxts.setPreferredSize(new Dimension(263, 40));
			manutxts.setFont(new Font("Tahoma", Font.PLAIN, 17));
			manutxts.setColumns(10);
			stockdetailpanel.add(manutxts);
			
			JLabel waretxt = new JLabel("Warehouse");
			waretxt.setPreferredSize(new Dimension(263, 30));
			waretxt.setHorizontalAlignment(SwingConstants.CENTER);
			waretxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
			stockdetailpanel.add(waretxt);
			
			Warehousetxt = new JTextField();
			Warehousetxt.setEditable(false);
			Warehousetxt.setPreferredSize(new Dimension(263, 40));
			Warehousetxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
			Warehousetxt.setColumns(10);
			stockdetailpanel.add(Warehousetxt);
			
			JLabel animalName = new JLabel("Animal Name");
			animalName.setForeground(new Color(255, 253, 233));
			animalName.setFont(new Font("Tahoma", Font.PLAIN, 17));
			animalName.setBounds(10, 212, 212, 28);
			Contactpanel.add(animalName);
			
			JLabel animalSpecies = new JLabel("Species");
			animalSpecies.setForeground(new Color(255, 253, 233));
			animalSpecies.setFont(new Font("Tahoma", Font.PLAIN, 17));
			animalSpecies.setBounds(258, 285, 212, 28);
			Contactpanel.add(animalSpecies);
			
			JLabel lblGender = new JLabel("Gender");
			lblGender.setForeground(new Color(255, 253, 233));
			lblGender.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblGender.setBounds(258, 356, 212, 28);
			Contactpanel.add(lblGender);
			
			JButton transitionbtnnu = new JButton("Transition Added +");
			transitionbtnnu.setFocusable(false);
			transitionbtnnu.setForeground(new Color(255, 253, 233));
			transitionbtnnu.setBackground(new Color(255, 100, 0));
		
			transitionbtnnu.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
			transitionbtnnu.setBounds(10, 384, 212, 37);
			Contactpanel.add(transitionbtnnu);
			
			animaltxtf = new JTextField();
			animaltxtf.setBackground(new Color(255, 253, 233));
			animaltxtf.setFont(new Font("Tahoma", Font.PLAIN, 17));
			animaltxtf.setEditable(false);
			animaltxtf.setBounds(10, 251, 212, 37);
			Contactpanel.add(animaltxtf);
			animaltxtf.setColumns(10);
			
			Speciestxtf = new JTextField();
			Speciestxtf.setFont(new Font("Tahoma", Font.PLAIN, 17));
			Speciestxtf.setEditable(false);
			Speciestxtf.setColumns(10);
			Speciestxtf.setBounds(258, 323, 212, 37);
			Contactpanel.add(Speciestxtf);
			
			Gendertxtf = new JTextField();
			Gendertxtf.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
			Gendertxtf.setEditable(false);
			Gendertxtf.setColumns(10);
			Gendertxtf.setBounds(258, 384, 212, 37);
			Contactpanel.add(Gendertxtf);
			
			
		
			db.Refresh(PKanimalID); //Refresh methode is remove first and add data into combo box
			
			ShowQty = new JTextField();
			ShowQty.setBackground(new Color(255, 253, 233));
			ShowQty.setFont(new Font("Tahoma", Font.PLAIN, 17));
			ShowQty.setEditable(false);
			ShowQty.setColumns(10);
			ShowQty.setBounds(116, 9, 87, 37);
			Contactpanel.add(ShowQty);
			
			
			PKanimalID.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				      String selectedID = (String) PKanimalID.getSelectedItem();
				        if (selectedID != null) {
				            try {
				                ResultSet rs = db.getAnimalById(selectedID);
				                if (rs != null && rs.next()) {
				                	animaltxtf.setText(rs.getString("name"));
				                	Speciestxtf.setText(rs.getString("Species"));
				                	Gendertxtf.setText(rs.getString("gender"));
				                   
				                	
				                    
				                }
				            } catch (SQLException ex) {
				                ex.printStackTrace();
				            }
				        }
				}
			});	
			
			
			Itemcombobox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
				      String selecteditem = (String) Itemcombobox.getSelectedItem();
				        if (selecteditem != null) {
				            try {
				                ResultSet rs = db.getQtyByItem(selecteditem);
				                if (rs != null && rs.next()) {
				                	ShowQty.setText(String.valueOf(rs.getInt("stock")));
				                	productTxt.setText(rs.getString("product_id"));
				                	manutxts.setText(rs.getString("manufacturer"));
				                	Warehousetxt.setText(rs.getString("warehouse"));
				                	
            
				                }
				            } catch (SQLException ex) {
				                ex.printStackTrace();
				            }
				        }					
										
				}
			});
		
			
			
			transitionbtnnu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				int qtys = 	Integer.parseInt(qtytxt.getText());
			String aniID = (String) PKanimalID.getSelectedItem();
	
			String stockID = (String) productTxt.getText();
				
			//	System.out.print(qtys);
					
					db.transition(qtys,aniID,stockID);
					ShowQty.setText(String.valueOf(db.RefreshStock(stockID)));
					
					
					
					db.Refresh(PKanimalID);
					db.Refresh(choose2RemoveCombo);
			        db.Refresh(editComboBox);
					
					
					
					
					
					
				}
			});
		
		
		/*Decoration Between Split Screen (Divider) */
		splitPane.setDividerSize(1);
		splitPane.setEnabled(false);
		
		JButton removeBtn = new JButton("Remove");
		removeBtn.setFocusable(false);
		removeBtn.setForeground(new Color(255, 253, 233));
		removeBtn.setBackground(new Color(255, 100, 0));
		removeBtn.setFocusPainted(false);
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* cardlayout */
				leftPanel.removeAll();
				leftPanel.add(removePanel);
				leftPanel.repaint();
				leftPanel.revalidate();

			}
		});
		
		JButton Editbtn = new JButton("Edit");
		Editbtn.setFocusable(false);
		Editbtn.setForeground(new Color(255, 253, 233));
		Editbtn.setBackground(new Color(255, 100, 0));
		Editbtn.setFocusPainted(false);
		Editbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* cardlayout */
				leftPanel.removeAll();
				leftPanel.add(editPanel);
				leftPanel.repaint();
				leftPanel.revalidate();
			}
		});
		Editbtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		Editbtn.setBounds(355, 5, 105, 38);
		ManagemetPanel.add(Editbtn);
		removeBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		removeBtn.setBounds(240, 5, 105, 38);
		ManagemetPanel.add(removeBtn);
		
		
		
		
		
		JButton allBtn = new JButton("All");
		allBtn.setFocusable(false);
		allBtn.setBackground(new Color(255, 100, 0));
		allBtn.setFocusPainted(false);
		allBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* cardlayout */
				leftPanel.removeAll();
				leftPanel.add(AllPanel);
				leftPanel.repaint();
				leftPanel.revalidate();
			
				
				
			}
		});
		allBtn.setForeground(new Color(255, 253, 233));
		allBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		allBtn.setBounds(10, 5, 105, 38);
		ManagemetPanel.add(allBtn);
		
		
		
		
		JButton Add = new JButton("Add");
		Add.setFocusable(false);
		Add.setForeground(new Color(255, 253, 233));
		Add.setBackground(new Color(255, 100, 0));
		Add.setFocusPainted(false);
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* cardlayout */
				leftPanel.removeAll();
				leftPanel.add(AddPanel);
				leftPanel.repaint();
				leftPanel.revalidate();
				
				

				
			}
		});
		Add.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		Add.setBounds(125, 5, 105, 38);
		ManagemetPanel.add(Add);
		
		JLabel Activitylog = new JLabel("Activity Logs");
		Activitylog.setForeground(new Color(206, 241, 123));
		Activitylog.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 18));
		Activitylog.setBounds(704, 12, 121, 26);
		ManagemetPanel.add(Activitylog);
		
		JPanel Healthpanel = new JPanel();
		Healthpanel.setLayout(null);
		Healthpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		Healthpanel.setBackground(new Color(206, 241, 123));
		mainwindow.add(Healthpanel, "name_496842954292200");
		
		JPanel WarehouseBox_1 = new JPanel();
		WarehouseBox_1.setBackground(new Color(255, 253, 233));
		WarehouseBox_1.setLayout(null);
		WarehouseBox_1.setBounds(10, 54, 181, 431);
		Healthpanel.add(WarehouseBox_1);
		
		
		
		
		//Med 
		
		
		Map<String, List <String>> ChangeCatDatMed = new HashMap<>();
		
		System.out.println(ChangeCatData);
	
	ChangeCatDatMed = db.LoadCatboxMed();
	JComboBox medCat = new JComboBox();
	medCat.setBackground(new Color(255, 253, 233));
	
	//loop and take all the Category in DataBase
	for (String category : ChangeCatDatMed.keySet()) {
		medCat.addItem(category);
		
		
    }
		
	
		
	JComboBox meditem = new JComboBox();
	meditem.setBackground(new Color(255, 253, 233));
	meditem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		      String selecteditem = (String) meditem.getSelectedItem();
		        if (selecteditem != null) {
		            try {
		                ResultSet rs = db.getQtyByMedItem(selecteditem);
		                if (rs != null && rs.next()) {
		                	medtotalstock.setText(String.valueOf(rs.getInt("stock")));
		                	medID.setText(rs.getString("product_id"));
		                	Medmanu.setText(rs.getString("manufacturer"));
		                	medware.setText(rs.getString("warehouse"));
		                	
  
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		}
	});
		
	
		medCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Map<String, List <String>> Item= new HashMap<>();
				Item =  db.LoadCatboxMed();
				
				String SelectCat = (String) medCat.getSelectedItem();
				
				db.changeitem(SelectCat,meditem,Item);
				
				
				
			}
		});
		medCat.setToolTipText("Select");
		medCat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		medCat.setBounds(10, 33, 161, 40);
		WarehouseBox_1.add(medCat);
		
		JLabel categoryLb_1 = new JLabel("Category");
		categoryLb_1.setHorizontalAlignment(SwingConstants.CENTER);
		categoryLb_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		categoryLb_1.setBounds(10, 0, 161, 26);
		WarehouseBox_1.add(categoryLb_1);
		
		JLabel Itemlb_1 = new JLabel("Item");
		Itemlb_1.setHorizontalAlignment(SwingConstants.CENTER);
		Itemlb_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Itemlb_1.setBounds(10, 211, 161, 26);
		WarehouseBox_1.add(Itemlb_1);
		
		
		meditem.setToolTipText("Select");
		meditem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		meditem.setBounds(10, 247, 161, 40);
		WarehouseBox_1.add(meditem);
		
		JLabel healthtran = new JLabel("Medical Transition");
		healthtran.setHorizontalAlignment(SwingConstants.CENTER);
		healthtran.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 24));
		healthtran.setBackground(new Color(255, 128, 0));
		healthtran.setBounds(10, 10, 983, 41);
		Healthpanel.add(healthtran);
		
		JPanel Contactpanel_1 = new JPanel();
		Contactpanel_1.setBackground(new Color(206, 241, 123));
		Contactpanel_1.setLayout(null);
		Contactpanel_1.setBounds(204, 54, 789, 431);
		Healthpanel.add(Contactpanel_1);
		
		JLabel TotalmedStock = new JLabel("Total Stock :");
		TotalmedStock.setFont(new Font("Tahoma", Font.PLAIN, 17));
		TotalmedStock.setBounds(10, 13, 137, 28);
		Contactpanel_1.add(TotalmedStock);
		
		medtxt = new JTextField();
		medtxt.setBackground(new Color(255, 253, 233));
		medtxt.setColumns(10);
		medtxt.setBounds(107, 67, 123, 27);
		Contactpanel_1.add(medtxt);
		
		JLabel AmountLb_1 = new JLabel("Amount");
		AmountLb_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AmountLb_1.setBounds(10, 63, 87, 28);
		Contactpanel_1.add(AmountLb_1);
		
		JLabel Animaldetail_1 = new JLabel("Animal Detail");
		Animaldetail_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Animaldetail_1.setBounds(307, 9, 110, 28);
		Contactpanel_1.add(Animaldetail_1);
		
		JPanel AnimalDetalPenal_1 = new JPanel();
		AnimalDetalPenal_1.setBackground(new Color(255, 253, 233));
		AnimalDetalPenal_1.setBounds(258, 40, 212, 235);
		Contactpanel_1.add(AnimalDetalPenal_1);
		
		
		
		
		
	
		
		
		JComboBox PKanimalID_med = new JComboBox();
		PKanimalID_med.setBackground(new Color(255, 253, 233));
		db.Refresh(PKanimalID_med);
		PKanimalID_med.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			      String selectedID = (String) PKanimalID_med.getSelectedItem();
			        if (selectedID != null) {
			            try {
			                ResultSet rs = db.getAnimalById(selectedID);
			                if (rs != null && rs.next()) {
			                	namemed.setText(rs.getString("name"));
			                	spmed.setText(rs.getString("Species"));
			                	gendermed.setText(rs.getString("gender"));
			                   
			                	
			                    
			                }
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }
			        }
			}
		});
		PKanimalID_med.setPreferredSize(new Dimension(200, 30));
		PKanimalID_med.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AnimalDetalPenal_1.add(PKanimalID_med);
		
		JLabel StockDetail_1 = new JLabel("Stock Detail");
		StockDetail_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		StockDetail_1.setBounds(591, 24, 137, 28);
		Contactpanel_1.add(StockDetail_1);
		
		JPanel stockdetailpanel_1 = new JPanel();
		stockdetailpanel_1.setBackground(new Color(255, 100, 0));
		stockdetailpanel_1.setBounds(516, 63, 263, 344);
		Contactpanel_1.add(stockdetailpanel_1);
		
		JLabel productID_1 = new JLabel("Product ID");
		productID_1.setPreferredSize(new Dimension(263, 30));
		productID_1.setHorizontalAlignment(SwingConstants.CENTER);
		productID_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		stockdetailpanel_1.add(productID_1);
		
		medID = new JTextField();
		medID.setBackground(new Color(255, 253, 233));
		medID.setPreferredSize(new Dimension(263, 40));
		medID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		medID.setEditable(false);
		medID.setColumns(10);
		stockdetailpanel_1.add(medID);
		
		JLabel manutxt_1 = new JLabel("Manufacturer");
		manutxt_1.setPreferredSize(new Dimension(263, 30));
		manutxt_1.setHorizontalAlignment(SwingConstants.CENTER);
		manutxt_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		stockdetailpanel_1.add(manutxt_1);
		
		Medmanu = new JTextField();
		Medmanu.setBackground(new Color(255, 253, 233));
		Medmanu.setPreferredSize(new Dimension(263, 40));
		Medmanu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Medmanu.setEditable(false);
		Medmanu.setColumns(10);
		stockdetailpanel_1.add(Medmanu);
		
		JLabel waretxt_1 = new JLabel("Warehouse");
		waretxt_1.setPreferredSize(new Dimension(263, 30));
		waretxt_1.setHorizontalAlignment(SwingConstants.CENTER);
		waretxt_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		stockdetailpanel_1.add(waretxt_1);
		
		medware = new JTextField();
		medware.setBackground(new Color(255, 253, 233));
		medware.setPreferredSize(new Dimension(263, 40));
		medware.setFont(new Font("Tahoma", Font.PLAIN, 17));
		medware.setEditable(false);
		medware.setColumns(10);
		stockdetailpanel_1.add(medware);
		
		JLabel animalName_1 = new JLabel("Animal Name");
		animalName_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		animalName_1.setBounds(10, 212, 212, 28);
		Contactpanel_1.add(animalName_1);
		
		JLabel animalSpecies_1 = new JLabel("Species");
		animalSpecies_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		animalSpecies_1.setBounds(258, 285, 212, 28);
		Contactpanel_1.add(animalSpecies_1);
		
		JLabel lblGender_1 = new JLabel("Gender");
		lblGender_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblGender_1.setBounds(258, 356, 212, 28);
		Contactpanel_1.add(lblGender_1);
		
		JButton transitionbtnmed = new JButton("Transition Added +");
		transitionbtnmed.setForeground(new Color(255, 253, 233));
		transitionbtnmed.setFocusable(false);
		transitionbtnmed.setBackground(new Color(255, 100, 0));
		transitionbtnmed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int qtys = 	Integer.parseInt(medtxt.getText());
				String aniID = (String) PKanimalID_med.getSelectedItem();
		
				String stockID = (String) medID.getText();
					
				//	System.out.print(qtys);
						db.transitionMed(qtys,aniID,stockID);
						
						medtotalstock.setText(String.valueOf(db.RefreshMedStock(stockID)));
						
						
						
						db.Refresh(PKanimalID_med);
						db.Refresh(choose2RemoveCombo);
				        db.Refresh(editComboBox);
			}
		});
		transitionbtnmed.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		transitionbtnmed.setBounds(10, 384, 212, 37);
		Contactpanel_1.add(transitionbtnmed);
		
		namemed = new JTextField();
		namemed.setBackground(new Color(255, 253, 233));
		namemed.setFont(new Font("Tahoma", Font.PLAIN, 17));
		namemed.setEditable(false);
		namemed.setColumns(10);
		namemed.setBounds(10, 251, 212, 37);
		Contactpanel_1.add(namemed);
		
		spmed = new JTextField();
		spmed.setBackground(new Color(255, 253, 233));
		spmed.setFont(new Font("Tahoma", Font.PLAIN, 17));
		spmed.setEditable(false);
		spmed.setColumns(10);
		spmed.setBounds(258, 323, 212, 37);
		Contactpanel_1.add(spmed);
		
		gendermed = new JTextField();
		gendermed.setBackground(new Color(255, 253, 233));
		gendermed.setFont(new Font("Tahoma", Font.PLAIN, 17));
		gendermed.setEditable(false);
		gendermed.setColumns(10);
		gendermed.setBounds(258, 384, 212, 37);
		Contactpanel_1.add(gendermed);
		
		medtotalstock = new JTextField();
		medtotalstock.setBackground(new Color(255, 253, 233));
		medtotalstock.setFont(new Font("Tahoma", Font.PLAIN, 17));
		medtotalstock.setEditable(false);
		medtotalstock.setColumns(10);
		medtotalstock.setBounds(116, 9, 87, 37);
		Contactpanel_1.add(medtotalstock);
		
		
		
		
			
			//Resizing frame when maximize 
		
		addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		        // Handle resize
		    	
		    		
		    	int frameWidth = getWidth();
				int frameheight = getHeight();
				int splithalfframeW = (int) (frameWidth * 0.5);
				int splithalfframeH = (int) (frameheight * 0.5);
				int mainwindowWidth = mainwindow.getWidth();
				
				
				//Nutration 
				Feedinglb.setSize(frameWidth  , 30);
				 WarehouseBox.setBounds(10, 54, 180 , frameheight - 230);
				 Contactpanel.setBounds(204, 54, frameWidth - 330, frameheight - 230);
				
				
				
				
				BackFrame.setSize(frameWidth  , frameheight);
				
		        allScrollPane.setSize(splithalfframeW, frameheight);
		       
		        
		        //This Two Bounds is important for responsive need manually to fix because i was choosing absolute can't help but let's try
		        mainwindow.setBounds(10, 54, frameWidth + 295, frameheight + 10);
		       
		        
		        
		        
		        
		        
		        
		        splitPane.setBounds(0, 46, frameWidth - 125, frameheight -210);
		      Activitylog.setBounds(frameWidth - 470 ,12,121,26);
		        
		        
		        
		        
		        
		        splitPane.setDividerLocation(splithalfframeW);
		        splitPane.setResizeWeight(0.5);
		       
		        
		    
				
				
			
		    }
		});
		
		
		
		
		
		
		

			//Button Creation
		JButton nutritionBtn = new JButton("Nutrition");
		nutritionBtn.setBackground(new Color(255, 255, 255));
		JButton healthBtn = new JButton("Health");
		healthBtn.setBackground(new Color(255, 255, 255));
		JButton managementBtn = new JButton("Management");
		managementBtn.setBackground(Color.white);
		nutritionBtn.setFocusPainted(false);
		healthBtn.setFocusPainted(false);
		managementBtn.setFocusPainted(false);
		
		//remove bottom border
		nutritionBtn.setBorder(new MatteBorder(1, 1, 0, 1, Color.BLACK));
		nutritionBtn.setFocusPainted(false);
	        
		healthBtn.setBorder(new MatteBorder(1, 1, 0, 1, Color.BLACK));
		healthBtn.setFocusPainted(false);
	        
		managementBtn.setBorder(new MatteBorder(1, 1, 0, 1, Color.BLACK));
		managementBtn.setFocusPainted(false);
		
		
			//Remove button action listener
		removebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selectedID = (String) choose2RemoveCombo.getSelectedItem();
				
				
            	
            	
				
		        
		        
		        
		        if (selectedID == null) {
		            JOptionPane.showMessageDialog(null, "Please select an ID first!", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete ID: "+ selectedID + "?","Confirm Delete",JOptionPane.YES_NO_OPTION
		        );

		        if (confirm == JOptionPane.YES_OPTION) {
		        	
		        
					 //Remove Log
	            	String [] Data = db.nts(selectedID);
		        	
		        
	            	if (Data != null) {
	            	   
	            														//User name is trying to fix
	            		logsContact.add(logCardCreation(Data[0],Data[1],Data[2],SignIn_Form.signInAccUsername,
	            				SignIn_Form.signInAccRole,"Removed"));
	            		
	            		
	            		
	            	} else {
	            	    System.out.println("No data found for ID: " );
	            	}
	            	
					  
					       
		        	
		        	
		            db.deleteAnimal(selectedID);

		            // Refresh combo box
		            choose2RemoveCombo.removeAllItems();
		            db.Refresh(choose2RemoveCombo);
		            db.Refresh(editComboBox);

		            // Clear details
		            namelb.setText("Name: ");
		            agelb.setText("Age: ");
		            lbenclosure.setText("Enclosure: ");
		            lbType.setText("Type: ");
		            lbSpecies.setText("Species: ");
		            
		        
		  
		            //This one is removing card in all page
		            for (Component comp : contactBox.getComponents()) {
		                if (comp.getName() != null && comp.getName().equals(selectedID)) {
		                    contactBox.remove(comp);
		                    break; 
		                }
		            }
		            
		            contactBox.revalidate(); //To take deleted card place
		            contactBox.repaint();    //To take deleted card place
		            
		    
			        	
 
			 
			       
			        
		            
		            

		           };
		        };
			});
		
		
		
		
		
		
		
		
		
		/*btnActionListener*/
		healthBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				healthBtn.setBackground(Color.white);
				nutritionBtn.setBackground(new Color(255, 253, 233));
				managementBtn.setBackground(new Color(255, 255, 233));
				
				/*crd layout*/
				
				mainwindow.removeAll();
				mainwindow.add(Healthpanel);
				mainwindow.repaint();
				mainwindow.revalidate();
				
				
			}
		});
		
		
		
		
		
		nutritionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nutritionBtn.setBackground(Color.white);
				managementBtn.setBackground(new Color(255, 253, 233));
				healthBtn.setBackground(new Color(255, 253, 233));
				
				
				/*crd layout*/
				
				mainwindow.removeAll();
				mainwindow.add(NutritionPanel);
				mainwindow.repaint();
				mainwindow.revalidate();
			}
		});
		nutritionBtn.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 18));
		nutritionBtn.setBounds(194, 21, 184, 32);
		BackFrame.add(nutritionBtn);
		
		
		managementBtn.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 18));
		managementBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				managementBtn.setBackground(Color.white);
				nutritionBtn.setBackground(new Color(255, 253,233 ));
				
				healthBtn.setBackground(new Color(255, 253, 233));
				/*crd layout*/
				
				mainwindow.removeAll();
				mainwindow.add(ManagemetPanel);
				mainwindow.repaint();
				mainwindow.revalidate();
				
				
				
			}
		});
		managementBtn.setBounds(10, 21, 184, 32);
		BackFrame.add(managementBtn);
		
		
		healthBtn.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 18));
		healthBtn.setBounds(378, 21, 184, 32);
		BackFrame.add(healthBtn);
		
		
		
		
		
		
		
		
		
		

	}
	//Data Base Connection
	class DbConnectoion{
		
			private Connection con;
			private static final String hostURL = "jdbc:mysql://localhost:3306/ZooManagementSystem";
			private static final String user = "root";
			private static final String password = "mintOfficial2BE4$";	
			
		
		//Constructor
		public DbConnectoion() {
			
			 try {
		           con = DriverManager.getConnection(hostURL, user, password);
		            System.out.println(" Connected to database.");
		        } catch (SQLException e) {
		            System.out.println(" Database connection failed.");
		            e.printStackTrace();
		        }
		}
		
		
		public void AnimalCount() throws SQLException {
			totalAnimalCount=0;
			 String sql = "SELECT * FROM animal";
			   
			        PreparedStatement stmt = con.prepareStatement(sql);   
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                    totalAnimalCount+=1;
		
        }}
		
		
		
		
		
			 //This one is to catch user Data
			 public void insertAnimal(String id, String name, String type, int age, String gender,
                     String species, String enclosure, String health) {
				 String sql = "INSERT INTO animal (idanimal, name, type, age, gender, Species, enclosure, healthstats) " +
						 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

				 try (PreparedStatement stmt = con.prepareStatement(sql)) {
					 stmt.setString(1, id);
					 stmt.setString(2, name);
					 	stmt.setString(3, type);
					 	stmt.setInt(4, age);
					 	stmt.setString(5, gender);
					 	stmt.setString(6, species);
					 	stmt.setString(7, enclosure);
					 	stmt.setString(8, health);

					 	stmt.executeUpdate();
					 	System.out.println("✅ Animal inserted successfully.");

				 } catch (SQLException e) {
					 System.out.println("❌ Failed to insert animal.");
					 e.printStackTrace();
				 }
			 
 
			
		};
		
		//This one is to read the data from DataBase

		public ResultSet getAllAnimals() {
		    String sql = "SELECT * FROM animal";
		    try {
		        PreparedStatement stmt = con.prepareStatement(sql);
		        return stmt.executeQuery();
		    } catch (SQLException e) {
		        System.out.println("❌ Failed to fetch data.");
		        e.printStackTrace();
		        return null;
		    }
		}
		
		//This One will read all the primary key in database want a length for removing page
		public String[] getAllPrimaryKeys() {
		    String sql = "SELECT idanimal FROM animal";
		    List<String> ids = new ArrayList<>();

		    try (PreparedStatement stmt = con.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {

		        while (rs.next()) {
		            ids.add(rs.getString("idanimal"));
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return ids.toArray(new String[0]);
		}
			//This one is Refresh data after click add button
		public void Refresh(JComboBox<String> refreshBox) {
			
				refreshBox.removeAllItems();
				String [] primaryID = getAllPrimaryKeys();
			
				 for (String id : primaryID) {
				        refreshBox.addItem(id); // Add each ID to the combo box
				    }
			
		};
		
		
		
		//This one is to search data in database by using primary key
		
		public ResultSet getAnimalById(String id) {
		    String sql = "SELECT * FROM animal WHERE idanimal = ?";
		    try {
		        PreparedStatement stmt = con.prepareStatement(sql);
		        stmt.setString(1, id);
		        return stmt.executeQuery();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return null;
		    }
		}
		
		public ResultSet getQtyByItem(String id) {
		    String sql = "SELECT  product_id,manufacturer,warehouse,stock FROM animalfoodinventory WHERE product_name =? ";
		    try {
		        PreparedStatement stmt = con.prepareStatement(sql);
		        stmt.setString(1, id);
		        return stmt.executeQuery();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return null;
		    }
		}
		
		public ResultSet getQtyByMedItem(String id) {
		    String sql = "SELECT  product_id,manufacturer,warehouse,stock FROM medicalinventory WHERE product_name =? ";
		    try {
		        PreparedStatement stmt = con.prepareStatement(sql);
		        stmt.setString(1, id);
		        return stmt.executeQuery();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return null;
		    }
		}
		
		
		//This one is for logs return get by name,type and species by ID 
		
		public String[] nts(String IDs) {
			
			String sql = "SELECT idanimal, name, type FROM animal WHERE idanimal = ?";
			
				try {
					
					PreparedStatement pS = con.prepareStatement(sql);
					pS.setString(1,IDs);
					ResultSet rs = pS.executeQuery();
					
					
					if(rs.next()) {
						
							String [] animalData = new String[3];
							animalData[0] = rs.getString("idanimal");
							animalData[1] = rs.getString("name");						
							animalData[2] = rs.getString("type");
							return animalData;
						
					}else {
						System.out.println("Error in logs return section nts(); ");
						return null;
					}
					
					
					
					
					
					
				}catch(SQLException es) {
					
					es.printStackTrace();
			        return null;
					
				}
			
		};
		
		
		//This one is remove data from database
		
		public void deleteAnimal(String id) {
		    String sql = "DELETE FROM animal WHERE idanimal = ?";
		    try (PreparedStatement stmt = con.prepareStatement(sql)) {
		    	
		        stmt.setString(1, id);
		        int rowsAffected = stmt.executeUpdate();
		        if (rowsAffected > 0) {
		        	
		            System.out.println("✅ Animal deleted successfully.");
		            JOptionPane.showMessageDialog(null,"Animal removed successfully!");
		            
		        } else {
		        	
		            System.out.println("⚠️ No animal found with that ID.");
		            JOptionPane.showMessageDialog(null,"No animal found with that ID.");
		        }
		    } catch (SQLException e) {
		    	
		        System.out.println("❌ Failed to delete animal.");
		        JOptionPane.showMessageDialog(null,"Failed to delete animal..");
		        e.printStackTrace();
		    }
		}
		
		//This Section is for Edit Btn i choose to write catch with boolean
				private boolean updateAnimalRecord(String id, String name, String type, int age, String gender, String species, String enclosure, String health) {
				    String sql = "UPDATE animal SET name=?, type=?, age=?, gender=?, Species=?, enclosure=?, healthstats=? WHERE idanimal=?";
;
				    
				    
				    try (PreparedStatement pst = con.prepareStatement(sql)) {
				      //Stuck in this place for a long time lol
				        pst.setString(1, name);
				        pst.setString(2, type );
				        pst.setInt(3, age);
				        pst.setString(4, gender);
				        pst.setString(5, species);
				        pst.setString(6, enclosure);
				        pst.setString(7, health);
				        pst.setString(8, id);
				        

				        int rowsAffected = pst.executeUpdate();
				        return rowsAffected > 0;
				    } catch (SQLException ex) {
				        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
				        return false;
				    }
				    
				}
				
				
				
				private String[] Log() throws SQLException {
					
					List<String> logData = new ArrayList<>();
				//	String sqlAnimal = "SELECT idanimal, name, type FROM animal limit 1";
					String  sqlStuff = "SELECT Name, Role FROM stuff Limit 1";
					
			/*		
					try(
							PreparedStatement stmt = con.prepareStatement(sqlAnimal);
							 ResultSet rs = stmt.executeQuery()){
						while(rs.next()) {
						
							logData.add(rs.getString("idanimal"));
				            logData.add(rs.getString("name"));
				            logData.add(rs.getString("type"));
						
					}
						
						
						
					};
					
					*/
					
					  try (
							  
							  PreparedStatement stmt = con.prepareStatement(sqlStuff);
						         ResultSet rs = stmt.executeQuery()) {

						        while (rs.next()) {
						            logData.add(rs.getString("Name"));
						            logData.add(rs.getString("Role"));
						        }
						    }
						
						
						return logData.toArray(new String[0]);
						
						};
						
					
				
				
			
						//This one is for 2 combo box cascading 
						
						//This one load Cat and it's item
						private Map<String, List<String>> LoadCatbox() {

								String sql = "SELECT product_name, category From animalfoodinventory WHERE STR_TO_DATE(exp_date, '%d-%m-%Y') >=CURDATE();";
								Map<String,List<String>> catnitem = new HashMap<>();
							
							
								try(PreparedStatement stmt = con.prepareStatement(sql);
										ResultSet rs = stmt.executeQuery();
										){
									
									
										while(rs.next()) {
											
											String Cat = rs.getString("category");
											String Item = rs.getString("product_name");
											
											catnitem.putIfAbsent(Cat, new ArrayList<>());
											catnitem.get(Cat).add(Item);
										
											
							            }
											
										
											
										
								
								}catch(SQLException ex ) {
										ex.printStackTrace();
										
									
								};
								return catnitem;
							
							
	
							
						}
						
						
						private Map<String, List<String>> LoadCatboxMed() {

							String sql = "SELECT product_name, category From medicalinventory WHERE STR_TO_DATE(exp_date, '%d-%m-%Y') >=CURDATE();";
							Map<String,List<String>> catnitem = new HashMap<>();
						
						
							try(PreparedStatement stmt = con.prepareStatement(sql);
									ResultSet rs = stmt.executeQuery();
									){
								
								
									while(rs.next()) {
										
										String Cat = rs.getString("category");
										String Item = rs.getString("product_name");
										
										catnitem.putIfAbsent(Cat, new ArrayList<>());
										catnitem.get(Cat).add(Item);
									
										
						            }
										
									
										
									
							
							}catch(SQLException ex ) {
									ex.printStackTrace();
									
								
							};
							return catnitem;
						
						

						
					}
					
						
						

					    // Update item combo when category changes
					    private void changeitem(String category , JComboBox JC, Map<String,List<String>> st ) {
					        JC.removeAllItems();
					        
					        
					        if (category != null && st.containsKey(category)) {
					            for (String item : st.get(category)) {
					                JC.addItem(item);
					            }
					        }
					    }
						
						
						//transitition methode nutrition check insert and update
						private void transition(int qty,String aniID,String item_id) {
							
							
							//Check inventory
							String sqlCheck ="SELECT stock FROM animalfoodinventory WHERE product_id = ?";
							//Insert into tran table
							String sqlTran ="INSERT INTO transition (animal_id, product_id, qty) VALUES (?, ?, ?)";
							//Update Inventory
							String sqlInv = "UPDATE animalfoodinventory SET stock = stock - ? WHERE product_id = ?";
							
							
							try {
								
								 PreparedStatement checkStmt = con.prepareStatement(sqlCheck);
									    checkStmt.setString(1, item_id);
									    ResultSet rs = checkStmt.executeQuery();

									    if (rs.next()) {
									        int currentQty = rs.getInt("stock");
									        if (currentQty >= qty ) {
									        	
									        	
									            // 2. Insert to tran tabel
									            PreparedStatement insertStmt = con.prepareStatement(sqlTran);
									            
	 
									            insertStmt.setString(1, aniID);
									            insertStmt.setString(2, item_id);
									            insertStmt.setInt(3, qty);
									            insertStmt.executeUpdate();

									            
									            
									            
									            
									            
									            // 3. Update inventory
									            PreparedStatement updateStmt = con.prepareStatement(sqlInv);
									            updateStmt.setInt(1, qty);
									            updateStmt.setString(2, item_id);
									            updateStmt.executeUpdate();
									            
									            
									            

									            JOptionPane.showMessageDialog(null, "Feeding recorded successfully!");
									        } else {
									            JOptionPane.showMessageDialog(null, "Not enough stock to feed.");
									        }
									    } else {
									        JOptionPane.showMessageDialog(null, "Item not found in inventory.");
									    }

	    
									    
									    

								
							}catch(SQLException w) {
								
								w.printStackTrace();
								
								
							}
							
							
							
									
							
							
						}
						
						
						//Med transition
							private void transitionMed(int qty,String aniID,String item_id) {
							
							
							//Check inventory
							String sqlCheck ="SELECT stock FROM medicalinventory WHERE product_id = ?";
							//Insert into tran table
							String sqlTran ="INSERT INTO medtransition (animal_id, product_id, qty) VALUES (?, ?, ?)";
							//Update Inventory
							String sqlInv = "UPDATE medicalinventory SET stock = stock - ? WHERE product_id = ?";
							
							
							try {
								
								 PreparedStatement checkStmt = con.prepareStatement(sqlCheck);
									    checkStmt.setString(1, item_id);
									    ResultSet rs = checkStmt.executeQuery();

									    if (rs.next()) {
									        int currentQty = rs.getInt("stock");
									        if (currentQty >= qty ) {
									        	
									        	
									            // 2. Insert to tran tabel
									            PreparedStatement insertStmt = con.prepareStatement(sqlTran);
									            
	 
									            insertStmt.setString(1, aniID);
									            insertStmt.setString(2, item_id);
									            insertStmt.setInt(3, qty);
									            insertStmt.executeUpdate();

									            
									            
									            
									            
									            
									            // 3. Update inventory
									            PreparedStatement updateStmt = con.prepareStatement(sqlInv);
									            updateStmt.setInt(1, qty);
									            updateStmt.setString(2, item_id);
									            updateStmt.executeUpdate();
									            
									            
									            

									            JOptionPane.showMessageDialog(null, "Medical recorded successfully!");
									        } else {
									            JOptionPane.showMessageDialog(null, "Not enough stock!.");
									        }
									    } else {
									        JOptionPane.showMessageDialog(null, "Item not found in inventory.");
									    }

	    
									    
									    

								
							}catch(SQLException w) {
								
								w.printStackTrace();
								
								
							}
							
							
							
									
							
							
						}
						
						private int RefreshStock(String stockID) {
							
							int qty = 0 ;
							String sql = "SELECT stock FROM animalfoodinventory WHERE product_id = ?";
							 try (
							         PreparedStatement stmt = con.prepareStatement(sql)) {
							        stmt.setString(1, stockID);
							        ResultSet rs = stmt.executeQuery();
							        if (rs.next()) {
							            qty = rs.getInt("stock");
							        }
							    } catch (SQLException e) {
							        e.printStackTrace();
							    }


							
							
							
							return qty;
							
						};
						
						private int RefreshMedStock(String stockID) {
							
							int qty = 0 ;
							String sql = "SELECT stock FROM medicalinventory WHERE product_id = ?";
							 try (
							         PreparedStatement stmt = con.prepareStatement(sql)) {
							        stmt.setString(1, stockID);
							        ResultSet rs = stmt.executeQuery();
							        if (rs.next()) {
							            qty = rs.getInt("stock");
							        }
							    } catch (SQLException e) {
							        e.printStackTrace();
							    }


							
							
							
							return qty;
							
						};
			
		
	}
	
	private static JPanel cardCreation(String Name,int Age,String ID,String Enclosure,String Species,String Type) {
		
		JPanel card = new JPanel(new BorderLayout());
		//Border
		card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		card.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		
		//Size
		card.setMaximumSize(new Dimension(1000,120));
		
		
		//catch the ID
		card.setName(ID); 
		
		
		/*Grid Laout With 2 role 3 colum */
		JPanel infoPane = new JPanel(new GridLayout(2,3,10,5));
		infoPane.setBackground(new Color(255, 253, 233));
		infoPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		
		/* */
		infoPane.add(new JLabel("Name : "+ Name));
		infoPane.add(new JLabel("Age : "+ Age));
		infoPane.add(new JLabel("ID : "+ ID));
		infoPane.add(new JLabel("Enclosure : "+ Enclosure));
		infoPane.add(new JLabel("Species : "+ Species));
		infoPane.add(new JLabel("Type : "+ Type));
		
		card.add(infoPane, BorderLayout.CENTER);
	
		return card;	
	}	
	// This is for editing page they will help to fill in combo box and Jtext field
	
	
	private void selectComboBoxItem(JComboBox<String> combo, String value) {
	    if (value == null) {
	        combo.setSelectedIndex(-1);
	        return;
	    }
	    value = value.trim();
	    for (int i = 0; i < combo.getItemCount(); i++) {
	        if (combo.getItemAt(i).equalsIgnoreCase(value)) {
	            combo.setSelectedIndex(i);
	            return;
	        }
	    }
	    combo.setSelectedIndex(-1); // No match found
	}
	
	
	private static JPanel logCardCreation(String ID,String Name,String Type,String userName,String Role,String activity ) {
		
		JPanel logcard = new JPanel(new BorderLayout());
		//Border
		logcard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		logcard.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		
		//Size
		logcard.setMaximumSize(new Dimension(1000,120));
		
	
		/*Grid Laout With 2 role 3 colum */
		JPanel infoPane = new JPanel(new GridLayout(2,3,15,5));
		infoPane.setBackground(new Color(255, 253, 233));
		infoPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		
		
		/* */ // 3 role 2 column middle column space
		
		
		infoPane.add(new JLabel(activity+ " : " + ID));
		infoPane.add(new JLabel(" "));
		infoPane.add(new JLabel("Stuff : "+ userName));
		infoPane.add(new JLabel( Type + " : " + Name ));
		infoPane.add(new JLabel(" "));
		infoPane.add(new JLabel("Role : "+ Role));

		logcard.add(infoPane, BorderLayout.CENTER);
		 logcard.setAlignmentX(Component.LEFT_ALIGNMENT); 
	
		return logcard;	
	}
	
}
