package body;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeListener;
import java.util.List;


import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerModel;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import welcome.SignIn_Form;
import welcome.User;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Visitor_Management extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private JTextField tFPriceIceCream;
	private JPanel panelmenu;
	private JTextField tFTax;
	private JTextField tFSubtotal;
	private JTextField tFgrandTotal;
	private List<MenuItem> menuItems = new ArrayList<>();
	private ArrayList<ticketSales_info>ticketSalesArrayList=new ArrayList<>();
	private JTextField taxField, subTotalField, totalField;
	private JTextArea textAreaRC;
	private JTextField tFTStotal;
	private JTable TSTable;
	
	//TicketSalesVar
	static private JSpinner spinneradult;
	static private JSpinner spinnerchild;
	static private JSpinner spinnermale;
	static private JSpinner spinnerfemale;
	
	static private int adult;
    static private int child;
    static private int totalCust;
	static private int leftCust;
    private int transportQty;
    private static final double PRICE_ADULT = 2000.0;
    private static final double PRICE_CHILD = 1000.0;
    private static final double PRICE_GUIDED_TOUR = 5000.0;
    private static final double PRICE_TRANSPORTBuggy = 6000.0;
    private static final double PRICE_TRANSPORTShuttleBus = 8000.0;
    private static final double PRICE_TRANSPORTBicycle= 1000.0;
    private JScrollPane scrollPaneTStable;
    private JPanel menuheader;
    private DefaultTableCellRenderer centerRenderer;
    
    
    LocalDateTime localDateTime;
    DateTimeFormatter dateTimeFormatter;
    String formattedDateTime; 
	


