package companyAdministration;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class EmployeesMenu extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField PartTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeesMenu frame = new EmployeesMenu();
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
	public EmployeesMenu() throws Exception {
		setTitle("Employees menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final JList PartsList = new JList();
		final JPanel employeesP = new JPanel();
		employeesP.setBounds(216, 102, 501, 317);
		contentPane.add(employeesP);
		employeesP.setLayout(null);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		
		table.setBounds(0, 0, 483, 278);

		employeesP.add(table);

		JLabel lbParts = new JLabel("departments");
		lbParts.setBounds(57, 77, 76, 14);
		contentPane.add(lbParts);
  		
  		
		PartsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
		            
				try {
				EmployeesDAO emp=new EmployeesDAO();
				
				 DefaultTableModel tableModel = new DefaultTableModel();
				 
				 tableModel.addColumn("name");
				 tableModel.addColumn("surname");
				 tableModel.addColumn("date");
				 
				 for (Employee e:emp.getByPart(PartsList.getSelectedValue().toString())){
					tableModel.addRow(new String[]{e.name,e.surname,e.date});
					
				 }
				 table.setModel(tableModel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		});
		final PartsDAO parts=new PartsDAO();
		final DefaultListModel model=new DefaultListModel();
				
			
		
		for (Parts p:parts.getAllParts()){
			
			model.addElement(p.name);
			
		}
		
		PartsList.setModel(model);
		
		PartsList.setBounds(57, 102, 113, 317);
		contentPane.add(PartsList);
		
		
		
	
		
		
		
		JButton newEmpBtn = new JButton("New employee");
		newEmpBtn.setBounds(216, 23, 165, 39);
		contentPane.add(newEmpBtn);
		
		JButton btnAddPart = new JButton("add department");
		btnAddPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!PartTxt.getText().equals("")){
				Parts part=new Parts(PartTxt.getText());
				try {
					PartsDAO dao=new PartsDAO();
					dao.insert(part);
					model.clear();
					for (Parts p:parts.getAllParts()){
						
						model.addElement(p.name);
						
					}
					
					PartsList.setModel(model);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else JOptionPane.showMessageDialog(null, "you must fill the fields");
				PartTxt.setText("");	
			}
			
		});
		btnAddPart.setBounds(51, 430, 129, 23);
		contentPane.add(btnAddPart);
		
		PartTxt = new JTextField();
		PartTxt.setBounds(216, 430, 86, 20);
		contentPane.add(PartTxt);
		PartTxt.setColumns(10);
		
		JButton backBtn = new JButton("back");
		backBtn.setFont(new Font("",Font.BOLD,20));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				centralMenu m=new centralMenu();
				m.setVisible(true);
			}
		});
		backBtn.setBounds(10, 11, 104, 55);
		contentPane.add(backBtn);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(256, 77, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("surname");
		lblSurname.setBounds(416, 77, 61, 14);
		contentPane.add(lblSurname);
		
		JLabel lblDate = new JLabel("date");
		lblDate.setBounds(610, 77, 46, 14);
		contentPane.add(lblDate);
		newEmpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				  newEmployee nE;
				try {
					nE = new newEmployee();
					nE.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  
				
			}
		});

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
				
			}
		});
	}
}
