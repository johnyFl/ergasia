package companyAdministration;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class ReviewDAO {
	
	private java.sql.Connection myCon;

	public ReviewDAO() throws Exception {
		myCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/company", "root", "ςυνγρεεψε7");
		System.out.println("connection ok");
	}

	public List<Review> getReviews(String empid) throws Exception{
		
		List<Review> list=new ArrayList();
		Statement st=null;
		ResultSet rs=null;
		try{
	    st=myCon.createStatement();
	    rs=st.executeQuery("SELECT * FROM review where empid='"+empid+"'");
		while(rs.next()){
			Review p=new Review(rs.getInt("rate"),rs.getString("text"),rs.getString("empid"),rs.getString("date"));
			list.add(p);
		}
		
		return list;
		}
		finally{
			
			rs.close();
		    st.close();
			
		}
	}
	
	public void insert(Review r) throws SQLException{
		Statement st=null;
		
		try{
	    st=myCon.createStatement();
	    String sql="insert into review"
	    		+"(text,rate,empid,date)"
	            +"values('"+r.st+"','"+r.rate+"','"+r.empid+"','"+r.date+"')";
	    
	    st.executeUpdate(sql);
		
		}
		finally{
			
		    st.close();
			
		}
		
	}
	public Review getRev(String date) throws Exception{
		Statement myStm=null;
		ResultSet myRs=null;
		Review rev=null;
			
		myStm=myCon.createStatement();
		myRs=myStm.executeQuery("SELECT * FROM REVIEW WHERE DATE='"+date+"'");
			while(myRs.next()){
				rev=new Review(myRs.getInt("rate"),myRs.getString("text"),myRs.getString("empid"),"");
				}
			return rev;
			
			
		
	}

}
