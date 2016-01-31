package companyAdministration;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class UsersMenu<E> extends JFrame {

	private JPanel contentPane;
	private JTextField hrNameTxt;
	private JTextField hrPdTxt;
	private JTextField svNameTxt;
	private JTextField svPdTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersMenu frame = new UsersMenu();
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
	public UsersMenu() throws Exception {
		setTitle("Hr's menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 397, 396);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHr = new JLabel("hr");
		lblHr.setBounds(188, 5, 20, 26);
		lblHr.setFont(new Font("",Font.BOLD,20));
		panel.add(lblHr);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 28, 169, 357);
		panel.add(scrollPane);
		
		final JList hrList = new JList();
		
		hrList.setModel(getHr());
		scrollPane.setViewportView(hrList);
		
		
		
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(10, 68, 59, 14);
		panel.add(lblUsername);
		
		hrNameTxt = new JTextField();
		hrNameTxt.setBounds(84, 65, 86, 20);
		panel.add(hrNameTxt);
		hrNameTxt.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(10, 145, 59, 14);
		panel.add(lblPassword);
		
		hrPdTxt = new JTextField();
		hrPdTxt.setBounds(84, 142, 86, 20);
		panel.add(hrPdTxt);
		hrPdTxt.setColumns(10);
		
		JButton btnAddHr = new JButton("add hr");
		btnAddHr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!hrNameTxt.getText().equals("")||!hrPdTxt.getText().equals("")){
				Hr hr=new Hr(hrNameTxt.getText(),hrPdTxt.getText());
				try {
					HrDAO dao=new HrDAO();
					dao.insert(hr);
					hrList.setModel(getHr());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hrNameTxt.setText("");
				hrPdTxt.setText("");
				}else JOptionPane.showMessageDialog(null, "you must fill the fields");
			}
		});
		btnAddHr.setBounds(81, 199, 89, 23);
		panel.add(btnAddHr);
		
		JButton backBtn = new JButton("back");
		backBtn.setFont(new Font("",Font.BOLD,20));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				centralMenu m=new centralMenu();
				m.setVisible(true);
			}
		});
		backBtn.setBounds(0, 310, 95, 75);
		panel.add(backBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(417, 11, 397, 396);
		contentPane.add(panel_1);
		
		JLabel svLbl = new JLabel("sv");
		svLbl.setFont(new Font("Dialog", Font.BOLD, 20));
		svLbl.setBounds(175, 5, 38, 26);
		panel_1.add(svLbl);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(218, 28, 169, 357);
		panel_1.add(scrollPane_1);
		
		final JList<? extends E> svList = new JList();
		svList.setModel(getSv());
		scrollPane_1.setViewportView(svList);
		
		JLabel label_1 = new JLabel("username");
		label_1.setBounds(10, 68, 59, 14);
		panel_1.add(label_1);
		
		svNameTxt = new JTextField();
		svNameTxt.setColumns(10);
		svNameTxt.setBounds(84, 65, 86, 20);
		panel_1.add(svNameTxt);
		
		JLabel label_2 = new JLabel("password");
		label_2.setBounds(10, 145, 69, 14);
		panel_1.add(label_2);
		
		svPdTxt = new JTextField();
		svPdTxt.setColumns(10);
		svPdTxt.setBounds(84, 142, 86, 20);
		panel_1.add(svPdTxt);
		
		
		final JComboBox comboBox = new JComboBox();
		PartsDAO p=new PartsDAO();
		ArrayList<String> list=new ArrayList();
		for (Parts part:p.getAllParts()){
			list.add(part.name);
		}
		DefaultComboBoxModel model=new DefaultComboBoxModel(list.toArray());
		
		comboBox.setModel(model);
		comboBox.setBounds(84, 197, 86, 20);
		panel_1.add(comboBox);
		JButton btnAddSv = new JButton("add sv");
		btnAddSv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if (!svNameTxt.getText().equals("")||!svPdTxt.getText().equals("")){
				
				Sv sv=new Sv(svNameTxt.getText(),svPdTxt.getText(),comboBox.getSelectedItem().toString());
			SvDAO dao;
			try {
				dao = new SvDAO();
				dao.insert(sv);
				svList.setModel(getSv());
				svNameTxt.setText("");
				svPdTxt.setText("");
			}
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}else JOptionPane.showMessageDialog(null, "you must fill the fields");
			
			}
		});
		btnAddSv.setBounds(81, 255, 89, 23);
		panel_1.add(btnAddSv);
		
		
		
		JLabel lblSelect = new JLabel("select ");
		lblSelect.setBounds(10, 197, 46, 14);
		panel_1.add(lblSelect);
		
		JLabel lblDeapartment = new JLabel("department");
		lblDeapartment.setBounds(10, 211, 69, 14);
		panel_1.add(lblDeapartment);
	}
	public DefaultListModel getHr() throws Exception{
		DefaultListModel model=new DefaultListModel();
		model.clear();
		HrDAO dao=new HrDAO();
		for (Hr hr:dao.getAllHr()){
			model.addElement(hr.username);
		}
		return model;
		
	}
	
	public DefaultListModel getSv() throws Exception{
		DefaultListModel model=new DefaultListModel();
		model.clear();
		SvDAO dao=new SvDAO();
		for (Sv sv:dao.getAllSv()){
			model.addElement(sv.username + "                "+ sv.department );
		}
		return model;
		
	}
}