//   these data to dashboard
    public static double totalTicketSales;
    public static int totalCustomerCount;
    
    
 //  these data to dashboard
   
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visitor_Management frame = new Visitor_Management();
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
	public Visitor_Management() throws SQLException { 
		//SQL class
		 DbConnection db= new  DbConnection();
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Visitor_Management.class.getResource("/icon/zoo_logo_nobg.png")));
		setForeground(new Color(0, 50, 35));
		setBackground(new Color(0, 50, 35));
		setTitle("Zoo Management System ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(120, 50, 1132, 653);
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
		dashboardIcon.setIcon(new ImageIcon(Visitor_Management.class.getResource("/icon/dashboardSidebarIcon_bgGreen.png")));
		dashboardIcon.setBackground(new Color(255, 100, 0));
		dashboardIcon.setBounds(-2, 2, 80, 32);
		dashboardIcon.setForeground(new Color(255, 100, 0));
		dashboardMenu.add(dashboardIcon);
		
		JLabel lblMenuDashboard = new JLabel("Dashboard");
		lblMenuDashboard.setBounds(8, 35, 72, 16);
		lblMenuDashboard.setForeground(new Color(255, 253, 233));
		lblMenuDashboard.setFont(new Font("Arial", Font.BOLD, 12));
		dashboardMenu.add(lblMenuDashboard);
		
		JPanel logo = new JPanel();
		logo.setBackground(new Color(0, 50, 35));
		logo.setBounds(0, 0, 85, 56);
		siderBar.add(logo);
		logo.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("");
		lblNewLabel_1_1_1_2.setBounds(0, 10, 85, 40);
		lblNewLabel_1_1_1_2.setIcon(new ImageIcon(Visitor_Management.class.getResource("/icon/resized_GreenBg_ZooLogo2.png")));
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
		animalIcon.setIcon(new ImageIcon(Visitor_Management.class.getResource("/icon/animalSidebarIcon_bgGreen.png")));
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
		staffIcon.setIcon(new ImageIcon(Visitor_Management.class.getResource("/icon/staffSidebaricon_bgGreen.png")));
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
		lblMenuStaff.setForeground(new Color(255, 253, 233));
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
		visitorMenu.setBackground(new Color(255, 253, 233));
		visitorMenu.setBounds(5, 292, 80, 56);
		siderBar.add(visitorMenu);
		
		JLabel visitorIcon = new JLabel("");
		visitorIcon.setIcon(new ImageIcon(Visitor_Management.class.getResource("/icon/visitorSidebarIcon.png")));
		visitorIcon.setForeground(new Color(255, 100, 0));
		visitorIcon.setBounds(-2, 2, 80, 32);
		visitorMenu.add(visitorIcon);
		
		JLabel lblMenuVisitor = new JLabel("Visitor");
		lblMenuVisitor.setForeground(new Color(0, 50, 35));
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
		inventoryIcon.setIcon(new ImageIcon(Visitor_Management.class.getResource("/icon/inventorySidebarIcon_bgGreen.png")));
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
		
		JPanel body = new JPanel();
		body.setBorder(null);
		body.setBackground(new Color(255, 253, 233));
		body.setPreferredSize(new Dimension(100, 65));
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BorderLayout(0, 0));
		
		JPanel header = new JPanel();
		header.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(108, 108, 108)));
		header.setBackground(new Color(255, 253, 233));
		header.setPreferredSize(new Dimension(100, 60));
		body.add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
		JPanel headerLeftPanel = new JPanel();
		headerLeftPanel.setPreferredSize(new Dimension(530, 10));
		headerLeftPanel.setBackground(new Color(12, 59, 46));
		header.add(headerLeftPanel, BorderLayout.WEST);
		headerLeftPanel.setLayout(null);
		
		JLabel lblPageNameDashboard = new JLabel("Visitor Management");
		lblPageNameDashboard.setForeground(new Color(206, 241, 123));
		lblPageNameDashboard.setBackground(new Color(255, 100, 0));
		lblPageNameDashboard.setBounds(20, 9, 500, 36);
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
		lblNewLabel_2.setIcon(new ImageIcon(Visitor_Management.class.getResource("/icon/userProfileIconGreenGreen.png")));
		lblNewLabel_2.setForeground(new Color(255, 100, 0));
		lblNewLabel_2.setBackground(new Color(255, 100, 0));
		lblNewLabel_2.setBounds(132, 6, 40, 44);
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
		lblHeaderRole.setBounds(26, 31, 96, 14);
		headerRightPanel.add(lblHeaderRole);
		
		lblHeaderUsername.setText(SignIn_Form.signInAccUsername);
		lblHeaderRole.setText(SignIn_Form.signInAccRole);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(12, 59, 46));
		header.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(108, 108, 108)));
		panel_1.setBackground(new Color(12, 59, 46));
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
		
		//Gwennn
		    
	        
	       
		//double totalPrice = 0.0;
		//public void initialize() {
			
		//}
		
		//public void reset() {
			
		//}
		    subTotalField = new JTextField("0.0", 10);
	        taxField = new JTextField("0.0", 10);
	        totalField = new JTextField("0.0", 10);
		
		
		JPanel basepanelmain = new JPanel();
		body.add(basepanelmain, BorderLayout.CENTER);
		basepanelmain.setLayout(new BorderLayout(0, 0));
		
		JPanel mainbody = new JPanel();
		mainbody.setBackground(SystemColor.info);
		basepanelmain.add(mainbody, BorderLayout.CENTER);
		mainbody.setLayout(new CardLayout(0, 0));
		
		JPanel TicketSale = new JPanel();
		TicketSale.setBackground(new Color(255, 255, 255));
		mainbody.add(TicketSale, "name_966887329930100");
		TicketSale.setLayout(new BorderLayout(0, 0));
		
		JPanel TSLeft = new JPanel();
		TicketSale.add(TSLeft, BorderLayout.CENTER);
		TSLeft.setLayout(new BorderLayout(0, 0));
		
		JPanel Tabel = new JPanel();
		Tabel.setPreferredSize(new Dimension(10, 300));
		Tabel.setBackground(new Color(255, 255, 255));
		TSLeft.add(Tabel, BorderLayout.SOUTH);
		Tabel.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTStable = new JScrollPane();
		scrollPaneTStable.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		Tabel.add(scrollPaneTStable, BorderLayout.CENTER);
		
		
		TSTable = new JTable();
		TSTable.setSelectionBackground(new Color(216, 236, 196));
		TSTable.setGridColor(new Color(226, 226, 226));
		TSTable.setFont(new Font("Arial", Font.PLAIN, 14));
		TSTable.setBackground(Color.WHITE);
		TSTable.setRowHeight(30);
		scrollPaneTStable.setViewportView(TSTable);
		TSTable.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"Male", "Female", "Adult", "Child", "Tour", "Transport", "Transport Quantity", "Total Price"
			}
		));

		db.showDb();
		
		centerRenderer=new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TSTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        TSTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        TSTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        TSTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        TSTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        TSTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        TSTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        TSTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
        
		JPanel PriceTS = new JPanel();
		PriceTS.setPreferredSize(new Dimension(10, 0));
		PriceTS.setBackground(new Color(255, 253, 233));
		TSLeft.add(PriceTS, BorderLayout.CENTER);
		PriceTS.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(20, 20));
		panel_5.setBackground(new Color(255, 253, 233));
		PriceTS.add(panel_5, BorderLayout.NORTH);
		
		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(10, 20));
		panel_6.setBackground(new Color(255, 253, 233));
		PriceTS.add(panel_6, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 253, 233));
		PriceTS.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(226, 226, 226)));
		panel_7.setBackground(new Color(255, 253, 233));
		panel_7.setPreferredSize(new Dimension(450, 210));
		panel_2.add(panel_7, BorderLayout.WEST);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Visitor_Management.class.getResource("/icon/ticket_edited.png")));
		lblNewLabel.setPreferredSize(new Dimension(310, 200));
		lblNewLabel.setSize(new Dimension(310, 200));
		panel_7.add(lblNewLabel);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(null);
		panel_8.setBackground(new Color(255, 253, 233));
		panel_8.setPreferredSize(new Dimension(450, 210));
		panel_2.add(panel_8, BorderLayout.EAST);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setBackground(new Color(255, 253, 233));
		lblNewLabel_1.setIcon(new ImageIcon(Visitor_Management.class.getResource("/icon/transportation_edited.png")));
		lblNewLabel_1.setPreferredSize(new Dimension(310, 200));
		panel_8.add(lblNewLabel_1);
		
		JPanel panel_11 = new JPanel();
		panel_2.add(panel_11, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(15, 10));
		panel_3.setBackground(new Color(255, 253, 233));
		PriceTS.add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(15, 10));
		panel_4.setBackground(new Color(255, 253, 233));
		PriceTS.add(panel_4, BorderLayout.EAST);
		
		JPanel TSForm = new JPanel();
		TSForm.setPreferredSize(new Dimension(350, 10));
		TSForm.setBackground(new Color(255, 255, 255));
		TicketSale.add(TSForm, BorderLayout.EAST);
		TSForm.setLayout(new BorderLayout(0, 0));
		
		JPanel TSTitleHeader = new JPanel();
		TSTitleHeader.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(108, 108, 108)));
		TSTitleHeader.setBackground(new Color(12, 59, 46));
		TSTitleHeader.setPreferredSize(new Dimension(10, 45));
		TSForm.add(TSTitleHeader, BorderLayout.NORTH);
		
		JPanel titlepanel = new JPanel();
		titlepanel.setBackground(new Color(12, 59, 46));
		titlepanel.setPreferredSize(new Dimension(330, 40));
		TSTitleHeader.add(titlepanel);
		titlepanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbltitleTS = new JLabel("TICKET SALES");
		lbltitleTS.setForeground(new Color(206, 241, 123));
		lbltitleTS.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lbltitleTS.setHorizontalTextPosition(SwingConstants.CENTER);
		lbltitleTS.setHorizontalAlignment(SwingConstants.CENTER);
		titlepanel.add(lbltitleTS);
		
		JPanel TSBody = new JPanel();
		TSForm.add(TSBody, BorderLayout.CENTER);
		TSBody.setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel Tsbodypanel = new JPanel();
		Tsbodypanel.setBorder(new MatteBorder(0, 1, 0, 1, (Color) new Color(108, 108, 108)));
		Tsbodypanel.setBackground(new Color(255,253,233));
		TSBody.add(Tsbodypanel, BorderLayout.CENTER);
		Tsbodypanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelTSBodyFlow = new JPanel();
		panelTSBodyFlow.setBackground(new Color(255,253,233));
		panelTSBodyFlow.setPreferredSize(new Dimension(340, 450));
		Tsbodypanel.add(panelTSBodyFlow);
		panelTSBodyFlow.setLayout(null);
		
		JLabel lblAdult = new JLabel("Adult");
		lblAdult.setForeground(new Color(12, 59, 46));
		lblAdult.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblAdult.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdult.setBounds(39, 23, 72, 32);
		panelTSBodyFlow.add(lblAdult);
		
		JLabel lblChild = new JLabel("Child");
		lblChild.setForeground(new Color(12, 59, 46));
		lblChild.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblChild.setHorizontalAlignment(SwingConstants.LEFT);
		lblChild.setBounds(39, 65, 72, 32);
		panelTSBodyFlow.add(lblChild);
		
		JLabel lblMale = new JLabel("Male");
		lblMale.setForeground(new Color(12, 59, 46));
		lblMale.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblMale.setHorizontalAlignment(SwingConstants.LEFT);
		lblMale.setBounds(39, 107, 72, 32);
		panelTSBodyFlow.add(lblMale);
		
		JLabel lblFemale = new JLabel("Female");
		lblFemale.setForeground(new Color(12, 59, 46));
		lblFemale.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblFemale.setHorizontalAlignment(SwingConstants.LEFT);
		lblFemale.setBounds(39, 149, 72, 32);
		panelTSBodyFlow.add(lblFemale);
		
		JLabel lblTour = new JLabel("Tour");
		lblTour.setForeground(new Color(12, 59, 46));
		lblTour.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblTour.setHorizontalAlignment(SwingConstants.LEFT);
		lblTour.setBounds(39, 191, 72, 32);
		panelTSBodyFlow.add(lblTour);
		
		JLabel lblTransport = new JLabel("Transport");
		lblTransport.setForeground(new Color(12, 59, 46));
		lblTransport.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblTransport.setHorizontalAlignment(SwingConstants.LEFT);
		lblTransport.setBounds(39, 233, 72, 32);
		panelTSBodyFlow.add(lblTransport);
		
		JLabel lblToatlPriceTS = new JLabel("Total Price");
		lblToatlPriceTS.setForeground(new Color(12, 59, 46));
		lblToatlPriceTS.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblToatlPriceTS.setHorizontalAlignment(SwingConstants.LEFT);
		lblToatlPriceTS.setBounds(39, 334, 85, 32);
		panelTSBodyFlow.add(lblToatlPriceTS);
		
		spinneradult = new JSpinner();
		spinneradult.setFont(new Font("Arial", Font.PLAIN, 14));
		spinneradult.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinneradult.setBounds(166, 30, 135, 29);
		
		panelTSBodyFlow.add(spinneradult);
		
		spinnerchild = new JSpinner();
		spinnerchild.setFont(new Font("Arial", Font.PLAIN, 14));
		spinnerchild.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinnerchild.setBounds(166, 72, 135, 29);
		panelTSBodyFlow.add(spinnerchild);
		
		
		spinneradult.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateTotalSpinnerMax();
			}
		});
		spinnerchild.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateTotalSpinnerMax();
			}
		});
	       
	    
	    
		spinnermale = new JSpinner();
		spinnermale.setFont(new Font("Arial", Font.PLAIN, 14));
		spinnermale.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinnermale.setBounds(166, 114, 135, 29);
		panelTSBodyFlow.add(spinnermale);
		
		spinnermale.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateLeftSpinnerValue();
			}
		});
		
		
		spinnerfemale = new JSpinner();
		spinnerfemale.setFont(new Font("Arial", Font.PLAIN, 14));
		spinnerfemale.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinnerfemale.setBounds(166, 156, 135, 29);
		panelTSBodyFlow.add(spinnerfemale);
		
		
		
		JSpinner spinnertransportQty = new JSpinner();
		spinnertransportQty.setMinimumSize(new Dimension(30, 25));
		spinnertransportQty.setPreferredSize(new Dimension(30, 25));
		spinnertransportQty.setFont(new Font("Arial", Font.PLAIN, 14));
		spinnertransportQty.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinnertransportQty.setBounds(166, 279, 135, 29);
		panelTSBodyFlow.add(spinnertransportQty);
		
		JCheckBox chckbxTour = new JCheckBox("Guided Tour");
		chckbxTour.setBackground(new Color(255, 253, 233));
		chckbxTour.setForeground(new Color(0, 0, 0));
		chckbxTour.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		chckbxTour.setBounds(166, 197, 115, 29);
		panelTSBodyFlow.add(chckbxTour);
		
		JComboBox<String> transportbox = new JComboBox<String>();
		transportbox.setFont(new Font("Arial", Font.PLAIN, 14));
		transportbox.setForeground(new Color(0,50,35));
		transportbox.setModel(new DefaultComboBoxModel(new String[] {"Walking", "Shuttle Bus", "Buggy", "Bicycle"}));
		transportbox.setBounds(166, 239, 135, 29);
		panelTSBodyFlow.add(transportbox);
		
		tFTStotal = new JTextField();
		tFTStotal.setFont(new Font("Arial", Font.PLAIN, 14));
		tFTStotal.setHorizontalAlignment(SwingConstants.CENTER);
		tFTStotal.setEditable(false);
		tFTStotal.setBounds(166, 340, 135, 29);
		panelTSBodyFlow.add(tFTStotal);
		tFTStotal.setColumns(10);
		
		JButton btnCalculatePriceTS = new JButton("Calculate Price");
		btnCalculatePriceTS.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCalculatePriceTS.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnCalculatePriceTS.setFocusable(false);
		btnCalculatePriceTS.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(12, 59, 46)));
		btnCalculatePriceTS.setBounds(14, 400, 310, 43);
		panelTSBodyFlow.add(btnCalculatePriceTS);
		btnCalculatePriceTS.setForeground(new Color(12, 59, 46));
		btnCalculatePriceTS.setBackground(new Color(206, 241, 123));
		btnCalculatePriceTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		//Getting spinner Value
		        adult = (int) spinneradult.getValue();
		        child = (int) spinnerchild.getValue();
		        
		         
		        
		        transportQty = (int) spinnertransportQty.getValue();
		        // calculating ticket prices
		        double currentTotalPrice = (adult * PRICE_ADULT) + (child * PRICE_CHILD);
		    // get tour
		        if ( chckbxTour.isSelected()) {
		            currentTotalPrice += PRICE_GUIDED_TOUR;
		        }
		        
		        String transportOption = (String) transportbox.getSelectedItem();
		        if ("Buggy".equals(transportOption)) {
		            currentTotalPrice +=transportQty*PRICE_TRANSPORTBuggy;
		        }else if("Shuttle Bus".equals(transportOption)) {
		        	currentTotalPrice +=transportQty*PRICE_TRANSPORTShuttleBus;
		        }else if("Bicycle".equals(transportOption)) {
		        	currentTotalPrice +=transportQty*PRICE_TRANSPORTBicycle;
		        }else if("Walking".equals(transportOption)) {
		        	spinnertransportQty.setValue(adult+child);
		        }
		        
		        
		        tFTStotal.setText(String.valueOf(currentTotalPrice));
			}
		});
		
		
		
		// TicketSale Buttons 
		JPanel TSbuttonpanel = new JPanel();
		TSbuttonpanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(108, 108, 108)));
		TSbuttonpanel.setBackground(new Color(255, 253, 233));
		TSbuttonpanel.setPreferredSize(new Dimension(10, 70));
		TSBody.add(TSbuttonpanel, BorderLayout.SOUTH);
		
		JButton btnCPaymentTS = new JButton("Comfirm ");
		btnCPaymentTS.setFocusable(false);
		btnCPaymentTS.setBorder(null);
		btnCPaymentTS.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnCPaymentTS.setPreferredSize(new Dimension(117, 32));
		btnCPaymentTS.setBounds(15, 10, 100, 43);
		btnCPaymentTS.setForeground(new Color(255,253,233));
		btnCPaymentTS.setBackground(new Color(12, 59, 46));
		btnCPaymentTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 adult = (int) spinneradult.getValue();
			     child = (int) spinnerchild.getValue();
				 int male = (int) spinnermale.getValue();
		         int female = (int) spinnerfemale.getValue();
		         transportQty = (int) spinnertransportQty.getValue();
		        
		        String guidedTourStatus = "None";
		        String transportType = (String) transportbox.getSelectedItem();
		//calculating        
		        double currentTotalPrice = (adult * PRICE_ADULT) + (child * PRICE_CHILD);
		         if ( chckbxTour.isSelected()) {
	                 currentTotalPrice += PRICE_GUIDED_TOUR;
	             }
		         if ("Buggy".equals(transportType)) {
			            currentTotalPrice +=transportQty*PRICE_TRANSPORTBuggy;
			        }else if("Shuttle Bus".equals(transportType)) {
			        	currentTotalPrice +=transportQty*PRICE_TRANSPORTShuttleBus;
			        }else if("Bicycle".equals(transportType)) {
			        	currentTotalPrice +=transportQty*PRICE_TRANSPORTBicycle;
			        }else if("Walking".equals(transportType)) {
			        	spinnertransportQty.setValue(adult+child);
			        }
        //calculating
		         
		        String totalPriceText = String.valueOf(currentTotalPrice);
		        
		        if (chckbxTour.isSelected()) {
		            guidedTourStatus = "Guided"; 
		        }
		        
		       
		        db.addDb(male,female,adult,child, guidedTourStatus, transportType,transportQty, totalPriceText);
		        
