package body;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
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
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import welcome.SignIn_Form;
import welcome.User;

import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.ComponentOrientation;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.interfaces.RSAKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;

public class Inventory_Management extends JFrame {
	
	
//Medical Supplies
	static ArrayList<InventoryProduct> medArrayList = new ArrayList<InventoryProduct>();
	static ArrayList<InventoryProduct> MedInStockArrayList = new ArrayList<InventoryProduct>();
    static ArrayList<InventoryProduct> MedLowStockArrayList = new ArrayList<InventoryProduct>();
    static ArrayList<InventoryProduct> MedOutOfStockArrayList = new ArrayList<InventoryProduct>();
    static ArrayList<InventoryProduct> MedNearExpiryProductsArrayList = new ArrayList<InventoryProduct>();
    static ArrayList<InventoryProduct> MedExpiredProductsArrayList = new ArrayList<InventoryProduct>();
    
    JLabel medNearExpItemlbl;
    JLabel medinStocklbl;
	JLabel medLowStocklbl;
    JLabel medOutOfStocklbl;
	JLabel medTotallbl;
	private JTable MedicalSuppliesTable;
	
	static String[] columnName;
	static String selectedMedFilter=""; 
    static int medicalItemTotal;
    static int boxMedInStockStatus;
    static int boxMedLowStockStatus;
    static int boxMedOutOfStockStatus;
    static int medNearExpiryDate;
    static int medAlreadyExpired;
    private TableColumn Medcolumn;
    private DefaultTableCellRenderer centerRenderer;
    private static String SearchMed;
	private static Boolean medNearExpiryCheck=false;
	private static Boolean medExpiredCheck=false;
    
 //system     
	static JLabel lblInvestmentValueTotal;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    static int selectedRowIndex; 
    LocalDateTime localDateTime;
    String formattedDateTime;
    DateTimeFormatter dateTimeFormatter;
    static private double InvestmentValueTotal;
    static private double MedInvestmentValue;
    static private double FoodInvestmentValue;
 //for medic product id 
    static String FinalProduct_id;
    static String productidFormattedFourNums;
    static int product_id_int_num;
 //for food product id
    static String FinalfProduct_id;
    static String fproductidFormattedFourNums;
    static int fproduct_id_int_num;
    
    

    
//Food Items
    static ArrayList<InventoryProduct> foodArrayList = new ArrayList<InventoryProduct>();
    static ArrayList<InventoryProduct> FoodInStockArrayList = new ArrayList<InventoryProduct>();
    static ArrayList<InventoryProduct> FoodLowStockArrayList = new ArrayList<InventoryProduct>();
    static ArrayList<InventoryProduct> FoodOutOfStockArrayList = new ArrayList<InventoryProduct>();
    static ArrayList<InventoryProduct> FoodNearExpiryProductsArrayList = new ArrayList<InventoryProduct>();
    static ArrayList<InventoryProduct> FoodExpiredProductsArrayList = new ArrayList<InventoryProduct>();
    
    JLabel foodNearExpItemlbl;
    JLabel foodinStocklbl;
	JLabel foodLowStocklbl;
    JLabel foodOutOfStocklbl;
	JLabel foodTotallbl;
    private JTable FoodItemsTable;
    static String [] fcolumnName;
    static String selectedFoodFilter=""; 
    static int foodItemTotal;
    static int boxFoodInStockStatus;
    static int boxFoodLowStockStatus;
    static int boxFoodOutOfStockStatus;
    static int foodNearExpiryDate;
    static int foodAlreadyExpired;
    private TableColumn Foodcolumn;
    private JTextField MedicSearchtextField;
    private JTextField FoodSearchtextField;
    private static String SearchFood;
    private static Boolean foodNearExpiryCheck=false;
    private static Boolean foodExpiredCheck=false;
    
//system function
    public String delete(JTable table) {
        JTable jtable = table;
        selectedRowIndex = jtable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jtable.getModel();
        String productID = (model.getValueAt(selectedRowIndex, 1)).toString();
        return productID;
    }
 
    public String stockStatus(int stock) {
        if (stock > 10) {
            return "In Stock";
        } else if (stock <= 10 && stock > 0) {
            return "Low Stock";
        } else if (stock == 0) {
            return "Out Of Stock";
        }
        return null;
    }
    
    
  //medical function
    public void medicNearExpiryCheck(String expString) {   
    	LocalDate todayDate=LocalDate.now();	
    // String -> LocalDate ပြောင်း
    	DateTimeFormatter myLDateFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	LocalDate expDate=LocalDate.parse(expString,myLDateFormatter);
    	
    // ရက်ကြာချိန်
    	long daysLeft=ChronoUnit.DAYS.between(todayDate,expDate);
        if (daysLeft<=10 && daysLeft>=0) {
        	 medNearExpiryCheck=true;
        	 medExpiredCheck=false;
        	 medNearExpiryDate+=1; 
        	 medNearExpItemlbl.setText(String.valueOf(medNearExpiryDate));
        	 System.out.println("Near expiry! Only " + daysLeft + " days left.");
        }
        else if(daysLeft<0) {
        	 medAlreadyExpired+=1;
         	 System.out.println("Already expired.");
         	medExpiredCheck=true;
         	medNearExpiryCheck=false;
        }else {
             System.out.println("Still valid. " + daysLeft + " days remaining.");
             medNearExpiryCheck=false;
             medExpiredCheck=false;
        }	
    }
 
    
    //food function
    public void foodNearExpiryCheck(String expString) {   
    	LocalDate todayDate=LocalDate.now();	
    // String -> LocalDate ပြောင်း
    	DateTimeFormatter myLDateFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	LocalDate expDate=LocalDate.parse(expString,myLDateFormatter);
    	
    // ရက်ကြာချိန်
    	long daysLeft=ChronoUnit.DAYS.between(todayDate,expDate);
        if (daysLeft<=10 && daysLeft>=0) {
        	 foodNearExpiryDate+=1; 
        	 foodNearExpiryCheck=true;
        	 foodExpiredCheck=false;
        	 foodNearExpItemlbl.setText(String.valueOf(foodNearExpiryDate));
        	 System.out.println("Near expiry! Only " + daysLeft + " days left.");
        }
        else if(daysLeft<0) {
        	 foodAlreadyExpired+=1;
         	 System.out.println("Already expired.");
         	foodExpiredCheck=true;
         	foodNearExpiryCheck=false;
        }else {
             System.out.println("Still valid. " + daysLeft + " days remaining.");
             foodNearExpiryCheck=false;
             foodExpiredCheck=false;
        }	
    }
    
    
    
    private static void setupPlaceholder(JTextField textfield,String placeholderText,Color placeholderColor) {
    	textfield.setText(placeholderText);
    	textfield.setForeground(placeholderColor);
    	
    	String originalPlaceholder=placeholderText;
    	Color originalPlaceholderColor=placeholderColor;
    
    	textfield.addFocusListener(new FocusAdapter() {
            @Override
    		public void focusGained(FocusEvent e) {
    			if(textfield.getText().equals(originalPlaceholder) && textfield.getForeground().equals(originalPlaceholderColor)) {
    				textfield.setText("");
    				textfield.setForeground(UIManager.getColor("textText"));
    			}
    		}
            @Override
    		public void focusLost(FocusEvent e) {
    			if(textfield.getText().isEmpty()) {
    				textfield.setText(originalPlaceholder);
    				textfield.setForeground(originalPlaceholderColor);
    			}
    		}
    		
    	});
    }
    
    
    public static void calculateTotalInvestment() {
    	InvestmentValueTotal=MedInvestmentValue+FoodInvestmentValue;
        lblInvestmentValueTotal.setText(String.valueOf(InvestmentValueTotal)+" Ks");
    }
    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {


        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Window Classic".equals(info.getName())) { //"Windows Classic" "Windows" "Nimbus","Mac OS X", "Metal" စသည်တို့ကိုလည်း စမ်းကြည့်နိုင်
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the system default
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        SwingUtilities.invokeLater(() -> {
            try {
                Inventory_Management frame = new Inventory_Management();
                frame.setVisible(true);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     * @throws SQLException
     */
    public Inventory_Management() throws SQLException {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Inventory_Management.class.getResource("/icon/zoo_logo_nobg.png")));
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
					int extendedValue = getExtendedState();
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
        dashboardIcon.setIcon(new ImageIcon(Inventory_Management.class.getResource("/icon/dashboardSidebarIcon_bgGreen.png")));
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
        lblNewLabel_1_1_1_2.setBorder(null);
        lblNewLabel_1_1_1_2.setBounds(0, 10, 85, 40);
        lblNewLabel_1_1_1_2.setIcon(new ImageIcon(Inventory_Management.class.getResource("/icon/resized_GreenBg_ZooLogo2.png")));
        lblNewLabel_1_1_1_2.setPreferredSize(new Dimension(15, 15));
        lblNewLabel_1_1_1_2.setForeground(new Color(255, 100, 0));
        logo.add(lblNewLabel_1_1_1_2);

        JPanel animalMenu = new JPanel();
        animalMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Animal_Management an = new Animal_Management();
                int extendedValue = getExtendedState();
                an.setExtendedState(extendedValue);
                an.setVisible(true);
                setVisible(false);
            }
        });
        animalMenu.setLayout(null);
        animalMenu.setBackground(new Color(0, 50, 35));
        animalMenu.setBounds(5, 148, 80, 56);
        siderBar.add(animalMenu);

        JLabel animalIcon = new JLabel("ICON");
        animalIcon.setIcon(new ImageIcon(Inventory_Management.class.getResource("/icon/animalSidebarIcon_bgGreen.png")));
        animalIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Animal_Management an = new Animal_Management();
                int extendedValue = getExtendedState();
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

