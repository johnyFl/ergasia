package companyAdministration;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.SQLException;
public class logIn extends JFrame {

	private JPanel contentPane;
	public JTextField userTxt;
	private JTextField pdTxt;
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logIn frame = new logIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public logIn()  {
		
		
		setTitle("company project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("welcome");
		lblWelcome.setFont(new Font("",Font.BOLD,20));
		lblWelcome.setBounds(104, 11, 88, 36);
		contentPane.add(lblWelcome);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setBounds(71, 73, 62, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setBounds(71, 99, 62, 14);
		contentPane.add(lblNewLabel_1);
		
		userTxt = new JTextField();
		userTxt.setBounds(137, 70, 112, 20);
		contentPane.add(userTxt);
		userTxt.setColumns(10);
		
		pdTxt = new JTextField();
		pdTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pdTxt.setText("*");
			}
		});
		pdTxt.setBounds(137, 96, 112, 20);
		contentPane.add(pdTxt);
		pdTxt.setColumns(10);
		
		JButton logIn = new JButton("log in");
		logIn.setFont(new Font("",Font.BOLD,15));
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					HrDAO hr=new HrDAO();
					SvDAO sv=new SvDAO();

					
					
					if (hr.loginCheck(userTxt.getText(), pdTxt.getText())==true){
						
					   JOptionPane.showMessageDialog(null,"you enter as an hr");
						dispose();
						centralMenu m=new centralMenu();
						m.setVisible(true);	
					}
					else if (sv.loginCheck(userTxt.getText(), pdTxt.getText())==true){
						JOptionPane.showMessageDialog(null,"you enter as an sv");
						
						dispose();
						SvMenu m=new SvMenu();
						m.getSv(userTxt.getText());
						m.setVisible(true);
						
					}
					else JOptionPane.showMessageDialog(null,"ivalid name or password");
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
             
				
	
			}
		});
		logIn.setBounds(104, 142, 89, 23);
		contentPane.add(logIn);
	}
	
	
}
