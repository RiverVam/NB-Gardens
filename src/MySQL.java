import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public  class MySQL {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 static final String DB_URL = "jdbc:mysql://localhost/mydb";

	 static final String USER = "root";
	 static final String PASS = "netbuilder";
	

	 	public static ArrayList<Order> gettingOrderID(){
	 		Connection conn = null;
			 Statement stmt = null;
			 System.out.println("access db");
			 try {
			  Class.forName( JDBC_DRIVER);
			  System.out.println("Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL, USER, PASS);
			  
			  ArrayList<Order> listOfOrders = new ArrayList<Order>() ;
			  
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql2 = "SELECT Order_ID FROM orders";
			  ResultSet rs = stmt.executeQuery(sql2);
			  while (rs.next()) {
				Order  order = new Order(rs.getInt("Order_ID"));
				
			  
			  // System.out.println("Order_ID: " + order + ", Product_ID: " + product + ", Quantity: " + quantity);
				listOfOrders.add(order);
			  }
			  rs.close();
			  return listOfOrders;
			  
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
			 	return null;
		 }
			  
	 	
	 
		 public static ArrayList<Order_Line> gettingOrders() {
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
			  String sql = "INSERT INTO product(Name,Price,Warehouse_Location) " + "VALUES ('Luxury Garden Furniture', '£1500', 'shelf 3')";
			  System.out.print(sql);
			  stmt.executeUpdate(sql);
			  System.out.println("\nInserted records into the table...");
*/

			  //Read
			  
			  ArrayList<Order_Line> OrderResults = new ArrayList<Order_Line>() ;
			  
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql2 = "SELECT Order_ID, Product_ID, Quantity FROM order_line";
			  ResultSet rs = stmt.executeQuery(sql2);
			  while (rs.next()) {
				Order_Line OrderIDResult = new Order_Line();
			   OrderIDResult.setOrder_ID(rs.getInt("Order_ID"));
			   OrderIDResult.setProduct_Id(rs.getInt("Product_ID"));
			   OrderIDResult.setQuantity(rs.getInt("Quantity"));
			  // System.out.println("Order_ID: " + order + ", Product_ID: " + product + ", Quantity: " + quantity);
			   OrderResults.add(OrderIDResult);
			  }
			  rs.close();
			  return OrderResults;
			  
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
			 	return null;
		 }
			  /*
			  //Update
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql3 = "UPDATE Languages " + "SET date = 1994 WHERE id in (1, 2)";
			  stmt.executeUpdate(sql3);

			  //Delete
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql4 = "DELETE FROM product " + "WHERE Product_ID = 15";
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
				  
				
*/

	


}