        JLabel staffIcon = new JLabel("ICON");
        staffIcon.setIcon(new ImageIcon(Inventory_Management.class.getResource("/icon/staffSidebaricon_bgGreen.png")));
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
					int extendedValue = getExtendedState();
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
        visitorIcon.setIcon(new ImageIcon(Inventory_Management.class.getResource("/icon/visitorSidebarIcon_bgGreen.png")));
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
        }});
        inventoryMenu.setLayout(null);
        inventoryMenu.setBackground(new Color(255, 253, 233));
        inventoryMenu.setBounds(5, 364, 80, 56);
        siderBar.add(inventoryMenu);

        JLabel inventoryIcon = new JLabel("");
        inventoryIcon.setIcon(new ImageIcon(Inventory_Management.class.getResource("/icon/inventorySidebarIcon.png")));
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
        }});
        inventoryMenu.add(inventoryIcon);

        JLabel lblMenuInventory = new JLabel("Inventory");
        lblMenuInventory.setForeground(new Color(0, 50, 35));
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
        }});
        inventoryMenu.add(lblMenuInventory);

        JPanel body = new JPanel();
        body.setBorder(null);
        body.setBackground(new Color(255, 253, 233));
        body.setPreferredSize(new Dimension(100, 65));
        contentPane.add(body, BorderLayout.CENTER);
        body.setLayout(new BorderLayout(0, 0));

        JPanel header = new JPanel();
        header.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(108, 108, 108)));
        header.setBackground(new Color(140, 200, 80));
        header.setPreferredSize(new Dimension(100, 60));
        body.add(header, BorderLayout.NORTH);
        header.setLayout(new BorderLayout(0, 0));

        JPanel headerLeftPanel = new JPanel();
        headerLeftPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(108, 108, 108)));
        headerLeftPanel.setForeground(new Color(12, 59, 46));
        headerLeftPanel.setPreferredSize(new Dimension(530, 10));
        headerLeftPanel.setBackground(new Color(12, 59, 46));
        header.add(headerLeftPanel, BorderLayout.WEST);
        headerLeftPanel.setLayout(null);

        JLabel lblPageNameInventory = new JLabel("Inventory Management");
        lblPageNameInventory.setForeground(new Color(206, 241, 123));
        lblPageNameInventory.setBackground(new Color(255, 100, 0));
        lblPageNameInventory.setBounds(20, 9, 510, 36);
        lblPageNameInventory.setName(" ");
        lblPageNameInventory.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 30));
        headerLeftPanel.add(lblPageNameInventory);

        JPanel headerRightPanel = new JPanel();
        headerRightPanel.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(108, 108, 108)));
        headerRightPanel.setBackground(new Color(12, 59, 46));
        headerRightPanel.setPreferredSize(new Dimension(200, 10));
        header.add(headerRightPanel, BorderLayout.EAST);
        headerRightPanel.setLayout(null);

        JLabel lblHeaderRole = new JLabel("Admin");
        lblHeaderRole.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeaderRole.setForeground(new Color(206, 241, 123));
        lblHeaderRole.setFont(new Font("Arial", Font.BOLD, 12));
        lblHeaderRole.setBounds(26, 31, 96, 14);
        headerRightPanel.add(lblHeaderRole);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Inventory_Management.class.getResource("/icon/userProfileIconGreenGreen.png")));
        lblNewLabel_2.setBackground(new Color(255, 100, 0));
        lblNewLabel_2.setForeground(new Color(255, 100, 0));
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
        
        JPanel panel_16 = new JPanel();
        panel_16.setBackground(new Color(45, 100, 43));
        header.add(panel_16, BorderLayout.CENTER);
        panel_16.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_17 = new JPanel();
        panel_17.setForeground(new Color(0, 0, 0));
        panel_17.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(108, 108, 108)));
        panel_17.setBackground(new Color(12, 59, 46));
        panel_17.setPreferredSize(new Dimension(150, 10));
        panel_16.add(panel_17, BorderLayout.EAST);
        panel_17.setLayout(null);
        
        JLabel lblToday = new JLabel("Today ");
        lblToday.setForeground(new Color(255, 253, 233));
        lblToday.setBounds(25, 12, 69, 14);
        lblToday.setPreferredSize(new Dimension(42, 20));
        lblToday.setFont(new Font("Arial", Font.BOLD, 12));
        panel_17.add(lblToday);
        
        JLabel lblHeaderLocalDateTime = new JLabel("");
        lblHeaderLocalDateTime.setBounds(25, 28, 85, 16);
        panel_17.add(lblHeaderLocalDateTime);
        lblHeaderLocalDateTime.setForeground(new Color(206, 241, 123));
        lblHeaderLocalDateTime.setPreferredSize(new Dimension(80, 17));
        lblHeaderLocalDateTime.setFont(new Font("Arial", Font.BOLD, 16));
        localDateTime=LocalDateTime.now();
        dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        formattedDateTime=localDateTime.format(dateTimeFormatter); 
        lblHeaderLocalDateTime.setText(formattedDateTime);
        
        JPanel panel_19 = new JPanel();
        panel_19.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(108, 108, 108)));
        panel_19.setForeground(new Color(12, 59, 46));
        panel_19.setBackground(new Color(12, 59, 46));
        panel_16.add(panel_19, BorderLayout.CENTER);
        panel_19.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        
        // --- START CHANGES HERE ---
        // Replaced JScrollPane with JPanel
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBackground(new Color(255, 253, 233));
        // Use BoxLayout to stack inventoryBoxPanel and tablePanel vertically
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        body.add(mainContentPanel, BorderLayout.CENTER); // Add this new panel to the body

        JPanel inventoryBoxPanel = new JPanel();
        inventoryBoxPanel.setBackground(new Color(255, 253, 233));
        // Set preferred size for inventoryBoxPanel directly, as it's not in a scroll pane anymore
        inventoryBoxPanel.setPreferredSize(new Dimension(90, 150));
        inventoryBoxPanel.setMinimumSize(new Dimension(90, 150)); // Add min size for BoxLayout
        inventoryBoxPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 150)); // Add max size to prevent stretching
        mainContentPanel.add(inventoryBoxPanel); // Add to the new mainContentPanel
        inventoryBoxPanel.setLayout(new BorderLayout(0, 0));

        JPanel leftBorderPanel = new JPanel();
        leftBorderPanel.setForeground(new Color(255, 253, 233));
        leftBorderPanel.setBackground(new Color(255, 253, 233));
        leftBorderPanel.setPreferredSize(new Dimension(15, 10));
        inventoryBoxPanel.add(leftBorderPanel, BorderLayout.WEST);

        JPanel rightBorderPanel = new JPanel();
        rightBorderPanel.setBackground(new Color(255, 253, 233));
        rightBorderPanel.setPreferredSize(new Dimension(15, 10));
        inventoryBoxPanel.add(rightBorderPanel, BorderLayout.EAST);

        JPanel centerBoxPanel = new JPanel();
        centerBoxPanel.setBackground(new Color(255, 253, 233));
        inventoryBoxPanel.add(centerBoxPanel, BorderLayout.CENTER);
        centerBoxPanel.setLayout(new BoxLayout(centerBoxPanel, BoxLayout.X_AXIS));

        JPanel box1containerPanel = new JPanel();
        box1containerPanel.setPreferredSize(new Dimension(220, 120));
        box1containerPanel.setBackground(new Color(0, 50, 35));
        centerBoxPanel.add(box1containerPanel);

        JPanel box1panel = new JPanel();
        box1panel.setForeground(new Color(206, 241, 123));
        box1panel.setBorder(new LineBorder(new Color(0, 50, 35), 2, true));
        box1panel.setPreferredSize(new Dimension(220, 130));
        box1panel.setBackground(new Color(206, 241, 123));
        box1containerPanel.add(box1panel);
        box1panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("TOTAL PRODUCTS");
        lblNewLabel.setForeground(new Color(12, 59, 46));
        lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
        lblNewLabel.setBounds(15, 9, 175, 37);
        box1panel.add(lblNewLabel);

        JPanel panel_13 = new JPanel();
        panel_13.setBackground(new Color(255, 255, 255));
        panel_13.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(215, 215, 215), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
        panel_13.setBounds(3, 52, 108, 76);
        box1panel.add(panel_13);
        panel_13.setLayout(null);

        JLabel lblNewLabel_4 = new JLabel("Medical");
        lblNewLabel_4.setForeground(new Color(0, 0, 0));
        lblNewLabel_4.setIcon(null);
        lblNewLabel_4.setBounds(18, 1, 73, 40);
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("BankGothic Md BT", Font.PLAIN, 15));
        panel_13.add(lblNewLabel_4);

        medTotallbl = new JLabel();
        medTotallbl.setBounds(8, 39, 93, 32);
        panel_13.add(medTotallbl);
        medTotallbl.setForeground(new Color(255, 100, 0));
        medTotallbl.setHorizontalAlignment(SwingConstants.CENTER);
        medTotallbl.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));
        

        JPanel panel_13_1 = new JPanel();
        panel_13_1.setBackground(new Color(255, 255, 255));
        panel_13_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(215, 215, 215), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
        panel_13_1.setBounds(110, 52, 108, 76);
        box1panel.add(panel_13_1);
        panel_13_1.setLayout(null);

        JLabel lblNewLabel_4_1 = new JLabel("Food");
        lblNewLabel_4_1.setIcon(null);
        lblNewLabel_4_1.setBounds(28, 1, 53, 40);
        lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1.setFont(new Font("BankGothic Md BT", Font.PLAIN, 15));
        panel_13_1.add(lblNewLabel_4_1);

        foodTotallbl = new JLabel();
        foodTotallbl.setBounds(8, 39, 93, 32);
        panel_13_1.add(foodTotallbl);
        foodTotallbl.setForeground(new Color(255, 100, 0));
        foodTotallbl.setHorizontalAlignment(SwingConstants.CENTER);
        foodTotallbl.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));

        JPanel box2containerPanel = new JPanel();
        box2containerPanel.setPreferredSize(new Dimension(220, 120));
        box2containerPanel.setBackground(new Color(0, 50, 35));
        centerBoxPanel.add(box2containerPanel);

        JPanel box2panel = new JPanel();
        box2panel.setBorder(new LineBorder(new Color(0, 50, 35), 2, true));
        box2panel.setPreferredSize(new Dimension(220, 130));
        box2panel.setBackground(new Color(206, 241, 123));
        box2containerPanel.add(box2panel);
        box2panel.setLayout(null);

        JLabel lblNearExpireDate = new JLabel("NEAR EXPIRY PRODUCTS");
        lblNearExpireDate.setForeground(new Color(12, 59, 46));
        lblNearExpireDate.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
        lblNearExpireDate.setBounds(15, 9, 203, 37);
        box2panel.add(lblNearExpireDate);

        JPanel panel_13_2 = new JPanel();
        panel_13_2.setBackground(new Color(255, 255, 255));
        panel_13_2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(215, 215, 215), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
        panel_13_2.setBounds(3, 52, 108, 76);
        box2panel.add(panel_13_2);
        panel_13_2.setLayout(null);

        medNearExpItemlbl = new JLabel("-");
        medNearExpItemlbl.setBounds(8, 39, 93, 32);
        panel_13_2.add(medNearExpItemlbl);
        medNearExpItemlbl.setForeground(new Color(255, 100, 0));
        medNearExpItemlbl.setHorizontalAlignment(SwingConstants.CENTER);
        medNearExpItemlbl.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));
        
        JLabel lblNewLabel_4_10 = new JLabel("Medical");
        lblNewLabel_4_10.setIcon(null);
        lblNewLabel_4_10.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_10.setFont(new Font("BankGothic Md BT", Font.PLAIN, 15));
        lblNewLabel_4_10.setBounds(18, 1, 73, 40);
        panel_13_2.add(lblNewLabel_4_10);

        JPanel panel_13_3 = new JPanel();
        panel_13_3.setBackground(new Color(255, 255, 255));
        panel_13_3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(215, 215, 215), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
        panel_13_3.setBounds(110, 52, 108, 76);
        box2panel.add(panel_13_3);
        panel_13_3.setLayout(null);

        foodNearExpItemlbl = new JLabel("-");
        foodNearExpItemlbl.setBounds(8, 39, 93, 32);
        panel_13_3.add(foodNearExpItemlbl);
        foodNearExpItemlbl.setForeground(new Color(255, 100, 0));
        foodNearExpItemlbl.setHorizontalAlignment(SwingConstants.CENTER);
        foodNearExpItemlbl.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));
        
        JLabel lblNewLabel_4_1_1 = new JLabel("Food");
        lblNewLabel_4_1_1.setIcon(null);
        lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_1.setFont(new Font("BankGothic Md BT", Font.PLAIN, 15));
        lblNewLabel_4_1_1.setBounds(28, 1, 53, 40);
        panel_13_3.add(lblNewLabel_4_1_1);

        JPanel box3containerPanel = new JPanel();
        box3containerPanel.setPreferredSize(new Dimension(220, 120));
        box3containerPanel.setBackground(new Color(0, 50, 35));
        centerBoxPanel.add(box3containerPanel);

        JPanel box3panel = new JPanel();
        box3panel.setBorder(new LineBorder(new Color(0, 50, 35), 2));
        box3panel.setPreferredSize(new Dimension(220, 130));
        box3panel.setBackground(new Color(206, 241, 123));
        box3containerPanel.add(box3panel);
        box3panel.setLayout(null);

        JLabel lblInStock = new JLabel("IN STOCK");
        lblInStock.setForeground(new Color(12, 59, 46));
        lblInStock.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
        lblInStock.setBounds(15, 9, 140, 37);
        box3panel.add(lblInStock);

        JPanel panel_13_4 = new JPanel();
        panel_13_4.setBackground(new Color(255, 255, 255));
        panel_13_4.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(215, 215, 215), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
        panel_13_4.setBounds(3, 52, 108, 76);
        box3panel.add(panel_13_4);
        panel_13_4.setLayout(null);

        medinStocklbl = new JLabel("-");
        medinStocklbl.setBounds(8, 39, 93, 32);
        panel_13_4.add(medinStocklbl);
        medinStocklbl.setForeground(new Color(255, 100, 0));
        medinStocklbl.setHorizontalAlignment(SwingConstants.CENTER);
        medinStocklbl.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));
        
        JLabel lblNewLabel_4_10_1 = new JLabel("Medical");
        lblNewLabel_4_10_1.setIcon(null);
        lblNewLabel_4_10_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_10_1.setFont(new Font("BankGothic Md BT", Font.PLAIN, 15));
        lblNewLabel_4_10_1.setBounds(18, 1, 73, 40);
        panel_13_4.add(lblNewLabel_4_10_1);

        JPanel panel_13_5 = new JPanel();
        panel_13_5.setBackground(new Color(255, 255, 255));
        panel_13_5.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(215, 215, 215), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
        panel_13_5.setBounds(110, 52, 108, 76);
        box3panel.add(panel_13_5);
        panel_13_5.setLayout(null);

        foodinStocklbl = new JLabel("-");
        foodinStocklbl.setBounds(8, 39, 93, 32);
        panel_13_5.add(foodinStocklbl);
        foodinStocklbl.setForeground(new Color(255, 100, 0));
        foodinStocklbl.setHorizontalAlignment(SwingConstants.CENTER);
        foodinStocklbl.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));
        
        JLabel lblNewLabel_4_1_1_1 = new JLabel("Food");
        lblNewLabel_4_1_1_1.setIcon(null);
        lblNewLabel_4_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_1_1.setFont(new Font("BankGothic Md BT", Font.PLAIN, 15));
        lblNewLabel_4_1_1_1.setBounds(28, 1, 53, 40);
        panel_13_5.add(lblNewLabel_4_1_1_1);

        JPanel box4containerPanel = new JPanel();
        box4containerPanel.setPreferredSize(new Dimension(220, 120));
        box4containerPanel.setBackground(new Color(0, 50, 35));
        centerBoxPanel.add(box4containerPanel);

        JPanel box4panel = new JPanel();
        box4panel.setBorder(new LineBorder(new Color(0, 50, 35), 2, true));
        box4panel.setPreferredSize(new Dimension(220, 130));
        box4panel.setBackground(new Color(206, 241, 123));
        box4containerPanel.add(box4panel);
        box4panel.setLayout(null);

        JLabel lblLowStock = new JLabel("LOW STOCK");
        lblLowStock.setForeground(new Color(12, 59, 46));
        lblLowStock.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
        lblLowStock.setBounds(15, 9, 140, 37);
        box4panel.add(lblLowStock);

        JPanel panel_13_6 = new JPanel();
        panel_13_6.setBackground(new Color(255, 255, 255));
        panel_13_6.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(215, 215, 215), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
        panel_13_6.setBounds(3, 52, 108, 76);
        box4panel.add(panel_13_6);
        panel_13_6.setLayout(null);

        medLowStocklbl = new JLabel("-");
        medLowStocklbl.setBounds(8, 39, 93, 32);
        panel_13_6.add(medLowStocklbl);
        medLowStocklbl.setForeground(new Color(255, 100, 0));
        medLowStocklbl.setHorizontalAlignment(SwingConstants.CENTER);
        medLowStocklbl.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));
        
        JLabel lblNewLabel_4_10_1_1 = new JLabel("Medical");
        lblNewLabel_4_10_1_1.setIcon(null);
        lblNewLabel_4_10_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_10_1_1.setFont(new Font("BankGothic Md BT", Font.PLAIN, 15));
        lblNewLabel_4_10_1_1.setBounds(18, 1, 73, 40);
        panel_13_6.add(lblNewLabel_4_10_1_1);

        JPanel panel_13_7 = new JPanel();
        panel_13_7.setBackground(new Color(255, 255, 255));
        panel_13_7.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(215, 215, 215), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
        panel_13_7.setBounds(110, 52, 108, 76);
        box4panel.add(panel_13_7);
        panel_13_7.setLayout(null);

        foodLowStocklbl = new JLabel("-");
        foodLowStocklbl.setBounds(8, 39, 93, 32);
        panel_13_7.add(foodLowStocklbl);
        foodLowStocklbl.setForeground(new Color(255, 100, 0));
        foodLowStocklbl.setHorizontalAlignment(SwingConstants.CENTER);
        foodLowStocklbl.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));
        
        JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Food");
        lblNewLabel_4_1_1_1_1.setIcon(null);
        lblNewLabel_4_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_1_1_1.setFont(new Font("BankGothic Md BT", Font.PLAIN, 15));
        lblNewLabel_4_1_1_1_1.setBounds(28, 1, 53, 40);
        panel_13_7.add(lblNewLabel_4_1_1_1_1);

        JPanel box5containerPanel = new JPanel();
        box5containerPanel.setPreferredSize(new Dimension(220, 120));
        box5containerPanel.setBorder(null);
        box5containerPanel.setBackground(new Color(0, 50, 35));
        centerBoxPanel.add(box5containerPanel);

        JPanel box5panel = new JPanel();
        box5panel.setBorder(new LineBorder(new Color(0, 50, 35), 2, true));
        box5panel.setBackground(new Color(206, 241, 123));
        box5panel.setPreferredSize(new Dimension(220, 130));
        box5containerPanel.add(box5panel);
        box5panel.setLayout(null);

        JLabel lblOutOfStock = new JLabel("OUT OF STOCK");
        lblOutOfStock.setForeground(new Color(12, 59, 46));
        lblOutOfStock.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
        lblOutOfStock.setBounds(15, 9, 140, 37);
        box5panel.add(lblOutOfStock);

        JPanel panel_13_8 = new JPanel();
        panel_13_8.setBackground(new Color(255, 255, 255));
        panel_13_8.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(215, 215, 215), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
        panel_13_8.setBounds(3, 52, 108, 76);
        box5panel.add(panel_13_8);
        panel_13_8.setLayout(null);

        medOutOfStocklbl = new JLabel("-");
        medOutOfStocklbl.setBounds(8, 39, 93, 32);
        panel_13_8.add(medOutOfStocklbl);
        medOutOfStocklbl.setForeground(new Color(255, 100, 0));
        medOutOfStocklbl.setHorizontalAlignment(SwingConstants.CENTER);
        medOutOfStocklbl.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));
        
        JLabel lblNewLabel_4_10_1_1_1 = new JLabel("Medical");
        lblNewLabel_4_10_1_1_1.setIcon(null);
        lblNewLabel_4_10_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_10_1_1_1.setFont(new Font("BankGothic Md BT", Font.PLAIN, 15));
        lblNewLabel_4_10_1_1_1.setBounds(18, 1, 73, 40);
        panel_13_8.add(lblNewLabel_4_10_1_1_1);

        JPanel panel_13_9 = new JPanel();
        panel_13_9.setBackground(new Color(255, 255, 255));
        panel_13_9.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(215, 215, 215), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
        panel_13_9.setBounds(110, 52, 108, 76);
        box5panel.add(panel_13_9);
        panel_13_9.setLayout(null);

        foodOutOfStocklbl = new JLabel("-");
        foodOutOfStocklbl.setBounds(8, 39, 93, 32);
        panel_13_9.add(foodOutOfStocklbl);
        foodOutOfStocklbl.setForeground(new Color(255, 100, 0));
        foodOutOfStocklbl.setHorizontalAlignment(SwingConstants.CENTER);
        foodOutOfStocklbl.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));
        
        JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("Food");
        lblNewLabel_4_1_1_1_1_1.setIcon(null);
        lblNewLabel_4_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_1_1_1_1.setFont(new Font("BankGothic Md BT", Font.PLAIN, 15));
        lblNewLabel_4_1_1_1_1_1.setBounds(28, 1, 53, 40);
        panel_13_9.add(lblNewLabel_4_1_1_1_1_1);

        JPanel topBorderPanel = new JPanel();
        topBorderPanel.setBackground(new Color(255, 253, 233));
        inventoryBoxPanel.add(topBorderPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(new Color(45, 100, 43));
        // No longer set as viewport view, just add directly to mainContentPanel
        mainContentPanel.add(tablePanel); // Add to the new mainContentPanel
        tablePanel.setLayout(new BorderLayout(0, 0));

        JPanel tableNamePanel = new JPanel();
        tableNamePanel.setBackground(new Color(255, 253, 233));
        tableNamePanel.setPreferredSize(new Dimension(10, 60));
        tablePanel.add(tableNamePanel, BorderLayout.NORTH);
        tableNamePanel.setLayout(new BorderLayout(0, 0));

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 253, 233));
        tableNamePanel.add(panel_1, BorderLayout.NORTH);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(226, 226, 226)));
        panel_3.setBackground(new Color(255, 253, 233));
        panel_3.setPreferredSize(new Dimension(250, 10));
        tableNamePanel.add(panel_3, BorderLayout.WEST);
        panel_3.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Inventory Overview");
        lblNewLabel_1.setForeground(new Color(12, 59, 46));
        lblNewLabel_1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 22));
        lblNewLabel_1.setBounds(24, 12, 216, 28);
        panel_3.add(lblNewLabel_1);

        JPanel tableControlContainerPanel = new JPanel();
        tableControlContainerPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(226, 226, 226)));
        tableControlContainerPanel.setBackground(new Color(255, 253, 233));
        tableControlContainerPanel.setPreferredSize(new Dimension(360, 10));
        tableNamePanel.add(tableControlContainerPanel, BorderLayout.EAST);
        tableControlContainerPanel.setLayout(null);
        
        JLabel lblInvestmentValueText = new JLabel("INVESTMENT  VALUE  -");
        lblInvestmentValueText.setForeground(new Color(255, 253, 233));
        lblInvestmentValueText.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
        lblInvestmentValueText.setBounds(13, 10, 165, 28);
        tableControlContainerPanel.add(lblInvestmentValueText);
        
        
        lblInvestmentValueTotal = new JLabel("00.00 Ks");
        lblInvestmentValueTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblInvestmentValueTotal.setForeground(new Color(206, 241, 123));
        lblInvestmentValueTotal.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
        lblInvestmentValueTotal.setBounds(177, 10, 146, 28);
        tableControlContainerPanel.add(lblInvestmentValueTotal);
        
        JPanel panel_20 = new JPanel();
        panel_20.setBackground(new Color(0, 50, 35));
        panel_20.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(12, 59, 46)));
        panel_20.setBounds(0, 5, 338, 40);
        tableControlContainerPanel.add(panel_20);

        JPanel panel_15 = new JPanel();
        panel_15.setForeground(new Color(255, 253, 233));
        panel_15.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(226, 226, 226)));
        panel_15.setBackground(new Color(255, 253, 233));
        tableNamePanel.add(panel_15, BorderLayout.CENTER);

        JPanel panel_5 = new JPanel();
        tablePanel.add(panel_5, BorderLayout.CENTER);
        panel_5.setLayout(new BorderLayout(0, 0));

        JPanel leftBorder = new JPanel();
        leftBorder.setPreferredSize(new Dimension(20, 10));
        leftBorder.setBackground(new Color(255, 253, 233));
        panel_5.add(leftBorder, BorderLayout.WEST);

        JPanel rightBorder = new JPanel();
        rightBorder.setPreferredSize(new Dimension(20, 10));
        rightBorder.setBackground(new Color(255, 253, 233));
        panel_5.add(rightBorder, BorderLayout.EAST);

        UIManager.put("TabbedPane.selectedBackground", new Color(45, 100, 43));
        UIManager.put("TabbedPane.unselectedBackground", new Color(255, 255, 255));
        UIManager.put("TabbedPane.contentAreaColor", new Color(255, 255, 255));
        UIManager.put("TabbedPane.selectedForeground", new Color(255, 255, 255));
        UIManager.put("TabbedPane.selectedForeground", new Color(45, 100, 43));
        UIManager.put("TabbedPane.selectedTabPadInsets", new Insets(2, 5, 2, 5));
        UIManager.put("TabbedPane.selectedTabPad", new Insets(2, 5, 2, 5));
        UIManager.put("TabbedPane.tabAreaBackground", new Color(255, 255, 255));
        UIManager.put("TabbedPane:TabbedPaneTab.contentMargins", new Insets(0, 0, 0, 0));
        UIManager.put("TabbedPane.focusColor", new Color(45, 100, 43));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setSize(new Dimension(20, 20));
        tabbedPane.setPreferredSize(new Dimension(20, 20));
        tabbedPane.setMinimumSize(new Dimension(20, 20));
        tabbedPane.setBounds(new Rectangle(0, 0, 30, 20));
        tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tabbedPane.setBackground(new Color(238, 238, 238));
        tabbedPane.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 14));
        panel_5.add(tabbedPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(45, 100, 43));
        tabbedPane.addTab("Medical Supplies Table", null, panel, null);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panel_6 = new JPanel();
        panel.add(panel_6, BorderLayout.CENTER);
        panel_6.setLayout(new BorderLayout(0, 0));

        JPanel panel_4 = new JPanel();
        panel_6.add(panel_4, BorderLayout.NORTH);
        panel_4.setLayout(new BorderLayout(0, 0));

        JPanel panel_7 = new JPanel();
        panel_7.setBackground(new Color(0, 50, 35));
        panel_7.setPreferredSize(new Dimension(10, 42));
        panel_4.add(panel_7);
        panel_7.setLayout(new BorderLayout(0, 0));

        JPanel panel_14 = new JPanel();
        panel_14.setPreferredSize(new Dimension(5, 5));
        panel_14.setBackground(new Color(0, 50, 35));
        panel_7.add(panel_14, BorderLayout.SOUTH);

        JPanel MedicControlPanel = new JPanel();
        MedicControlPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        MedicControlPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        MedicControlPanel.setBackground(new Color(0, 50, 35));
        MedicControlPanel.setPreferredSize(new Dimension(470, 50));
        panel_7.add(MedicControlPanel, BorderLayout.EAST);
        MedicControlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        String[] options= {"All Items", "In Stock", "Low Stock", "Out Of Stock"};
        JComboBox <String> medfilterComboBox = new JComboBox<String>(options);
        medfilterComboBox.setModel(new DefaultComboBoxModel(new String[] {"All Items", "In Stock", "Low Stock", "Out Of Stock", "Near Expiry Products", "Expired Products"}));
        medfilterComboBox.setBorder(null);
        medfilterComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        medfilterComboBox.setFocusable(false);
        medfilterComboBox.setBackground(new Color(255, 255, 255));
        medfilterComboBox.setPreferredSize(new Dimension(160, 32));
        medfilterComboBox.setFont(new Font("Arial", Font.PLAIN, 13));