//		        Object[] rowData = {
//		        		    male,
//		        		    female,
//		                adult,
//		                child,
//		                guidedTourStatus,
//		                transportType,
//		                transportQty,
//		                totalPriceText
//		               
//		            };
//		      
//		        DefaultTableModel model = (DefaultTableModel) TSTable.getModel();
//		        model.addRow(rowData);
//		        
		        try {
					db.showDb();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        
		        
		     //table value center
		          centerRenderer=new DefaultTableCellRenderer();
		          centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		          TSTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		          TSTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		          TSTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		          TSTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		          TSTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		          TSTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		          TSTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		          TSTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		        
		        
		      
		        //Methodcall SQL code
		       
		         //  db.addDb(male,female,adult,child, guidedTourStatus, transportType,transportQty, totalPriceText);
		        
		        
		        spinneradult.setValue(0);
			    spinnerchild.setValue(0);
				spinnermale.setValue(0);
		        spinnerfemale.setValue(0);
		        spinnertransportQty.setValue(0);
		        chckbxTour.setSelected(false);
		        transportbox.setSelectedIndex(0);
		        tFTStotal.setText("");
			}
		});
		
		JButton btnResetTS = new JButton("Reset");
		btnResetTS.setFocusable(false);
		btnResetTS.setBorder(null);
		btnResetTS.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnResetTS.setBounds(125, 10, 100, 43);
		btnResetTS.setForeground(new Color(255, 253, 233));
		btnResetTS.setBackground(new Color(255, 100, 0));
		btnResetTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spinneradult.setValue(0);
			    spinnerchild.setValue(0);
				spinnermale.setValue(0);
		        spinnerfemale.setValue(0);
		        spinnertransportQty.setValue(0);
		        chckbxTour.setSelected(false);
		        transportbox.setSelectedIndex(0);
		        tFTStotal.setText("");
			}
		});
		
		JButton btnExitTS = new JButton("Exit");
		btnExitTS.setFocusable(false);
		btnExitTS.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(227, 72, 72)));
		btnExitTS.setFont(new Font("Arial", Font.PLAIN, 13));
		btnExitTS.setBounds(236, 10, 100, 43);
		btnExitTS.setForeground(new Color(227, 72, 72));
		btnExitTS.setBackground(new Color(255, 253, 233));
		btnExitTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		TSbuttonpanel.setLayout(null);
		TSbuttonpanel.add(btnCPaymentTS);
		TSbuttonpanel.add(btnResetTS);
		TSbuttonpanel.add(btnExitTS);
		
		JPanel Shopping = new JPanel();
		Shopping.setBackground(new Color(255, 255, 255));
		mainbody.add(Shopping, "name_966890708955200");
		Shopping.setLayout(new BorderLayout(0, 0));
		
		JPanel receipt = new JPanel();
		receipt.setBackground(new Color(173, 216, 230));
		receipt.setPreferredSize(new Dimension(300, 10));
		Shopping.add(receipt, BorderLayout.EAST);
		receipt.setLayout(new BorderLayout(0, 0));
		
		JPanel rcbase = new JPanel();
		rcbase.setPreferredSize(new Dimension(10, 150));
		receipt.add(rcbase, BorderLayout.SOUTH);
		rcbase.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel rcFlow = new JPanel();
		rcFlow.setBackground(new Color(12, 59, 46));
		rcFlow.setForeground(new Color(12, 59, 46));
		rcFlow.setPreferredSize(new Dimension(300, 150));
		rcbase.add(rcFlow);
		rcFlow.setLayout(null);
		
		tFTax = new JTextField("0.0", 10);
		tFTax.setHorizontalAlignment(SwingConstants.CENTER);
		tFTax.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		tFTax.setText("0.0");
		tFTax.setEditable(false);
		tFTax.setBounds(168, 11, 96, 29);
		rcFlow.add(tFTax);
		tFTax.setColumns(10);
		
		tFSubtotal = new JTextField();
		tFSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		tFSubtotal.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		tFSubtotal.setText("0.0");
		tFSubtotal.setEditable(false);
		tFSubtotal.setColumns(10);
		tFSubtotal.setBounds(168, 50, 96, 29);
		rcFlow.add(tFSubtotal);
		
		tFgrandTotal = new JTextField();
		tFgrandTotal.setHorizontalAlignment(SwingConstants.CENTER);
		tFgrandTotal.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		tFgrandTotal.setText("0.0");
		tFgrandTotal.setEditable(false);
		tFgrandTotal.setColumns(10);
		tFgrandTotal.setBounds(168, 89, 96, 32);
		rcFlow.add(tFgrandTotal);
		
	     taxField = new JTextField("0.0", 10);
		JLabel taxLabel = new JLabel("Tax");
		taxLabel.setForeground(new Color(255, 253, 233));
		taxLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 19));
		taxLabel.setBounds(25, 11, 85, 29);
		rcFlow.add(taxLabel);
		
	       
	       
	     subTotalField = new JTextField("0.0", 10);
		JLabel subTotalLabel = new JLabel("Sub Total");
		subTotalLabel.setForeground(new Color(255, 253, 233));
		subTotalLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 19));
		subTotalLabel.setBounds(25, 50, 101, 29);
		rcFlow.add(subTotalLabel);
		
		
		 totalField = new JTextField("0.0", 10);
		JLabel grandTotalLabel = new JLabel("Total");
		grandTotalLabel.setForeground(new Color(255, 253, 233));
		grandTotalLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 19));
		grandTotalLabel.setBounds(25, 92, 85, 29);
		rcFlow.add(grandTotalLabel);
		
		textAreaRC = new JTextArea();
		textAreaRC.setBorder(null);
		textAreaRC.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		textAreaRC.setEditable(false);
		receipt.add(textAreaRC, BorderLayout.CENTER);
		textAreaRC.setEditable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane(textAreaRC);
		scrollPane_1.setBorder(null);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// add the scroll pane to the receipt panel
		receipt.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(255, 255, 255));
		panel_10.setPreferredSize(new Dimension(20, 10));
		receipt.add(panel_10, BorderLayout.WEST);
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(15, 10));
		panel_9.setBackground(new Color(255, 255, 255));
		receipt.add(panel_9, BorderLayout.NORTH);
		
		//JScrollPane scrollrcp = new JScrollPane(textAreaRC);
		//receipt.add(scrollrcp, BorderLayout.NORTH);
		
		
		
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(255, 228, 196));
		Shopping.add(menu, BorderLayout.CENTER);
		menu.setLayout(new BorderLayout(0, 0));
		
		JPanel btnpannel = new JPanel();
		btnpannel.setForeground(new Color(255, 253, 233));
		btnpannel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(108, 108, 108)));
		btnpannel.setBackground(new Color(255, 253, 233));
		btnpannel.setPreferredSize(new Dimension(10, 48));
		menu.add(btnpannel, BorderLayout.SOUTH);
		
		JButton btnTotal = new JButton("Confirm");
		btnTotal.setMargin(new Insets(2, 24, 2, 24));
		btnTotal.setForeground(new Color(206, 241, 123));
		btnTotal.setBackground(new Color(12, 59, 46));
		btnTotal.setFocusable(false);
		btnTotal.setBorder(null);
		btnTotal.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 JOptionPane.showMessageDialog(null, "Sale Process Successful!!","Sale Process Info" , JOptionPane.INFORMATION_MESSAGE);
				resetAll();
				textAreaRC.setText("");;
				
			}
			
		
		});
		btnTotal.setPreferredSize(new Dimension(80, 38));
		btnpannel.add(btnTotal);
		
		JButton btnReceipt = new JButton("Receipt");
		btnReceipt.setForeground(new Color(12, 59, 46));
		btnReceipt.setBackground(new Color(206, 241, 123));
		btnReceipt.setBorder(null);
		btnReceipt.setFocusable(false);
		btnReceipt.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateReceipt();
			}
		});
		btnReceipt.setPreferredSize(new Dimension(80, 38));
		btnpannel.add(btnReceipt);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(new Color(255, 253, 233));
		btnReset.setBackground(new Color(255, 100, 0));
		btnReset.setFocusable(false);
		btnReset.setBorder(null);
		btnReset.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAll();
