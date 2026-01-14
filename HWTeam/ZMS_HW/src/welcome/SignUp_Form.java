package welcome;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SignUp_Form extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameSUField;
	private JPasswordField passwordSUField;
	private User person;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp_Form frame = new SignUp_Form();
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
	public SignUp_Form() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\HP\\eclipse-workspace\\ZMS_HW\\icon\\zoo_logo_nobg.png"));
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
		
		JPanel left_panel = new JPanel();
		left_panel.setLayout(null);
		left_panel.setBackground(Color.WHITE);
		left_panel.setBounds(0, 0, 436, 405);
		contentPane.add(left_panel);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setBackground(new Color(255, 253, 233));
		lblSignUp.setBounds(106, 47, 144, 31);
		lblSignUp.setForeground(new Color(255, 253, 233));
		lblSignUp.setFont(new Font("Candara Light", Font.BOLD, 30));
		right_panel.add(lblSignUp);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setForeground(new Color(255, 253, 233));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(29, 95, 81, 14);
		right_panel.add(lblNewLabel_2);
		
		usernameSUField = new JTextField();
		usernameSUField.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setLabelFor(usernameSUField);
		usernameSUField.setBounds(29, 112, 257, 41);
		right_panel.add(usernameSUField);
		usernameSUField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setForeground(new Color(255, 253, 233));
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(29, 162, 81, 14);
		right_panel.add(lblNewLabel_2_1);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(255, 253, 233));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inUsername=usernameSUField.getText().trim();
				String inPassword=String.valueOf(passwordSUField.getPassword());
				System.out.println(inUsername);
				System.out.println(inPassword);
				if (inUsername.equals("") || inPassword.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill the following boxes!!");
			    	usernameSUField.setText("");
			    	passwordSUField.setText("");
			    
				}else {
					JOptionPane.showMessageDialog(null, "Account Registered!!");
							 
				  person=new User(inUsername,inPassword);
//				  SIn_SUp_dataManager.arrayList.add(person);
			
			    	usernameSUField.setText("");
			    	passwordSUField.setText("");
			    	setVisible(false);
			    	SignIn_Form sI=new SignIn_Form();
			    	sI.setVisible(true);
				}
			}
		});
		
		btnSignUp.setBackground(new Color(255, 100, 0));
		btnSignUp.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSignUp.setBounds(29, 242, 257, 45);
		right_panel.add(btnSignUp);
		
		passwordSUField = new JPasswordField();
		passwordSUField.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_1.setLabelFor(passwordSUField);
		passwordSUField.setBounds(29, 179, 257, 41);
		right_panel.add(passwordSUField);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\HP\\eclipse-workspace\\ZMS_HW\\icon\\HELLO WORLD (1).png"));
		lblNewLabel_1.setBounds(0, 0, 437, 405);
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
