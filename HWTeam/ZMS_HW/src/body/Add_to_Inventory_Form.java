package body;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.awt.Cursor;
import javax.swing.border.MatteBorder;

public class Add_to_Inventory_Form extends JFrame {
	
	private JTable targetTable;
	static private String ClickedButtonType;
	static public int selectedRowIndex;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldProductName;
	private JTextField textFieldProductId;
	private JTextField textFieldManufacturer;
	private JTextField textFieldStock;
	private JTextField textFieldPrice;
	private JDateChooser dateChooser;
//	private JDateChooser dateChooser_1;
    private JLabel lblManufacturer ;
	public Object edit(JTable table) {
		JTable jtable=table;
		selectedRowIndex=jtable.getSelectedRow();
		DefaultTableModel model=(DefaultTableModel)jtable.getModel();
		String productID=(model.getValueAt(selectedRowIndex,1)).toString();
		String productNAME=(model.getValueAt(selectedRowIndex,2)).toString();
		String category=(model.getValueAt(selectedRowIndex,3)).toString();
		String manufacturer=(model.getValueAt(selectedRowIndex,4)).toString();
		String expDate=(model.getValueAt(selectedRowIndex,5)).toString();
		String warehouse=(model.getValueAt(selectedRowIndex,6)).toString();
		int stock=Integer.parseInt((model.getValueAt(selectedRowIndex,7)).toString());
		double price=Double.parseDouble((model.getValueAt(selectedRowIndex,8)).toString());
        InventoryProduct mep=new InventoryProduct(productID,productNAME,category,manufacturer,expDate,warehouse,stock,price);
		return mep;
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_to_Inventory_Form frame = new Add_to_Inventory_Form("",null);
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
	public Add_to_Inventory_Form(String buttonType,JTable table) throws SQLException {
		this.targetTable=table;
		Add_to_Inventory_Form.ClickedButtonType=buttonType;
	
		setResizable(false);
		setTitle("Zoo Management System ");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\HP\\eclipse-workspace\\ZMS_HW\\icon\\zoo_logo_nobg.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 100, 430, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel formHeaderpanel = new JPanel();
		formHeaderpanel.setForeground(new Color(206, 241, 123));
		formHeaderpanel.setBackground(new Color(12, 59, 46));
		formHeaderpanel.setPreferredSize(new Dimension(10, 75));
		contentPane.add(formHeaderpanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Product Form");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setPreferredSize(new Dimension(400, 60));
		lblNewLabel.setForeground(new Color(206, 241, 123));
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 32));
		formHeaderpanel.add(lblNewLabel);
		
		JPanel formFooterpanel = new JPanel();
		formFooterpanel.setBackground(new Color(206, 241, 123));
		formFooterpanel.setPreferredSize(new Dimension(10, 60));
		contentPane.add(formFooterpanel, BorderLayout.SOUTH);
		formFooterpanel.setLayout(null);
		
		
		
		JPanel formBodypanel = new JPanel();
		formBodypanel.setBackground(new Color(255, 253, 233));
		contentPane.add(formBodypanel, BorderLayout.CENTER);
		formBodypanel.setLayout(null);
		
		JLabel lblProductName = new JLabel("Name");
		lblProductName.setForeground(new Color(12, 59, 46));
		lblProductName.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblProductName.setBounds(34, 22, 108, 25);
		formBodypanel.add(lblProductName);
		
		JLabel lblProductId = new JLabel("ID");
		lblProductId.setForeground(new Color(12, 59, 46));
		lblProductId.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblProductId.setBounds(34, 64, 108, 25);
		formBodypanel.add(lblProductId);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(new Color(12, 59, 46));
		lblCategory.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblCategory.setBounds(34, 106, 108, 25);
		formBodypanel.add(lblCategory);
		
		if(targetTable.getName().equals("MedicalSuppliesTable")) {
		     lblManufacturer = new JLabel("Manufacturer");
		}else if(targetTable.getName().equals("FoodItemsTable")) {
			 lblManufacturer = new JLabel("Supplier");
		}
		lblManufacturer.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblManufacturer.setBounds(34, 146, 108, 25);
		formBodypanel.add(lblManufacturer);
		
		JLabel lblExpirationDate = new JLabel("Exp Date");
		lblExpirationDate.setForeground(new Color(12, 59, 46));
		lblExpirationDate.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblExpirationDate.setBounds(34, 189, 120, 25);
		formBodypanel.add(lblExpirationDate);
		
		JLabel lblWarehouse = new JLabel("Warehouse");
		lblWarehouse.setForeground(new Color(12, 59, 46));
		lblWarehouse.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblWarehouse.setBounds(34, 232, 131, 25);
		formBodypanel.add(lblWarehouse);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(new Color(12, 59, 46));
		lblStock.setHorizontalAlignment(SwingConstants.LEFT);
		lblStock.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblStock.setBounds(34, 273, 108, 25);
		formBodypanel.add(lblStock);
		
		JLabel lblPrice = new JLabel("Price                  ");
		lblPrice.setForeground(new Color(12, 59, 46));
		lblPrice.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblPrice.setBounds(34, 315, 131, 25);
		formBodypanel.add(lblPrice);
		
		textFieldProductName = new JTextField();
		textFieldProductName.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(206, 241, 123)));
		textFieldProductName.setFont(new Font("Arial", Font.PLAIN, 14));
		textFieldProductName.setCaretColor(new Color(0, 0, 0));
		textFieldProductName.setPreferredSize(new Dimension(7, 80));
		textFieldProductName.setBounds(175, 21, 188, 29);
		formBodypanel.add(textFieldProductName);
		textFieldProductName.setColumns(10);
		
		textFieldProductId = new JTextField();
		if(targetTable.getName().equals("MedicalSuppliesTable")) {
		    textFieldProductId.setText(Inventory_Management.FinalProduct_id);
		}else if(targetTable.getName().equals("FoodItemsTable")) {
			textFieldProductId.setText(Inventory_Management.FinalfProduct_id);
		}
		textFieldProductId.setFont(new Font("Arial", Font.PLAIN, 14));
		textFieldProductId.setPreferredSize(new Dimension(7, 80));
		textFieldProductId.setColumns(10);
		textFieldProductId.setBounds(175, 63, 188, 29);
		textFieldProductId.setEditable(false);
		formBodypanel.add(textFieldProductId);
		
		textFieldManufacturer = new JTextField();
		textFieldManufacturer.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(206, 241, 123)));
		textFieldManufacturer.setFont(new Font("Arial", Font.PLAIN, 14));
		textFieldManufacturer.setPreferredSize(new Dimension(7, 80));
		textFieldManufacturer.setColumns(10);
		textFieldManufacturer.setBounds(175, 149, 188, 29);
		formBodypanel.add(textFieldManufacturer);
		
		textFieldStock = new JTextField();
		textFieldStock.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(206, 241, 123)));
		textFieldStock.setFont(new Font("Arial", Font.PLAIN, 14));
		textFieldStock.setPreferredSize(new Dimension(7, 75));
		textFieldStock.setColumns(10);
		textFieldStock.setBounds(175, 276, 188, 29);
		formBodypanel.add(textFieldStock);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(206, 241, 123)));
		textFieldPrice.setFont(new Font("Arial", Font.PLAIN, 14));
		textFieldPrice.setPreferredSize(new Dimension(7, 75));
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(175, 314, 188, 29);
		formBodypanel.add(textFieldPrice);
		
		
		JComboBox <String> comboCategory = new JComboBox<String>();
		comboCategory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if(targetTable.getName().equals("MedicalSuppliesTable")) {
		    comboCategory.setModel(new DefaultComboBoxModel(new String[] {"Tablet/Pill", "Syrup/Liquid medicine", "Eye/Ear Drops", "Rectal/Vaginal Suppositories", "Inhalers", "Tropical Medications", "Injectables"}));
		}else if(targetTable.getName().equals("FoodItemsTable")) {
			comboCategory.setModel(new DefaultComboBoxModel(new String[] {"Carnivore Diet", "Herbivore Diet", "Omnivore Diet", "Specialized Diets"}));
		}
		comboCategory.setFont(new Font("Arial", Font.PLAIN, 14));
		comboCategory.setBounds(175, 105, 188, 29);
		formBodypanel.add(comboCategory);
		
		
		
		JComboBox<String> comboWarehouse = new JComboBox<String>();
		if(targetTable.getName().equals("MedicalSuppliesTable")) {
		    comboWarehouse.setModel(new DefaultComboBoxModel(new String[] {"Warehouse 1", "Warehouse 2", "Warehouse 3"}));
		}else if(targetTable.getName().equals("FoodItemsTable")) {
			comboWarehouse.setModel(new DefaultComboBoxModel(new String[] {"Warehouse 4", "Warehouse 5", "Warehouse 6"}));
		}
		comboWarehouse.setFont(new Font("Arial", Font.PLAIN, 14));
		comboWarehouse.setBounds(175, 234, 188, 29);
		formBodypanel.add(comboWarehouse);
		
		dateChooser=new JDateChooser();
