import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQL {
	
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 static final String DB_URL = "jdbc:mysql://localhost:3306/Local instance MySQL56";

	 static final String USER = "root";
	 static final String PASS = "netbuilder";

	 
		 public void accessBD() {
			 Connection conn = null;
			 Statement stmt = null;
			 try {
			  Class.forName( "com.mysql.jdbc.Driver");
			  System.out.println("Connecting to database...");
			  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Local instance MySQL56", "root", "netbuilder");
			 //Create
			  System.out.println("Inserting records into the table...");
			  stmt = conn.createStatement();
			  String sql = "INSERT INTO Languages " + "VALUES (1, 'Java', 1992)";
			  stmt.executeUpdate(sql);
			  System.out.println("Inserted records into the table...");

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

			  //Delete
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql4 = "DELETE FROM Languages " + "WHERE id = 1";
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
}//end class
