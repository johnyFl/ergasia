package companyAdministration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PartsDAO {
	private Connection myCon;

	public PartsDAO() throws SQLException {
		myCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/company", "root", "ςυνγρεεψε7");
	
	}
	
	public List<Parts> getAllParts() throws Exception{
		
		Statement myStm= null;
		ResultSet myRs=null;
		List<Parts> list=new ArrayList();
		
		try{
		myStm=myCon.createStatement();
		myRs=myStm.executeQuery("SELECT * FROM parts");
		
		while(myRs.next()){
		Parts part=new Parts(myRs.getString("name"));
		list.add(part);
		}
		System.out.println(list);
		return list;
		
		}
		finally{
		myStm.close();	
		myRs.close();
		}
	} 
	
	public void insert(Parts part) throws Exception{
		PreparedStatement myStm=null;
		ResultSet myRs=null;
		
		
		try{
			myStm=myCon.prepareStatement("insert into parts"
		    		+"(name)"
		            +"values(?)");
			myStm.setString(1, part.name);
			myStm.executeUpdate();
			
		}
		finally{
			myStm.close();
		}
	}
		
		
}
