package companyAdministration;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JEditorPane;

public class SvMenu extends JFrame {

	private JPanel setRevBtn;
	private JTextField rateTxt;
	private String name;
	JList list = new JList();
	private JTextField dateTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					SvMenu frame = new SvMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param dep
	 * @throws Exception
	 */
	public SvMenu() throws Exception {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 476);
		setRevBtn = new JPanel();
		setRevBtn.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(setRevBtn);
		setRevBtn.setLayout(null);

		JLabel lblEmployees = new JLabel("employees");
		lblEmployees.setBounds(35, 68, 82, 14);
		setRevBtn.add(lblEmployees);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 93, 198, 309);
		setRevBtn.add(scrollPane);
		
		
		scrollPane.setViewportView(list);

		JButton backBtn = new JButton("exit");

		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				logIn m;
				try {
					m = new logIn();
					m.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		backBtn.setBounds(28, 11, 89, 52);
		setRevBtn.add(backBtn);

		final JEditorPane reviewTxt = new JEditorPane();
		reviewTxt.setBounds(253, 93, 300, 309);
		setRevBtn.add(reviewTxt);

		JButton btnNewButton_1 = new JButton("set review");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.getSelectedIndex() < 0) {
					JOptionPane.showMessageDialog(null, "you must select an employee");

				} else {
					if (reviewTxt.getText().equals("") || dateTxt.getText().equals("") || rateTxt.getText().equals(""))
						JOptionPane.showMessageDialog(null, "you must fill the fields");
					else {
						if (Integer.parseInt(rateTxt.getText())>10||Integer.parseInt(rateTxt.getText())<0){
							JOptionPane.showMessageDialog(null, "rate must be from 1-10");
						
					}else {
						String[] ar = list.getSelectedValue().toString().split(" ");
						EmployeesDAO emp;

						try {

							ReviewDAO rev = new ReviewDAO();
							emp = new EmployeesDAO();
							Review review = new Review(Integer.parseInt(rateTxt.getText()), reviewTxt.getText(),
									emp.getid(ar[0], ar[2]), dateTxt.getText());
							rev.insert(review);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						reviewTxt.setText("");
						dateTxt.setText("");
						rateTxt.setText("");
						JOptionPane.showMessageDialog(null, "review added successfully");
						
					}
				}

			}}
		});
		btnNewButton_1.setBounds(432, 64, 121, 23);
		setRevBtn.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("/10");
		lblNewLabel.setBounds(330, 68, 46, 14);
		setRevBtn.add(lblNewLabel);

		JLabel lblRate = new JLabel("rate");
		lblRate.setBounds(243, 68, 32, 14);
		setRevBtn.add(lblRate);

		rateTxt = new JTextField();
		rateTxt.setBounds(288, 65, 32, 20);
		setRevBtn.add(rateTxt);
		rateTxt.setColumns(10);

		JButton btnNewButton = new JButton("show reviews");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.getSelectedIndex() < 0) { //ελεχγουμε αν εχει επιλεξει ο χρηστης καποιο στοιχειο απο τη λιστα
                //αν οχι του λεμε πως για να προχωρησει σε εκχωρηση η  προβoλη review πρεπει να εχει επιλιξει καποιο στοιχειο απο τη λιστα                               
					JOptionPane.showMessageDialog(null, "you must select an employee");
				} else {
					String[] ar = list.getSelectedValue().toString().split(" ");//εφοσον εχει επιλεξει στοιχειο,επειδη στην jlist εχουμε ονομα,επωνυμο,ημερομηνια
					//σαν μια λεξη(ετσι δουλευει η jlist) σπαμε το επιλεγμενο στοιχειο της λιστας (αφου το εχουμε μετατρεψει σε string)
					//σε τρεις λεξεις με χρηση της split(" ") και τοποθετουμε τις λεξεις σε εναν πινακα. 
					ShowReviews m = new ShowReviews();//καλουμε τον constructor του επομενου jframe που θα μας διχνει τα reviews του καθε εργαζομενου
					try { //try-catch block γιατι θα επιχειρησουμε συνδεση με mysql
						m.getRev(ar[0], ar[2]);//μεθοδος του ShowReviews() η οποια παιρνει σαν ορισματα το ονομα και επωνυμο και βαζει στη jlist του επομενου 
						//jframe τα reviews του επιλεγμενου εργαζομενου
						m.setVisible(true);//εμφανιζουμε το επομενο Jframe

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		btnNewButton.setBounds(432, 36, 121, 23);
		setRevBtn.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("date");
		lblNewLabel_1.setBounds(243, 49, 46, 14);
		setRevBtn.add(lblNewLabel_1);

		dateTxt = new JTextField();
		dateTxt.setBounds(290, 43, 86, 20);
		setRevBtn.add(dateTxt);
		dateTxt.setColumns(10);

	}

	public void getSv(String name) throws Exception {
		this.name = name;
		SvDAO sv = new SvDAO();
		EmployeesDAO emp = new EmployeesDAO();
		DefaultListModel model = new DefaultListModel();
		for (Employee e : emp.getByPart(sv.getDep(name))) {
			model.addElement(e.name + "  " + e.surname + "  " + e.date);
		}

		list.setModel(model);

	}
}