//		dateChooser_1 = new JDateChooser();
		dateChooser.setFont(new Font("Arial", Font.PLAIN, 14));
		dateChooser.setBounds(175, 189, 188, 29); // Set the bounds to match your other text fields
		formBodypanel.add(dateChooser);
		

		if((Add_to_Inventory_Form.ClickedButtonType).equals("edit")) {
			InventoryProduct editMp=(InventoryProduct) edit(targetTable);
			
			textFieldProductName.setText(editMp.getProductName());
			textFieldProductId.setText(editMp.getProductId());
			comboCategory.setSelectedItem(editMp.getCategory());
			textFieldManufacturer.setText(editMp.getManufacturer());
	//date chooser
			try {
		        Date expDate = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(editMp.getExpDate());
		        dateChooser.setDate(expDate);
		    } catch (java.text.ParseException e) {
		        e.printStackTrace();
		    }
	//date chooser	
			comboWarehouse.setSelectedItem(editMp.getWareHouse());
			textFieldStock.setText(String.valueOf(editMp.getStock()));
			textFieldPrice.setText(String.valueOf(editMp.getPrice()));
		}	
//		else if((Add_to_Inventory_Form.ClickedButtonType).equals("delete")) {
//			String deleteID=delete(targetTable);
//			
//			String insertSQL="DELETE FROM medicalInventory WHERE product_id=?";
//			        
//	
//	        try(Connection con = DbConnection.getConnection();
//			PreparedStatement pstmt=con.prepareStatement(insertSQL)){
//	        	pstmt.setString(1, deleteID);
//	        	
//	        	int rowsInserted=pstmt.executeUpdate();
//				if(rowsInserted>0) {
//					JOptionPane.showMessageDialog(null, "Deleted successfully!");
//					System.out.println("Deleted Successfully");
//					setVisible(false);
//					
//					DefaultTableModel model=(DefaultTableModel)targetTable.getModel();
//					model.removeRow(selectedRowIndex);
//					
//				}else {
//					System.out.println("Adding failed");
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			
//	        }
//		}
		
		
		
		
		
	    JButton btnCancel = new JButton("Reset");
	    btnCancel.setBorder(null);
	    btnCancel.setForeground(new Color(255, 255, 255));
	    btnCancel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
	    		 if (confirm == JOptionPane.YES_OPTION) {
	    			 setVisible(false);
	    		 }
	    	}
	    });
		btnCancel.setFocusable(false);
		btnCancel.setBackground(new Color(255, 100, 0));
		btnCancel.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnCancel.setPreferredSize(new Dimension(100, 30));
		btnCancel.setBounds(228, 11, 106, 38);
		formFooterpanel.add(btnCancel);
		
		
