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
			 System.out.println("(1)access db");
			 ArrayList<Order> listOfOrders = new ArrayList<Order>() ;
			 try {
			  Class.forName( JDBC_DRIVER);
			  System.out.println("(2)Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL, USER, PASS);
			  
			 // ArrayList<Order> listOfOrders = new ArrayList<Order>() ;
			  
			  System.out.println("(3)Creating statement...");
			  stmt = conn.createStatement();
			  String sql2 = "SELECT Order_ID FROM orders";
			  ResultSet rs = stmt.executeQuery(sql2);
			  while (rs.next()) {
				Order  order = new Order(rs.getInt("Order_ID"));
				
			  
			  // System.out.println("Order_ID: " + order + ", Product_ID: " + product + ", Quantity: " + quantity);
				listOfOrders.add(order);
			  }
			  rs.close();
			 
			  
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
			 return listOfOrders;
		 }// end public static ArrayList<Order> gettingOrderID()
			  
	 	
	 
		 public static ArrayList<Order_Line> gettingOrders(int orderId) {
			 Connection conn = null;
			 Statement stmt = null;
			 System.out.println("(4)access db");
			 ArrayList<Order_Line> OrderResults = new ArrayList<Order_Line>() ;
			 try {
			  Class.forName( JDBC_DRIVER);
			  System.out.println("(5)Connecting to database...");
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
			  
			 // ArrayList<Order_Line> OrderResults = new ArrayList<Order_Line>() ;
			  
			  System.out.println("(6)retreving products and quantity.");
			  stmt = conn.createStatement();
			  // SQl statement that links 2 tables and gets order info
			  String sql2 = "SELECT p.Product_ID, p.Name,  ol.Product_ID, ol.Quantity, ol.Porous_Ware "
			  		+ "FROM product p "
			  		+ "JOIN order_line ol "
			  		//uses the foreign key to link the two tables in the database
			  		+ "ON p.Product_ID=ol.Product_ID"	  
			  		+ " WHERE Order_Id =" +orderId;
			  ResultSet rs = stmt.executeQuery(sql2);
			  while (rs.next()) {
				Order_Line OrderIDResult = new Order_Line();
			   //OrderIDResult.setOrder_ID(rs.getInt("Order_ID"));
			   OrderIDResult.setProduct_Id(rs.getInt("Product_ID"));
			   OrderIDResult.setQuantity(rs.getInt("Quantity"));
			   OrderIDResult.setProduct_Name(rs.getString("Name"));
			   OrderIDResult.setPorous_Status(rs.getString("Porous_Ware"));
			  //System.out.println("Order_ID: " +  + ", Product_ID: " + product + ", Quantity: " + quantity);
			   OrderResults.add(OrderIDResult);
			  }
			  rs.close();
			  //return OrderResults;
			  
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
			 return OrderResults;
		 }//End ArrayList<Order_Line> gettingOrders(int orderId)
		 
		 public static ArrayList<item> gettingProducts(){
			 Connection conn = null;
			 Statement stmt = null;
			 System.out.println("(1)access db");
			 ArrayList<item> listOfItems = new ArrayList<item>() ;
			 try {
			  Class.forName( JDBC_DRIVER);
			  System.out.println("(2)Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   
			  System.out.println("(3)Creating statement...");
			  stmt = conn.createStatement();
			  String sql2 = "SELECT Name FROM product";
			  ResultSet rs = stmt.executeQuery(sql2);
			  while (rs.next()) {
				item  item = new item(rs.getString("Name"));
				
			  
			  // System.out.println("Order_ID: " + order + ", Product_ID: " + product + ", Quantity: " + quantity);
				listOfItems.add(item);
			  }
			  rs.close();
			  
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
			  return listOfItems;
		 }
}