//				generateReceipt();
			}
		});
		btnReset.setPreferredSize(new Dimension(80, 38));
		btnpannel.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setForeground(new Color(227, 72, 72));
		btnExit.setBackground(new Color(255, 253, 233));
		btnExit.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(227, 72, 72)));
		btnExit.setFocusable(false);
		btnExit.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnExit.setPreferredSize(new Dimension(80, 38));
		btnpannel.add(btnExit);
		
		JPanel bodypanel = new JPanel();
		menu.add(bodypanel, BorderLayout.CENTER);
		bodypanel.setLayout(new BorderLayout(0, 0));
		
		menuheader = new JPanel();
		menuheader.setBorder(new MatteBorder(1, 2, 2, 2, (Color) new Color(108, 108, 108)));
		menuheader.setForeground(new Color(254, 202, 89));
		menuheader.setBackground(new Color(255, 253, 233));
		menuheader.setPreferredSize(new Dimension(10, 45));
		bodypanel.add(menuheader, BorderLayout.NORTH);
		
		JLabel lblMenuitems = new JLabel("MENU ITEMS");
		lblMenuitems.setBounds(new Rectangle(0, 15, 0, 0));
		lblMenuitems.setForeground(new Color(12, 59, 46));
		lblMenuitems.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 22));
		lblMenuitems.setHorizontalAlignment(SwingConstants.CENTER);
		menuheader.add(lblMenuitems);
		
		JScrollPane scrollPane = new JScrollPane(panelmenu);
		scrollPane.setBorder(null);
		bodypanel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		panelmenu = new JPanel();
		panelmenu.setPreferredSize(new Dimension(500, 10));
		panelmenu.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(panelmenu);
		panelmenu.setLayout(new GridLayout(0, 3, 5,5));
        
		
		    MenuItem iceCream = new MenuItem("Ice Cream", 1500.0,new Color(255, 240, 245));
		    MenuItem friedChicken = new MenuItem("Fried Chicken", 2500.0,new Color(255, 240, 245));
		    MenuItem fishCake = new MenuItem("Fish  cake",1500.0,new Color(255, 240, 245));
		    MenuItem CocaCola = new MenuItem("Coca Cola",1300.0,new Color(255, 240, 245));
		    MenuItem potatochips = new MenuItem("Potato Chips",1500.0,new Color(255, 240, 245));
		    MenuItem Burger   = new MenuItem("Burger",4500.0,new Color(255, 240, 245));
		    MenuItem Sandwich = new MenuItem("Sandwich",3500.0,new Color(255, 240, 245));
		    MenuItem macaroons = new MenuItem("Macaroons",5000.0,new Color(255, 240, 245));
		    MenuItem MangoSalad = new MenuItem("Mango Slices",2000.0,new Color(255, 240, 245));
		    MenuItem water = new MenuItem("Water",500.0,new Color(255, 240, 245));
		    MenuItem coffee = new MenuItem("Coffee",3500.0,new Color(255, 240, 245));
		    MenuItem tea = new MenuItem("Tea",1900.0,new Color(255, 240, 245));
		    MenuItem hotdog = new MenuItem("Hot Dog",3500.0,new Color(255, 240, 245));
		    MenuItem matcha = new MenuItem("Matcha",3200.0,new Color(255, 240, 245));
		    MenuItem LemonTea = new MenuItem("Lemon Tea",1600.0,new Color(255, 240, 245));
		   
		    
	        menuItems.add(iceCream);
	        menuItems.add(friedChicken);
	        menuItems.add(fishCake);
	        menuItems.add(CocaCola);
	        menuItems.add(potatochips);
	        menuItems.add(Burger);
	        menuItems.add(Sandwich);
	        menuItems.add(macaroons);
	        menuItems.add(MangoSalad);
	        menuItems.add(water);
	        menuItems.add(coffee);
	        menuItems.add(tea);
	        menuItems.add(hotdog);
	        menuItems.add(matcha);
	        menuItems.add(LemonTea);
	        
	        
	        
	        for (MenuItem item : menuItems) { //
	        	 
				  panelmenu.add(createMenuItemUI(item));}
			      panelmenu.revalidate();
			      panelmenu.repaint();
	        
	        
	        
	       
	
		
		JPanel headerbar = new JPanel();
		headerbar.setBorder(new MatteBorder(0, 1, 0, 1, (Color) new Color(108, 108, 108)));
		headerbar.setForeground(new Color(0, 50, 35));
		headerbar.setPreferredSize(new Dimension(10, 53));
		headerbar.setBackground(new Color(0, 50, 35));
		basepanelmain.add(headerbar, BorderLayout.NORTH);
		
		JButton btnticketsale = new JButton("TICKET SALES");
		btnticketsale.setForeground(new Color(0, 50, 35));
		btnticketsale.setMargin(new Insets(9, 14, 8, 14));
		btnticketsale.setFocusable(false);
		btnticketsale.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnticketsale.setBorder(null);
		btnticketsale.setPreferredSize(new Dimension(450, 43));
		btnticketsale.setBackground(new Color(206, 241, 123));
		headerbar.add(btnticketsale);
		
		JButton btnshopping = new JButton("SHOPPING");
		btnshopping.setForeground(new Color(206, 241, 123));
		btnshopping.setMargin(new Insets(9, 14, 8, 14));
		btnshopping.setFocusable(false);
		btnshopping.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnshopping.setBorder(null);
		btnshopping.setPreferredSize(new Dimension(450, 43));
		btnshopping.setBackground(new Color(0,50,35));
		headerbar.add(btnshopping);
		
		
		
		btnticketsale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainbody.removeAll();
				mainbody.add(TicketSale);
				mainbody.repaint();
				mainbody.revalidate();
				btnticketsale.setBackground(new Color(206, 241, 123));
				btnticketsale.setForeground(new Color(0,50,35));
				btnshopping.setBackground(new Color(0,50,35));
				btnshopping.setForeground(new Color(206, 241, 123));
				
			}
		});
		
		btnshopping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainbody.removeAll();
				mainbody.add(Shopping);
				mainbody.repaint();
				mainbody.revalidate();
				btnshopping.setBackground(new Color(206, 241, 123));
				btnshopping.setForeground(new Color(0, 50, 35));
				btnticketsale.setBackground(new Color(0, 50, 35));
				btnticketsale.setForeground(new Color(206, 241, 123));
			}
		});}
		
	
	 private JPanel createMenuItemUI(MenuItem item) {
		   JPanel panel = new JPanel(new GridLayout(2, 3, 5, 5));
		    panel.setPreferredSize(new Dimension(500, 10));
		    panel.setBackground(new Color(255, 253, 233));
		    panel.setBorder(BorderFactory.createLineBorder(new Color(202, 202, 202),2));

		    // Row 0
		    JLabel nameLabel = new JLabel(item.getName());
		    nameLabel.setBounds(15, 0, 50, 0);
		    nameLabel.setForeground(new Color(8, 71, 52));
		    nameLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 16));
		    panel.add(nameLabel);

		    JLabel priceLabel = new JLabel(": " + item.getPrice()+" Ks");
		    priceLabel.setForeground(new Color(255, 100, 0));
		    priceLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
		    panel.add(priceLabel);

		    panel.add(new JLabel("")); // empty space for alignment

		    // Row 1
		    JLabel quantityLbl = new JLabel("Quantity:");
		    quantityLbl.setForeground(new Color(8, 71, 52));
		    quantityLbl.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
		    panel.add(quantityLbl);
		    

		    SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 100, 1);
		    JSpinner quantitySpinner = new JSpinner(model);
		    quantitySpinner.setFont(new Font("Arial", Font.PLAIN, 14));
		    panel.add(quantitySpinner);

		    JCheckBox purchaseCheckBox = new JCheckBox("Select");
		    purchaseCheckBox.setBackground(new Color(206, 241, 123));
		    purchaseCheckBox.setForeground(new Color(8, 71, 52));
		    purchaseCheckBox.setFocusable(false);
		    panel.add(purchaseCheckBox);

		    // Listeners
		    quantitySpinner.addChangeListener(e -> item.setQuantity((int) quantitySpinner.getValue()));
		    purchaseCheckBox.addItemListener(e -> item.setSelected(purchaseCheckBox.isSelected()));

		    return panel;
	 
	    
	    
		/*
		 * panel.add(nameLabel); panel.add(priceLabel); panel.add(new
		 * JLabel("Quantity:")); panel.add(quantitySpinner);
		 * panel.add(purchaseCheckBox);
		 */
	   
	    
	    
	}

	
	 
