import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class printOrdersWO extends JFrame {
	

		 private JFrame mainFrame;
		 private JLabel headerLabel;
		 private JTextArea  itemTextArea;
		// private JLabel orderStatusLabel;
		 private JPanel controlPanel;
		 ArrayList<Order> orderStatus;
		 ArrayList<Order> listOfOrders ;
		 ArrayList<Order_Line> OrderResults;
		 ArrayList<item> listOfItems;
		
		
		 Scanner input = new Scanner(System.in);
		public printOrdersWO(){
			prepareGUI();
			showEvent();
			
			listOfOrders = new ArrayList<Order>();
			OrderResults = new ArrayList<Order_Line>();
			listOfItems = new ArrayList<item>();
			orderStatus = new ArrayList<Order>();
			

		}
		
	// Makes a GUI
		 private void prepareGUI() {
			 mainFrame = new JFrame("Warehouse IMS");
			 mainFrame.setSize(1500, 1500);
			 mainFrame.setLayout(new GridLayout(3, 1));
			 headerLabel = new JLabel("", JLabel.CENTER);
			 itemTextArea = new JTextArea("");
			 
			 JScrollPane scrollPane = new JScrollPane( itemTextArea );
			 scrollPane.setPreferredSize(new Dimension(350,200));;
			 itemTextArea.setWrapStyleWord(true);
			 itemTextArea.setLineWrap(true);
			 itemTextArea.setSize(350, 200);
			
			 mainFrame.addWindowListener(new WindowAdapter() {
				  public void windowClosing(WindowEvent windowEvent)     {
				   System.exit(0);
				  }
				});
				 controlPanel = new JPanel();
				
				 controlPanel.setLayout(new FlowLayout());
				 mainFrame.add(headerLabel);
				 mainFrame.add(controlPanel);
				 controlPanel.add( scrollPane );
				 mainFrame.setVisible(true);
		 }
		 
		 private void showEvent() {
			 //Four main  buttons
			 headerLabel.setText("Press Appropriate Button");
			 JButton ShowOrders = new  JButton("Show Orders");
			 JButton UpdateStock =new JButton("Update Stock Levels");
			 JButton UpdateOrderStatus = new JButton("Update Order Status");
			 JButton Cancel =new JButton("Cancel");
			 
			  //action commands for four main buttons
			 ShowOrders.setActionCommand("Show Orders");
			 UpdateStock.setActionCommand("Update Stock Levels");
			 UpdateOrderStatus.setActionCommand("Update Order Status");
			 Cancel.setActionCommand("Cancel");
			 
			 
			 
			 //create action listeners for the four main buttons
			 ShowOrders.addActionListener( new BCL());
			 controlPanel.add(ShowOrders);
			 
			 UpdateStock.addActionListener( new BCL());
			 controlPanel.add(UpdateStock);
			 
			 UpdateOrderStatus.addActionListener( new BCL());
			 controlPanel.add(UpdateOrderStatus);
			 
			 Cancel.addActionListener( new BCL());
			controlPanel.add(Cancel);
			 
			
			 // shows the buttons on the JFrame
			 mainFrame.setVisible(true);
		 }// BCL class that implements the action for the buttons when they are pressed
		 private class BCL implements ActionListener {
			 
			 @Override
			 public void actionPerformed (ActionEvent ae) {
			  String command = ae.getActionCommand();
			  switch (command) {
			  
			  // brings up order buttons
			  	case "Show Orders":
			  //calls in the function for getting the array of orders 
			  		listOfOrders = MySQL.gettingOrderID();
			  		// loops through the array of orders to get the order ID for the selected order and implements the printOrder() class 
				  for (int i=0; i<listOfOrders.size(); i++) {
					    JButton ChooseOrder = new  JButton("Order ID: "+listOfOrders.get(i).getOrderNumber()); 
					   ChooseOrder.setActionCommand("Choose Order");
					   ChooseOrder.addActionListener( new printOrder(i));
						 controlPanel.add(ChooseOrder);
						 mainFrame.setVisible(true);
						 
				   }
				   break;
				   // when the update stock levels button is pressed then the products are displayed
			 	case "Update Stock Levels":
			 		//calls in the function for getting the array of products
			  		listOfItems =MySQL.gettingProducts();
			  		//loops through the array of products to get the product id for the selected order and implements the updateStockLevel() class
			  		for (int i=000; i<listOfItems.size(); i++) {
			  			JButton ChooseProduct = new  JButton(listOfItems.get(i).getItemName()); 
			  			ChooseProduct.setActionCommand("Choose Product");
			  			ChooseProduct.addActionListener( new updateStockLevel(listOfItems.get(i).getItemName()));
							 controlPanel.add(ChooseProduct);
							 mainFrame.setVisible(true);
			  		}
			  		break;
			  	// when the Update Order Status button is pressed then the orders are displayed
			  	case "Update Order Status":
			  	//calls in the function for getting the array of orders
			  		listOfOrders = MySQL.gettingOrderID();
			  	//loops through the array of orders to get the order id for the selected order and implements the displayOrders() class
					  for (int i=0; i<listOfOrders.size(); i++) {
				  		
						   JButton ChooseOrder = new  JButton("Order ID: "+listOfOrders.get(i).getOrderNumber()); 
						   ChooseOrder.setActionCommand("Choose Order");
						   ChooseOrder.addActionListener( new displayOrders(i));
							 controlPanel.add(ChooseOrder);
							 mainFrame.setVisible(true);
					  }
			  		break;
			  	
			  		// allows the user to exit the program
			  	case "Cancel":
			  		
						  System.exit(0);
			  		
					 
			  		break;
			   
			  }

			 }
			 // class that shows the info for the order selected with the BCL class above
			 private class printOrder implements ActionListener{
				 int orderId ;
				 public printOrder(int orderId){
					 this.orderId =orderId+1;
				 }
				 
				 @Override
				 public void actionPerformed  (ActionEvent ae) {
					 //output string for the product details 
					 String output = "";
					 // output string for the Travelling salesman algorithm
					 String output2 = "";
					 // calls the method gettingOrders() which pulls the order details from the database
					 OrderResults = MySQL.gettingOrders(orderId);
					 // loops through each product on the order and gives details on it
					 for (int j=0; j<OrderResults.size();j++){
						 output += "\n-Product ID: "+OrderResults.get(j).getProduct_Id()+"\nProduct Name:"+OrderResults.get(j).getProduct_Name()+", \nPorous Status: "+OrderResults.get(j).getPorous_Status()+", \nQuantity: "+OrderResults.get(j).getQuantity()+", \nWarehouse Location: ("+OrderResults.get(j).getProduct_LocationX()+","+OrderResults.get(j).getProduct_LocationY()+")\n";
						
						}
					 	// two arrays for the Travelling salesman algorithm that hold the products to be collected and the products not yet collected when finding the nearest neighbour 
					 	ArrayList<Order_Line> ProductsCollected = new ArrayList<Order_Line>();
						ArrayList<Order_Line> ProductsNotCollected = new ArrayList<Order_Line>();
						
						//sets the product not collected to all the products on the order as none have been collected yet 
						ProductsNotCollected = OrderResults;
						double currentDist;
						double PreviousBest = Integer.MAX_VALUE;	
						int index =0;
						Order_Line currentOrderLine;
						// loop to find the closest point to (0,0) and set it as the starting point fo4r the rest of the route 
						for (int i=0; i<ProductsNotCollected.size();i++){
							//call the function to find the distance
							currentDist = ProductsNotCollected.get(i).getLocation().distanceTo(new ProductLocationTSP(0,0)) ;
							//compare distance......current will always be more favourable here
							if(currentDist < PreviousBest){
								index = i;
								PreviousBest = currentDist;
							}
						}
						// once the start point has be established the while loop will continue to run until all of the products have been included in the route
						while (ProductsNotCollected.size() !=0){
							currentOrderLine = ProductsNotCollected.get(index);
							ProductsNotCollected.remove(index);
							ProductsCollected.add(currentOrderLine);
							PreviousBest = Integer.MAX_VALUE;
								for (int i=0; i<ProductsNotCollected.size();i++){
									//call the function to find the distance and uses the variables from the product that have not yet been collected
									currentDist = currentOrderLine.getLocation().distanceTo(ProductsNotCollected.get(i).getLocation());
									 
									if(currentDist < PreviousBest){
										index = i;
										PreviousBest = currentDist;
									}
								}
								OrderResults = ProductsCollected;
						 
						
						 
							}
						//loop to fill the output string with the points for the route that the algorithm has selected
						for (int i=0; i<ProductsCollected.size();i++){
							output2 += "("+ProductsCollected.get(i).getLocation().getX()+", "+ProductsCollected.get(i).getLocation().getY()+") then ";
						}
					 // prints out the list of products for an order and the route to take to select that order
					 itemTextArea.setText("Order Number: "+orderId+": "+output+"\n"+output2);
					 
			 }
			 
			
				 }//end printOrder
			 // class that brings up a button and a text box when the update stock  button is pushed  
			private class updateStockLevel implements ActionListener{
				String name;
				
				 public updateStockLevel(String name){
					 this.name =name;
				 }
				
				@Override
				 public void actionPerformed  (ActionEvent ae) {
					//text box to enter the quantity that you want to update the stock by 
					JTextArea enterQuantityNum = new JTextArea("Delete me and enter update value");
					enterQuantityNum.setSize(100, 100);
					//button to use the data put into the text box
					JButton enterQuantity = new JButton("Update Quantity");
					// add the box and the button
					controlPanel.add(enterQuantity);
					controlPanel.add(enterQuantityNum);
					mainFrame.setVisible(true);
					// calls update() class
					enterQuantity.addActionListener(new update(name, enterQuantityNum));
				}
			}
			//updates the stock level by the quantity in the text box generated in updateStockLevel() when the Update Quantity button is pushed
			private class update implements ActionListener{
				String name;
				private JTextArea enterQuantityNum ;
				public update(String name, JTextArea textArea){
					this.name =name;
					this.enterQuantityNum = textArea;
				}
				// calls the method updateProducts() from MySQl class to update the stock be the requested amount
				@Override
				public void actionPerformed  (ActionEvent ae) {
					MySQL.updateProducts(name, Integer.valueOf(enterQuantityNum.getText()));
				}
			}
			private class displayOrders implements ActionListener{
				int orderId ;
				 public displayOrders(int orderId){
					 this.orderId =orderId+1;
				 }
				 //calls the method orderStatus(() from MySQl class to 
				 @Override
				 public void actionPerformed  (ActionEvent ae) {
					 String output = "";
					//calls the method orderStatus(() from MySQl class to display the status of the selected order
					 orderStatus = MySQL.orderStatus(orderId);
					 for (int j=0; j<orderStatus.size();j++){
						 output += "-Order ID: "+orderId+"\n Order Status:"+orderStatus.get(j).getOrderStatus();
						 
						 itemTextArea.setText(output);
						 //create two new buttons to change the order status
						 JButton statusProcessing = new JButton("Change Status to Processing");
						 JButton statusComplete = new JButton("Change Status to Complete");
							controlPanel.add(statusProcessing);
							controlPanel.add(statusComplete);
							mainFrame.setVisible(true);
							statusProcessing.addActionListener(new statusProcessing(orderId));
							statusComplete.addActionListener(new statusComplete(orderId));
					 }
				 }// if the status is changed to processing
				 private class statusProcessing implements ActionListener{
						int orderId ;
						 public statusProcessing(int orderId){
							 this.orderId =orderId;
						 }
						 @Override
						 public void actionPerformed  (ActionEvent ae) {
							 // calls the updateStatusProcessing() method to change the status to processing
							 MySQL.updateStatusProcessing(orderId);
							 String output = "";
							 // shows that the status has been changed
							 orderStatus = MySQL.orderStatus(orderId);
							 for (int j=0; j<orderStatus.size();j++){
								 output += "-Order ID: "+orderId+"\n Order Status:"+orderStatus.get(j).getOrderStatus();
								 
								 itemTextArea.setText(output);
							 }
						 }
				 }// if the status has been changed to completed 
				 private class statusComplete implements ActionListener{
						int orderId ;
						 public statusComplete(int orderId){
							 this.orderId =orderId;
						 }
						 //calls the method to change the status to complete and update the product stock levels 
						 @Override
						 public void actionPerformed  (ActionEvent ae) {
							 MySQL.updateStatusComplete(orderId);
						 }
				 }
			}
}//end BCL class
	}//end class
