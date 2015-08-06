import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public  class MySQL {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 static final String DB_URL = "jdbc:mysql://localhost/mydb";

	 static final String USER = "root";
	 static final String PASS = "netbuilder";
	
		 public static void accessBD() {
			 Connection conn = null;
			 Statement stmt = null;
			 System.out.println("access db");
			 try {
			  Class.forName( JDBC_DRIVER);
			  System.out.println("Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 //Create
			  /*
			  System.out.println("Inserting records into the table...");
			  stmt = conn.createStatement();
			  String sql = "INSERT INTO product(Name,Price,Warehouse_Location) " + "VALUES ('Green Gnome', '£19.99', 'shelf 2')";
			  System.out.print(sql);
			  stmt.executeUpdate(sql);
			  System.out.println("\nInserted records into the table...");
*/
/*
			  //Read
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql2 = "SELECT id, name, date FROM Languages";
			  ResultSet rs = stmt.executeQuery(sql2);
			  while (rs.next()) {
			   int id = rs.getInt("id");
			   String name = rs.getString("name");
			   int date = rs.getInt("date");
			   System.out.println("ID: " + id + ", name: " + name + ", date: " + date);
			  }
			  rs.close();
			  
			  //Update
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql3 = "UPDATE Languages " + "SET date = 1994 WHERE id in (1, 2)";
			  stmt.executeUpdate(sql3);
*/
			  //Delete
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql4 = "DELETE FROM product " + "WHERE Product_ID != 1";
			  stmt.executeUpdate(sql4);
			  
			  //Closing the connection
			 } catch (SQLException sqle) {
				 sqle.printStackTrace();
				} catch (Exception e) {
				 e.printStackTrace();
				} finally {
				 try {
				  if (stmt != null)
				   conn.close();
				  } catch (SQLException se) { }
				  try {
				   if (conn != null)
				    conn.close();
				   } catch (SQLException se) {
				    se.printStackTrace();
				   }
				  }
				  System.out.println("Goodbye!");
				  
				


		 
		 
}

}