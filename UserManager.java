package snapfood;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter; // Import the FileWriter class
public class UserManager {

	
	public UserManager() {
	
	}
	
	public boolean checkPhoneNumber(String phoneNumber) {
		if(!phoneNumber.equals(null)) {
			
			if(phoneNumber.length()<11)
				return false;
			if(phoneNumber.startsWith("0")==false && phoneNumber.charAt(1)!='9')
				return false;
		}
		else
			return false;
		
		return true;
	}
	
	////////////////////////////

	List<User> userList=new ArrayList<User>();
	public void addUserToDb(Connection con,User user) throws SQLException {
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		PreparedStatement ps;
		ps=con.prepareStatement("insert into user (phoneNumber,name,postalCode,date) values(?,?,?,?)");
		ps.setString(1, user.phoneNumber);
		ps.setString(2, user.name);
		ps.setString(3, user.postalCode);
		ps.setDate(4, sqlDate);
		ps.executeUpdate();			
		
		ps.close();
		
	}
	
	/////////////////////////
	public void userFile(User user,List<CartManager> cartArr,Double serviceFee) {
		//SnapFoodProject
		//"C:\\Users\\win10\\Desktop\\restaurant.txt"
		try {
			///////create a user file///////////////////////////////
			String path="C:\\Users\\win10\\Desktop\\SnapFoodProject\\"+user.name+".txt";
		      File fileUser = new File(path);
		      if (fileUser.createNewFile()) {
		        System.out.println("File created: " + fileUser.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		      /////////////////write to user file/////////////////
		      
		      FileWriter fileUserWriter = new FileWriter(path,true);
		      fileUserWriter.write("Time of order: "+new Timestamp(System.currentTimeMillis())+"\n");
		      fileUserWriter.write("User Information: "+user.name+" "+user.phoneNumber+" "+user.postalCode);
		      fileUserWriter.write("\n");
		      double totalPrice=0;
		      double eachPrice;
				if(cartArr.isEmpty()==false) {
					for(int i=0;i<cartArr.size();i++) {
						
						fileUserWriter.write(cartArr.get(i).toString());
						fileUserWriter.write("\n");
						eachPrice=(cartArr.get(i).count)*(cartArr.get(i).price);
						totalPrice+=eachPrice;
						String price=Double.toString(eachPrice);
						fileUserWriter.write(price);
						fileUserWriter.write("\n");
						
					}
					totalPrice=totalPrice+serviceFee;
					String total=Double.toString(totalPrice);
					fileUserWriter.write(total);
				}else{
					System.out.println("Your Shopping cart is Empty!");
				}
		      fileUserWriter.close();
		      System.out.println("Successfully wrote to the file.");
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
//////////////////////////////////////////////////////////////
	public User checkUserInDb(String phoneNumber,Connection con) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("Select * from user where phoneNumber='"+phoneNumber+"'");
		if(rs.next()) {
			User user =new User(rs.getString(1),rs.getString(2),rs.getString(3));
			rs.close();
			stmt.close();
			return user;
		}
		return null;
	}

	public void editUserInformation(Connection con, String phoneNumber, String newUsername, String newPostalcode) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt=con.createStatement();
		int rs=stmt.executeUpdate("UPDATE user SET name='"+newUsername+"' , postalCode='"+newPostalcode+"' WHERE phoneNumber='"+ phoneNumber+"'");
	}

	
}