//        medfilterComboBox.setModel(new DefaultComboBoxModel(new String[] {"All Items", "In Stock", "Low Stock", "Out Of Stock"}));
//        medfilterComboBox.setSelectedIndex(0);
        MedicControlPanel.add(medfilterComboBox);
        medfilterComboBox.addActionListener(new ActionListener() {
           	public void actionPerformed(ActionEvent e) {
            	selectedMedFilter=medfilterComboBox.getSelectedItem().toString();	
            	loadMedicalSuppliesData();
            	}
            });
          

        JButton btnAddNewMedic = new JButton("+  Add New Product");
        btnAddNewMedic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Add_to_Inventory_Form ADDF = null;
                try {
                    ADDF = new Add_to_Inventory_Form("new", MedicalSuppliesTable);
                    ADDF.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            // Refresh data when Add_to_Inventory_Form is closed
                            loadMedicalSuppliesData();
                          
                        }
                    });
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                ADDF.setVisible(true);
            }
        });
        btnAddNewMedic.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAddNewMedic.setMinimumSize(new Dimension(150, 28));
        btnAddNewMedic.setMaximumSize(new Dimension(150, 28));
        btnAddNewMedic.setMargin(new Insets(0, 0, 0, 0));
        btnAddNewMedic.setFocusable(false);
        btnAddNewMedic.setForeground(new Color(12, 59, 46));
        btnAddNewMedic.setBackground(new Color(206, 241, 123));
        btnAddNewMedic.setPreferredSize(new Dimension(150, 32));
        btnAddNewMedic.setFont(new Font("Arial", Font.PLAIN, 13));
        btnAddNewMedic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddNewMedic.setBorder(null);
        btnAddNewMedic.setAlignmentY(Component.TOP_ALIGNMENT);
        MedicControlPanel.add(btnAddNewMedic);

        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = MedicalSuppliesTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(btnAddNewMedic, "Please select a row to edit.", "No Product Selected", JOptionPane.WARNING_MESSAGE);
                    return; // Exit if no row is selected
                }

                Add_to_Inventory_Form aif = null;
                try {
                    aif = new Add_to_Inventory_Form("edit", MedicalSuppliesTable);
                    aif.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            // Refresh data when Add_to_Inventory_Form is closed
                            loadMedicalSuppliesData();
                            
                        }
                    });
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                aif.setVisible(true);
            }
        });
        btnEdit.setPreferredSize(new Dimension(70, 32));
        btnEdit.setMinimumSize(new Dimension(70, 28));
        btnEdit.setMaximumSize(new Dimension(70, 28));
        btnEdit.setMargin(new Insets(0, 0, 0, 0));
        btnEdit.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEdit.setForeground(new Color(12, 59, 46));
        btnEdit.setFont(new Font("Arial", Font.PLAIN, 13));
        btnEdit.setFocusable(false);
        btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEdit.setBorder(null);
        btnEdit.setBackground(new Color(254, 202, 89));
        btnEdit.setAlignmentY(0.0f);
        MedicControlPanel.add(btnEdit);


        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = MedicalSuppliesTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(btnAddNewMedic, "Please select a row to delete.", "No Product Selected", JOptionPane.WARNING_MESSAGE);
                } else {
                    String deleteID = delete(MedicalSuppliesTable); // This gets the product ID
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        String insertSQL = "DELETE FROM medicalInventory WHERE product_id=?";

                        try (Connection con = DbConnection.getConnection();
                             PreparedStatement pstmt = con.prepareStatement(insertSQL)) {
                            pstmt.setString(1, deleteID);

                            int rowsAffected = pstmt.executeUpdate();
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Deleted successfully!");
                                System.out.println("Deleted Successfully");
                                // After deletion, reload the data
                                loadMedicalSuppliesData();
                               
                            } else {
                                System.out.println("Deleting failed");
                                JOptionPane.showMessageDialog(null, "Failed to delete product.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Database error during deletion: " + e1.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        btnDelete.setPreferredSize(new Dimension(70, 32));
        btnDelete.setMinimumSize(new Dimension(70, 28));
        btnDelete.setMaximumSize(new Dimension(70, 28));
        btnDelete.setMargin(new Insets(0, 0, 0, 0));
        btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setFont(new Font("Arial", Font.PLAIN, 13));
        btnDelete.setFocusable(false);
        btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDelete.setBorder(null);
        btnDelete.setBackground(new Color(227, 72, 72));
        btnDelete.setAlignmentY(0.0f);
        MedicControlPanel.add(btnDelete);
        
        JPanel panel_18 = new JPanel();
        panel_18.setBackground(new Color(0, 50, 35));
        panel_18.setPreferredSize(new Dimension(350, 10));
        panel_7.add(panel_18, BorderLayout.WEST);
        panel_18.setLayout(null);
        
        MedicSearchtextField = new JTextField();
        MedicSearchtextField.setFont(new Font("Arial", Font.PLAIN, 13));
        MedicSearchtextField.setPreferredSize(new Dimension(7, 32));
        MedicSearchtextField.setBounds(23, 5, 198, 32);
        panel_18.add(MedicSearchtextField);
        MedicSearchtextField.setColumns(10);
        
        setupPlaceholder(MedicSearchtextField,"eg : product name",Color.GRAY);
        
        JButton MedicSearchbtn = new JButton("");
        MedicSearchbtn.setForeground(new Color(12, 59, 46));
        MedicSearchbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SearchMed=MedicSearchtextField.getText().trim();
        		if (SearchMed.equals("eg : product name") ) {
                    JOptionPane.showMessageDialog(MedicSearchbtn, "Please enter something!!", "No Product Name", JOptionPane.WARNING_MESSAGE);
                } else {
        		try {
					searchMedicalSuppliesData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                }
        	}
        });
        MedicSearchbtn.setIcon(new ImageIcon(Inventory_Management.class.getResource("/icon/tableSearchButtonIcon.png")));
        MedicSearchbtn.setBackground(new Color(255, 253, 233));
        MedicSearchbtn.setFont(new Font("Arial", Font.PLAIN, 12));
        MedicSearchbtn.setBounds(231, 5, 32, 32);
        panel_18.add(MedicSearchbtn);

        JPanel panel_9 = new JPanel();
        panel_6.add(panel_9, BorderLayout.CENTER);
        panel_9.setLayout(new BorderLayout(0, 0));

        JPanel panel_10 = new JPanel();
        panel_9.add(panel_10, BorderLayout.CENTER);
        panel_10.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setSize(new Dimension(0, 22));
        scrollPane_1.setBackground(new Color(216, 236, 196));
        scrollPane_1.setViewportBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
        scrollPane_1.setPreferredSize(new Dimension(2, 5));
        scrollPane_1.setFont(new Font("Arial", Font.PLAIN, 17));
        panel_10.add(scrollPane_1);


        MedicalSuppliesTable = new JTable();
        MedicalSuppliesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        MedicalSuppliesTable.setName("MedicalSuppliesTable");
        MedicalSuppliesTable.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        MedicalSuppliesTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        MedicalSuppliesTable.setGridColor(new Color(226, 226, 226));
        MedicalSuppliesTable.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        MedicalSuppliesTable.setSelectionBackground(new Color(216, 236, 196));
        MedicalSuppliesTable.setRowMargin(15);
        MedicalSuppliesTable.setRowHeight(35);
        MedicalSuppliesTable.setIntercellSpacing(new Dimension(0, 0));
        MedicalSuppliesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        MedicalSuppliesTable.setBackground(new Color(255, 255, 255));
        scrollPane_1.setViewportView(MedicalSuppliesTable); // Set this here after MedicalSuppliesTable is initialized

        // Call the data loading method in the constructor
        loadMedicalSuppliesData();
        calculateTotalInvestment();
        // Add a WindowListener to refresh data when the frame becomes visible
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                loadMedicalSuppliesData();
                
            }
        });


        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Food Items Table ", null, panel_2, null);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPanel panel_8 = new JPanel();
        panel_2.add(panel_8, BorderLayout.CENTER);
        panel_8.setLayout(new BorderLayout(0, 0));

        JPanel panel_11 = new JPanel();
        panel_11.setBackground(new Color(12, 59, 46));
        panel_11.setPreferredSize(new Dimension(10, 42));
        panel_8.add(panel_11, BorderLayout.NORTH);
        panel_11.setLayout(new BorderLayout(0, 0));

        JPanel panel_14_1 = new JPanel();
        panel_14_1.setPreferredSize(new Dimension(5, 5));
        panel_14_1.setBackground(new Color(12, 59, 46));
        panel_11.add(panel_14_1, BorderLayout.SOUTH);

        JPanel AniFoodControlPanel = new JPanel();
        AniFoodControlPanel.setPreferredSize(new Dimension(470, 50));
        AniFoodControlPanel.setBackground(new Color(12, 59, 46));
        AniFoodControlPanel.setAlignmentY(0.0f);
        AniFoodControlPanel.setAlignmentX(1.0f);
        panel_11.add(AniFoodControlPanel, BorderLayout.EAST);
        AniFoodControlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JComboBox<String> filterFoodComboBox = new JComboBox<String>();
        filterFoodComboBox.setModel(new DefaultComboBoxModel(new String[] {"All Items", "In Stock", "Low Stock", "Out Of Stock", "Near Expiry Products", "Expired Products"}));
        filterFoodComboBox.setPreferredSize(new Dimension(160, 32));
        filterFoodComboBox.setFont(new Font("Arial", Font.PLAIN, 13));
        filterFoodComboBox.setFocusable(false);
        filterFoodComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        filterFoodComboBox.setBackground(Color.WHITE);
        AniFoodControlPanel.add(filterFoodComboBox);
        filterFoodComboBox.addActionListener(new ActionListener() {
           	public void actionPerformed(ActionEvent e) {
            	selectedFoodFilter=filterFoodComboBox.getSelectedItem().toString();	
            	loadFoodSuppliesData();
            	}
            });
        
        JButton btnAddNewFood = new JButton("+  Add New Product");
  btnAddNewFood.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          Add_to_Inventory_Form ADDF = null;
          try {
              ADDF = new Add_to_Inventory_Form("new", FoodItemsTable);
              ADDF.addWindowListener(new WindowAdapter() {
                  @Override
                  public void windowClosed(WindowEvent e) {
                      // Refresh data when Add_to_Inventory_Form is closed
                      loadFoodSuppliesData();
                  }
              });
          } catch (SQLException e1) {
              e1.printStackTrace();
          }
          ADDF.setVisible(true);
      }
  });
        btnAddNewFood.setPreferredSize(new Dimension(150, 32));
        btnAddNewFood.setMinimumSize(new Dimension(150, 28));
        btnAddNewFood.setMaximumSize(new Dimension(150, 28));
        btnAddNewFood.setMargin(new Insets(0, 0, 0, 0));
        btnAddNewFood.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAddNewFood.setForeground(new Color(12, 59, 46));
        btnAddNewFood.setFont(new Font("Arial", Font.PLAIN, 13));
        btnAddNewFood.setFocusable(false);
        btnAddNewFood.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddNewFood.setBorder(null);
        btnAddNewFood.setBackground(new Color(206, 241, 123));
        btnAddNewFood.setAlignmentY(0.0f);
        AniFoodControlPanel.add(btnAddNewFood);
        
        JButton btnFoodInvEdit = new JButton("Edit");
  btnFoodInvEdit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          int selectedRow = FoodItemsTable.getSelectedRow();
          if (selectedRow == -1) {
              JOptionPane.showMessageDialog(null, "Please select a row to edit.", "No Product Selected", JOptionPane.WARNING_MESSAGE);
              return; // Exit if no row is selected
          }

          Add_to_Inventory_Form aif = null;
          try {
              aif = new Add_to_Inventory_Form("edit", FoodItemsTable);
              aif.addWindowListener(new WindowAdapter() {
                  @Override
                  public void windowClosed(WindowEvent e) {
                      // Refresh data when Add_to_Inventory_Form is closed
                      loadFoodSuppliesData();
                  }
              });
          } catch (SQLException e1) {
              e1.printStackTrace();
          }

          aif.setVisible(true);
      }
  });
        btnFoodInvEdit.setPreferredSize(new Dimension(70, 32));
        btnFoodInvEdit.setMinimumSize(new Dimension(70, 28));
        btnFoodInvEdit.setMaximumSize(new Dimension(70, 28));
        btnFoodInvEdit.setMargin(new Insets(0, 0, 0, 0));
        btnFoodInvEdit.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFoodInvEdit.setForeground(new Color(12, 59, 46));
        btnFoodInvEdit.setFont(new Font("Arial", Font.PLAIN, 13));
        btnFoodInvEdit.setFocusable(false);
        btnFoodInvEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnFoodInvEdit.setBorder(null);
        btnFoodInvEdit.setBackground(new Color(254, 202, 89));
        btnFoodInvEdit.setAlignmentY(0.0f);
        AniFoodControlPanel.add(btnFoodInvEdit);
        
        JButton btnDeleteFood = new JButton("Delete");
      btnDeleteFood.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = FoodItemsTable.getSelectedRow();
           if (selectedRow == -1) {
               JOptionPane.showMessageDialog(btnAddNewMedic, "Please select a row to delete.", "No Product Selected", JOptionPane.WARNING_MESSAGE);
           } else {
               String deleteID = delete(FoodItemsTable); // This gets the product ID
               int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
               if (confirm == JOptionPane.YES_OPTION) {
                   String insertSQL = "DELETE FROM animalFoodInventory WHERE product_id=?";

                   try (Connection con = DbConnection.getConnection();
                        PreparedStatement pstmt = con.prepareStatement(insertSQL)) {
                       pstmt.setString(1, deleteID);

                       int rowsAffected = pstmt.executeUpdate();
                       if (rowsAffected > 0) {
                           JOptionPane.showMessageDialog(null, "Deleted successfully!");
                           System.out.println("Deleted Successfully");
                           // After deletion, reload the data
                           loadFoodSuppliesData();
                       } else {
                           System.out.println("Deleting failed");
                           JOptionPane.showMessageDialog(null, "Failed to delete product.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
                       }
                   } catch (SQLException e1) {
                       e1.printStackTrace();
                       JOptionPane.showMessageDialog(null, "Database error during deletion: " + e1.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                   }
               }
           }
       }
   });
   
        btnDeleteFood.setPreferredSize(new Dimension(70, 32));
        btnDeleteFood.setMinimumSize(new Dimension(70, 28));
        btnDeleteFood.setMaximumSize(new Dimension(70, 28));
        btnDeleteFood.setMargin(new Insets(0, 0, 0, 0));
        btnDeleteFood.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDeleteFood.setForeground(new Color(255, 255, 255));
        btnDeleteFood.setFont(new Font("Arial", Font.PLAIN, 13));
        btnDeleteFood.setFocusable(false);
        btnDeleteFood.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDeleteFood.setBorder(null);
        btnDeleteFood.setBackground(new Color(227, 72, 72));
        btnDeleteFood.setAlignmentY(0.0f);
        AniFoodControlPanel.add(btnDeleteFood);
        
        JPanel panel_18_1 = new JPanel();
        panel_18_1.setBackground(new Color(12, 59, 46));
        panel_18_1.setLayout(null);
        panel_18_1.setPreferredSize(new Dimension(350, 10));
        panel_11.add(panel_18_1, BorderLayout.WEST);
        
        FoodSearchtextField = new JTextField();
        FoodSearchtextField.setFont(new Font("Arial", Font.PLAIN, 13));
        FoodSearchtextField.setPreferredSize(new Dimension(7, 32));
        FoodSearchtextField.setColumns(10);
        FoodSearchtextField.setBounds(23, 5, 198, 32);
        panel_18_1.add(FoodSearchtextField);
        setupPlaceholder(FoodSearchtextField,"eg : product name",Color.GRAY);
        
        JButton FoodSearchbtn = new JButton("");
        FoodSearchbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SearchFood=FoodSearchtextField.getText().trim();
        		if (SearchFood.equals("eg : product name") ) {
                    JOptionPane.showMessageDialog(MedicSearchbtn, "Please enter something!!", "No Product Name", JOptionPane.WARNING_MESSAGE);
                } else {
        		try {
					searchFoodItemsData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                }
        	}
        });
        FoodSearchbtn.setIcon(new ImageIcon(Inventory_Management.class.getResource("/icon/tableSearchButtonIcon.png")));
        FoodSearchbtn.setFont(new Font("Arial", Font.PLAIN, 12));
        FoodSearchbtn.setBackground(Color.WHITE);
        FoodSearchbtn.setBounds(231, 5, 32, 32);
        panel_18_1.add(FoodSearchbtn);


        JPanel panel_12 = new JPanel();
        panel_8.add(panel_12, BorderLayout.CENTER);
        panel_12.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(new Color(216, 236, 196));
        scrollPane.setFont(new Font("Arial", Font.PLAIN, 17));
        panel_12.add(scrollPane);
        
        FoodItemsTable = new JTable();
        FoodItemsTable.setName("FoodItemsTable");
        FoodItemsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        scrollPane.setViewportView(FoodItemsTable);
        FoodItemsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        FoodItemsTable.setSelectionBackground(new Color(216, 236, 196));
        FoodItemsTable.setRowMargin(15);
        FoodItemsTable.setRowHeight(35);
        FoodItemsTable.setIntercellSpacing(new Dimension(0, 0));
        FoodItemsTable.setGridColor(new Color(226, 226, 226));
        FoodItemsTable.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        FoodItemsTable.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        FoodItemsTable.setBackground(Color.WHITE);
        FoodItemsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        // Call the data loading method in the constructor
        loadFoodSuppliesData();
        // Add a WindowListener to refresh data when the frame becomes visible
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                loadFoodSuppliesData();
            }
        });
        
        calculateTotalInvestment();
        SwingUtilities.updateComponentTreeUI(tabbedPane);
    }

    
    private void searchMedicalSuppliesData() throws SQLException {

    	String insertSQL="SELECT * FROM medicalInventory WHERE product_name LIKE ?";
    	 try(Connection con = DbConnection.getConnection();
    				PreparedStatement pstmt=con.prepareStatement(insertSQL)){
    		        	pstmt.setString(1,"%"+ SearchMed+"%");     	
    		        	try(ResultSet rs=pstmt.executeQuery()){
    		        		DefaultTableModel model=(DefaultTableModel)MedicalSuppliesTable.getModel();
    						model.setRowCount(0);
    						int i=1;
    						while(rs.next()) {
    							model.addRow(new Object[] {
    							i++,
    						   rs.getString("product_id"),
    			               rs.getString("product_name"),
    			               rs.getString("category"),
    			               rs.getString("manufacturer"),
    			               rs.getString("exp_date"),
    			               rs.getString("warehouse"),
    			               rs.getInt("stock"),
    			               rs.getDouble("price"),
    			               stockStatus(rs.getInt("stock"))});   
    				};
    			}
    		}   
    }
    
    
    private void searchFoodItemsData() throws SQLException {

    	String insertSQL="SELECT * FROM animalFoodInventory WHERE product_name LIKE ?";
    	 try(Connection con = DbConnection.getConnection();
    				PreparedStatement pstmt=con.prepareStatement(insertSQL)){
    		        	pstmt.setString(1,"%"+ SearchFood+"%");
    		        	try(ResultSet rs=pstmt.executeQuery()){	
    		        		DefaultTableModel model=(DefaultTableModel)FoodItemsTable.getModel();
    						model.setRowCount(0);
    						int i=1;
    						while(rs.next()) {
    							model.addRow(new Object[] {
    							i++,
    						   rs.getString("product_id"),
    			               rs.getString("product_name"),
    			               rs.getString("category"),
    			               rs.getString("manufacturer"),
    			               rs.getString("exp_date"),
    			               rs.getString("warehouse"),
    			               rs.getInt("stock"),
    			               rs.getDouble("price"),
    			               stockStatus(rs.getInt("stock"))});    
    				};
    			}
    		}         
    }
    
    
    
   
    // New method to load and refresh medical supplies data
    private void loadMedicalSuppliesData() {
    	medNearExpiryDate=0;
    	medNearExpItemlbl.setText("-");
    	
    	MedExpiredProductsArrayList.clear();
    	MedNearExpiryProductsArrayList.clear();
        medArrayList.clear(); // Clear existing data
        MedInStockArrayList.clear();
        MedLowStockArrayList.clear();
        MedOutOfStockArrayList.clear();
        MedInvestmentValue=0;
        
        boxMedInStockStatus=0;
        boxMedLowStockStatus=0;
        boxMedOutOfStockStatus=0;
        medinStocklbl.setText("-");
    	medLowStocklbl.setText("-");
        medOutOfStocklbl.setText("-");
        

        try (Connection con = DbConnection.getConnection();
             Statement stmt = con.createStatement();
        		
             ResultSet rs = stmt.executeQuery("SELECT * FROM medicalInventory")) {
       //Product ID process
        	if(!rs.isBeforeFirst()){
            	productidFormattedFourNums=String.format("%04d" ,product_id_int_num);
            	FinalProduct_id="MI-"+productidFormattedFourNums;		
            }
       //Product ID process
            while (rs.next()) {
                String productId = rs.getString("product_id");
                String productName = rs.getString("product_name");
                String category = rs.getString("category");
                String manufacturer = rs.getString("manufacturer");
                String expDate = rs.getString("exp_date");
                medicNearExpiryCheck(expDate);
                String warehouse = rs.getString("warehouse");
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");
                MedInvestmentValue+=price*stock;
                
                System.out.println(productId + productName + category + manufacturer + expDate + warehouse + stock + price);

                InventoryProduct mP = new InventoryProduct(productId, productName, category, manufacturer, expDate, warehouse, stock, price);
//                medArrayList.add(mP);
                 
         //Near Expiry Product process
                if(medNearExpiryCheck==true) {
                MedNearExpiryProductsArrayList.add(mP);           
                }
         //Near Expiry Product process    
         //Near Expired Product process
                if(medExpiredCheck==true) {
    	           MedExpiredProductsArrayList.add(mP);
               
                }else if(medExpiredCheck==false) {
                	medArrayList.add(mP);
                }
         //Near Expired Product process      
                
                
                
         //Product ID process
                if(!medArrayList.isEmpty()){
                	int lastIndex=medArrayList.size()-1;
                	String NeedToCutProductId=medArrayList.get(lastIndex).getProductId();
                	productidFormattedFourNums=NeedToCutProductId.substring(3);
                	product_id_int_num=Integer.parseInt(productidFormattedFourNums);
                	product_id_int_num+=1;
                	productidFormattedFourNums=String.format("%04d" ,product_id_int_num);
                	FinalProduct_id="MI-"+productidFormattedFourNums;
                }
         //Product ID process  
                
         //filtering process part
                if(stockStatus(stock).equals("In Stock") && medExpiredCheck==false) {
                	MedInStockArrayList.add(mP);
                }else if(stockStatus(stock).equals("Low Stock")&& medExpiredCheck==false) {
                	MedLowStockArrayList.add(mP);
                }else if(stockStatus(stock).equals("Out Of Stock")&& medExpiredCheck==false) {
                	MedOutOfStockArrayList.add(mP);
                }
         //filtering process part     
                
                
            }
             medicalItemTotal=medArrayList.size();
             medTotallbl.setText(String.valueOf(medicalItemTotal));
            //System.out.println("SEE TOTAL="+	medicalItemTotal);
            // Define column names
            String[] columnName = new String[]{
                    "No", "Product ID", "Product Name", "Category", "Manufacturer", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
            };

            // Prepare data for the table model
            Object[][] data = new Object[medArrayList.size()][10];
            for (int i = 0; i < medArrayList.size(); i++) {
                data[i][0] = i + 1;
                data[i][1] = medArrayList.get(i).getProductId();
                data[i][2] = medArrayList.get(i).getProductName();
                data[i][3] = medArrayList.get(i).getCategory();
                data[i][4] = medArrayList.get(i).getManufacturer();
                data[i][5] = medArrayList.get(i).getExpDate();
                data[i][6] = medArrayList.get(i).getWareHouse();
                data[i][7] = medArrayList.get(i).getStock();
                int stock = medArrayList.get(i).getStock();
                data[i][8] = medArrayList.get(i).getPrice();
                data[i][9] = stockStatus(stock);
                if(stockStatus(stock).equals("In Stock")) {
                	boxMedInStockStatus+=1;
                	System.out.println("instock no : "+boxMedInStockStatus);
                	medinStocklbl.setText(String.valueOf(boxMedInStockStatus)); 
                }else if(stockStatus(stock).equals("Low Stock")) {
                	boxMedLowStockStatus+=1;
                	System.out.println("low stock no : "+boxMedLowStockStatus);
                    medLowStocklbl.setText(String.valueOf(boxMedLowStockStatus)); 
                }else if(stockStatus(stock).equals("Out Of Stock")) {
                	boxMedOutOfStockStatus+=1;
                	System.out.println("out of stock no : "+boxMedOutOfStockStatus);
                	medOutOfStocklbl.setText(String.valueOf(boxMedOutOfStockStatus)); 
                }
            }
         // Update the table model
            DefaultTableModel model = new DefaultTableModel(data, columnName) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make all cells non-editable
                }
            };
            MedicalSuppliesTable.setModel(model);
            // Ensure the table headers are displayed if they sometimes disappear
            MedicalSuppliesTable.getTableHeader().repaint();
            MedicalSuppliesTable.repaint();
            MedicalSuppliesTable.revalidate();
        
            
          Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(0);
          Medcolumn.setPreferredWidth(5);
          Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(1);
          Medcolumn.setPreferredWidth(50);
          Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(2);
          Medcolumn.setPreferredWidth(150);
          Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(3);
          Medcolumn.setPreferredWidth(120);
          Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(4);
          Medcolumn.setPreferredWidth(150);
          Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(5);
          Medcolumn.setPreferredWidth(50);
          Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(6);
          Medcolumn.setPreferredWidth(50);
          Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(7);
          Medcolumn.setPreferredWidth(30);
          Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(8);
          Medcolumn.setPreferredWidth(50);
          Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(9);
          Medcolumn.setPreferredWidth(80);
          centerRenderer=new DefaultTableCellRenderer();
          centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
          MedicalSuppliesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
          MedicalSuppliesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
          MedicalSuppliesTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
          MedicalSuppliesTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
          MedicalSuppliesTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
          MedicalSuppliesTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
          MedicalSuppliesTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
          MedicalSuppliesTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
          MedicalSuppliesTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
          MedicalSuppliesTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
          
   // filtering the table   
         if(selectedMedFilter.equals("All Items")) {

            // Update the table model
            DefaultTableModel Allmodel = new DefaultTableModel(data, columnName) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make all cells non-editable
                }
            };
            MedicalSuppliesTable.setModel(Allmodel);
            // Ensure the table headers are displayed if they sometimes disappear
            MedicalSuppliesTable.getTableHeader().repaint();
            MedicalSuppliesTable.repaint();
            MedicalSuppliesTable.revalidate();
            
            
            Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(0);
            Medcolumn.setPreferredWidth(5);
            Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(1);
            Medcolumn.setPreferredWidth(50);
            Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(2);
            Medcolumn.setPreferredWidth(150);
            Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(3);
            Medcolumn.setPreferredWidth(120);
            Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(4);
            Medcolumn.setPreferredWidth(150);
            Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(5);
            Medcolumn.setPreferredWidth(50);
            Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(6);
            Medcolumn.setPreferredWidth(50);
            Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(7);
            Medcolumn.setPreferredWidth(30);
            Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(8);
            Medcolumn.setPreferredWidth(50);
            Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(9);
            Medcolumn.setPreferredWidth(80);
            centerRenderer=new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            MedicalSuppliesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            MedicalSuppliesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            MedicalSuppliesTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
            MedicalSuppliesTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
            MedicalSuppliesTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            MedicalSuppliesTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            MedicalSuppliesTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            MedicalSuppliesTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
            MedicalSuppliesTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
            MedicalSuppliesTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
            
          }else if (selectedMedFilter.equals("In Stock")) {
        	                
                  columnName = new String[]{
                          "No", "Product ID", "Product Name", "Category", "Manufacturer", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
                  };

                  // Prepare data for the table model
                  Object[][] InStockdata = new Object[MedInStockArrayList.size()][10];
                  for (int i = 0; i < MedInStockArrayList.size(); i++) {
                	  InStockdata[i][0] = i + 1;
                	  InStockdata[i][1] = MedInStockArrayList.get(i).getProductId();
                	  InStockdata[i][2] = MedInStockArrayList.get(i).getProductName();
                	  InStockdata[i][3] = MedInStockArrayList.get(i).getCategory();
                	  InStockdata[i][4] = MedInStockArrayList.get(i).getManufacturer();
                	  InStockdata[i][5] = MedInStockArrayList.get(i).getExpDate();
                	  InStockdata[i][6] = MedInStockArrayList.get(i).getWareHouse();
                	  InStockdata[i][7] = MedInStockArrayList.get(i).getStock();
                      int stock = MedInStockArrayList.get(i).getStock();
                      InStockdata[i][8] = MedInStockArrayList.get(i).getPrice();
                      InStockdata[i][9] = stockStatus(stock);
          }
                  // Update the table model
                  DefaultTableModel InStockmodel = new DefaultTableModel(InStockdata, columnName) {
                      @Override
                      public boolean isCellEditable(int row, int column) {
                          return false; // Make all cells non-editable
                      }
                  };
                  MedicalSuppliesTable.setModel(InStockmodel);
                  // Ensure the table headers are displayed if they sometimes disappear
                  MedicalSuppliesTable.getTableHeader().repaint();
                  MedicalSuppliesTable.repaint();
                  MedicalSuppliesTable.revalidate();
          
                  Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(0);
                  Medcolumn.setPreferredWidth(5);
                  Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(1);
                  Medcolumn.setPreferredWidth(50);
                  Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(2);
                  Medcolumn.setPreferredWidth(150);
                  Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(3);
                  Medcolumn.setPreferredWidth(120);
                  Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(4);
                  Medcolumn.setPreferredWidth(150);
                  Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(5);
                  Medcolumn.setPreferredWidth(50);
                  Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(6);
                  Medcolumn.setPreferredWidth(50);
                  Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(7);
                  Medcolumn.setPreferredWidth(30);
                  Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(8);
                  Medcolumn.setPreferredWidth(50);
                  Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(9);
                  Medcolumn.setPreferredWidth(80);
                  centerRenderer=new DefaultTableCellRenderer();
                  centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                  MedicalSuppliesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                  MedicalSuppliesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                  MedicalSuppliesTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
                  MedicalSuppliesTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
                  MedicalSuppliesTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                  MedicalSuppliesTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                  MedicalSuppliesTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
                  MedicalSuppliesTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
                  MedicalSuppliesTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
                  MedicalSuppliesTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
                  
   }else if (selectedMedFilter.equals("Low Stock")) {
              
    columnName = new String[]{
            "No", "Product ID", "Product Name", "Category", "Manufacturer", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
    };

    // Prepare data for the table model
    Object[][] LowStockdata = new Object[MedLowStockArrayList.size()][10];
    for (int i = 0; i < MedLowStockArrayList.size(); i++) {
  	  LowStockdata[i][0] = i + 1;
  	  LowStockdata[i][1] = MedLowStockArrayList.get(i).getProductId();
  	  LowStockdata[i][2] = MedLowStockArrayList.get(i).getProductName();
  	  LowStockdata[i][3] = MedLowStockArrayList.get(i).getCategory();
  	  LowStockdata[i][4] = MedLowStockArrayList.get(i).getManufacturer();
  	  LowStockdata[i][5] = MedLowStockArrayList.get(i).getExpDate();
  	  LowStockdata[i][6] = MedLowStockArrayList.get(i).getWareHouse();
  	  LowStockdata[i][7] = MedLowStockArrayList.get(i).getStock();
        int stock = MedLowStockArrayList.get(i).getStock();
        LowStockdata[i][8] = MedLowStockArrayList.get(i).getPrice();
        LowStockdata[i][9] = stockStatus(stock);
}
    // Update the table model
    DefaultTableModel LowStockmodel = new DefaultTableModel(LowStockdata, columnName) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Make all cells non-editable
        }
    };
    MedicalSuppliesTable.setModel(LowStockmodel);
    // Ensure the table headers are displayed if they sometimes disappear
    MedicalSuppliesTable.getTableHeader().repaint();
    MedicalSuppliesTable.repaint();
    MedicalSuppliesTable.revalidate();

    Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(0);
    Medcolumn.setPreferredWidth(5);
    Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(1);
    Medcolumn.setPreferredWidth(50);
    Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(2);
    Medcolumn.setPreferredWidth(150);
    Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(3);
    Medcolumn.setPreferredWidth(120);
    Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(4);
    Medcolumn.setPreferredWidth(150);
    Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(5);
    Medcolumn.setPreferredWidth(50);
    Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(6);
    Medcolumn.setPreferredWidth(50);
    Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(7);
    Medcolumn.setPreferredWidth(30);
    Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(8);
    Medcolumn.setPreferredWidth(50);
    Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(9);
    Medcolumn.setPreferredWidth(80);
    centerRenderer=new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    MedicalSuppliesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    MedicalSuppliesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    MedicalSuppliesTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
    MedicalSuppliesTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
    MedicalSuppliesTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
    MedicalSuppliesTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
    MedicalSuppliesTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
    MedicalSuppliesTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
    MedicalSuppliesTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
    MedicalSuppliesTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
    
}else if (selectedMedFilter.equals("Out Of Stock")) {
    
columnName = new String[]{
  "No", "Product ID", "Product Name", "Category", "Manufacturer", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
};

// Prepare data for the table model
Object[][] OutOfStockdata = new Object[MedOutOfStockArrayList.size()][10];
for (int i = 0; i < MedOutOfStockArrayList.size(); i++) {
	OutOfStockdata[i][0] = i + 1;
	OutOfStockdata[i][1] = MedOutOfStockArrayList.get(i).getProductId();
	OutOfStockdata[i][2] = MedOutOfStockArrayList.get(i).getProductName();
	OutOfStockdata[i][3] = MedOutOfStockArrayList.get(i).getCategory();
	OutOfStockdata[i][4] = MedOutOfStockArrayList.get(i).getManufacturer();
	OutOfStockdata[i][5] = MedOutOfStockArrayList.get(i).getExpDate();
	OutOfStockdata[i][6] = MedOutOfStockArrayList.get(i).getWareHouse();
	OutOfStockdata[i][7] = MedOutOfStockArrayList.get(i).getStock();
int stock = MedOutOfStockArrayList.get(i).getStock();
OutOfStockdata[i][8] = MedOutOfStockArrayList.get(i).getPrice();
OutOfStockdata[i][9] = stockStatus(stock);
}
// Update the table model
DefaultTableModel OutOfStockmodel = new DefaultTableModel(OutOfStockdata, columnName) {
@Override
public boolean isCellEditable(int row, int column) {
  return false; // Make all cells non-editable
}
};
MedicalSuppliesTable.setModel(OutOfStockmodel);
// Ensure the table headers are displayed if they sometimes disappear
MedicalSuppliesTable.getTableHeader().repaint();
MedicalSuppliesTable.repaint();
MedicalSuppliesTable.revalidate();

Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(0);
Medcolumn.setPreferredWidth(5);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(1);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(2);
Medcolumn.setPreferredWidth(150);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(3);
Medcolumn.setPreferredWidth(120);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(4);
Medcolumn.setPreferredWidth(150);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(5);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(6);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(7);
Medcolumn.setPreferredWidth(30);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(8);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(9);
Medcolumn.setPreferredWidth(80);
centerRenderer=new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
MedicalSuppliesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);

}else if (selectedMedFilter.equals("Near Expiry Products")) {
    
columnName = new String[]{
  "No", "Product ID", "Product Name", "Category", "Manufacturer", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
};

// Prepare data for the table model
Object[][] NearExpiryProductdata = new Object[MedNearExpiryProductsArrayList.size()][10];
for (int i = 0; i < MedNearExpiryProductsArrayList.size(); i++) {
	NearExpiryProductdata[i][0] = i + 1;
	NearExpiryProductdata[i][1] = MedNearExpiryProductsArrayList.get(i).getProductId();
	NearExpiryProductdata[i][2] = MedNearExpiryProductsArrayList.get(i).getProductName();
	NearExpiryProductdata[i][3] = MedNearExpiryProductsArrayList.get(i).getCategory();
	NearExpiryProductdata[i][4] = MedNearExpiryProductsArrayList.get(i).getManufacturer();
	NearExpiryProductdata[i][5] = MedNearExpiryProductsArrayList.get(i).getExpDate();
	NearExpiryProductdata[i][6] = MedNearExpiryProductsArrayList.get(i).getWareHouse();
	NearExpiryProductdata[i][7] = MedNearExpiryProductsArrayList.get(i).getStock();
int stock = MedNearExpiryProductsArrayList.get(i).getStock();
NearExpiryProductdata[i][8] = MedNearExpiryProductsArrayList.get(i).getPrice();
NearExpiryProductdata[i][9] = stockStatus(stock);
}
// Update the table model
DefaultTableModel NearExpirymodel = new DefaultTableModel(NearExpiryProductdata, columnName) {
@Override
public boolean isCellEditable(int row, int column) {
  return false; // Make all cells non-editable
}
};
MedicalSuppliesTable.setModel(NearExpirymodel);
// Ensure the table headers are displayed if they sometimes disappear
MedicalSuppliesTable.getTableHeader().repaint();
MedicalSuppliesTable.repaint();
MedicalSuppliesTable.revalidate();

Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(0);
Medcolumn.setPreferredWidth(5);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(1);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(2);
Medcolumn.setPreferredWidth(150);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(3);
Medcolumn.setPreferredWidth(120);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(4);
Medcolumn.setPreferredWidth(150);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(5);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(6);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(7);
Medcolumn.setPreferredWidth(30);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(8);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(9);
Medcolumn.setPreferredWidth(80);
centerRenderer=new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
MedicalSuppliesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);

}else if (selectedMedFilter.equals("Expired Products")) {
    
columnName = new String[]{
  "No", "Product ID", "Product Name", "Category", "Manufacturer", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
};

// Prepare data for the table model
Object[][] ExpiredProductdata = new Object[MedExpiredProductsArrayList.size()][10];
for (int i = 0; i < MedExpiredProductsArrayList.size(); i++) {
	ExpiredProductdata[i][0] = i + 1;
	ExpiredProductdata[i][1] = MedExpiredProductsArrayList.get(i).getProductId();
	ExpiredProductdata[i][2] = MedExpiredProductsArrayList.get(i).getProductName();
	ExpiredProductdata[i][3] = MedExpiredProductsArrayList.get(i).getCategory();
	ExpiredProductdata[i][4] = MedExpiredProductsArrayList.get(i).getManufacturer();
	ExpiredProductdata[i][5] = MedExpiredProductsArrayList.get(i).getExpDate();
	ExpiredProductdata[i][6] = MedExpiredProductsArrayList.get(i).getWareHouse();
	ExpiredProductdata[i][7] = MedExpiredProductsArrayList.get(i).getStock();
int stock = MedExpiredProductsArrayList.get(i).getStock();
ExpiredProductdata[i][8] = MedExpiredProductsArrayList.get(i).getPrice();
ExpiredProductdata[i][9] = stockStatus(stock);
}
// Update the table model
DefaultTableModel Expiredmodel = new DefaultTableModel(ExpiredProductdata, columnName) {
@Override
public boolean isCellEditable(int row, int column) {
  return false; // Make all cells non-editable
}
};
MedicalSuppliesTable.setModel(Expiredmodel);
// Ensure the table headers are displayed if they sometimes disappear
MedicalSuppliesTable.getTableHeader().repaint();
MedicalSuppliesTable.repaint();
MedicalSuppliesTable.revalidate();

Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(0);
Medcolumn.setPreferredWidth(5);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(1);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(2);
Medcolumn.setPreferredWidth(150);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(3);
Medcolumn.setPreferredWidth(120);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(4);
Medcolumn.setPreferredWidth(150);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(5);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(6);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(7);
Medcolumn.setPreferredWidth(30);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(8);
Medcolumn.setPreferredWidth(50);
Medcolumn=MedicalSuppliesTable.getColumnModel().getColumn(9);
Medcolumn.setPreferredWidth(80);
centerRenderer=new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
MedicalSuppliesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
MedicalSuppliesTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);

}

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading medical supplies data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
       
    }
    

    
    
 // New method to load and refresh medical supplies data
    private void loadFoodSuppliesData() {
    	foodNearExpiryDate=0;
        foodNearExpItemlbl.setText("-");
    	
        FoodExpiredProductsArrayList.clear();
        FoodNearExpiryProductsArrayList.clear();
        foodArrayList.clear(); // Clear existing data
        FoodInStockArrayList.clear();
        FoodLowStockArrayList.clear();
        FoodOutOfStockArrayList.clear();
        
        FoodInvestmentValue=0;
        boxFoodInStockStatus=0;
        boxFoodLowStockStatus=0;
        boxFoodOutOfStockStatus=0;
        foodinStocklbl.setText("-");
    	foodLowStocklbl.setText("-");
        foodOutOfStocklbl.setText("-");
        

        try (Connection con1 = DbConnection.getConnection();
             Statement stmt1 = con1.createStatement();
        		
             ResultSet rs1 = stmt1.executeQuery("SELECT * FROM animalFoodInventory")) {
       //Product ID process
        	if(!rs1.isBeforeFirst()){
            	fproductidFormattedFourNums=String.format("%04d" ,fproduct_id_int_num);
            	FinalfProduct_id="FI-"+fproductidFormattedFourNums;		
            }
       //Product ID process
            while (rs1.next()) {
                String fproductId = rs1.getString("product_id");
                String fproductName = rs1.getString("product_name");
                String fcategory = rs1.getString("category");
                String fmanufacturer = rs1.getString("manufacturer");
                String fexpDate = rs1.getString("exp_date");
                foodNearExpiryCheck(fexpDate);
                String fwarehouse = rs1.getString("warehouse");
                int fstock = rs1.getInt("stock");
                double fprice = rs1.getDouble("price");
                FoodInvestmentValue+=fprice*fstock;
              
                System.out.println(fproductId + fproductName + fcategory + fmanufacturer + fexpDate + fwarehouse + fstock + fprice);

                InventoryProduct fP = new InventoryProduct(fproductId, fproductName, fcategory, fmanufacturer, fexpDate, fwarehouse, fstock, fprice);
//                foodArrayList.add(fP);
                
             //Near Expiry Product process
                if(foodNearExpiryCheck==true) {
                FoodNearExpiryProductsArrayList.add(fP);
                }
            //Near Expiry Product process 
            //Near Expired Product process
                if(foodExpiredCheck==true) {
    	           FoodExpiredProductsArrayList.add(fP);
               
                }else if(foodExpiredCheck==false) {
                	foodArrayList.add(fP);
                }
         //Near Expired Product process 
                
                
                
          //Product ID process
                if(!foodArrayList.isEmpty()){
                	int flastIndex=foodArrayList.size()-1;
                	String NeedToCutfProductId=foodArrayList.get(flastIndex).getProductId();
                	fproductidFormattedFourNums=NeedToCutfProductId.substring(3);
                	fproduct_id_int_num=Integer.parseInt(fproductidFormattedFourNums);
                	fproduct_id_int_num+=1;
                	fproductidFormattedFourNums=String.format("%04d" ,fproduct_id_int_num);
                	FinalfProduct_id="FI-"+fproductidFormattedFourNums;
                }
         //Product ID process       
                
         //filtering process part
               if(stockStatus(fstock).equals("In Stock") && foodExpiredCheck==false) {
                	FoodInStockArrayList.add(fP);
               }else if(stockStatus(fstock).equals("Low Stock")  && foodExpiredCheck==false) {
                	FoodLowStockArrayList.add(fP);
                }else if(stockStatus(fstock).equals("Out Of Stock")  && foodExpiredCheck==false) {
                	FoodOutOfStockArrayList.add(fP);
                }
         //filtering process part     
                
                
            }
             foodItemTotal=foodArrayList.size();
             foodTotallbl.setText(String.valueOf(foodItemTotal));
            //System.out.println("SEE TOTAL="+	medicalItemTotal);
            // Define column names
            fcolumnName = new String[]{
                    "No", "Product ID", "Product Name", "Category", "Supplier", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
            };

            // Prepare data for the table model
            Object[][] fdata = new Object[foodArrayList.size()][10];
            for (int i = 0; i < foodArrayList.size(); i++) {
                fdata[i][0] = i + 1;
                fdata[i][1] = foodArrayList.get(i).getProductId();
                fdata[i][2] = foodArrayList.get(i).getProductName();
                fdata[i][3] = foodArrayList.get(i).getCategory();
                fdata[i][4] = foodArrayList.get(i).getManufacturer();
                fdata[i][5] = foodArrayList.get(i).getExpDate();
                fdata[i][6] = foodArrayList.get(i).getWareHouse();
                fdata[i][7] = foodArrayList.get(i).getStock();
                int fstock = foodArrayList.get(i).getStock();
                fdata[i][8] = foodArrayList.get(i).getPrice();
                fdata[i][9] = stockStatus(fstock);
                if(stockStatus(fstock).equals("In Stock")) {
                	boxFoodInStockStatus+=1;
                	System.out.println("instock no : "+boxFoodInStockStatus);
                	foodinStocklbl.setText(String.valueOf(boxFoodInStockStatus)); 
                }else if(stockStatus(fstock).equals("Low Stock")) {
                	boxFoodLowStockStatus+=1;
                	System.out.println("low stock no : "+boxFoodLowStockStatus);
                    foodLowStocklbl.setText(String.valueOf(boxFoodLowStockStatus)); 
                }else if(stockStatus(fstock).equals("Out Of Stock")) {
                	boxFoodOutOfStockStatus+=1;
                	System.out.println("out of stock no : "+boxFoodOutOfStockStatus);
                	foodOutOfStocklbl.setText(String.valueOf(boxFoodOutOfStockStatus)); 
                }
            }
         // Update the table model
            DefaultTableModel fmodel = new DefaultTableModel(fdata, fcolumnName) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make all cells non-editable
                }
            };
            FoodItemsTable.setModel(fmodel);
            // Ensure the table headers are displayed if they sometimes disappear
            FoodItemsTable.getTableHeader().repaint();
            FoodItemsTable.repaint();
            FoodItemsTable.revalidate();
            
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(0);
            Foodcolumn.setPreferredWidth(5);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(1);
            Foodcolumn.setPreferredWidth(50);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(2);
            Foodcolumn.setPreferredWidth(150);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(3);
            Foodcolumn.setPreferredWidth(100);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(4);
            Foodcolumn.setPreferredWidth(150);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(5);
            Foodcolumn.setPreferredWidth(50);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(6);
            Foodcolumn.setPreferredWidth(50);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(7);
            Foodcolumn.setPreferredWidth(30);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(8);
            Foodcolumn.setPreferredWidth(50);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(9);
            Foodcolumn.setPreferredWidth(80);
            centerRenderer=new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            FoodItemsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
            
   // filtering the table   
         if(selectedFoodFilter.equals("All Items")) {

            // Update the table model
            DefaultTableModel fAllmodel = new DefaultTableModel(fdata, fcolumnName) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make all cells non-editable
                }
            };
            FoodItemsTable.setModel(fAllmodel);
            // Ensure the table headers are displayed if they sometimes disappear
            FoodItemsTable.getTableHeader().repaint();
            FoodItemsTable.repaint();
            FoodItemsTable.revalidate();
            
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(0);
            Foodcolumn.setPreferredWidth(5);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(1);
            Foodcolumn.setPreferredWidth(50);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(2);
            Foodcolumn.setPreferredWidth(150);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(3);
            Foodcolumn.setPreferredWidth(100);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(4);
            Foodcolumn.setPreferredWidth(150);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(5);
            Foodcolumn.setPreferredWidth(50);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(6);
            Foodcolumn.setPreferredWidth(50);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(7);
            Foodcolumn.setPreferredWidth(30);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(8);
            Foodcolumn.setPreferredWidth(50);
            Foodcolumn=FoodItemsTable.getColumnModel().getColumn(9);
            Foodcolumn.setPreferredWidth(80);
            centerRenderer=new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            FoodItemsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
            FoodItemsTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
            
          }else if (selectedFoodFilter.equals("In Stock")) {
        	                
                fcolumnName = new String[]{
                          "No", "Product ID", "Product Name", "Category", "Supplier", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
                  };

                  // Prepare data for the table model
                  Object[][] fInStockdata = new Object[FoodInStockArrayList.size()][10];
                  for (int i = 0; i < FoodInStockArrayList.size(); i++) {
                	  fInStockdata[i][0] = i + 1;
                	  fInStockdata[i][1] = FoodInStockArrayList.get(i).getProductId();
                	  fInStockdata[i][2] = FoodInStockArrayList.get(i).getProductName();
                	  fInStockdata[i][3] = FoodInStockArrayList.get(i).getCategory();
                	  fInStockdata[i][4] = FoodInStockArrayList.get(i).getManufacturer();
                	  fInStockdata[i][5] = FoodInStockArrayList.get(i).getExpDate();
                	  fInStockdata[i][6] = FoodInStockArrayList.get(i).getWareHouse();
                	  fInStockdata[i][7] = FoodInStockArrayList.get(i).getStock();
                      int fstock = FoodInStockArrayList.get(i).getStock();
                      fInStockdata[i][8] = FoodInStockArrayList.get(i).getPrice();
                      fInStockdata[i][9] = stockStatus(fstock);
         }
                  // Update the table model
                  DefaultTableModel fInStockmodel = new DefaultTableModel(fInStockdata, fcolumnName) {
                      @Override
                      public boolean isCellEditable(int row, int column) {
                          return false; // Make all cells non-editable
                      }
                  };
                  FoodItemsTable.setModel(fInStockmodel);
                  // Ensure the table headers are displayed if they sometimes disappear
                  FoodItemsTable.getTableHeader().repaint();
                  FoodItemsTable.repaint();
                  FoodItemsTable.revalidate();
          
                  Foodcolumn=FoodItemsTable.getColumnModel().getColumn(0);
                  Foodcolumn.setPreferredWidth(5);
                  Foodcolumn=FoodItemsTable.getColumnModel().getColumn(1);
                  Foodcolumn.setPreferredWidth(50);
                  Foodcolumn=FoodItemsTable.getColumnModel().getColumn(2);
                  Foodcolumn.setPreferredWidth(150);
                  Foodcolumn=FoodItemsTable.getColumnModel().getColumn(3);
                  Foodcolumn.setPreferredWidth(100);
                  Foodcolumn=FoodItemsTable.getColumnModel().getColumn(4);
                  Foodcolumn.setPreferredWidth(150);
                  Foodcolumn=FoodItemsTable.getColumnModel().getColumn(5);
                  Foodcolumn.setPreferredWidth(50);
                  Foodcolumn=FoodItemsTable.getColumnModel().getColumn(6);
                  Foodcolumn.setPreferredWidth(50);
                  Foodcolumn=FoodItemsTable.getColumnModel().getColumn(7);
                  Foodcolumn.setPreferredWidth(30);
                  Foodcolumn=FoodItemsTable.getColumnModel().getColumn(8);
                  Foodcolumn.setPreferredWidth(50);
                  Foodcolumn=FoodItemsTable.getColumnModel().getColumn(9);
                  Foodcolumn.setPreferredWidth(80);
                  centerRenderer=new DefaultTableCellRenderer();
                  centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                  FoodItemsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                  FoodItemsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                  FoodItemsTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
                  FoodItemsTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
                  FoodItemsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                  FoodItemsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                  FoodItemsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
                  FoodItemsTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
                  FoodItemsTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
                  FoodItemsTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
                  
   }else if (selectedFoodFilter.equals("Low Stock")) {
              
   fcolumnName = new String[]{
            "No", "Product ID", "Product Name", "Category", "Supplier", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
    };

    // Prepare data for the table model
    Object[][] fLowStockdata = new Object[FoodLowStockArrayList.size()][10];
    for (int i = 0; i < FoodLowStockArrayList.size(); i++) {
  	  fLowStockdata[i][0] = i + 1;
  	  fLowStockdata[i][1] = FoodLowStockArrayList.get(i).getProductId();
  	  fLowStockdata[i][2] = FoodLowStockArrayList.get(i).getProductName();
  	  fLowStockdata[i][3] = FoodLowStockArrayList.get(i).getCategory();
  	  fLowStockdata[i][4] = FoodLowStockArrayList.get(i).getManufacturer();
  	  fLowStockdata[i][5] = FoodLowStockArrayList.get(i).getExpDate();
  	  fLowStockdata[i][6] = FoodLowStockArrayList.get(i).getWareHouse();
  	  fLowStockdata[i][7] = FoodLowStockArrayList.get(i).getStock();
        int fstock = FoodLowStockArrayList.get(i).getStock();
        fLowStockdata[i][8] = FoodLowStockArrayList.get(i).getPrice();
        fLowStockdata[i][9] = stockStatus(fstock);
}
    // Update the table model
    DefaultTableModel fLowStockmodel = new DefaultTableModel(fLowStockdata, fcolumnName) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Make all cells non-editable
        }
    };
    FoodItemsTable.setModel(fLowStockmodel);
    // Ensure the table headers are displayed if they sometimes disappear
    FoodItemsTable.getTableHeader().repaint();
    FoodItemsTable.repaint();
    FoodItemsTable.revalidate();

    Foodcolumn=FoodItemsTable.getColumnModel().getColumn(0);
    Foodcolumn.setPreferredWidth(5);
    Foodcolumn=FoodItemsTable.getColumnModel().getColumn(1);
    Foodcolumn.setPreferredWidth(50);
    Foodcolumn=FoodItemsTable.getColumnModel().getColumn(2);
    Foodcolumn.setPreferredWidth(150);
    Foodcolumn=FoodItemsTable.getColumnModel().getColumn(3);
    Foodcolumn.setPreferredWidth(100);
    Foodcolumn=FoodItemsTable.getColumnModel().getColumn(4);
    Foodcolumn.setPreferredWidth(150);
    Foodcolumn=FoodItemsTable.getColumnModel().getColumn(5);
    Foodcolumn.setPreferredWidth(50);
    Foodcolumn=FoodItemsTable.getColumnModel().getColumn(6);
    Foodcolumn.setPreferredWidth(50);
    Foodcolumn=FoodItemsTable.getColumnModel().getColumn(7);
    Foodcolumn.setPreferredWidth(30);
    Foodcolumn=FoodItemsTable.getColumnModel().getColumn(8);
    Foodcolumn.setPreferredWidth(50);
    Foodcolumn=FoodItemsTable.getColumnModel().getColumn(9);
    Foodcolumn.setPreferredWidth(80);
    centerRenderer=new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    FoodItemsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);    
    FoodItemsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    FoodItemsTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
    FoodItemsTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
    FoodItemsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
    FoodItemsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
    FoodItemsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
    FoodItemsTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
    FoodItemsTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
    FoodItemsTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
    
}else if (selectedFoodFilter.equals("Out Of Stock")) {
    
fcolumnName = new String[]{
  "No", "Product ID", "Product Name", "Category", "Supplier", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
};

// Prepare data for the table model
Object[][] fOutOfStockdata = new Object[FoodOutOfStockArrayList.size()][10];
for (int i = 0; i < FoodOutOfStockArrayList.size(); i++) {
	fOutOfStockdata[i][0] = i + 1;
	fOutOfStockdata[i][1] = FoodOutOfStockArrayList.get(i).getProductId();
	fOutOfStockdata[i][2] = FoodOutOfStockArrayList.get(i).getProductName();
	fOutOfStockdata[i][3] = FoodOutOfStockArrayList.get(i).getCategory();
	fOutOfStockdata[i][4] = FoodOutOfStockArrayList.get(i).getManufacturer();
	fOutOfStockdata[i][5] = FoodOutOfStockArrayList.get(i).getExpDate();
	fOutOfStockdata[i][6] = FoodOutOfStockArrayList.get(i).getWareHouse();
	fOutOfStockdata[i][7] = FoodOutOfStockArrayList.get(i).getStock();
int fstock = FoodOutOfStockArrayList.get(i).getStock();
fOutOfStockdata[i][8] = FoodOutOfStockArrayList.get(i).getPrice();
fOutOfStockdata[i][9] = stockStatus(fstock);
}
// Update the table model
DefaultTableModel fOutOfStockmodel = new DefaultTableModel(fOutOfStockdata, fcolumnName) {
@Override
public boolean isCellEditable(int row, int column) {
  return false; // Make all cells non-editable
}
};
FoodItemsTable.setModel(fOutOfStockmodel);
// Ensure the table headers are displayed if they sometimes disappear
FoodItemsTable.getTableHeader().repaint();
FoodItemsTable.repaint();
FoodItemsTable.revalidate();

Foodcolumn=FoodItemsTable.getColumnModel().getColumn(0);
Foodcolumn.setPreferredWidth(5);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(1);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(2);
Foodcolumn.setPreferredWidth(150);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(3);
Foodcolumn.setPreferredWidth(100);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(4);
Foodcolumn.setPreferredWidth(150);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(5);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(6);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(7);
Foodcolumn.setPreferredWidth(30);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(8);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(9);
Foodcolumn.setPreferredWidth(80);
centerRenderer=new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
FoodItemsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
}
else if (selectedFoodFilter.equals("Near Expiry Products")) {
    
fcolumnName = new String[]{
  "No", "Product ID", "Product Name", "Category", "Supplier", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
};

// Prepare data for the table model
Object[][] fNearExpirydata = new Object[FoodNearExpiryProductsArrayList.size()][10];
for (int i = 0; i < FoodNearExpiryProductsArrayList.size(); i++) {
	fNearExpirydata[i][0] = i + 1;
	fNearExpirydata[i][1] = FoodNearExpiryProductsArrayList.get(i).getProductId();
	fNearExpirydata[i][2] = FoodNearExpiryProductsArrayList.get(i).getProductName();
	fNearExpirydata[i][3] = FoodNearExpiryProductsArrayList.get(i).getCategory();
	fNearExpirydata[i][4] = FoodNearExpiryProductsArrayList.get(i).getManufacturer();
	fNearExpirydata[i][5] = FoodNearExpiryProductsArrayList.get(i).getExpDate();
	fNearExpirydata[i][6] = FoodNearExpiryProductsArrayList.get(i).getWareHouse();
	fNearExpirydata[i][7] = FoodNearExpiryProductsArrayList.get(i).getStock();
int fstock = FoodNearExpiryProductsArrayList.get(i).getStock();
fNearExpirydata[i][8] = FoodNearExpiryProductsArrayList.get(i).getPrice();
fNearExpirydata[i][9] = stockStatus(fstock);
}
// Update the table model
DefaultTableModel fNearExpirymodel = new DefaultTableModel(fNearExpirydata, fcolumnName) {
@Override
public boolean isCellEditable(int row, int column) {
  return false; // Make all cells non-editable
}
};
FoodItemsTable.setModel(fNearExpirymodel);
// Ensure the table headers are displayed if they sometimes disappear
FoodItemsTable.getTableHeader().repaint();
FoodItemsTable.repaint();
FoodItemsTable.revalidate();

Foodcolumn=FoodItemsTable.getColumnModel().getColumn(0);
Foodcolumn.setPreferredWidth(5);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(1);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(2);
Foodcolumn.setPreferredWidth(150);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(3);
Foodcolumn.setPreferredWidth(100);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(4);
Foodcolumn.setPreferredWidth(150);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(5);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(6);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(7);
Foodcolumn.setPreferredWidth(30);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(8);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(9);
Foodcolumn.setPreferredWidth(80);
centerRenderer=new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
FoodItemsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
}
else if (selectedFoodFilter.equals("Expired Products")) {
    
columnName = new String[]{
  "No", "Product ID", "Product Name", "Category", "Manufacturer", "Expiration Date", "Warehouse", "Stock", "Price (1Qty)", "Status"
};

// Prepare data for the table model
Object[][] ExpiredProductdata = new Object[FoodExpiredProductsArrayList.size()][10];
for (int i = 0; i < FoodExpiredProductsArrayList.size(); i++) {
	ExpiredProductdata[i][0] = i + 1;
	ExpiredProductdata[i][1] = FoodExpiredProductsArrayList.get(i).getProductId();
	ExpiredProductdata[i][2] = FoodExpiredProductsArrayList.get(i).getProductName();
	ExpiredProductdata[i][3] = FoodExpiredProductsArrayList.get(i).getCategory();
	ExpiredProductdata[i][4] = FoodExpiredProductsArrayList.get(i).getManufacturer();
	ExpiredProductdata[i][5] = FoodExpiredProductsArrayList.get(i).getExpDate();
	ExpiredProductdata[i][6] = FoodExpiredProductsArrayList.get(i).getWareHouse();
	ExpiredProductdata[i][7] = FoodExpiredProductsArrayList.get(i).getStock();
int stock = FoodExpiredProductsArrayList.get(i).getStock();
ExpiredProductdata[i][8] = FoodExpiredProductsArrayList.get(i).getPrice();
ExpiredProductdata[i][9] = stockStatus(stock);
}
// Update the table model
DefaultTableModel Expiredmodel = new DefaultTableModel(ExpiredProductdata, columnName) {
@Override
public boolean isCellEditable(int row, int column) {
  return false; // Make all cells non-editable
}
};
FoodItemsTable.setModel(Expiredmodel);
// Ensure the table headers are displayed if they sometimes disappear
FoodItemsTable.getTableHeader().repaint();
FoodItemsTable.repaint();
FoodItemsTable.revalidate();

Foodcolumn=FoodItemsTable.getColumnModel().getColumn(0);
Foodcolumn.setPreferredWidth(5);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(1);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(2);
Foodcolumn.setPreferredWidth(150);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(3);
Foodcolumn.setPreferredWidth(100);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(4);
Foodcolumn.setPreferredWidth(150);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(5);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(6);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(7);
Foodcolumn.setPreferredWidth(30);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(8);
Foodcolumn.setPreferredWidth(50);
Foodcolumn=FoodItemsTable.getColumnModel().getColumn(9);
Foodcolumn.setPreferredWidth(80);
centerRenderer=new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
FoodItemsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
FoodItemsTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

}
    
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading medical supplies data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }
}