private void updateTotalSpinnerMax() {
	adult = (int) spinneradult.getValue();
	child = (int) spinnerchild.getValue();
	totalCust=adult+child;
	
	SpinnerNumberModel totalModel=(SpinnerNumberModel)spinnermale.getModel();
	totalModel.setMaximum(totalCust);
	
	
	if((int)totalModel.getValue()>totalCust) {
		totalModel.setValue(totalCust);
	}
}

private void updateLeftSpinnerValue() {
	int male=(int)spinnermale.getValue();

	updateTotalSpinnerMax();
	leftCust=totalCust-male;
	SpinnerNumberModel leftModel=(SpinnerNumberModel)spinnerfemale.getModel();
	leftModel.setMaximum(leftCust);
	leftModel.setValue(leftCust);

	if((int)leftModel.getValue() > leftCust) {
	leftModel.setValue(leftCust);}
    if((int)leftModel.getValue()== -1) {
    	leftModel.setValue(0);
    	

    }
}


private void calculateTotal() {
	
	System.out.println();
double subTotal = 0.0;

for (MenuItem item : menuItems) {
    if (item.isSelected()) {
        subTotal += item.getPrice() * item.getQuantity();
        
        
    }
}

double tax = subTotal * 0.05; // 5% tax
double total = subTotal + tax;

subTotalField.setText(String.format("%.2f", subTotal));
taxField.setText(String.format("%.2f", tax));
totalField.setText(String.format("%.2f", total));


tFgrandTotal.setText(String.valueOf(total));
tFSubtotal.setText(String.valueOf(subTotal));
tFTax.setText(String.valueOf(tax));}
//textAreaRC.setText(receiptText.toString());

