package companyAdministration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDAO {
	private Connection myCon;

	public EmployeesDAO() throws SQLException {
		myCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/company", "root", "ςυνγρεεψε7");
		
	}

	public List<Employee> getByPart(String p) throws SQLException{
		Statement myStm= null;
		ResultSet myRs=null;
		List<Employee> list=new ArrayList();
		
		try{
		myStm=myCon.createStatement();
		String sql="SELECT * FROM EMPLOYEES WHERE idpart='"+p+"'";
		myRs=myStm.executeQuery(sql);
		
		while(myRs.next()){
		Employee emp=new Employee(myRs.getString("name"),myRs.getString("surname"),myRs.getString("date"),myRs.getString("idpart"));
		list.add(emp);
		}
		
		return list;
		
		}
		finally{
		myStm.close();	
		myRs.close();
		}
	}  
		
	public void insert(Employee emp) throws Exception{
		PreparedStatement myStm=null;
		ResultSet myRs=null;
		
		
		try{
			myStm=myCon.prepareStatement("insert into employees"
		    		+"(name,surname,date,idpart)"
		            +"values(?,?,?,?)");
			myStm.setString(1, emp.name);
			myStm.setString(2, emp.surname);
			myStm.setString(3, emp.date);
			myStm.setString(4, emp.part);
			myStm.executeUpdate();
			
		}
		finally{
			myStm.close();
		}
	}
		
		
	
	public String getid(String n,String s) throws SQLException{
		Statement myStm= null;
		ResultSet myRs=null;
	    String id="";
		
		try{
		myStm=myCon.createStatement();
		String sql="SELECT * FROM EMPLOYEES WHERE name='"+n+"' and surname='"+s+"'";
		myRs=myStm.executeQuery(sql);
		
		while(myRs.next()){
		id=myRs.getString("idemployees");
		
		}
		
		return id;
		
		}
		finally{
		myStm.close();	
		myRs.close();
		}
	}  
}
