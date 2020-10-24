package snapfood;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {
	
	
//////////////////////search restaurant////////////////////
	public List<Restaurant> searchRest(Connection con, String region) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt = con.createStatement();
		String query="select nameOfRest,type,serviceFee from restaurant,restinfo"
				+ " where restinfo.idRest=restaurant.idRest and region="+region+" group by type";
		ResultSet rs=stmt.executeQuery(query);
		List<Restaurant> restArr=new ArrayList<Restaurant>();
		while(rs.next()){
			restArr.add(new Restaurant(rs.getString(1),rs.getString(2),rs.getDouble(3)));
		}
		
		rs.close();
		stmt.close();
		return restArr;
	}
	//////////////////////////////////////////////////////////////////////////////////
	/////////////////////////search restaurant based on food type/////////////////////
	public List<Restaurant> searchRestFoodType(Connection con, String region,String type) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt = con.createStatement();
		String query="select nameOfRest,type,serviceFee from restaurant,restinfo"
				+ " where restinfo.idRest=restaurant.idRest and region="+region+" and type='"+type+"'";
		ResultSet rs=stmt.executeQuery(query);
		List<Restaurant> restArr=new ArrayList<Restaurant>();
		while(rs.next()){
			restArr.add(new Restaurant(rs.getString(1),rs.getString(2),
					rs.getDouble(3)));
		}
		
		rs.close();
		stmt.close();
		return restArr;
	}
	//////////////////////////////////////////////////////////////////////////////////

	public List<Food> searchFoodOfRest(Connection con, String nameOfRest) throws SQLException {
		Statement stmt = con.createStatement();
		String query="select idInfo,nameOfFood,type,price from restaurant,restinfo"
				+ " where restinfo.idRest=restaurant.idRest and restaurant.nameOfRest='"+nameOfRest+"'";
		ResultSet rs=stmt.executeQuery(query);
		List<Food> restFood=new ArrayList<Food>();
		while(rs.next()){
			restFood.add(new Food(rs.getInt(1),rs.getString(2),
					rs.getString(3),rs.getDouble(4)));
		}
		
		rs.close();
		stmt.close();
		return restFood;
		
		
	}
	
	public List<Food> searchFoodOfRestByType(Connection con, String nameOfRest,String foodType) throws SQLException {
		Statement stmt = con.createStatement();
		String query="select idInfo,nameOfFood,type,price from restaurant,restinfo"
				+ " where restinfo.restaurant=restaurant.idRest and restaurant.nameOfRest='"+nameOfRest+"' and restinfo.type='"+foodType+"'";
		ResultSet rs=stmt.executeQuery(query);
		List<Food> restFoodType=new ArrayList<Food>();
		while(rs.next()){
			restFoodType.add(new Food(rs.getInt(1),rs.getString(2),
					rs.getString(3),rs.getDouble(4)));
		}
		
		rs.close();
		stmt.close();
		return restFoodType;
		
		
	}
	

}