private void resetAll() {
    // Reset the data model for each menu item
    for (MenuItem item : menuItems) {
        item.setQuantity(0);
        item.setSelected(false);
    }
    
    // Reset the calculation fields to zero
    tFTax.setText("0.0");
    tFSubtotal.setText("0.0");
    tFgrandTotal.setText("0.0");

    // Rebuild the UI to reflect the reset data
    // This is the most crucial step
    panelmenu.removeAll();
    for (MenuItem item : menuItems) {
        panelmenu.add(createMenuItemUI(item));
    }
    
    panelmenu.revalidate();
    panelmenu.repaint();}
    
    private void generateReceipt() {
        // Make sure the totals are up-to-date
        calculateTotal();
        
        // Build the receipt string
        StringBuilder receiptText = new StringBuilder();
        receiptText.append("-------- HELLO WORLD SHOP ------- \n");
        receiptText.append("---------------------------------\n");
        receiptText.append(String.format("%-16s %-9s %-15s\n", "ITEM", "QTY", "PRICE"));
        receiptText.append("---------------------------------\n");

        double subTotal = 0.0;

        // Loop through the list to get details of purchased items
        for (MenuItem item : menuItems) {
            if (item.isSelected() && item.getQuantity() > 0) {
                String name = item.getName();
                int quantity = item.getQuantity();
                double price = item.getPrice();
                double itemTotal = price * quantity;
                
                subTotal += itemTotal;
                
                // Append the item details to the receipt string
                receiptText.append(String.format("%-16s %-9d $%.2f\n", name, quantity ,itemTotal));
            }
        }

        // Add totals to the receipt
        receiptText.append("---------------------------------\n");
        double tax = subTotal * 0.05;
        double grandTotal = subTotal + tax;
        
        receiptText.append(String.format("Sub Total:    \t\t$%.2f\n", subTotal));
        receiptText.append(String.format("Tax (5%%):    \t\t$%.2f\n", tax));
        receiptText.append(String.format("Grand Total:  \t\t$%.2f\n", grandTotal));
        receiptText.append("-----Thank You For Purchasing----\n");
        receiptText.append("---------------------------------\n");
        
        // Update the JTextArea with the generated receipt string
        textAreaRC.setText(receiptText.toString());
        textAreaRC.setFont(new Font("Monospaced", Font.PLAIN, 12));
    

}	


