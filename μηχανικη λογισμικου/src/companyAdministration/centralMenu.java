package companyAdministration;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class centralMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					centralMenu frame = new centralMenu();
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
	public centralMenu() {
		setTitle("central hr menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton users = new JButton("users");
		users.setFont(new Font("",Font.BOLD,15));
		users.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					UsersMenu m=new UsersMenu();
					m.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		users.setBounds(36, 85, 124, 47);
		contentPane.add(users);
		
		JButton employees = new JButton("employees");
		employees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
				dispose();
				
			
				try {
					EmployeesMenu m = new EmployeesMenu();
					m.setVisible(true);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				}
			
		});
		employees.setFont(new Font("",Font.BOLD,15));
		employees.setBounds(210, 85, 124, 47);
		contentPane.add(employees);
		
		JButton btnExit = new JButton("exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				logIn m=new logIn();
				m.setVisible(true);
			}
		});
		btnExit.setBounds(0, 0, 89, 23);
		contentPane.add(btnExit);
	}
}
