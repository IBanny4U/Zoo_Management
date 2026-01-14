package body;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.IllegalComponentStateException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import welcome.SignIn_Form;
import welcome.User;

import javax.swing.JScrollBar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JCheckBox;
import java.awt.Insets;



public class Staff_Management extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable staffRegistration_table;
	private JTable table_2;
	private JTable table_3;
	private JTextField Name_t;
	private JTextField txtRole;
	private JTextField txtAmount;
	private JTable day_shift;
	private JTable night_shift;
	private JTextField roleedit;
	private JTextField salaryedit;
	private JTextField txtDate;
	JLabel lblTopBoxImageChange;

	public static ArrayList <staff_info> staffList = new ArrayList <staff_info> ();
	private JTextField txtDateedit;
	private JTextField nametxt;
	private JTextField StaffNo;
	private JTextField dayshiftadd;
	private JTextField dayshiftdepadd;
	private JTextField dayshiftimeadd;
	private JTextField editDayName;
	private JTextField editDayDep;
	private JTextField editdayTime;
	private JTextField nightName;
	private JTextField nightDep;
	private JTextField nightDuty;
	private JTextField editnightStaff;
	private JTextField editnightDep;
	private JTextField editnihttime;
	private JTextField txtNightshiftEdit;
	private JTextField txtEditNightshiftStaff;
	private JTextField txtAddDayshiftStaff;
	private JTextField txtAddDayshiftStaff_1;
	DefaultTableCellRenderer centerRenderer;
	
    static int totalStaffCount;
    LocalDateTime localDateTime;
    DateTimeFormatter dateTimeFormatter;
    String formattedDateTime; 
    private JTextField textField_Username;
    private JTextField textField_Password;
    private JTextField txtUsername_editf;
    private JTextField txtPassword_passwordf;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff_Management frame = new Staff_Management();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Staff_Management() {
		
		Staffdbconnection db = new Staffdbconnection ();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Staff_Management.class.getResource("/icon/zoo_logo_nobg.png")));
		setForeground(new Color(0, 50, 35));
		setBackground(new Color(0, 50, 35));
		setTitle("Zoo Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 992, 654);
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
		dashboardIcon.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/dashboardSidebarIcon_bgGreen.png")));
		dashboardIcon.setBackground(new Color(255, 100, 0));
		dashboardIcon.setBounds(-2, 2, 80, 32);
		dashboardIcon.setForeground(new Color(255, 100, 0));
		dashboardMenu.add(dashboardIcon);
		
		JLabel lblMenuDashboard = new JLabel("Dashboard");
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
		animalMenu.setBackground(new Color(0, 50, 35));
		animalMenu.setBounds(5, 148, 80, 56);
		siderBar.add(animalMenu);
		
		JLabel animalIcon = new JLabel("");
		animalIcon.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/animalSidebarIcon_bgGreen.png")));
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
		lblMenuAnimal.setForeground(new Color(255, 253, 233));
		lblMenuAnimal.setFont(new Font("Arial", Font.BOLD, 12));
		lblMenuAnimal.setBounds(17, 35, 60, 16);
		animalMenu.add(lblMenuAnimal);
		
		JPanel staffMenu = new JPanel();
		staffMenu.setBorder(null);
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
		staffMenu.setBackground(new Color(255, 253, 233));
		staffMenu.setBounds(5, 220, 80, 56);
		siderBar.add(staffMenu);
		
		JLabel staffIcon = new JLabel("");
		staffIcon.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/staffSidebaricon.png")));
		staffIcon.setForeground(new Color(255, 100, 0));
		staffIcon.setBounds(-2, 2, 80, 32);
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
		staffMenu.add(staffIcon);
		
		JLabel lblMenuStaff = new JLabel("Staff");
		lblMenuStaff.setForeground(new Color(0, 50, 35));
		lblMenuStaff.setFont(new Font("Arial", Font.BOLD, 12));
		lblMenuStaff.setBounds(22, 35, 52, 16);
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
		visitorIcon.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/visitorSidebarIcon_bgGreen.png")));
		visitorIcon.setForeground(new Color(255, 100, 0));
		visitorIcon.setBounds(-2, 2, 80, 32);
		visitorMenu.add(visitorIcon);
		
		JLabel lblMenuVisitor = new JLabel("Visitor");
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
		inventoryIcon.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/inventorySidebarIcon_bgGreen.png")));
		inventoryIcon.setForeground(new Color(255, 100, 0));
		inventoryIcon.setBounds(-2, 2, 80, 32);
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
		inventoryMenu.add(inventoryIcon);
		
		JLabel lblMenuInventory = new JLabel("Inventory");
		lblMenuInventory.setForeground(new Color(255, 253, 233));
		lblMenuInventory.setFont(new Font("Arial", Font.BOLD, 12));
		lblMenuInventory.setBounds(13, 35, 67, 16);
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
		inventoryMenu.add(lblMenuInventory);
		
		JPanel logo = new JPanel();
		logo.setLayout(null);
		logo.setBackground(new Color(0, 50, 35));
		logo.setBounds(0, 0, 85, 56);
		siderBar.add(logo);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("");
		lblNewLabel_1_1_1_2.setPreferredSize(new Dimension(15, 15));
		lblNewLabel_1_1_1_2.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/resized_GreenBg_ZooLogo2.png")));
		lblNewLabel_1_1_1_2.setForeground(new Color(255, 100, 0));
		lblNewLabel_1_1_1_2.setBorder(null);
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
		headerLeftPanel.setForeground(new Color(12, 59, 46));
		headerLeftPanel.setPreferredSize(new Dimension(500, 10));
		headerLeftPanel.setBackground(new Color(12, 59, 46));
		header.add(headerLeftPanel, BorderLayout.WEST);
		headerLeftPanel.setLayout(null);
		
		JLabel lblPageNameDashboard = new JLabel("Staff Management");
		lblPageNameDashboard.setForeground(new Color(206, 241, 123));
		lblPageNameDashboard.setBackground(new Color(255, 100, 0));
		lblPageNameDashboard.setBounds(20, 9, 405, 36);
		lblPageNameDashboard.setName(" ");
		lblPageNameDashboard.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 30));
		headerLeftPanel.add(lblPageNameDashboard);
		
		JPanel headerRightPanel = new JPanel();
		headerRightPanel.setLayout(null);
		headerRightPanel.setPreferredSize(new Dimension(200, 10));
		headerRightPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(108, 108, 108)));
		headerRightPanel.setBackground(new Color(12, 59, 46));
		header.add(headerRightPanel, BorderLayout.EAST);
		
		JLabel lblHeaderRole = new JLabel("Admin");
		lblHeaderRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderRole.setForeground(new Color(206, 241, 123));
		lblHeaderRole.setFont(new Font("Arial", Font.BOLD, 12));
		lblHeaderRole.setBounds(26, 31, 96, 14);
		headerRightPanel.add(lblHeaderRole);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/userProfileIconGreenGreen.png")));
		lblNewLabel_2.setForeground(new Color(255, 100, 0));
		lblNewLabel_2.setBackground(new Color(255, 100, 0));
		lblNewLabel_2.setBounds(132, 6, 40, 44);
		headerRightPanel.add(lblNewLabel_2);
		
		JLabel lblHeaderUsername = new JLabel("Username");
		lblHeaderUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderUsername.setForeground(new Color(255, 253, 233));
		lblHeaderUsername.setFont(new Font("Arial", Font.BOLD, 16));
		lblHeaderUsername.setBounds(26, 14, 96, 14);
		headerRightPanel.add(lblHeaderUsername);
		
		lblHeaderUsername.setText(SignIn_Form.signInAccUsername);
		lblHeaderRole.setText(SignIn_Form.signInAccRole);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(108, 108, 108)));
		panel_7.setBackground(new Color(12, 59, 46));
		header.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(108, 108, 108)));
		panel_8.setBackground(new Color(12, 59, 46));
		panel_8.setPreferredSize(new Dimension(150, 10));
		panel_7.add(panel_8, BorderLayout.EAST);
		panel_8.setLayout(null);
		
		JLabel lblToday = new JLabel("Today ");
		lblToday.setBounds(25, 12, 69, 14);
		lblToday.setPreferredSize(new Dimension(42, 20));
		lblToday.setForeground(new Color(255, 253, 233));
		lblToday.setFont(new Font("Arial", Font.BOLD, 12));
		panel_8.add(lblToday);
		
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
		lblHeaderLocalDateTime.setBounds(25, 28, 85, 16);
		panel_8.add(lblHeaderLocalDateTime);
		
		JPanel panel = new JPanel();
		body.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel StaffMangePanel = new JPanel();
		panel.add(StaffMangePanel, BorderLayout.CENTER);
		StaffMangePanel.setLayout(new CardLayout(0, 0));
		
		JPanel registration = new JPanel();
		registration.setBackground(new Color(0, 64, 0));
		StaffMangePanel.add(registration, "name_681664299631400");
		registration.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		registration.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		panel_6.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 253, 233));
		panel_3.setPreferredSize(new Dimension(10, 40));
		mainPanel.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		mainPanel.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 253, 233));
		panel_5.add(scrollPane, BorderLayout.CENTER);
		
		
		db.getDataLength();
		

		staffRegistration_table = new JTable();
		staffRegistration_table.setGridColor(new Color(226, 226, 226));
		staffRegistration_table.setFont(new Font("Arial", Font.PLAIN, 14));
		staffRegistration_table.setRowSelectionAllowed(false);
		staffRegistration_table.setBackground(new Color(255, 255, 255));
		staffRegistration_table.setEnabled(false);
		staffRegistration_table.setRowHeight(40);
		staffRegistration_table.setModel(new DefaultTableModel(
				new Object[][] {
					
					
				},
			new String[] {
				"NO", "Name", "Employed Date", "Status", "Department", "Role", "Monthly Salary" ,"Username", "Password"
			}
		));
		
		db.StaffTable(staffRegistration_table);
		staffRegistration_table.getColumnModel().getColumn(0).setPreferredWidth(15);
		staffRegistration_table.getColumnModel().getColumn(1).setPreferredWidth(70);
		staffRegistration_table.getColumnModel().getColumn(2).setPreferredWidth(90);
		staffRegistration_table.getColumnModel().getColumn(3).setPreferredWidth(60);
		staffRegistration_table.getColumnModel().getColumn(4).setPreferredWidth(90);
		staffRegistration_table.getColumnModel().getColumn(5).setPreferredWidth(70);
		staffRegistration_table.getColumnModel().getColumn(6).setPreferredWidth(70);
		staffRegistration_table.getColumnModel().getColumn(7).setPreferredWidth(50);
		staffRegistration_table.getColumnModel().getColumn(8).setPreferredWidth(90);
		scrollPane.setViewportView(staffRegistration_table);
		
		centerRenderer=new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        staffRegistration_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        staffRegistration_table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        staffRegistration_table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        staffRegistration_table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        staffRegistration_table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        staffRegistration_table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        staffRegistration_table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        staffRegistration_table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        staffRegistration_table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        staffRegistration_table.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
		
		JPanel regpanel = new JPanel();
		regpanel.setBackground(new Color(255, 100, 0));
		regpanel.setPreferredSize(new Dimension(350, 10));
		panel_5.add(regpanel, BorderLayout.EAST);
		regpanel.setLayout(new BorderLayout(0, 0));
		
		JPanel form = new JPanel();
		regpanel.add(form, BorderLayout.CENTER);
		form.setLayout(new CardLayout(0, 0));
		
		JPanel normal = new JPanel();
		normal.setBackground(new Color(0, 50, 35));
		form.add(normal, "name_57698698173500");
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/Welcome.png")));
		normal.add(lblNewLabel_7);
		
		JPanel rgform = new JPanel();
		rgform.setBackground(new Color(0, 50, 35));
		form.add(rgform, "name_58022492149500");
		rgform.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Registration Form");
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_3.setForeground(new Color(255, 253, 233));
		lblNewLabel_3.setBounds(26, 11, 210, 25);
		rgform.add(lblNewLabel_3);
		
		Name_t = new JTextField();
		Name_t.setFont(new Font("Arial", Font.PLAIN, 14));
		Name_t.setBackground(new Color(255, 253, 233));
		Name_t.setBounds(26, 62, 150, 29);
		rgform.add(Name_t);
		Name_t.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Employed Date");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setForeground(new Color(255, 253, 233));
		lblNewLabel_4.setBounds(26, 107, 113, 14);
		rgform.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Staff Name");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_1.setBounds(26, 47, 113, 14);
		rgform.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Department");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_2.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_2.setBounds(26, 167, 113, 14);
		rgform.add(lblNewLabel_4_2);
		
		JComboBox departmentRG = new JComboBox();
		departmentRG.setFont(new Font("Arial", Font.PLAIN, 14));
		departmentRG.setBackground(new Color(255, 253, 233));
		departmentRG.setModel(new DefaultComboBoxModel(new String[] {"Select Department", "Animal Management", "Inventory Management", "Visitor Management", "Health Department"}));
		departmentRG.setBounds(26, 181, 172, 29);
		rgform.add(departmentRG);
		
		JComboBox statusRG = new JComboBox();
		statusRG.setFont(new Font("Arial", Font.PLAIN, 14));
		statusRG.setBackground(new Color(255, 253, 233));
		statusRG.setModel(new DefaultComboBoxModel(new String[] {"Select", "Active", "Resigned"}));
		statusRG.setBounds(225, 61, 100, 29);
		rgform.add(statusRG);
		
		JLabel lblNewLabel_4_3 = new JLabel("Status");
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_3.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_3.setBounds(225, 47, 71, 14);
		rgform.add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_4_4 = new JLabel("Role");
		lblNewLabel_4_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_4.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_4.setBounds(26, 227, 113, 14);
		rgform.add(lblNewLabel_4_4);
		
		txtRole = new JTextField();
		txtRole.setFont(new Font("Arial", Font.PLAIN, 14));
		txtRole.setBounds(26, 242, 150, 29);
		rgform.add(txtRole);
		txtRole.setColumns(10);
		JComboBox<String> editSearchBox = new JComboBox<>();
		editSearchBox.setFont(new Font("Arial", Font.PLAIN, 14));
		editSearchBox.setBackground(new Color(255, 253, 233));
		JButton DoneBtn = new JButton("Done");
		DoneBtn.setFocusable(false);
		DoneBtn.setForeground(new Color(12, 59, 46));
		DoneBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		DoneBtn.setBorder(null);
		JComboBox<String> SearchStaffDay = new JComboBox<String>();
		SearchStaffDay.setFont(new Font("Arial", Font.PLAIN, 14));
		JComboBox<String> SearchStaffNight = new JComboBox<String>();
		SearchStaffNight.setFont(new Font("Arial", Font.PLAIN, 14));
		
		DoneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String name = Name_t.getText();
				String status = (String) statusRG.getSelectedItem();
				String department = (String) departmentRG.getSelectedItem();
				String employedDate = txtDate.getText();
				String role = txtRole.getText();
				String salary = txtAmount.getText();
				String username =textField_Username.getText();
				String password =textField_Password.getText();
				  if (name.isEmpty() || employedDate.isEmpty() || role.isEmpty() || salary.isEmpty() || username.isEmpty() || password.isEmpty()) {
					  
			            JOptionPane.showMessageDialog(null, "All Detail Must Be Exactly Fill in the Form!!" , "Registration Form", JOptionPane.INFORMATION_MESSAGE);
			        } else {
			        	
			        	
			        	
			        	db.insertStaff(name,employedDate,status,department,role,salary,username,password);
						db.reloadStaffTable();
						db.reloadSearchList(editSearchBox);
						db.reloadSearchList(SearchStaffDay);
						db.reloadSearchList(SearchStaffNight);
						
			        }


				

				
			  
			}
		});
		
		
		
		
		
		
		
		
		
		DoneBtn.setBackground(new Color(206, 241, 123));
		DoneBtn.setBounds(251, 450, 89, 38);
		rgform.add(DoneBtn);
		
		JLabel lblNewLabel_4_5 = new JLabel("Salary");
		lblNewLabel_4_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_5.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_5.setBounds(26, 292, 113, 14);
		rgform.add(lblNewLabel_4_5);
		
		txtAmount = new JTextField();
		txtAmount.setFont(new Font("Arial", Font.PLAIN, 14));
		txtAmount.setBounds(26, 306, 150, 29);
		rgform.add(txtAmount);
		txtAmount.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDate.setColumns(10);
		txtDate.setBackground(new Color(255, 253, 233));
		txtDate.setBounds(26, 123, 150, 29);
		rgform.add(txtDate);
		
		JLabel lblNewLabel_4_5_2 = new JLabel("Username");
		lblNewLabel_4_5_2.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_5_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_5_2.setBounds(26, 356, 113, 14);
		rgform.add(lblNewLabel_4_5_2);
		
		textField_Username = new JTextField();
		textField_Username.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_Username.setColumns(10);
		textField_Username.setBounds(26, 370, 150, 29);
		rgform.add(textField_Username);
		
		JLabel lblNewLabel_4_5_2_1 = new JLabel("Password");
		lblNewLabel_4_5_2_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_5_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_5_2_1.setBounds(216, 356, 113, 14);
		rgform.add(lblNewLabel_4_5_2_1);
		
		textField_Password = new JTextField();
		textField_Password.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_Password.setColumns(10);
		textField_Password.setBounds(216, 370, 110, 29);
		rgform.add(textField_Password);
		
		JPanel Editform = new JPanel();
		Editform.setLayout(null);
		Editform.setBackground(new Color(45, 100, 43));
		form.add(Editform, "name_210478443051300");
		
		JLabel lblNewLabel_3_1 = new JLabel("Editing");
		lblNewLabel_3_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_3_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(26, 11, 101, 25);
		Editform.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_6 = new JLabel("Employed Date");
		lblNewLabel_4_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_6.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_6.setBounds(26, 107, 113, 14);
		Editform.add(lblNewLabel_4_6);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Staff Name");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_1_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_1_1.setBounds(26, 47, 113, 14);
		Editform.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Department");
		lblNewLabel_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_2_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_2_1.setBounds(26, 167, 113, 14);
		Editform.add(lblNewLabel_4_2_1);
		
		
		
		
		JComboBox dpedit = new JComboBox();
		dpedit.setFont(new Font("Arial", Font.PLAIN, 14));
		dpedit.setBackground(new Color(255, 253, 233));
		dpedit.setModel(new DefaultComboBoxModel(new String[] {"Select Department", "Animal Management", "Inventory Management", "Visitor Management", "Health Department"}));
		dpedit.setBounds(26, 181, 150, 29);
		Editform.add(dpedit);
		
		JComboBox editstatus = new JComboBox();
		editstatus.setFont(new Font("Arial", Font.PLAIN, 14));
		editstatus.setBackground(new Color(255, 253, 233));
		editstatus.setModel(new DefaultComboBoxModel(new String[] {"Active", "Resigned"}));
		editstatus.setBounds(207, 129, 133, 29);
		Editform.add(editstatus);
		
		JLabel lblNewLabel_4_3_1 = new JLabel("Status");
		lblNewLabel_4_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_3_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_3_1.setBounds(207, 116, 71, 14);
		Editform.add(lblNewLabel_4_3_1);
		
		JLabel lblNewLabel_4_4_1 = new JLabel("Role");
		lblNewLabel_4_4_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_4_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_4_1.setBounds(26, 227, 113, 14);
		Editform.add(lblNewLabel_4_4_1);
		
		roleedit = new JTextField();
		roleedit.setFont(new Font("Arial", Font.PLAIN, 14));
		roleedit.setBackground(new Color(255, 253, 233));
		roleedit.setText("Role");
		roleedit.setColumns(10);
		roleedit.setBounds(26, 242, 150, 29);
		Editform.add(roleedit);
		
		JButton btnNewButton_10_1 = new JButton("Confirm");
		btnNewButton_10_1.setFocusable(false);
		btnNewButton_10_1.setForeground(new Color(255, 253, 233));
		btnNewButton_10_1.setBorder(null);
		btnNewButton_10_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		
		btnNewButton_10_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int ID =   Integer.parseInt(StaffNo.getText());
				String name = nametxt.getText();
				String Date = txtDateedit.getText();
				String depart = (String) dpedit.getSelectedItem();
				String role = roleedit.getText();
				String saly = salaryedit.getText();
				String stus = (String) editstatus.getSelectedItem();
				String usrne = txtUsername_editf.getText();
				String pswd = txtPassword_passwordf.getText();
		
			 db.updateStaff(name,Date, stus,depart,role,saly,usrne,pswd,ID);
	
			 db.reloadStaffTable();// This is reload Jtabel
			 db.reloadSearchList(editSearchBox);
				
			}
		});
		btnNewButton_10_1.setBackground(new Color(255, 100, 0));
		btnNewButton_10_1.setBounds(137, 450, 89, 38);
		Editform.add(btnNewButton_10_1);
		
		JLabel lblNewLabel_4_5_1 = new JLabel("Salary");
		lblNewLabel_4_5_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_5_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_5_1.setBounds(26, 293, 113, 14);
		Editform.add(lblNewLabel_4_5_1);
		
		salaryedit = new JTextField();
		salaryedit.setFont(new Font("Arial", Font.PLAIN, 14));
		salaryedit.setBackground(new Color(255, 253, 233));
		salaryedit.setText("amount");
		salaryedit.setColumns(10);
		salaryedit.setBounds(26, 307, 150, 29);
		Editform.add(salaryedit);
		
		JButton btnNewButton_10_1_1 = new JButton("Cancel");
		btnNewButton_10_1_1.setFocusable(false);
		btnNewButton_10_1_1.setForeground(new Color(12, 59, 46));
		btnNewButton_10_1_1.setBorder(null);
		btnNewButton_10_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnNewButton_10_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form.removeAll();
				form.add(normal);
				form.repaint();
				panel.revalidate();
			}
		});
		btnNewButton_10_1_1.setBackground(new Color(255, 253, 233));
		btnNewButton_10_1_1.setBounds(26, 450, 89, 38);
		Editform.add(btnNewButton_10_1_1);
				
		
		
		
	
		editSearchBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					String name = (String) editSearchBox.getSelectedItem();
					ResultSet rs = db.getStaffDetailsByName(name);
					
					
					 if (rs != null) {
				            try {
				                if (rs.next()) {
				                	nametxt.setText(rs.getString("Name"));
				                    txtDateedit.setText(rs.getString("EmployedDate"));
				                    roleedit.setText(rs.getString("Role"));
				                    salaryedit.setText(rs.getString("Salary"));
				                    dpedit.setSelectedItem(rs.getString("Department"));
				                    editstatus.setSelectedItem(rs.getString("Status"));
				                    StaffNo.setText(rs.getString("NO"));
				                    txtUsername_editf.setText(rs.getString("Username"));
				                    txtPassword_passwordf.setText(rs.getString("Acc_Password"));
				                }
				                rs.close(); 
				            } catch (SQLException ex) {
				                ex.printStackTrace();
				                JOptionPane.showMessageDialog(null, 
				                    "Error reading data: " + ex.getMessage(),
				                    "Error", 
				                    JOptionPane.ERROR_MESSAGE);
				            }
				        } else {
				            JOptionPane.showMessageDialog(null, 
				                "Failed to load data for: " + name,
				                "Error", 
				                JOptionPane.ERROR_MESSAGE);
				        }
					 
					
	
				
			}
		});
		editSearchBox.setBounds(207, 16, 133, 29);
		Editform.add(editSearchBox);

		// Deb load and auto show
		List<String> allStaff = db.loadStaffFromDB(); 

		db.setupAutoComplete(editSearchBox, allStaff);
		
		txtDateedit = new JTextField();
		txtDateedit.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDateedit.setBackground(new Color(255, 253, 233));
		txtDateedit.setText("Date");
		txtDateedit.setColumns(10);
		txtDateedit.setBounds(26, 123, 150, 29);
		Editform.add(txtDateedit);
		
		nametxt = new JTextField();
		nametxt.setFont(new Font("Arial", Font.PLAIN, 14));
		nametxt.setFocusable(false);
		nametxt.setBackground(new Color(255, 253, 233));
		nametxt.setText("Role");
		nametxt.setColumns(10);
		nametxt.setBounds(26, 62, 150, 29);
		Editform.add(nametxt);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Staff No");
		lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_1_1_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_1_1_1.setBounds(207, 57, 113, 14);
		Editform.add(lblNewLabel_4_1_1_1);
		
		StaffNo = new JTextField();
		StaffNo.setFont(new Font("Arial", Font.PLAIN, 14));
		StaffNo.setBackground(new Color(255, 253, 233));
		StaffNo.setEditable(false);
		StaffNo.setText("Staff Number");
		StaffNo.setColumns(10);
		StaffNo.setBounds(207, 71, 133, 29);
		Editform.add(StaffNo);
		
		JButton Deletebtn = new JButton("Delete");
		Deletebtn.setFocusable(false);
		Deletebtn.setForeground(new Color(255, 253, 233));
		Deletebtn.setBorder(null);
		Deletebtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		Deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			int ID = Integer.parseInt(StaffNo.getText());
				
			db.deleteAnimal(ID);
			
			 db.reloadStaffTable();// This is reload Jtabel
			 db.reloadSearchList(editSearchBox);	
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		Deletebtn.setBounds(241, 450, 99, 38);
		Editform.add(Deletebtn);
		Deletebtn.setBackground(new Color(255, 0, 0));
		
		JLabel lblNewLabel_4_5_1_1 = new JLabel("Username");
		lblNewLabel_4_5_1_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_5_1_1.setBounds(26, 356, 113, 14);
		Editform.add(lblNewLabel_4_5_1_1);
		
		txtUsername_editf = new JTextField();
		txtUsername_editf.setText("username");
		txtUsername_editf.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUsername_editf.setColumns(10);
		txtUsername_editf.setBackground(new Color(255, 253, 233));
		txtUsername_editf.setBounds(26, 370, 150, 29);
		Editform.add(txtUsername_editf);
		
		JLabel lblNewLabel_4_5_1_1_1 = new JLabel("Password");
		lblNewLabel_4_5_1_1_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_4_5_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_5_1_1_1.setBounds(216, 356, 113, 14);
		Editform.add(lblNewLabel_4_5_1_1_1);
		
		txtPassword_passwordf = new JTextField();
		txtPassword_passwordf.setText("password");
		txtPassword_passwordf.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPassword_passwordf.setColumns(10);
		txtPassword_passwordf.setBackground(new Color(255, 253, 233));
		txtPassword_passwordf.setBounds(216, 370, 110, 29);
		Editform.add(txtPassword_passwordf);
		
		JPanel btn = new JPanel();
		btn.setBackground(new Color(255, 253, 233));
		btn.setPreferredSize(new Dimension(10, 50));
		regpanel.add(btn, BorderLayout.NORTH);
		btn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton addnewbtn = new JButton("+  Add New");
		addnewbtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		addnewbtn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		addnewbtn.setFocusable(false);
		addnewbtn.setPreferredSize(new Dimension(115, 40));
		addnewbtn.setForeground(new Color(255, 253, 233));
		addnewbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form.removeAll();
				form.add(rgform);
				form.repaint();
				panel.revalidate();
			}
		});
		addnewbtn.setBackground(new Color(12, 59, 46));
		btn.add(addnewbtn);
		
		JButton editBtn = new JButton("Edit / Delete ");
		editBtn.setMinimumSize(new Dimension(112, 23));
		editBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		editBtn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		editBtn.setFocusable(false);
		editBtn.setPreferredSize(new Dimension(115, 40));
		editBtn.setForeground(new Color(255, 253, 233));
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form.removeAll();
				form.add(Editform);
				form.repaint();
				panel.revalidate();
			}
		});
		editBtn.setBackground(new Color(255, 100, 0));
		btn.add(editBtn);
		
		JPanel schedule = new JPanel();
		schedule.setBackground(new Color(0, 0, 0));
		StaffMangePanel.add(schedule, "name_681744767829600");
		schedule.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(700, 10));
		schedule.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(255, 253, 233));
		panel_4.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_18 = new JPanel();
		panel_11.add(panel_18, BorderLayout.CENTER);
		panel_18.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_31 = new JPanel();
		panel_31.setPreferredSize(new Dimension(450, 10));
		panel_18.add(panel_31, BorderLayout.EAST);
		panel_31.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_31.add(scrollPane_2, BorderLayout.CENTER);
		
		night_shift = new JTable();
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
		db.NightShiftAdd(night_shift);
		scrollPane_2.setViewportView(night_shift);
		
		JPanel panel_29 = new JPanel();
		panel_29.setPreferredSize(new Dimension(400, 10));
		panel_18.add(panel_29, BorderLayout.CENTER);
		panel_29.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_29.add(scrollPane_1, BorderLayout.CENTER);
		
		day_shift = new JTable();
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
		
		 db.DayShiftAdd(day_shift);
		scrollPane_1.setViewportView(day_shift);
		
		JPanel panel_37 = new JPanel();
		panel_37.setBackground(new Color(255, 253, 233));
		panel_29.add(panel_37, BorderLayout.WEST);
		
		JPanel shiftpanellb = new JPanel();
		shiftpanellb.setPreferredSize(new Dimension(10, 30));
		panel_18.add(shiftpanellb, BorderLayout.NORTH);
		shiftpanellb.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel datshiftlb = new JPanel();
		datshiftlb.setPreferredSize(new Dimension(100, 10));
		datshiftlb.setBackground(new Color(255, 253, 233));
		shiftpanellb.add(datshiftlb);
		
		JLabel lblNewLabel_5 = new JLabel("Day Shift");
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBackground(new Color(255, 253, 233));
		datshiftlb.add(lblNewLabel_5);
		JPanel nightshiftlb = new JPanel();
		nightshiftlb.setBackground(new Color(255, 253, 233));
		shiftpanellb.add(nightshiftlb);
		
		JLabel lblNewLabel_5_1 = new JLabel("Night Shift");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5_1.setBackground(new Color(255, 253, 233));
		nightshiftlb.add(lblNewLabel_5_1);
		
		JPanel panel_32 = new JPanel();
		panel_32.setBackground(new Color(255, 253, 233));
		panel_32.setPreferredSize(new Dimension(10, 100));
		panel_11.add(panel_32, BorderLayout.NORTH);
		panel_32.setLayout(null);
		
		JPanel shiftmenu = new JPanel();
		JPanel nightshiftreg = new JPanel();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		shiftmenu.setBackground(new Color(255, 0, 128));
		JPanel rightformpanel = new JPanel();
		JPanel dayshiftreg = new JPanel();
		dayshiftreg.setForeground(new Color(255, 253, 233));
		
		JButton btndayS = new JButton("Day Shift");
		btndayS.setForeground(new Color(255, 253, 233));
		btndayS.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btndayS.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(192, 192, 192)));
		btndayS.setFocusable(false);
		btndayS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   
				lblTopBoxImageChange.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/orangebg_koala.png")));
				shiftmenu.removeAll();
				shiftmenu.add(dayshiftreg);
				shiftmenu.repaint();
				shiftmenu.revalidate();
				
				
			}
		});
		btndayS.setBounds(37, 34, 115, 43);
		panel_32.add(btndayS);
		btndayS.setBackground(new Color(255, 100, 0));
		
		JButton btnnightS = new JButton("Night Shift");
		btnnightS.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(192, 192, 192)));
		btnnightS.setForeground(new Color(12, 59, 46));
		btnnightS.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnnightS.setFocusable(false);
		btnnightS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				lblTopBoxImageChange.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/greenbg_koala.png")));
				shiftmenu.removeAll();
				shiftmenu.add(nightshiftreg);
				shiftmenu.repaint();
				shiftmenu.revalidate();
				
				
			}
		});
		
		
		
		
		
		btnnightS.setBounds(317, 34, 129, 42);
		panel_32.add(btnnightS);
		btnnightS.setBackground(new Color(206, 241, 123));
		
		JButton editdayshift = new JButton("Edit");
		editdayshift.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		editdayshift.setForeground(new Color(255, 253, 233));
		editdayshift.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(192, 192, 192)));
		editdayshift.setFocusable(false);
		editdayshift.setBackground(new Color(255, 100, 0));
		editdayshift.setBounds(151, 34, 129, 43);
		panel_32.add(editdayshift);
		JPanel editdayshifpanels = new JPanel();
		JButton editnightshiftedit = new JButton("Edit");
		editnightshiftedit.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(192, 192, 192)));
		editnightshiftedit.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		editnightshiftedit.setForeground(new Color(12, 59, 46));
		editnightshiftedit.setFocusable(false);
		editnightshiftedit.setBackground(new Color(206, 241, 123));
		editnightshiftedit.setBounds(444, 34, 129, 42);
		panel_32.add(editnightshiftedit);
		
		JPanel editnightshifpanels = new JPanel();
		editnightshiftedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				lblTopBoxImageChange.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/greenbg_koala.png")));
				shiftmenu.removeAll();
				shiftmenu.add( editnightshifpanels);
				shiftmenu.repaint();
				shiftmenu.revalidate();
				
				
			}
		});
		editdayshift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTopBoxImageChange.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/orangebg_koala.png")));
				shiftmenu.removeAll();
				shiftmenu.add(editdayshifpanels);
				shiftmenu.repaint();
				shiftmenu.revalidate();
			
				
				
			}
		});
		
		JPanel panel_33 = new JPanel();
		panel_11.add(panel_33, BorderLayout.SOUTH);
		
		
		rightformpanel.setPreferredSize(new Dimension(300, 10));
		schedule.add(rightformpanel, BorderLayout.EAST);
		
		// Deb load and auto show
		List<String> allStaffs = db.loadStaffFromDB();
		rightformpanel.setLayout(new BorderLayout(0, 0));
		
	
		rightformpanel.add(shiftmenu);
		shiftmenu.setLayout(new CardLayout(0, 0));
		
		
		dayshiftreg.setLayout(null);
		dayshiftreg.setPreferredSize(new Dimension(10, 400));
		dayshiftreg.setBackground(new Color(255, 100, 0));
		shiftmenu.add(dayshiftreg, "name_604605548607000");
		
		
		SearchStaffDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = (String) SearchStaffDay.getSelectedItem();
				ResultSet rs = db.getStaffDetailsByName(name);
				
				
				 if (rs != null) {
			            try {
			                if (rs.next()) {
			                	dayshiftadd.setText(rs.getString("Name"));
			                	dayshiftdepadd.setText(rs.getString("Department"));
			           
			                  //  StaffNo.setText(rs.getString("NO"));
			                  
			                }
			                rs.close(); 
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(null, 
			                    "Error reading data: " + ex.getMessage(),
			                    "Error", 
			                    JOptionPane.ERROR_MESSAGE);
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, 
			                "Failed to load data for: " + name,
			                "Error", 
			                JOptionPane.ERROR_MESSAGE);
			        }

				
				
				
				
			}
		});
		
		
		// Deb load and auto show
		List<String> allStaffday = db.loadStaffFromDB(); 
		db.setupAutoComplete(SearchStaffDay, allStaffday);
		
		
		SearchStaffDay.setEditable(true);
		SearchStaffDay.setBounds(116, 57, 174, 32);
		dayshiftreg.add(SearchStaffDay);
		
		JLabel SN_lb = new JLabel("Staff Name");
		SN_lb.setForeground(new Color(255, 253, 233));
		SN_lb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SN_lb.setBounds(10, 90, 117, 32);
		dayshiftreg.add(SN_lb);
		
		dayshiftadd = new JTextField();
		dayshiftadd.setFont(new Font("Arial", Font.PLAIN, 14));
		dayshiftadd.setEditable(false);
		dayshiftadd.setColumns(10);
		dayshiftadd.setBounds(10, 116, 174, 32);
		dayshiftreg.add(dayshiftadd);
		
		JLabel dp_lb = new JLabel("Department");
		dp_lb.setForeground(new Color(255, 253, 233));
		dp_lb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dp_lb.setBounds(10, 168, 117, 32);
		dayshiftreg.add(dp_lb);
		
		dayshiftdepadd = new JTextField();
		dayshiftdepadd.setFont(new Font("Arial", Font.PLAIN, 14));
		dayshiftdepadd.setEditable(false);
		dayshiftdepadd.setColumns(10);
		dayshiftdepadd.setBounds(10, 195, 174, 32);
		dayshiftreg.add(dayshiftdepadd);
		
		JLabel Time = new JLabel("Time");
		Time.setForeground(new Color(255, 253, 233));
		Time.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Time.setBounds(10, 247, 117, 32);
		dayshiftreg.add(Time);
		
		dayshiftimeadd = new JTextField();
		dayshiftimeadd.setFont(new Font("Arial", Font.PLAIN, 14));
		dayshiftimeadd.setColumns(10);
		dayshiftimeadd.setBounds(10, 274, 174, 32);
		dayshiftreg.add(dayshiftimeadd);
		
		JButton Addbtn = new JButton("+  Add");
		Addbtn.setForeground(new Color(12, 59, 46));
		Addbtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		Addbtn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(12, 59, 46)));
		Addbtn.setBackground(new Color(206, 241, 123));
		Addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String DayShiftName = dayshiftadd.getText();
				String DayShiftDep = dayshiftdepadd.getText();
				String DayShiftTime = dayshiftimeadd.getText();
				
				if(DayShiftName.isEmpty() || DayShiftDep.isEmpty() || DayShiftTime.isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "All Blank Must Be Fill");
					
				}else {
				 db.addDayshift(DayShiftName,DayShiftDep ,DayShiftTime);
				 db.reloadDayTable();
				
				}
				
					
				
			}
		});
		Addbtn.setBounds(180, 356, 110, 38);
		dayshiftreg.add(Addbtn);
		
		txtAddDayshiftStaff_1 = new JTextField();
		txtAddDayshiftStaff_1.setForeground(new Color(255, 253, 233));
		txtAddDayshiftStaff_1.setBorder(null);
		txtAddDayshiftStaff_1.setBackground(new Color(255, 100, 0));
		txtAddDayshiftStaff_1.setDisabledTextColor(new Color(0, 0, 0));
		txtAddDayshiftStaff_1.setText("Add Dayshift Staff");
		txtAddDayshiftStaff_1.setSelectionColor(Color.BLACK);
		txtAddDayshiftStaff_1.setSelectedTextColor(new Color(255, 100, 0));
		txtAddDayshiftStaff_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddDayshiftStaff_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		txtAddDayshiftStaff_1.setEnabled(false);
		txtAddDayshiftStaff_1.setEditable(false);
		txtAddDayshiftStaff_1.setColumns(10);
		txtAddDayshiftStaff_1.setBounds(51, 11, 208, 28);
		dayshiftreg.add(txtAddDayshiftStaff_1);
		
		
		
		
	
		editdayshifpanels.setLayout(null);
		editdayshifpanels.setPreferredSize(new Dimension(10, 400));
		editdayshifpanels.setBackground(new Color(255, 100, 0));
		shiftmenu.add(editdayshifpanels, "name_605065869976300");
		JComboBox timebox = new JComboBox();
		timebox.setFont(new Font("Arial", Font.PLAIN, 14));
		JComboBox<String> SearchStaffDay_2 = new JComboBox<String>();
		SearchStaffDay_2.setFont(new Font("Arial", Font.PLAIN, 14));
		timebox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//		getTimeByData(String time,String name)
				
						String name = (String) SearchStaffDay_2.getSelectedItem();
						String time = (String) timebox.getSelectedItem();
						
						
				        if (name!= null|| time!= null ) {
				            try {
				                ResultSet rs = db.getTimeByData(time,name);
				                if (rs != null && rs.next()) {
				                	editDayName.setText(rs.getString("Staff_Name")); 
				                	editDayDep.setText(rs.getString("Department"));
				                	editdayTime.setText(rs.getString("Time"));
				                	
            
				                }
				            } catch (SQLException ex) {
				                ex.printStackTrace();
				            }
				        }	
				
				
				
				        
				
				
				
			}
		});
		
		
		
		SearchStaffDay_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				Map<String, List <String>> Item= new HashMap<>();
				Item =  db.LoadDuty();

				String SelectCat = (String) SearchStaffDay_2.getSelectedItem();

					db.changeTime(SelectCat,timebox,Item);

			}
		});
		
		// Deb load and auto show
		List<String> allStaffdays = db.loadDayShiftFromDB();
		db.setupAutoComplete(SearchStaffDay_2, allStaffdays);
		
		
		
		
		
		SearchStaffDay_2.setEditable(true);
		SearchStaffDay_2.setBounds(10, 57, 155, 32);
		editdayshifpanels.add(SearchStaffDay_2);
		
		JLabel SN_lb_2 = new JLabel("Staff Name");
		SN_lb_2.setForeground(new Color(255, 253, 233));
		SN_lb_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SN_lb_2.setBounds(10, 90, 117, 32);
		editdayshifpanels.add(SN_lb_2);
		
		editDayName = new JTextField();
		editDayName.setFont(new Font("Arial", Font.PLAIN, 14));
		editDayName.setEditable(false);
		editDayName.setColumns(10);
		editDayName.setBounds(10, 116, 174, 32);
		editdayshifpanels.add(editDayName);
		
		JLabel dp_lb_2 = new JLabel("Department");
		dp_lb_2.setForeground(new Color(255, 253, 233));
		dp_lb_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dp_lb_2.setBounds(10, 168, 117, 32);
		editdayshifpanels.add(dp_lb_2);
		
		editDayDep = new JTextField();
		editDayDep.setFont(new Font("Arial", Font.PLAIN, 14));
		editDayDep.setEditable(false);
		editDayDep.setColumns(10);
		editDayDep.setBounds(10, 195, 174, 32);
		editdayshifpanels.add(editDayDep);
		
		JLabel Time_2 = new JLabel("Time");
		Time_2.setForeground(new Color(255, 253, 233));
		Time_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Time_2.setBounds(10, 247, 117, 32);
		editdayshifpanels.add(Time_2);
		
		editdayTime = new JTextField();
		editdayTime.setFont(new Font("Arial", Font.PLAIN, 14));
		editdayTime.setColumns(10);
		editdayTime.setBounds(10, 274, 174, 32);
		editdayshifpanels.add(editdayTime);
		
		JButton Addbtn_2 = new JButton("Confirm");
		Addbtn_2.setForeground(new Color(12, 59, 46));
		Addbtn_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		Addbtn_2.setBorder(null);
		Addbtn_2.setBackground(new Color(206, 241, 123));
		Addbtn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					String Time = editdayTime.getText();
					String preTime = (String) timebox.getSelectedItem();
					String Name = editDayName.getText();
				db.updateDuty(Name, Time ,  preTime);
							
							
							//Reload all box
			db.reloadDayTable();
						
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		Addbtn_2.setBounds(180, 356, 110, 38);
		editdayshifpanels.add(Addbtn_2);
		
		JButton Deletebtnday = new JButton("Delete");
		Deletebtnday.setForeground(new Color(255, 253, 233));
		Deletebtnday.setBorder(null);
		Deletebtnday.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		Deletebtnday.setFocusable(false);
		Deletebtnday.setBackground(new Color(255, 0, 0));
		Deletebtnday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = editDayName.getText();

				String Time = (String)timebox.getSelectedItem();
				db.deleteDayDuty(name,Time);
				
				
				//reload daytable
				db.reloadDayTable();
				
				
				
				
			}
		});
		Deletebtnday.setBounds(17, 356, 110, 38);
		editdayshifpanels.add(Deletebtnday);
		
		
		timebox.setBounds(173, 56, 117, 32);
		editdayshifpanels.add(timebox);
		
		txtAddDayshiftStaff = new JTextField();
		txtAddDayshiftStaff.setForeground(new Color(255, 253, 233));
		txtAddDayshiftStaff.setBorder(null);
		txtAddDayshiftStaff.setBackground(new Color(255, 100, 0));
		txtAddDayshiftStaff.setDisabledTextColor(new Color(0, 0, 0));
		txtAddDayshiftStaff.setText("Edit Dayshift Staff");
		txtAddDayshiftStaff.setSelectionColor(Color.BLACK);
		txtAddDayshiftStaff.setSelectedTextColor(new Color(255, 100, 0));
		txtAddDayshiftStaff.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddDayshiftStaff.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		txtAddDayshiftStaff.setEnabled(false);
		txtAddDayshiftStaff.setEditable(false);
		txtAddDayshiftStaff.setColumns(10);
		txtAddDayshiftStaff.setBounds(51, 11, 208, 28);
		editdayshifpanels.add(txtAddDayshiftStaff);
		
		// Deb load and auto show
		List<String> allStaffnight = db.loadStaffFromDB();
		

		
		JPanel records = new JPanel();
		records.setBackground(new Color(255, 128, 0));
		StaffMangePanel.add(records, "name_681779679365300");
		records.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_13.setPreferredSize(new Dimension(10, 100));
		records.add(panel_13, BorderLayout.NORTH);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_19 = new JPanel();
		panel_19.setBackground(new Color(255, 253, 233));
		panel_19.setPreferredSize(new Dimension(20, 20));
		panel_13.add(panel_19, BorderLayout.NORTH);
		
		JPanel panel_20 = new JPanel();
		panel_13.add(panel_20, BorderLayout.CENTER);
		panel_20.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_23 = new JPanel();
		panel_23.setBackground(new Color(255, 253, 233));
		panel_20.add(panel_23);
		
		JButton btnNewButton_9 = new JButton("Resigned Form");
		btnNewButton_9.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 14));
		btnNewButton_9.setForeground(new Color(255, 253, 233));
		btnNewButton_9.setBackground(new Color(0, 50, 35));
		panel_20.add(btnNewButton_9);
		
		JButton btnNewButton_8 = new JButton("New button");
		btnNewButton_8.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 14));
		btnNewButton_8.setBackground(new Color(255, 100, 0));
		panel_20.add(btnNewButton_8);
		
		JPanel panel_26 = new JPanel();
		panel_26.setBackground(new Color(255, 253, 233));
		panel_13.add(panel_26, BorderLayout.WEST);
		
		
		JPanel panel_14 = new JPanel();
		panel_14.setPreferredSize(new Dimension(300, 10));
		records.add(panel_14, BorderLayout.WEST);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panel_21.setPreferredSize(new Dimension(10, 50));
		panel_14.add(panel_21, BorderLayout.NORTH);
		panel_21.setLayout(new BorderLayout(0, 0));
		
		JTextPane txtpnTopPerformers = new JTextPane();
		txtpnTopPerformers.setBackground(new Color(255, 253, 233));
		txtpnTopPerformers.setSelectionColor(new Color(0, 50, 35));
		txtpnTopPerformers.setPreferredSize(new Dimension(7, 10));
		txtpnTopPerformers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnTopPerformers.setText("Top Performers");
		txtpnTopPerformers.setEnabled(false);
		txtpnTopPerformers.setEditable(false);
		panel_21.add(txtpnTopPerformers, BorderLayout.CENTER);
		
		JPanel panel_25 = new JPanel();
		panel_21.add(panel_25, BorderLayout.WEST);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 253, 233));
		comboBox.setFont(new Font("Swis721 Blk BT", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Weekly", "Monthly", "Yearly"}));
		comboBox.setPreferredSize(new Dimension(150, 22));
		panel_21.add(comboBox, BorderLayout.EAST);
		
		JPanel panel_22 = new JPanel();
		panel_14.add(panel_22, BorderLayout.CENTER);
		panel_22.setLayout(new BorderLayout(0, 0));
		
		table_2 = new JTable();
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table_2.setBackground(new Color(255, 253, 233));
		table_2.setRowHeight(40);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"Lucy Clockman", "4.5"},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table_2.getColumnModel().getColumn(0).setPreferredWidth(120);
		panel_22.add(table_2, BorderLayout.CENTER);
		
		
		
		JPanel panel_24 = new JPanel();
		panel_24.setBackground(new Color(255, 253, 233));
		panel_22.add(panel_24, BorderLayout.WEST);
		
		JPanel panel_15 = new JPanel();
		records.add(panel_15, BorderLayout.CENTER);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_16 = new JPanel();
		panel_16.setPreferredSize(new Dimension(10, 200));
		panel_15.add(panel_16, BorderLayout.SOUTH);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_27 = new JPanel();
		panel_27.setBackground(new Color(0, 50, 35));
		panel_16.add(panel_27, BorderLayout.CENTER);
		panel_27.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(140, 200, 80));
		progressBar.setStringPainted(true);
		progressBar.setValue(80);
		progressBar.setString("80%");
		progressBar.setBounds(151, 11, 275, 35);
		panel_27.add(progressBar);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setValue(52);
		progressBar_1.setStringPainted(true);
		progressBar_1.setString("52%");
		progressBar_1.setForeground(new Color(255, 100, 0));
		progressBar_1.setBounds(151, 62, 275, 35);
		panel_27.add(progressBar_1);
		
		JProgressBar progressBar_1_1 = new JProgressBar();
		progressBar_1_1.setValue(65);
		progressBar_1_1.setStringPainted(true);
		progressBar_1_1.setString("65%");
		progressBar_1_1.setForeground(new Color(255, 100, 0));
		progressBar_1_1.setBounds(151, 113, 275, 35);
		panel_27.add(progressBar_1_1);
		
		JPanel panel_28 = new JPanel();
		panel_28.setBackground(new Color(0, 50, 35));
		panel_28.setPreferredSize(new Dimension(50, 30));
		panel_16.add(panel_28, BorderLayout.NORTH);
		
		JTextPane txtpnDepartmentPerformance = new JTextPane();
		txtpnDepartmentPerformance.setForeground(new Color(255, 253, 233));
		txtpnDepartmentPerformance.setBackground(new Color(0, 50, 35));
		txtpnDepartmentPerformance.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 12));
		txtpnDepartmentPerformance.setText("Department Performance");
		panel_28.add(txtpnDepartmentPerformance);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		panel_15.add(table_3, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 50));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("Registration");
		btnNewButton.setFocusable(false);
		btnNewButton.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(108, 108, 108)));
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		btnNewButton.setForeground(new Color(255, 253, 233));
		btnNewButton.setBackground(new Color(45, 100, 43));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMangePanel.removeAll();
				StaffMangePanel.add(registration);
				StaffMangePanel.repaint();
				panel.revalidate();
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Schedule");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMangePanel.removeAll();
				StaffMangePanel.add(schedule);
				StaffMangePanel.repaint();
				panel.revalidate();
			}
		});
		btnNewButton_1.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(108, 108, 108)));
		btnNewButton_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		btnNewButton_1.setForeground(new Color(255, 253, 233));
		btnNewButton_1.setBackground(new Color(45, 100, 43));
		panel_1.add(btnNewButton_1);
		
		/*JButton btnNewButton_2 = new JButton("Records");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.removeAll();
				panel_2.add(records);
				panel_2.repaint();
				panel.revalidate();
			}
		});
		btnNewButton_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_2.setFont(new Font("Swis721 BlkCn BT", Font.BOLD, 18));
		btnNewButton_2.setForeground(new Color(255, 253, 233));
		btnNewButton_2.setBackground(new Color(45, 100, 43));
		panel_1.add(btnNewButton_2);
		*/
		
		//Overwrite add
	
		
		
		
		nightshiftreg.setLayout(null);
		nightshiftreg.setPreferredSize(new Dimension(10, 400));
		nightshiftreg.setBackground(new Color(206, 241, 123));
		shiftmenu.add(nightshiftreg, "name_660895498683300");
		
	
		SearchStaffNight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = (String) SearchStaffNight.getSelectedItem();
				ResultSet rs = db.getStaffDetailsByName(name);
				
				
				 if (rs != null) {
			            try {
			                if (rs.next()) {
			                	nightName.setText(rs.getString("Name"));
			                	nightDep.setText(rs.getString("Department"));
			           
			                  //  StaffNo.setText(rs.getString("NO"));
			                  
			                }
			                rs.close(); 
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(null, 
			                    "Error reading data: " + ex.getMessage(),
			                    "Error", 
			                    JOptionPane.ERROR_MESSAGE);
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, 
			                "Failed to load data for: " + name,
			                "Error", 
			                JOptionPane.ERROR_MESSAGE);
			        }
				
				
				
				
				
				
			}
		});
		
		
		
		// Deb load and auto show
				List<String> allStaffNight = db.loadStaffFromDB(); 
				db.setupAutoComplete(SearchStaffNight, allStaffNight);
				
				
				
				
				
		SearchStaffNight.setEditable(true);
		SearchStaffNight.setBounds(69, 57, 221, 32);
		nightshiftreg.add(SearchStaffNight);
		
		JLabel SN_lb_1 = new JLabel("Staff Name");
		SN_lb_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SN_lb_1.setBounds(10, 90, 117, 32);
		nightshiftreg.add(SN_lb_1);
		
		nightName = new JTextField();
		nightName.setFont(new Font("Arial", Font.PLAIN, 14));
		nightName.setEditable(false);
		nightName.setColumns(10);
		nightName.setBounds(10, 116, 174, 32);
		nightshiftreg.add(nightName);
		
		JLabel dp_lb_1 = new JLabel("Department");
		dp_lb_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dp_lb_1.setBounds(10, 168, 117, 32);
		nightshiftreg.add(dp_lb_1);
		
		nightDep = new JTextField();
		nightDep.setFont(new Font("Arial", Font.PLAIN, 14));
		nightDep.setEditable(false);
		nightDep.setColumns(10);
		nightDep.setBounds(10, 195, 174, 32);
		nightshiftreg.add(nightDep);
		
		JLabel Time_1 = new JLabel("Time");
		Time_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Time_1.setBounds(10, 247, 117, 32);
		nightshiftreg.add(Time_1);
		
		nightDuty = new JTextField();
		nightDuty.setFont(new Font("Arial", Font.PLAIN, 14));
		nightDuty.setColumns(10);
		nightDuty.setBounds(10, 274, 174, 32);
		nightshiftreg.add(nightDuty);
		
		JButton nightAddBtn = new JButton("ADD+");
		nightAddBtn.setFocusable(false);
		nightAddBtn.setForeground(new Color(255, 253, 233));
		nightAddBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		nightAddBtn.setBorder(null);
		nightAddBtn.setActionCommand("+  Add");
		nightAddBtn.setBackground(new Color(255, 100, 0));
		nightAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String NightShiftName = nightName.getText();
				String NightShiftDep = nightDep.getText();
				String NightShiftTime = nightDuty.getText();
				
				if(NightShiftName.isEmpty() || NightShiftDep.isEmpty() || NightShiftTime.isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "All Blank Must Be Fill");
					
				}else {
				 db.addNightshift(NightShiftName,NightShiftDep ,NightShiftTime);
				 db.reloadNightTable();
				
				}
			}
			
			
		});
		nightAddBtn.setBounds(180, 356, 110, 38);
		nightshiftreg.add(nightAddBtn);
		
		txtNightshiftEdit = new JTextField();
		txtNightshiftEdit.setBorder(null);
		txtNightshiftEdit.setBackground(new Color(206, 241, 123));
		txtNightshiftEdit.setDisabledTextColor(new Color(0, 0, 0));
		txtNightshiftEdit.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		txtNightshiftEdit.setHorizontalAlignment(SwingConstants.CENTER);
		txtNightshiftEdit.setSelectedTextColor(new Color(45, 100, 43));
		txtNightshiftEdit.setSelectionColor(new Color(0, 0, 0));
		txtNightshiftEdit.setText("Add Nightshift Staff");
		txtNightshiftEdit.setEnabled(false);
		txtNightshiftEdit.setEditable(false);
		txtNightshiftEdit.setBounds(51, 11, 208, 28);
		nightshiftreg.add(txtNightshiftEdit);
		txtNightshiftEdit.setColumns(10);
		
		
		editnightshifpanels.setLayout(null);
		editnightshifpanels.setPreferredSize(new Dimension(10, 400));
		editnightshifpanels.setBackground(new Color(206, 241, 123));
		shiftmenu.add(editnightshifpanels, "name_660924722370600");
		
		JComboBox nightShitftDuty = new JComboBox();
		nightShitftDuty.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JComboBox<String> nightShiftEdit = new JComboBox<String>();
		nightShiftEdit.setFont(new Font("Arial", Font.PLAIN, 14));
		nightShiftEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
		        
		        
		    	Map<String, List <String>> Item= new HashMap<>();
				Item =  db.LoadNightDuty();

				String SelectCat = (String) nightShiftEdit.getSelectedItem();

					db.changeTime(SelectCat,nightShitftDuty,Item);
		        
			
				
				
				
			}
		});
		
		
		
		// Deb load and auto show
		List<String> allStaffnighty = db.loadNightShiftFromDB();
		db.setupAutoComplete(nightShiftEdit, allStaffnighty);
		
		
		nightShiftEdit.setEditable(true);
		nightShiftEdit.setBounds(10, 57, 155, 32);
		editnightshifpanels.add(nightShiftEdit);
		
		JLabel SN_lb_2_1 = new JLabel("Staff Name");
		SN_lb_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SN_lb_2_1.setBounds(10, 90, 117, 32);
		editnightshifpanels.add(SN_lb_2_1);
		
		editnightStaff = new JTextField();
		editnightStaff.setEditable(false);
		editnightStaff.setColumns(10);
		editnightStaff.setBounds(10, 116, 174, 32);
		editnightshifpanels.add(editnightStaff);
		
		JLabel dp_lb_2_1 = new JLabel("Department");
		dp_lb_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dp_lb_2_1.setBounds(10, 168, 117, 32);
		editnightshifpanels.add(dp_lb_2_1);
		
		editnightDep = new JTextField();
		editnightDep.setFont(new Font("Arial", Font.PLAIN, 14));
		editnightDep.setEditable(false);
		editnightDep.setColumns(10);
		editnightDep.setBounds(10, 195, 174, 32);
		editnightshifpanels.add(editnightDep);
		
		JLabel Time_2_1 = new JLabel("Time");
		Time_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Time_2_1.setBounds(10, 247, 117, 32);
		editnightshifpanels.add(Time_2_1);
		
		editnihttime = new JTextField();
		editnihttime.setFont(new Font("Arial", Font.PLAIN, 14));
		editnihttime.setColumns(10);
		editnihttime.setBounds(10, 274, 174, 32);
		editnightshifpanels.add(editnihttime);
		
		JButton Addbtn_2_1 = new JButton("Confirm");
		Addbtn_2_1.setFocusable(false);
		Addbtn_2_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		Addbtn_2_1.setForeground(new Color(255, 253, 233));
		Addbtn_2_1.setBorder(null);
		Addbtn_2_1.setBackground(new Color(255, 100, 0));
		Addbtn_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String name = editnightStaff.getText();
				String time = editnihttime.getText();
				String preNightduty = (String) nightShitftDuty.getSelectedItem();

				
				
				db.updateNgihtDuty( name, time , preNightduty);
				
				db.reloadNightTable();
				
				
				
				
				
				
			}
		});
		Addbtn_2_1.setBounds(173, 356, 110, 38);
		editnightshifpanels.add(Addbtn_2_1);
		
		JButton Deletebtnday_1 = new JButton("Delete");
		Deletebtnday_1.setFocusable(false);
		Deletebtnday_1.setForeground(new Color(255, 253, 233));
		Deletebtnday_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		Deletebtnday_1.setBorder(null);
		Deletebtnday_1.setBackground(new Color(255, 0, 0));
		Deletebtnday_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String name = editnightStaff.getText();

				String Time = (String)nightShitftDuty.getSelectedItem();
				
				
				db.deleteNightDuty(name,Time);
				
				
				//reload daytable
				db.reloadNightTable();
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		Deletebtnday_1.setBounds(17, 356, 110, 38);
		editnightshifpanels.add(Deletebtnday_1);
		
	
		nightShitftDuty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				String name = (String) nightShiftEdit.getSelectedItem();
				String time = (String) nightShitftDuty.getSelectedItem();
				
				
		        if (name!= null|| time!= null ) {
		            try {
		                ResultSet rs = db.getnightTimeByData(time,name);
		                if (rs != null && rs.next()) {
		                	editnightStaff.setText(rs.getString("Staff_Name")); 
		                	editnightDep.setText(rs.getString("Department"));
		                	editnihttime.setText(rs.getString("Time"));
		                	
    
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }	
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		nightShitftDuty.setBounds(173, 57, 117, 32);
		editnightshifpanels.add(nightShitftDuty);
		
		txtEditNightshiftStaff = new JTextField();
		txtEditNightshiftStaff.setBorder(null);
		txtEditNightshiftStaff.setBackground(new Color(206, 241, 123));
		txtEditNightshiftStaff.setDisabledTextColor(new Color(0, 0, 0));
		txtEditNightshiftStaff.setText("Edit Nightshift Staff");
		txtEditNightshiftStaff.setSelectionColor(Color.BLACK);
		txtEditNightshiftStaff.setSelectedTextColor(new Color(45, 100, 43));
		txtEditNightshiftStaff.setHorizontalAlignment(SwingConstants.CENTER);
		txtEditNightshiftStaff.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		txtEditNightshiftStaff.setEnabled(false);
		txtEditNightshiftStaff.setEditable(false);
		txtEditNightshiftStaff.setColumns(10);
		txtEditNightshiftStaff.setBounds(51, 11, 208, 28);
		editnightshifpanels.add(txtEditNightshiftStaff);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 253, 233));
		panel_2.setPreferredSize(new Dimension(10, 120));
		rightformpanel.add(panel_2, BorderLayout.NORTH);
		
		lblTopBoxImageChange = new JLabel("");
		lblTopBoxImageChange.setIcon(new ImageIcon(Staff_Management.class.getResource("/icon/orangebg_koala.png")));
		panel_2.add(lblTopBoxImageChange);