// SQL
public class DbConnection{
	//constructor
	 Connection con;
	 final String url = "jdbc:mysql://localhost:3306/ZooManagementSystem";
	 final String username = "root";
	 final String Password = "mintOfficial2BE4$";
	 
	public DbConnection() {
	
	try {
		con = DriverManager.getConnection(url,username,Password);
		System.out.println("connected");
	}
	catch (SQLException e) {
		System.out.println("failed");
		e.printStackTrace();
	};
	} 
    // constructor 
	
	//SQL codes 
	
	public void addDb (int male ,int female,int adult,int child, String  guidedTourStatus,String transportType,int transportQty,String totalPriceText) {
		String sql= "INSERT INTO ticketsales (Male,Female,Adult,Child,Tour,Transport,Transport_Quantity,TotalPrice)" + " VALUES (?,?,?,?,?,?,?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, male);
                stmt.setInt(2, female);
                stmt.setInt(3, adult);
                stmt.setInt(4, child);
                stmt.setString(5, guidedTourStatus);
                stmt.setString(6, transportType);
                stmt.setInt(7, transportQty);
                stmt.setString(8, totalPriceText);

                stmt.executeUpdate();
                System.out.println("✅  inserted successfully.");

        } catch (SQLException e) {
            System.out.println("❌ Failed to insert.");
            e.printStackTrace();
        }
	};
	
	public  void showDb() throws SQLException {
		ticketSalesArrayList.clear();
		totalCustomerCount=0;
		totalTicketSales=0;
		
		
		Statement stmt = con.createStatement();
		
        ResultSet rs = stmt.executeQuery("SELECT * FROM ticketsales WHERE DATE(Sale_Date)=CURRENT_DATE() ORDER BY Sale_Date DESC;");
        
        while (rs.next()) {
        	
            int MALE = rs.getInt("Male");
            int FEMALE = rs.getInt("Female");
            totalCustomerCount+= MALE+FEMALE;
            int ADULT = rs.getInt("Adult");
            int CHILD = rs.getInt("Child");
            String TOUR = rs.getString("Tour");
            String TRANSPORT = rs.getString("Transport");
            int QTY = rs.getInt("Transport_Quantity");
            double PRICE =Double.parseDouble(rs.getString("TotalPrice")) ;
            totalTicketSales+= PRICE;
        
            ticketSales_info tSi = new ticketSales_info(MALE, FEMALE, ADULT, CHILD, TOUR, TRANSPORT, QTY, PRICE);
            ticketSalesArrayList.add(tSi);
            
            String[] columnName = new String[]{
                    "Male", "Female", "Adult", "Child", "Tour", "Transport", "Transport Quantity",  "Total Price"
            };

            // Prepare data for the table model
            Object[][] data = new Object[ticketSalesArrayList.size()][8];
            for (int i = 0; i < ticketSalesArrayList.size(); i++) {
                data[i][0] = ticketSalesArrayList.get(i).getMale();
                data[i][1] = ticketSalesArrayList.get(i).getFemale();
                data[i][2] = ticketSalesArrayList.get(i).getAdult();
                data[i][3] = ticketSalesArrayList.get(i).getChild();
                data[i][4] = ticketSalesArrayList.get(i).getTour();
                data[i][5] = ticketSalesArrayList.get(i).getTransport();
                data[i][6] = ticketSalesArrayList.get(i).getTransport_QTY();      
                data[i][7] = ticketSalesArrayList.get(i).getTotalPrice();
                
	}
            DefaultTableModel model = new DefaultTableModel(data, columnName) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make all cells non-editable
                }
            };
           
    		scrollPaneTStable.setViewportView(TSTable);
    		
            TSTable.setModel(model);
            // Ensure the table headers are displayed if they sometimes disappear
            TSTable.getTableHeader().repaint();
            TSTable.repaint();
            TSTable.revalidate();
            
            
	
        }}
	
	
}}
	    	
	   

	

