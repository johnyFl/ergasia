package companyAdministration;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HrDAO {

	private Connection myCon;

	public HrDAO() throws SQLException {
		myCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/company", "root", "ςυνγρεεψε7");
		
	}

	
	
	
	public boolean loginCheck(String username, String password) throws Exception {
		PreparedStatement myStm = null;
		ResultSet myRs = null;
	
		String n = "";
		
		try {
			myStm = myCon.prepareStatement("SELECT * FROM HR WHERE USERNAME=? AND PASSWORD=?");
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
	
	
	
	public List<Hr> getAllHr() throws Exception{
		Statement myStm= null;
		ResultSet myRs=null;
		List<Hr> list=new ArrayList();
		
		try{
		myStm=myCon.createStatement();
		myRs=myStm.executeQuery("SELECT * FROM HR");
		
		while(myRs.next()){
		Hr hr=new Hr(myRs.getString("USERNAME"),myRs.getString("PASSWORD"));
		list.add(hr);
		}
		System.out.println(list);
		return list;
		
		}
		finally{
		myStm.close();	
		myRs.close();
		}
		}
	
	
	
	public void insert(Hr hr) throws Exception{
		PreparedStatement myStm=null;
		ResultSet myRs=null;
		
		try{
			myStm=myCon.prepareStatement("insert into Hr"
		    		+"(username,password)"
		            +"values(?,?)");
			myStm.setString(1, hr.username);
			myStm.setString(2, hr.password);
			myStm.executeUpdate();
			
		}
		finally{
			myStm.close();
		}
	}
		
		
		
		
	
}
