package welcome;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import body.*;
import body.Staff_Management.Staffdbconnection;

import javax.swing.SwingConstants;
public class SignIn_Form extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	public static String signInAccUsername;
	public static String signInAccRole;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn_Form frame = new SignIn_Form();
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
	
	public SignIn_Form() {
		//introduce staffList arraylist from staff_Management
		Staff_Management SM=new Staff_Management();
		Staff_Management.Staffdbconnection sdbConnection = SM.new Staffdbconnection();
			sdbConnection.getDataLength ();
			
		
		setForeground(new Color(0, 50, 35));
		setBackground(new Color(0, 50, 35));
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignIn_Form.class.getResource("/icon/zoo_logo_nobg.png")));
		setTitle("Zoo Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 160, 768, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JPanel right_panel = new JPanel();
		right_panel.setBackground(new Color(0, 50, 35));
		right_panel.setBounds(436, 0, 316, 405);
		contentPane.add(right_panel);
		right_panel.setLayout(null);
		
		JLabel lblSignIn = new JLabel("Sign In");
		lblSignIn.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignIn.setBackground(new Color(255, 253, 233));
		lblSignIn.setBounds(29, 42, 257, 35);
		lblSignIn.setForeground(new Color(206, 241, 123));
		lblSignIn.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		right_panel.add(lblSignIn);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setForeground(new Color(255, 253, 233));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(29, 112, 121, 14);
		right_panel.add(lblNewLabel_2);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_2.setLabelFor(usernameField);
		usernameField.setBounds(29, 129, 257, 41);
		right_panel.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(29, 181, 121, 14);
		right_panel.add(lblNewLabel_2_1);
	
		JLabel SignInStatus_lbl = new JLabel("");
		SignInStatus_lbl.setVerticalAlignment(SwingConstants.BOTTOM);
		SignInStatus_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		SignInStatus_lbl.setForeground(new Color(140, 200, 80));
		SignInStatus_lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		SignInStatus_lbl.setBounds(29, 82, 257, 19);
		right_panel.add(SignInStatus_lbl);	
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setFocusable(false);
		btnSignIn.setForeground(new Color(255, 253, 233));
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inUsername=usernameField.getText().trim();
				String inPassword=new String(passwordField.getPassword());
				
				boolean found=false;
//				for(User person:SIn_SUp_dataManager.arrayList) {
		        for(int i=0;i<Staff_Management.staffList.size();i++) {
				
		        	if(inUsername.equals(Staff_Management.staffList.get(i).getUsername()) && inPassword.equals(Staff_Management.staffList.get(i).getPassword()) && "Active".equals(Staff_Management.staffList.get(i).getStatus()) ) {
			    	 found=true;
			    	usernameField.setText("");
			    	passwordField.setText("");
			    	JLabel messageLabel=new JLabel("<html><font color='#003223'>Sign In Successful!!</font></html>");
		        	messageLabel.setFont(new Font("Arial",Font.PLAIN,13));
			    	JOptionPane.showMessageDialog(btnSignIn,messageLabel, "Sign In Status" ,JOptionPane.INFORMATION_MESSAGE);
			    	
			    	    	
//					SignInStatus_lbl.setForeground(new Color(140, 200, 80));
//			    	SignInStatus_lbl.setText("Sign In Successful!!");
			    	
			    	signInAccUsername=inUsername;
			    	signInAccRole=Staff_Management.staffList.get(i).getRole();
			    	
							
			    	 Dashboard d;
					try {
						d = new Dashboard(); 
						d.setVisible(true);
			    	 setVisible(false);
			    	 break;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	
			    }}
			    if(!found) {
//			    	JOptionPane.showMessageDialog( btnSignIn, "Something went wrong!");
			   	    SignInStatus_lbl.setForeground(new Color(255, 100, 0));
		    	    SignInStatus_lbl.setText("Something went wrong!!");
			    	
		    	    
			    	usernameField.setText("");
			    	passwordField.setText("");
			    }
				
	
			}
		});
		btnSignIn.setBackground(new Color(255, 100, 0));
		btnSignIn.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSignIn.setBounds(29, 269, 257, 45);
		right_panel.add(btnSignIn);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_2_1.setLabelFor(passwordField);
		passwordField.setBounds(29, 198, 257, 41);
		right_panel.add(passwordField);
		
		
		
		JPanel left_panel = new JPanel();
		left_panel.setBounds(0, 0, 438, 405);
		contentPane.add(left_panel);
		left_panel.setBackground(new Color(0, 50, 35));
		left_panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(SignIn_Form.class.getResource("/icon/HELLO WORLD.png")));
		lblNewLabel_1.setBounds(0, 0, 438, 405);
		left_panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Zoo Management System");
		lblNewLabel.setForeground(new Color(0, 13, 128));
		lblNewLabel.setBounds(42, 138, 358, 46);
		left_panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		
		JLabel lblNewLabel_3 = new JLabel("Zoo Management System");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_3.setBounds(42, 148, 358, 34);
		left_panel.add(lblNewLabel_3);

	}
}
