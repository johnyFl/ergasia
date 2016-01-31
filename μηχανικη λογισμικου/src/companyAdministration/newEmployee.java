package companyAdministration;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class newEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField dateTxt;
	private JTextField surnameTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newEmployee frame = new newEmployee();
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
	public newEmployee() throws Exception {
		setTitle("new employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		final JComboBox comboBox = new JComboBox();
		PartsDAO p=new PartsDAO();
		ArrayList<String> list=new ArrayList();
		for (Parts part:p.getAllParts()){
			list.add(part.name);
		}
		DefaultComboBoxModel model=new DefaultComboBoxModel(list.toArray());
		
		comboBox.setModel(model);
		
		comboBox.setBounds(293, 26, 67, 23);
		contentPane.add(comboBox);
	
		
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (nameTxt.getText().equals("")||dateTxt.getText().equals("")){
				JOptionPane.showMessageDialog(null, "you must fill the fields!");
				}
				else
				{
					Employee emp=new Employee(nameTxt.getText(),surnameTxt.getText(),dateTxt.getText(),comboBox.getSelectedItem().toString());
					try {
						EmployeesDAO dao=new EmployeesDAO();
						dao.insert(emp);
					JOptionPane.showMessageDialog(null, "insert ok");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dispose();}
				
			}
		});
		btnOk.setBounds(80, 142, 89, 23);
		contentPane.add(btnOk);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(24, 30, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblDate = new JLabel("date");
		lblDate.setBounds(24, 114, 46, 14);
		contentPane.add(lblDate);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(80, 27, 86, 20);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		
		dateTxt = new JTextField();
		dateTxt.setText("");
		dateTxt.setBounds(80, 111, 86, 20);
		contentPane.add(dateTxt);
		dateTxt.setColumns(10);
		
		JLabel lblSurname = new JLabel("surname");
		lblSurname.setBounds(24, 72, 46, 14);
		contentPane.add(lblSurname);
		
		surnameTxt = new JTextField();
		surnameTxt.setBounds(80, 69, 86, 20);
		contentPane.add(surnameTxt);
		surnameTxt.setColumns(10);
		
		JLabel lblSelectDepartment = new JLabel("select department");
		lblSelectDepartment.setBounds(176, 30, 107, 14);
		contentPane.add(lblSelectDepartment);
		
	
}	
}
