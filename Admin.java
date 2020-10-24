package snapfood;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Admin {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		
		Connection con=ConnectionManager.connectionManager.getConnection();
		
		List<User> user =userReport(con);
		for(User user2 : user) {
			System.out.println(user2);
		}
		System.out.println("-------------------------------------------------------");
		final int[] array= {1,2,3,4,5,6,7,8,9,10,11,12};
		for(int i:array) {
		user.stream().filter(u->u.date.getMonth()+1==i).filter(u->u.total<100000).forEach(u->System.out.println((u.date.getMonth()+1)+" "+"<100 "+u.name+" "+u.phoneNumber));
		user.stream().filter(u->u.date.getMonth()+1==i).filter(u->u.total>100000.0 && u.total<500000.0).forEach(u->System.out.println((u.date.getMonth()+1)+" "+"100< <500 "+u.name+" "+u.phoneNumber));
		user.stream().filter(u->u.date.getMonth()+1==i).filter(u->u.total>500000).forEach(u->System.out.println((u.date.getMonth()+1)+" "+">500 "+u.name+" "+u.phoneNumber));
		}
		System.out.println("------------------------------------------------------------------");
		
		List<ReportRest> reportRest =reportRest(con);
		for(ReportRest element:reportRest) {
			System.out.println(element);
		}
		System.out.println("-------------------------------------------------------");
		final String[] array2= {"1","2","3","4","5","6","7","8","9","10"};
		for(String i:array2) {
			reportRest.stream().filter(r->r.region.equals(i)).filter(r->r.totalFee<5000).forEach(r->System.out.println(r.region+" <5000 "+r.nameOfRest+" "+r.nameOfFood));
			reportRest.stream().filter(r->r.region.equals(i)).filter(r->r.totalFee>5000 && r.totalFee<8000).forEach(r->System.out.println(r.region+" 5000<<8000 "+r.nameOfRest+" "+r.nameOfFood));
			reportRest.stream().filter(r->r.region.equals(i)).filter(r->r.totalFee>5000 && r.totalFee>=8000).forEach(r->System.out.println(r.region+" >=8000 "+r.nameOfRest+" "+r.nameOfFood));
		}
	}

	private static List<ReportRest> reportRest(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		List<ReportRest> reportRest=new ArrayList<>();
		Statement stmt =con.createStatement();
		ResultSet rs=stmt.executeQuery(" Select nameOfFood, nameOfRest,max(count) ,region,count(distinct factorNum)*serviceFee "
				+ "from restaurant,restinfo,snappfood.order "
				+ "where restaurant.idRest=restinfo.idRest "
				+ "and restinfo.idInfo=snappfood.order.idInfo group by nameOfRest;");
		while(rs.next()) {
			reportRest.add(new ReportRest(rs.getString(1),rs.getString(2),rs.getString(4),rs.getDouble(5)));	
		}
		stmt.close();
		rs.close();
		return reportRest;
	}

	private static List<User> userReport(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		List<User> user=new ArrayList<>();
		Statement stmt =con.createStatement();
		ResultSet rs=stmt.executeQuery("Select name,user.phoneNumber,snappfood.user.date,sum(price*count)"
				+ " from user,snappfood.order,restinfo where\r\n" + 
				" snappfood.order.phoneNumber=user.phoneNumber"
				+ " and restinfo.idInfo=snappfood.order.idInfo group by phoneNumber;");
		while(rs.next()) {
			user.add(new User(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getDouble(4)));	
		}
		stmt.close();
		rs.close();
		return user;
	}
	

}
