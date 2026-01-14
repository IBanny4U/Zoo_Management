package welcome;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class Splash extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JProgressBar myProgressBar;
	private static JLabel progressNumlbl; 

	

	/**
	 * Create the frame.
	 */
	public Splash() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Splash.class.getResource("/icon/zoo_logo_nobg.png")));
		setResizable(false);
		setTitle("Zoo Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(440, 180, 480, 321);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 50, 35));
		contentPane.setForeground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Zoo Management System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(206, 241, 123));
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 143, 464, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Splash.class.getResource("/icon/splashbg.png")));
		lblNewLabel_1.setBounds(0, -22, 464, 209);
		contentPane.add(lblNewLabel_1);
		
		myProgressBar = new JProgressBar();
		myProgressBar.setBackground(new Color(255, 253, 233));
		myProgressBar.setForeground(new Color(255, 100, 0));
		myProgressBar.setBounds(0, 268, 464, 14);
		contentPane.add(myProgressBar);
		
		progressNumlbl = new JLabel("%");
		progressNumlbl.setForeground(new Color(255, 253, 233));
		progressNumlbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		progressNumlbl.setBounds(220, 235, 43, 27);
		contentPane.add(progressNumlbl);
		
		JLabel lblNewLabel_2 = new JLabel("Hello World");
		lblNewLabel_2.setForeground(new Color(255, 100, 0));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(54, 34, 78, 14);
		contentPane.add(lblNewLabel_2);

	}
	
	
	public static void main(String[] args) {
		Splash mySplash = new Splash();
		
		mySplash.setVisible(true);
				try {

					for(int i=0;i<=100;i++) {
						Thread.sleep(10);
						myProgressBar.setValue(i);
						progressNumlbl.setText(i+"%");
						if (i==33) {
							Thread.sleep(0);
						}
						if (i>33) {
							Thread.sleep(0);
						}
						if (i==98) {
							Thread.sleep(0);
						}
						if (i==99) {
							Thread.sleep(0);
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				SignIn_Form sI=new SignIn_Form();
				sI.setVisible(true);
				mySplash.setVisible(false);
			}
}