// Add to table
		JButton btnSubmit = new JButton("Confirm");
		btnSubmit.setBorder(null);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName=textFieldProductName.getText().trim();
				String productId=textFieldProductId.getText().trim();
				String category=comboCategory.getSelectedItem().toString();
				String manufacturer=textFieldManufacturer.getText().trim();
			//dateChooser
				Date selectedDate = dateChooser.getDate();
		        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
		        String expDate = sdf.format(selectedDate);
		     //dateChooser
		        String warehouse=comboWarehouse.getSelectedItem().toString();
				int stock=Integer.parseInt(textFieldStock.getText().trim());
				double price=Double.parseDouble(textFieldPrice.getText().trim());
				String status = null;
				try {
					Inventory_Management Im=new Inventory_Management();
				    status=Im.stockStatus(stock);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				InventoryProduct mediP=new InventoryProduct(productId,productName,category,manufacturer,expDate,warehouse,stock,price);
				
			 if((Add_to_Inventory_Form.ClickedButtonType).equals("new")) {
				 String insertSQL="";
			    if(targetTable.getName().equals("MedicalSuppliesTable")) {
				    insertSQL="INSERT INTO medicalInventory(product_id,product_name,category,manufacturer,exp_date,warehouse,stock,price)"
						         +"VALUES (?,?,?,?,?,?,?,?)";}
			    else if(targetTable.getName().equals("FoodItemsTable")) {
			    	insertSQL="INSERT INTO animalFoodInventory(product_id,product_name,category,manufacturer,exp_date,warehouse,stock,price)"
					         +"VALUES (?,?,?,?,?,?,?,?)";}
			    	
				try(Connection con = DbConnection.getConnection();
						PreparedStatement pstmt=con.prepareStatement(insertSQL)){
					
					pstmt.setString(1, mediP.getProductId());
					pstmt.setString(2, mediP.getProductName());
					pstmt.setString(3, mediP.getCategory());
					pstmt.setString(4, mediP.getManufacturer());
					pstmt.setString(5, mediP.getExpDate());
					pstmt.setString(6, mediP.getWareHouse());
					pstmt.setInt(7, mediP.getStock());
					pstmt.setDouble(8, mediP.getPrice());
		
					
					int rowsInserted=pstmt.executeUpdate();
					if(rowsInserted>0) {
						
						JOptionPane.showMessageDialog(null, "Product added successfully!");
						System.out.println("Product Added Successfully");
						setVisible(false);
						
						DefaultTableModel model=(DefaultTableModel)targetTable.getModel();
						model.addRow(new Object[] {model.getRowCount()+1,productId,productName,category,manufacturer,expDate,warehouse,stock,price,status});
						
					}else {
						System.out.println("Adding failed");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}}
				
			else if(ClickedButtonType.equals("edit")) {
				String insertSQL="";
				if(targetTable.getName().equals("MedicalSuppliesTable")) {
			    	insertSQL="UPDATE medicalInventory SET product_name=?,category=?,manufacturer=?,exp_date=?,warehouse=?,stock=?,price=? WHERE product_id=?";
			    }else if(targetTable.getName().equals("FoodItemsTable")) {
			    	insertSQL="UPDATE animalFoodInventory SET product_name=?,category=?,manufacturer=?,exp_date=?,warehouse=?,stock=?,price=? WHERE product_id=?";
			    }
		     	try(Connection con = DbConnection.getConnection();
					PreparedStatement pstmt=con.prepareStatement(insertSQL)){
				
				
				pstmt.setString(1, mediP.getProductName());
				pstmt.setString(2, mediP.getCategory());
				pstmt.setString(3, mediP.getManufacturer());
				pstmt.setString(4, mediP.getExpDate());
				pstmt.setString(5, mediP.getWareHouse());
				pstmt.setInt(6, mediP.getStock());
				pstmt.setDouble(7, mediP.getPrice());
				pstmt.setString(8, mediP.getProductId()) ;
	
				
				int rowsInserted=pstmt.executeUpdate();
				if(rowsInserted>0) {
					JOptionPane.showMessageDialog(null, "Product Updated successfully!");
					System.out.println("Product Updated Successfully");
					setVisible(false);
					
					DefaultTableModel model=(DefaultTableModel)targetTable.getModel();
					// Update each cell in the selectedRowIndex
					model.setValueAt(mediP.getProductId(), selectedRowIndex, 1); // Assuming Column 1 is Product ID
					model.setValueAt(mediP.getProductName(), selectedRowIndex, 2); // Assuming Column 2 is Product Name
					model.setValueAt(mediP.getCategory(), selectedRowIndex, 3);
					model.setValueAt(mediP.getManufacturer(), selectedRowIndex, 4);
					model.setValueAt(mediP.getExpDate(), selectedRowIndex, 5);
					model.setValueAt(mediP.getWareHouse(), selectedRowIndex, 6);
					model.setValueAt(mediP.getStock(), selectedRowIndex, 7);
					model.setValueAt(mediP.getPrice(), selectedRowIndex, 8);
					// Also update the status if it's a displayed column and might change based on new stock
					 model.setValueAt(status, selectedRowIndex, 9);
					
				}else {
					System.out.println("Editing failed");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
					
					
				}}}
				
			
			
		);
           

		btnSubmit.setFocusable(false);
		btnSubmit.setForeground(new Color(255, 253, 233));
		btnSubmit.setBackground(new Color(12, 59, 46));
		btnSubmit.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnSubmit.setPreferredSize(new Dimension(100, 30));
		btnSubmit.setBounds(69, 11, 106, 38);
		formFooterpanel.add(btnSubmit); 
	}
}
