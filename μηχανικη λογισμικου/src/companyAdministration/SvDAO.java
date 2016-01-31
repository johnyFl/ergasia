package companyAdministration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SvDAO {
	
	private Connection myCon;

	public SvDAO() throws SQLException {
		myCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/company", "root", "ςυνγρεεψε7");
		
	}

	
	public boolean loginCheck(String username, String password) throws Exception {
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		boolean found = true;
		String n = "";
		
		try {
			myStm = myCon.prepareStatement("SELECT * FROM sv WHERE USERNAME=? AND PASSWORD=?");
			myStm.setString(1, username);
			myStm.setString(2, password);
			
			myRs = myStm.executeQuery();
			
			while(myRs.next()){
				n=myRs.getString("username")+myRs.getString("password");
			}
		
			
			if (n.equals("")) return false;
			else return true;
			
		} finally {
			myStm.close();
            myRs.close();
		}
	}
	public String getDep(String username) throws Exception {
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		String n = "";
		
		try {
			myStm = myCon.prepareStatement("SELECT * FROM sv WHERE USERNAME=?");
			myStm.setString(1, username);
			
			
			myRs = myStm.executeQuery();
			
			while(myRs.next()){
				n=myRs.getString("idparts");
			}
		
			
			return n;
			
		} finally {
			myStm.close();
            myRs.close();
		}
	}
	
	
	
	
	public List<Sv> getAllSv() throws Exception{
		Statement myStm= null;
		ResultSet myRs=null;
		List<Sv> list=new ArrayList();
		
		try{
		myStm=myCon.createStatement();
		myRs=myStm.executeQuery("SELECT * FROM sv");
		
		while(myRs.next()){
		Sv sv=new Sv(myRs.getString("USERNAME"),myRs.getString("PASSWORD"),myRs.getString("idparts"));
		list.add(sv);
		}
		System.out.println(list);
		return list;
		
		}
		finally{
		myStm.close();	
		myRs.close();
		}
		}
	
	
	
	public void insert(Sv sv) throws Exception{
		PreparedStatement myStm=null;
		ResultSet myRs=null;
		
		try{
			myStm=myCon.prepareStatement("insert into sv"
		    		+"(username,password,idparts)"
		            +"values(?,?,?)");
			myStm.setString(1, sv.username);
			myStm.setString(2, sv.password);
			myStm.setString(3, sv.department);
			myStm.executeUpdate();
			
		}
		finally{
			myStm.close();
		}
	}
		
		
		
		
	

}
