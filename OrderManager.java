package snapfood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.time.LocalDate;


public class OrderManager {
	

	public void insertOrderDb(List<CartManager> cartArr, String phoneNumber,Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
		Statement stmt=con.createStatement();
		//LocalDate date=LocalDate.now();
		int factorNum=1;
		ResultSet rs=stmt.executeQuery("Select max(factorNum) from snappfood.order;");
		while(rs.next()) {
			factorNum=rs.getInt(1);
			factorNum++;
		}
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		PreparedStatement ps;
		ps=con.prepareStatement("insert into snappfood.order(phoneNumber,idInfo,factorNum,count,date) values(?,?,?,?,?)");
		for(int i=0;i<cartArr.size();i++) {
			
			ps.setString(1, phoneNumber);
			ps.setInt(2, cartArr.get(i).id);
			ps.setInt(3, factorNum);
			ps.setInt(4, cartArr.get(i).count);
			ps.setDate(5, sqlDate);
			ps.executeUpdate();			
			
			
		}
		rs.close();
		stmt.close();
		ps.close();
		con.close();
	}

	
}