//	rightformpanel.add(dayshiftmenu, "editPanel");
		
		
		
	}
//	localhost
	public class Staffdbconnection{
		ResultSet RS;
		Connection con;
		String serverURL = "jdbc:mysql://localhost:3306/zoomanagementsystem";
        String user = "root"; // Change if needed
        String password = "mintOfficial2BE4$"; // Change to your MySQL password
        
		public Staffdbconnection () {
			
	        
	        try {
	            con = DriverManager.getConnection(serverURL,user,password);
	            System.out.println("connected");
	        }
	        catch (SQLException e) {
	            System.out.println("failed");
	            e.printStackTrace();
	        };
	        
		}
		
		public void addData (String Name, String EmployedDate, String Status, String Department, String Role, String Salary,String Username,String Password) 
		
		{
			
			String SQL = "INSERT INTO tablestaff(Name, EmployedDate, Status, Department, Role, Salary,Username,Acc_Password) " + " VALUES (?,?,?,?,?,?,?,?)"; 
			
			try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setString(1, Name);
                stmt.setString(2, EmployedDate);
                    stmt.setString(3, Status);
                    stmt.setString(4, Department);
                    stmt.setString(5, Role);
                    stmt.setString(6, Salary);
                    stmt.setString(7,Username);
                    stmt.setString(8,Password);
                    stmt.executeUpdate();
                    System.out.println("✅ Staff info inserted successfully.");

            } catch (SQLException e) {
                System.out.println("❌ Failed to insert Staff info.");
                e.printStackTrace();
            }
			
		};
		
