package companyAdministration;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowReviews extends JFrame {

	private JPanel contentPane;
    String empid;
    JList list = new JList();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowReviews frame = new ShowReviews();
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
	public ShowReviews() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReviews = new JLabel("reviews");
		lblReviews.setBounds(10, 11, 46, 14);
		contentPane.add(lblReviews);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 113, 340);
		contentPane.add(scrollPane);
		
	
		
		scrollPane.setViewportView(list);
		
		final JTextPane textTxt = new JTextPane();
		textTxt.setBounds(232, 30, 320, 340);
		
		contentPane.add(textTxt);
		
		JLabel lblNewLabel = new JLabel("/10");
		lblNewLabel.setBounds(303, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		final JTextPane rateTxt = new JTextPane();
		rateTxt.setBounds(273, 5, 24, 20);
		
		contentPane.add(rateTxt);
		
		JLabel lblNewLabel_1 = new JLabel("rate");
		lblNewLabel_1.setBounds(232, 11, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(10, 381, 89, 65);
		contentPane.add(btnNewButton);
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				ReviewDAO dao=null;
				
				try {
					dao = new ReviewDAO();
					rateTxt.setText((Integer.toString(dao.getRev(list.getSelectedValue().toString()).rate)));
					textTxt.setText(dao.getRev(list.getSelectedValue().toString()).st);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
	}
	public void getRev(String name,String surname) throws Exception{
		ReviewDAO rev=new ReviewDAO();
		EmployeesDAO emp=new EmployeesDAO();
		DefaultListModel model=new DefaultListModel();
		for (Review r:rev.getReviews(emp.getid(name, surname))){
			model.addElement(r.date);
		}
		
		list.setModel(model);
		
		
		
		
	}
}
