package snapfood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private Connection con;
	public static ConnectionManager connectionManager=new ConnectionManager();
	private ConnectionManager() {
		// TODO Auto-generated constructor stub
		try {
				Class.forName("com.mysql.jdbc.Driver");  
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snappfood","root","");  
			}catch(ClassNotFoundException | SQLException e){
				e.printStackTrace();}  
			}
		
		public Connection getConnection() {
			return con;
		}  
		}