//This will add data to column 
				public  DefaultTableModel addDataLoadbyName(String name) {
					String sql = "SELECT * FROM tablestaff WHERE Name = ?";
				    DefaultTableModel model = new DefaultTableModel();

				    try (PreparedStatement stmt = con.prepareStatement(sql)) {
				        stmt.setString(1, name); // Set parameter BEFORE executing

				        try (ResultSet rs = stmt.executeQuery()) {
				            ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
				            int columnCount = meta.getColumnCount();

				            // Add column names
				            for (int i = 1; i <= columnCount; i++) {
				                model.addColumn(meta.getColumnName(i));
				            }

				            boolean hasData = false;

				            // Add all rows
				            while (rs.next()) {
				                hasData = true;
				                Object[] row = new Object[columnCount];
				                for (int i = 1; i <= columnCount; i++) {
				                    row[i - 1] = rs.getObject(i);
				                }
				                model.addRow(row);
				            }

				            if (!hasData) {
				                System.out.println("No data found for name: " + name);
				            }

				        }

		
								
								
					}catch(SQLException e) {
						
						e.printStackTrace();
					}
					
					return model;
					
				}
				public ResultSet getTimeByData(String time,String name) {
					
				    String sql = "SELECT  * FROM dayshift WHERE Staff_Name =? AND Time = ?";
				    try {
				        PreparedStatement stmt = con.prepareStatement(sql);
				        stmt.setString(1, name);
				        stmt.setString(2, time);
				        
				        return stmt.executeQuery();
				    } catch (SQLException e) {
				        e.printStackTrace();
				        return null;
				    }
				}
				
		public ResultSet getnightTimeByData(String time,String name) {
					
				    String sql = "SELECT  * FROM nightshift WHERE Staff_Name =? AND Time = ?";
				    try {
				        PreparedStatement stmt = con.prepareStatement(sql);
				        stmt.setString(1, name);
				        stmt.setString(2, time);
				        
				        return stmt.executeQuery();
				    } catch (SQLException e) {
				        e.printStackTrace();
				        return null;
				    }
				}
				
						
				
				public void StaffTable(JTable table) {
					 DefaultTableModel model = new DefaultTableModel(
						        new String[] { "NO", "Name", "Employed Date", "Status", "Department", "Role", "Monthly Salary","Username","Password"}, 0
						    ) {
						        @Override
						        public Class<?> getColumnClass(int columnIndex) {
						            return columnIndex == 0 ? Integer.class : String.class;
						        }
						    };

						    String query = "SELECT NO, Name, EmployedDate, Status, Department, Role, Salary, Username, Acc_Password FROM tablestaff";
						    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
						    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

						    try (PreparedStatement stmt = con.prepareStatement(query);
						         ResultSet rs = stmt.executeQuery()) {

						        while (rs.next()) {
						            // Handle EmployedDate as formatted string
						            String rawDate = rs.getString("EmployedDate");
						            String formattedDate;
						            if (rawDate != null && !rawDate.isEmpty()) {
						                try {
						                    java.util.Date parsedDate = inputFormat.parse(rawDate);
						                    formattedDate = outputFormat.format(parsedDate);
						                } catch (ParseException ex) {
						                    formattedDate = "Invalid Date";
						                }
						            } else {
						                formattedDate = "N/A";
						            }

						            // Handle Salary as-is (e.g., "$2500")
						            String salaryStr = rs.getString("Salary");
						            if (salaryStr == null || salaryStr.isEmpty()) {
						                salaryStr = "N/A";
						            }

						            Object[] row = {
						                rs.getInt("NO"),
						                rs.getString("Name") != null ? rs.getString("Name") : "N/A",
						                formattedDate,
						                rs.getString("Status") != null ? rs.getString("Status") : "N/A",
						                rs.getString("Department") != null ? rs.getString("Department") : "N/A",
						                rs.getString("Role") != null ? rs.getString("Role") : "N/A",
						                rs.getString("Salary") != null ? rs.getString("Salary") : "N/A",
						                rs.getString("Username"),
						                rs.getString("Acc_Password")
						            };

						            model.addRow(row);
						        }

						        table.setModel(model);






				    } catch (SQLException e) {
				        e.printStackTrace(); // or show a dialog
				    }
				}
				
				public int insertStaff(String name, String dateStr, String status, String department, String role, String salaryStr, String userName, String passWord) {
					String insertQuery = "INSERT INTO tablestaff (Name, EmployedDate, Status, Department, Role, Salary,Username,Acc_Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

				    try (PreparedStatement stmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
				        stmt.setString(1, name);
				        stmt.setString(2, dateStr);
				        stmt.setString(3, status);
				        stmt.setString(4, department);
				        stmt.setString(5, role);
				        stmt.setString(6, salaryStr);
				        stmt.setString(7, userName);
				        stmt.setString(8, passWord);

				        int affectedRows = stmt.executeUpdate();
				        if (affectedRows > 0) {
				            ResultSet keys = stmt.getGeneratedKeys();
				            if (keys.next()) {
				                return keys.getInt(1);
				            }
				        }
				    } catch (SQLException ex) {
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
				    }

				    return -1;


				}
				
				public void reloadStaffTable() {
				    DefaultTableModel model = (DefaultTableModel) staffRegistration_table.getModel();
				    model.setRowCount(0); // Clear existing rows

				    String query = "SELECT NO, Name, EmployedDate, Status, Department, Role, Salary, Username, Acc_Password FROM tablestaff";

				    try (Statement stmt = con.createStatement();
				         ResultSet rs = stmt.executeQuery(query)) {

				        while (rs.next()) {
				            model.addRow(new Object[] {
				                rs.getInt("NO"),
				                rs.getString("Name"),
				                rs.getString("EmployedDate"),
				                rs.getString("Status"),
				                rs.getString("Department"),
				                rs.getString("Role"),
				                rs.getString("Salary"),
				                rs.getString("Username"),
				                rs.getString("Acc_Password")
				            });
				        }

				    } catch (SQLException ex) {
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(null, "Error loading staff data: " + ex.getMessage());
				    }
				}
				
				public void reloadDayTable() {
				    DefaultTableModel model = (DefaultTableModel) day_shift.getModel();
				    model.setRowCount(0); // Clear existing rows

				    String query = "SELECT Staff_Name, Department, Time FROM dayshift";

				    try (Statement stmt = con.createStatement();
				         ResultSet rs = stmt.executeQuery(query)) {

				        while (rs.next()) {
				            model.addRow(new Object[] {
				                rs.getString("Staff_Name"),
				        
				                rs.getString("Department"),
				            
				                rs.getString("Time")
				            });
				        }

				    } catch (SQLException ex) {
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(null, "Error loading dayshift data: " + ex.getMessage());
				    }
				}
				public void reloadNightTable() {
				    DefaultTableModel model = (DefaultTableModel) night_shift.getModel();
				    model.setRowCount(0); // Clear existing rows

				    String query = "SELECT Staff_Name, Department, Time FROM nightshift";

				    try (Statement stmt = con.createStatement();
				         ResultSet rs = stmt.executeQuery(query)) {

				        while (rs.next()) {
				            model.addRow(new Object[] {
				                rs.getString("Staff_Name"),
				        
				                rs.getString("Department"),
				            
				                rs.getString("Time")
				            });
				        }

				    } catch (SQLException ex) {
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(null, "Error loading dayshift data: " + ex.getMessage());
				    }
				}

				
			
			
				// 🐱 FIXED FILTER METHOD: Safe popup handling
				private void filter(JTextField editor, JComboBox<String> comboBox, List<String> allNames) {
				    String currentText = editor.getText();
				    String searchText = currentText.toLowerCase();
				    
				    // Remember cursor position
				    int cursorPos = editor.getCaretPosition();
				    
				    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
				    
				    if (searchText.isEmpty()) {
				        // 🐱 SAFE: Hide popup without trying to show it
				        if (comboBox.isPopupVisible()) {
				            comboBox.hidePopup();
				        }
				        return;
				    }
				    
				    // Find matching names
				    for (String name : allNames) {
				        if (name.toLowerCase().startsWith(searchText)) {
				            model.addElement(name);
				        }
				    }
				    
				    // Set the model
				    comboBox.setModel(model);
				    
				    // Restore user's text and cursor position
				    editor.setText(currentText);
				    editor.setCaretPosition(cursorPos);
				    
				    // 🐱 SAFE: Only show popup if component is visible and has suggestions
				    if (model.getSize() > 0 && comboBox.isShowing() && comboBox.isVisible()) {
				        try {
				            comboBox.showPopup();
				        } catch (IllegalComponentStateException ex) {
				            // 🐱 SILENTLY IGNORE: Component not ready to show popup
				            System.out.println("🐱 Popup not ready yet, that's okay!");
				        }
				    } else {
				        if (comboBox.isPopupVisible()) {
				            comboBox.hidePopup();
				        }
				    }
				}

				
				public void setupAutoComplete(final JComboBox<String> comboBox, final List<String> allNames) {
				    comboBox.setEditable(true);
				    final JTextField editor = (JTextField) comboBox.getEditor().getEditorComponent();
				    
			
				    this.currentNameList = allNames;
				    this.currentComboBox = comboBox;
				    this.currentEditor = editor;
				    
				  
				    final javax.swing.Timer suggestionTimer = new javax.swing.Timer(300, new ActionListener() {
				        @Override
				        public void actionPerformed(ActionEvent e) {
				            filter(editor, comboBox, allNames);
				        }
				    });
				    suggestionTimer.setRepeats(false);
				    
				    // Listen to text changes
				    editor.getDocument().addDocumentListener(new DocumentListener() {
				        public void insertUpdate(DocumentEvent e) { suggestionTimer.restart(); }
				        public void removeUpdate(DocumentEvent e) { suggestionTimer.restart(); }
				        public void changedUpdate(DocumentEvent e) { suggestionTimer.restart(); }
				    });
				    

				    comboBox.addFocusListener(new FocusAdapter() {
				        @Override
				        public void focusGained(FocusEvent e) {
				            if (!editor.getText().isEmpty()) {
				                filter(editor, comboBox, allNames);
				            }
				        }
				    });
				}
				
				
				private List<String> currentNameList;
			    private JComboBox<String> currentComboBox;
			    private JTextField currentEditor;
	
			    public void reloadSearchList(JComboBox<String> comboBox) {
			        List<String> freshNames = loadStaffFromDB(); 
			        
			        javax.swing.SwingUtilities.invokeLater(() -> {
			            try {
			                // Store current text
			                JTextField editor = (JTextField) comboBox.getEditor().getEditorComponent();
			                String currentText = editor.getText();
			                
			                // Create new model with fresh data
			                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
			                for (String name : freshNames) {
			                    model.addElement(name);
			                }
			                
			                // Set the new model
			                comboBox.setModel(model);
			                
			                // COMPLETELY RE-SETUP AUTOCOMPLETE with fresh data
			                // This will automatically replace old listeners
			                setupAutoComplete(comboBox, freshNames);
			                
			                // Restore previous text
			                if (!currentText.isEmpty()) {
			                    editor.setText(currentText);
			                    editor.setCaretPosition(currentText.length());
			                }
			                
			                System.out.println("Search suggestions reloaded! Now has " + freshNames.size() + " items");
			                
			            } catch (Exception e) {
			                System.out.println("Error updating search suggestions: " + e.getMessage());
			                e.printStackTrace();
			            }
			        });
			    }
				
			
	
				public ResultSet getStaffDetailsByName(String staffName) {
				    String sql = "SELECT * FROM tablestaff WHERE Name = ?";
				    
				    try {
				        PreparedStatement stmt = con.prepareStatement(sql);
				        stmt.setString(1, staffName); 
				        return stmt.executeQuery();
				    } catch (SQLException e) {
				        System.out.println("❌ Failed to fetch staff details for: " + staffName);
				        e.printStackTrace();
				        return null;
				    }
				}
				
				public void updateStaff( String newName, String employedDate, String status, String department, String role, String salary,String Username,String Password,int ID) {
						String sql = "UPDATE tablestaff SET Name = ?, EmployedDate = ?, Status = ? , Department = ?, Role = ?, Salary = ?,Username = ?,Acc_Password = ? WHERE NO = ?";
  
							try (PreparedStatement stmt = con.prepareStatement(sql)) {
								stmt.setString(1, newName);
								stmt.setString(2, employedDate);
								stmt.setString(3, status);
								stmt.setString(4, department);
								stmt.setString(5, role);
								stmt.setString(6, salary);
								stmt.setString(7, Username);
								stmt.setString(8, Password);
								stmt.setInt(9, ID);
								 int rowsAffected = stmt.executeUpdate();
								JOptionPane.showMessageDialog(null,"Update Successful");
								System.out.print(newName+"______"+ID);
							
      
							} catch (SQLException e) {
								System.out.println("❌ Failed to update staff: ");
								e.printStackTrace();
								
							}
				}
				
				public void updateDuty( String name, String time , String previousTime) {
					String sql = "UPDATE dayshift SET Time = ? WHERE Staff_Name = ? AND Time =?";

						try (PreparedStatement stmt = con.prepareStatement(sql)) {
							stmt.setString(1, time);
							stmt.setString(2, name);
							stmt.setString(3, previousTime);
							 int rowsAffected = stmt.executeUpdate();
							JOptionPane.showMessageDialog(null,"Successfully Updated!");
					
						
  
						} catch (SQLException e) {
							System.out.println("❌ Failed to update Duty Time: ");
							e.printStackTrace();
							
						}
			}
				public void updateNgihtDuty( String name, String time , String previousTime) {
					String sql = "UPDATE nightshift SET Time = ? WHERE Staff_Name = ? AND Time =?";

						try (PreparedStatement stmt = con.prepareStatement(sql)) {
							stmt.setString(1, time);
							stmt.setString(2, name);
							stmt.setString(3, previousTime);
							 int rowsAffected = stmt.executeUpdate();
							JOptionPane.showMessageDialog(null,"Successfully Updated!");
					
						
  
						} catch (SQLException e) {
							System.out.println("❌ Failed to update Duty Time: ");
							e.printStackTrace();
							
						}
			}
				
				
				
				
				
				
				public void deleteAnimal(int id) {
				    String sql = "DELETE FROM tablestaff WHERE NO = ?";
				    try (PreparedStatement stmt = con.prepareStatement(sql)) {
				    	
				        stmt.setInt(1, id);
				        int rowsAffected = stmt.executeUpdate();
				        
				        if (rowsAffected > 0) {
				        	
				            System.out.println("Staff Remove successfully.");
				            JOptionPane.showMessageDialog(null,"Staff Remove successfully.");
				            
				        } else {
				        	
				            System.out.println("⚠️ No Staff found with that ID.");
				            JOptionPane.showMessageDialog(null," ⚠️ No Staff found with that ID");
				        }
				    } catch (SQLException e) {
				    	
				        System.out.println("❌ Failed to delete animal.");
				        JOptionPane.showMessageDialog(null,"Failed to delete animal..");
				        e.printStackTrace();
				    }
				}
				
				public void deleteDayDuty(String name, String Time) {
				    String sql = "DELETE FROM dayshift WHERE Staff_Name = ? AND Time =? ";
				    try (PreparedStatement stmt = con.prepareStatement(sql)) {
				    	
				        stmt.setString(1, name);
				        stmt.setString(2, Time);
				        
				        int rowsAffected = stmt.executeUpdate();
				        
				        if (rowsAffected > 0) {
				        	
				            System.out.println("Duty Time Remove successfully.");
				            JOptionPane.showMessageDialog(null,"Duty Time Remove successfully.");
				            
				        } else {
				        	
				            System.out.println("⚠️ No Shief found with that ID.");
				            JOptionPane.showMessageDialog(null," ⚠️ No Shift found with that ID");
				        }
				    } catch (SQLException e) {
				    	
				        System.out.println("❌ Failed to delete Duty Time.");
				        JOptionPane.showMessageDialog(null,"Failed to delete Duty Time");
				        e.printStackTrace();
				    }
				}
				public void deleteNightDuty(String name, String Time) {
				    String sql = "DELETE FROM nightshift WHERE Staff_Name = ? AND Time =? ";
				    try (PreparedStatement stmt = con.prepareStatement(sql)) {
				    	
				        stmt.setString(1, name);
				        stmt.setString(2, Time);
				        
				        int rowsAffected = stmt.executeUpdate();
				        
				        if (rowsAffected > 0) {
				        	
				            System.out.println("Duty Time Remove successfully.");
				            JOptionPane.showMessageDialog(null,"Duty Time Remove successfully.");
				            
				        } else {
				        	
				            System.out.println("⚠️ No Shief found with that ID.");
				            JOptionPane.showMessageDialog(null," ⚠️ No Shift found with that ID");
				        }
				    } catch (SQLException e) {
				    	
				        System.out.println("❌ Failed to delete Duty Time.");
				        JOptionPane.showMessageDialog(null,"Failed to delete Duty Time");
				        e.printStackTrace();
				    }
				}
				
				private Map<String, List<String>> LoadDuty() {

					String sql = "SELECT Staff_Name, Time From dayshift  ";
					Map<String,List<String>> catnitem = new HashMap<>();
				
				
					try(PreparedStatement stmt = con.prepareStatement(sql);
							ResultSet rs = stmt.executeQuery();
							){
						
						
							while(rs.next()) {
								
								String Cat = rs.getString("Staff_Name");
								String Item = rs.getString("Time");
								
								catnitem.putIfAbsent(Cat, new ArrayList<>());
								catnitem.get(Cat).add(Item);
							
								
				            }
								
							
								
							
					
					}catch(SQLException ex ) {
							ex.printStackTrace();
							
						
					};
					return catnitem;
				
				

				
			}
				private Map<String, List<String>> LoadNightDuty() {

					String sql = "SELECT Staff_Name, Time From nightshift  ";
					Map<String,List<String>> catnitem = new HashMap<>();
				
				
					try(PreparedStatement stmt = con.prepareStatement(sql);
							ResultSet rs = stmt.executeQuery();
							){
						
						
							while(rs.next()) {
								
								String Cat = rs.getString("Staff_Name");
								String Item = rs.getString("Time");
								
								catnitem.putIfAbsent(Cat, new ArrayList<>());
								catnitem.get(Cat).add(Item);
							
								
				            }
								
							
								
							
					
					}catch(SQLException ex ) {
							ex.printStackTrace();
							
						
					};
					return catnitem;
				
				

				
			}
				
				
				
				
				
				
				
				
				
				
				
				
			    private void changeTime(String category , JComboBox JC, Map<String,List<String>> st ) {
			        JC.removeAllItems();
			        
			        
			        if (category != null && st.containsKey(category)) {
			            for (String item : st.get(category)) {
			                JC.addItem(item);
			            }
			        }
			    }
				
				public void addDayshift(String name,String Dep,String time) {
					String sql = "INSERT INTO dayshift (Staff_Name, Department, Time) VALUES (?, ?, ?)";
					
					
					
				    try (PreparedStatement stmt = con.prepareStatement(sql)) {
				        stmt.setString(1, name);
				        stmt.setString(2, Dep);
				        stmt.setString(3, time);
				        stmt.executeUpdate();
				        JOptionPane.showMessageDialog(null, "Successfully Added! " );
				    } catch (SQLException ex) {
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
				    }
					
					
			
					
				}
				public void addNightshift(String name,String Dep,String time) {
					String sql = "INSERT INTO nightshift (Staff_Name, Department, Time) VALUES (?, ?, ?)";
					
					
					
				    try (PreparedStatement stmt = con.prepareStatement(sql)) {
				        stmt.setString(1, name);
				        stmt.setString(2, Dep);
				        stmt.setString(3, time);
				        stmt.executeUpdate();
				        JOptionPane.showMessageDialog(null, "Successfully Added! " );
				    } catch (SQLException ex) {
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
				    }
					
					
			
					
				}
				
				public void DayShiftAdd(JTable table) {
					 DefaultTableModel model = new DefaultTableModel(
						        new String[] {  "Name", "Department", "Time" }, 0
						    ) {
						        @Override
						        public Class<?> getColumnClass(int columnIndex) {
						            return columnIndex == 0 ? Integer.class : String.class;
						        }
						    };

						    String query = "SELECT Staff_Name, Department, Time FROM dayshift";

						    try (PreparedStatement stmt = con.prepareStatement(query);
						         ResultSet rs = stmt.executeQuery()) {

						        while (rs.next()) {
						 
	
						            Object[] row = {
						          
						                rs.getString("Staff_Name") != null ? rs.getString("Staff_Name") : "N/A",
						                rs.getString("Department") != null ? rs.getString("Department") : "N/A",
						                rs.getString("Time") != null ? rs.getString("Time") : "N/A",
						          
						            };

						            model.addRow(row);
						            
						            
						            
						            
						        }

						        table.setModel(model);

						        	//text Position adjuest 
						        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
						        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

						        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
						        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

						        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Name
						        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);   // Department
						        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);   // Time








				    } catch (SQLException e) {
				        e.printStackTrace(); // or show a dialog
				    }
				}
				    
				public void NightShiftAdd(JTable table) {
					 DefaultTableModel model = new DefaultTableModel(
						        new String[] {  "Name", "Department", "Time" }, 0
						    ) {
						        @Override
						        public Class<?> getColumnClass(int columnIndex) {
						            return columnIndex == 0 ? Integer.class : String.class;
						        }
						    };

						    String query = "SELECT Staff_Name, Department, Time FROM nightshift";

						    try (PreparedStatement stmt = con.prepareStatement(query);
						         ResultSet rs = stmt.executeQuery()) {

						        while (rs.next()) {
						 
	
						            Object[] row = {
						          
						                rs.getString("Staff_Name") != null ? rs.getString("Staff_Name") : "N/A",
						                rs.getString("Department") != null ? rs.getString("Department") : "N/A",
						                rs.getString("Time") != null ? rs.getString("Time") : "N/A",
						          
						            };

						            model.addRow(row);
						            
						            
						            
						            
						        }

						        table.setModel(model);

						        	//text Position adjuest 
						        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
						        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

						        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
						        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

						        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Name
						        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);   // Department
						        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);   // Time








				    } catch (SQLException e) {
				        e.printStackTrace(); // or show a dialog
				    }
				}
		
    public void getDataLength () {
		staffList.clear();
		totalStaffCount=0;
		
		String SQL = "SELECT * FROM tablestaff";
		
		try(
			
            Statement pS = con.createStatement();
            ResultSet rs = pS.executeQuery(SQL)){
           /* int staffrs = 0;
            while (rs.next()) {
                staffrs++;
            }
            
            return staffrs;*/
            
            while (rs.next()) 
            
            {
            	
            	int NO = rs.getInt("NO");
            	String Name = rs.getString("Name");
            	String EmployedDate = rs.getString("EmployedDate");
            	String Status = rs.getString("Status");
            	String Department = rs.getString("Department");
            	String Role = rs.getString("Role");
            	String Salary = rs.getString("Salary");
            	String Username = rs.getString("Username");
            	String Password = rs.getString("Acc_Password");
            	staff_info takuku = new staff_info (NO,Name,EmployedDate,Status,Department,Role,Salary,Username,Password);
            	staffList.add (takuku);
            	
//            	System.out.print("Username : "+Username+"\nPassword : "+Password+"\nStatus : "+Status);
            	totalStaffCount+=1;
            }
            
		}
		
		catch(SQLException es) {
            
            es.printStackTrace();
           
           
            
        }
		
	}
    // 🔹 Load Staff names from DB into list
    		private List<String> loadStaffFromDB() {
    			List<String> allStafflist = new ArrayList<>();
        try { 
        	Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Name FROM tablestaff");

            while (rs.next()) {
            	allStafflist.add(rs.getString("Name"));
            }

         
        } catch (Exception e) {
            e.printStackTrace();
            }
        	return allStafflist;
    		};
    		
    		
    		
    		

    		private List<String> loadDayShiftFromDB() {
    			List<String> allStafflist = new ArrayList<>();
        try { 
        	Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Staff_Name FROM dayshift");

            while (rs.next()) {
            	allStafflist.add(rs.getString("Staff_Name"));
            }

         
        } catch (Exception e) {
            e.printStackTrace();
            }
        	return allStafflist;
    		};
    		

    		private List<String> loadNightShiftFromDB() {
    			List<String> allStafflist = new ArrayList<>();
        try { 
        	Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Staff_Name FROM nightshift");

            while (rs.next()) {
            	allStafflist.add(rs.getString("Staff_Name"));
            }

         
        } catch (Exception e) {
            e.printStackTrace();
            }
        	return allStafflist;
    		};
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		private List<String> loadTimeSearch() {
    			List<String> allTimelist = new ArrayList<>();
        try { 
        	Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Time FROM dayshift");

            while (rs.next()) {
            	allTimelist.add(rs.getString("Time"));
            }

         
        } catch (Exception e) {
            e.printStackTrace();
            }
        	return allTimelist;
    		};
		
		/*public void loadStaffData() {
		    String SQL = "SELECT * FROM tablestaff";
		    try {
		        Statement pS = con.createStatement();
		        ResultSet rs = pS.executeQuery(SQL);

		        DefaultTableModel model = null;
				// clear the old table rows
		        model.setRowCount(0);

		        while (rs.next()) {
		            int NO = rs.getInt("NO");
		            String Name = rs.getString("Name");
		            String EmployedDate = rs.getString("EmployedDate");
		            String Status = rs.getString("Status");
		            String Department = rs.getString("Department");
		            String Role = rs.getString("Role");
		            String Salary = rs.getString("Salary");

		            // add row to JTable
		            model.addRow(new Object[]{NO, Name, EmployedDate, Status, Department, Role, Salary});
		        }

		        rs.close();
		        pS.close();
		    } catch (SQLException es) {
		        es.printStackTrace();
		    }
		}*/

		
		
		
	}
	
	public class staff_info {
		
		int NO;
		String Name;
		String EmployedDate;
		String Status;
		String Department;
		String Role;
		String Salary;
		String Username;
		String Password;
		
		public int getNO() {
			return NO;
		}
		public staff_info(int nO, String name, String employedDate, String status, String department, String role,
				String salary,String userName,String passWord) {
			super();
			NO = nO;
			Name = name;
			EmployedDate = employedDate;
			Status = status;
			Department = department;
			Role = role;
			Salary = salary;
			Username=userName;
			Password=passWord;
		}
		public String getUsername() {
			return Username;
		}
		public void setUsername(String username) {
			Username = username;
		}
		public String getPassword() {
			return Password;
		}
		public void setPassword(String password) {
			Password = password;
		}
		public void setNO(int nO) {
			NO = nO;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getEmployedDate() {
			return EmployedDate;
		}
		public void setEmployedDate(String employedDate) {
			EmployedDate = employedDate;
		}
		public String getStatus() {
			return Status;
		}
		public void setStatus(String status) {
			Status = status;
		}
		public String getDepartment() {
			return Department;
		}
		public void setDepartment(String department) {
			Department = department;
		}
		public String getRole() {
			return Role;
		}
		public void setRole(String role) {
			Role = role;
		}
		public String getSalary() {
			return Salary;
		}
		public void setSalary(String salary) {
			Salary = salary;
		}
		
		
		
	}
}
