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
	
	 	//gets the orders Id's from the database and populates the array with them  
	 	public static ArrayList<Order> gettingOrderID(){
	 		Connection conn = null;
			 Statement stmt = null;
			 System.out.println("(1)access db");
			 ArrayList<Order> listOfOrders = new ArrayList<Order>() ;
			 try {
			  Class.forName( JDBC_DRIVER);
			  System.out.println("(2)Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   System.out.println("(3)Creating statement...");
			  stmt = conn.createStatement();
			  String sql2 = "SELECT Order_ID FROM orders";
			  ResultSet rs = stmt.executeQuery(sql2);
			  while (rs.next()) {
				Order  order = new Order(rs.getInt("Order_ID"));
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
			  
	 	
	 	//gets the orders Id's from the database and populates the array with them
		 public static ArrayList<Order_Line> gettingOrders(int orderId) {
			 Connection conn = null;
			 Statement stmt = null;
			 System.out.println("(4)access db");
			 ArrayList<Order_Line> OrderResults = new ArrayList<Order_Line>() ;
			 try {
			  Class.forName( JDBC_DRIVER);
			  System.out.println("(5)Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 
			  
			  System.out.println("(6)retreving products and quantity.");
			  stmt = conn.createStatement();
			  // SQl statement that links 2 tables and gets order info
			  String sql2 = "SELECT p.Product_ID, p.Name, p.Warehouse_Location_x, p.Warehouse_Location_y,  ol.Product_ID, ol.Quantity, ol.Porous_Ware "
			  		+ "FROM product p "
			  		+ "JOIN order_line ol "
			  		//uses the foreign key to link the two tables in the database
			  		+ "ON p.Product_ID=ol.Product_ID"	  
			  		+ " WHERE Order_Id =" +orderId;
			  ResultSet rs = stmt.executeQuery(sql2);
			  while (rs.next()) {
			   Order_Line OrderIDResult = new Order_Line();
			   OrderIDResult.setProduct_Id(rs.getInt("Product_ID"));
			   OrderIDResult.setQuantity(rs.getInt("Quantity"));
			   OrderIDResult.setProduct_Name(rs.getString("Name"));
			   OrderIDResult.setPorous_Status(rs.getString("Porous_Ware"));
			   OrderIDResult.setProduct_LocationX(rs.getInt("Warehouse_Location_x"));
			   OrderIDResult.setProduct_LocationY(rs.getInt("Warehouse_Location_y"));
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
		 
		 // populates the arrayList with the names of the products 
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
		 // updates the stock levels of the products using the product name and quantity entered
		 public static void updateProducts(String name, int Quantity){
			 Connection conn = null;
			 Statement stmt = null;
			 System.out.println("(1)access db");
			 
			 try {
				  Class.forName( JDBC_DRIVER);
				  System.out.println("(2)Connecting to database...");
				  conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  
				  System.out.println("(3)Creating statement..."+String.valueOf(Quantity)+""+name);
				  stmt = conn.createStatement();
				  String sql3 = "UPDATE product SET Quantity = Quantity + "+String.valueOf(Quantity)+" WHERE Name = '"+name+"'";
				 stmt.executeUpdate(sql3);
		  
		  
		  
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
			
	 }
		 // uses the order ID to select order status and pupulate the array orderStatus 
		 public static ArrayList<Order> orderStatus(int orderId) {
			 Connection conn = null;
			 Statement stmt = null;
			 System.out.println("(4)access db");
			 ArrayList<Order> orderStatus = new ArrayList<Order>() ;
		 
			 try {
				  Class.forName( JDBC_DRIVER);
				  System.out.println("(5)Connecting to database...");
				  conn = DriverManager.getConnection(DB_URL, USER, PASS);
				 
				  
				  System.out.println("(6)retreving products and quantity.");
				  stmt = conn.createStatement();
				  // SQl statement that links 2 tables and gets order info
				  String sql2 = "SELECT Order_Status "
				  		+ "FROM orders "	  
				  		+ " WHERE Order_Id =" +orderId;
				  ResultSet rs = stmt.executeQuery(sql2);
				  while (rs.next()) {
					Order OrderStatusResult = new Order( orderId, rs.getString("Order_Status") );
				   
					
				  
				   orderStatus.add(OrderStatusResult);
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
				 return orderStatus;
		 
		 }//end orderStatus
		 
		 //uses the order id to update the order status in the database 
		 public static void updateStatusProcessing(int orderId){
			 
			 Connection conn = null;
			 Statement stmt = null;
			 System.out.println("(1)access db");
			 
			 try {
				  Class.forName( JDBC_DRIVER);
				  System.out.println("(2)Connecting to database...");
				  conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  
				  System.out.println("(3)Creating statement...");
				  stmt = conn.createStatement();
				  String sql3 = "UPDATE orders "
				  		+ "SET Order_Status = 'Processing' "
				  		+ "WHERE Order_ID =  "+orderId+"-1";
				 stmt.executeUpdate(sql3);
		  
		  
		  
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
			  }// end updateStatusProcessing()
			
	 }
		//uses the order id to update the order status and the stock level in the database 
		 public static void updateStatusComplete(int orderId){
			 
			 Connection conn = null;
			 Statement stmt = null;
			 Statement stmt1 = null;
			 Statement stmt2 = null;
			 
			 System.out.println("(1)access db");
			 
			 try {
				  Class.forName( JDBC_DRIVER);
				  System.out.println("(2)Connecting to database...");
				  conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  
				  System.out.println("(3)Creating statement...");
				  stmt = conn.createStatement();
				  String sql3 = "UPDATE orders "
				  		+ "SET Order_Status = 'Complete' "
				  		+ "WHERE Order_ID =  "+orderId;
				 stmt.executeUpdate(sql3);
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
			 try {
				  Class.forName( JDBC_DRIVER);
				  System.out.println("(2)Connecting to database...");
				  conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  
				  System.out.println("(3)Creating statement...");
				 ArrayList<Order_Line> OrderResults = new ArrayList<Order_Line>() ;
				 stmt1 = conn.createStatement();
				 String sql4 = "SELECT Product_ID, Quantity "
					  		+ "FROM order_line "
					  		+ "WHERE Order_ID =  "+orderId;
				 ResultSet rs = stmt1.executeQuery(sql4);
				  while (rs.next()) {
					Order_Line OrderIDResult = new Order_Line();
				   OrderIDResult.setProduct_Id(rs.getInt("Product_ID"));
				   OrderIDResult.setQuantity(rs.getInt("Quantity"));
				   OrderResults.add(OrderIDResult);
				  }
				 
				
					  System.out.println("(3)Creating statement...");
					  
					  for(Order_Line orders:OrderResults){
				  stmt2 = conn.createStatement();
				  String sql5 = "UPDATE product "
					  		+ "SET Quantity = Quantity - "+String.valueOf(orders.getQuantity())
					  		+ " WHERE Product_ID = "+String.valueOf(orders.getProduct_Id());
				  
				  stmt2.executeUpdate(sql5);
					  }
				  
		  
		  
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
				 
		 }//end updateStatusComplete()
		 
		 
		 
			 }// end MySQL class