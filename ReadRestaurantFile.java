package snapfood;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadRestaurantFile {

	public void readRestFile(Connection con) throws SQLException, IOException {
		try {
		Statement stmt = con.createStatement();
		int rs;
		int rs2;
//		// TODO Auto-generated method stub
		File file_to_read =new File("C:\\Users\\win10\\Desktop\\restaurant.txt");
		BufferedReader bf =new BufferedReader(new FileReader(file_to_read));
		String nameOfRest="";
		String countOfFood="";
		String region="";
		String serviceFee="";
		//////////////////
		String nameOfFood="";
		String price="";
		String type="";
		int countOfRestId=1;
		int countOfRestInfoId=1;
		/////////////////////
		String line;
		////////////////////////
//		
		ResultSet r=stmt.executeQuery("Select max(idInfo) from snappfood.restinfo;");
		if(r.next()) {
			
			countOfRestInfoId=r.getInt(1);
			countOfRestInfoId++;
		}else {
			countOfRestInfoId=1;
		}
		r=stmt.executeQuery("Select max(idRest) from snappfood.restaurant;");
		if(r.next()) {
			
			countOfRestId=r.getInt(1);
			countOfRestId++;
		}else {
			countOfRestId=1;
		}
		
		//////////////////////////////
		int numberOfRest = bf.read();
		while ((line = bf.readLine())!=null)
        {
			
			
			for(int i=0;i<numberOfRest && (line = bf.readLine())!=null;i++) {
	            String tmp[] = line.split(", ");
	            nameOfRest = tmp[0];
	            countOfFood = tmp[1];
	            region = tmp[2];
	            serviceFee = tmp[3];
	            
	             rs=stmt.executeUpdate("INSERT INTO restaurant (idRest,nameOfRest,countOfFood,region,serviceFee) values ('"
	                    +countOfRestId +"','"+ nameOfRest + "','" + countOfFood+ "','" + region + "','" + serviceFee +
	                    "')");
	           
	            
	            for(int j=0;j<Integer.parseInt(countOfFood) && (line = bf.readLine())!=null;j++) {
	            	
	            	String tmpFood[]=line.split(", ");
	            	nameOfFood=tmpFood[0];
	            	price=tmpFood[1];
	            	type=tmpFood[2];
	            		rs2=stmt.executeUpdate("INSERT INTO restinfo (idInfo,nameOfFood,price,type,idRest) values ('"
	     	               +countOfRestInfoId +"','"+ nameOfFood + "','" + price+ "','" + type + "','" + countOfRestId +
	   	                "')");
	            	countOfRestInfoId++;
	            	
	            }
	            countOfRestId++;
	            
			}

            
//
//            
        }
//
        bf.close();
       
        stmt.close();
        
        

    }
	
    catch (IOException e)
    {
        e.printStackTrace();
    }
        
	}

	
}
