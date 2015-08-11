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
import javax.swing.JTextArea;


public class printOrdersWO extends JFrame {
	

		 private JFrame mainFrame;
		 private JLabel headerLabel;
		 private JTextArea  itemTextArea;
		 private JLabel orderStatusLabel;
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
		
	
		 private void prepareGUI() {
			 mainFrame = new JFrame("Warehouse IMS");
			 mainFrame.setSize(1500, 1500);
			 mainFrame.setLayout(new GridLayout(3, 1));
			 headerLabel = new JLabel("", JLabel.CENTER);
			 itemTextArea = new JTextArea("");
			 itemTextArea.setWrapStyleWord(true);
			 itemTextArea.setLineWrap(true);
			 itemTextArea.setSize(350, 200);
			 
			 //Changes the order from waiting to processing on button click
			 orderStatusLabel = new JLabel("sbgfjksdbkgj", JLabel.RIGHT);
			 orderStatusLabel.setSize(100,100);
			 mainFrame.addWindowListener(new WindowAdapter() {
				  public void windowClosing(WindowEvent windowEvent)     {
				   System.exit(0);
				  }
				});
				 controlPanel = new JPanel();
				 controlPanel.setLayout(new FlowLayout());
				 mainFrame.add(headerLabel);
				 mainFrame.add(controlPanel);
				 mainFrame.add(itemTextArea);
				 mainFrame.add(orderStatusLabel);
				 mainFrame.setVisible(true);
		 }
		 private void showEvent() {
			 headerLabel.setText("Press Appropriate Button");
			 JButton ShowOrders = new  JButton("Show Orders");
			 JButton UpdateStock =new JButton("Update Stock Levels");
			 JButton UpdateOrderStatus = new JButton("Update Order Status");
			 JButton Cancel =new JButton("Cancel");
			 
			 ShowOrders.setActionCommand("Show Orders");
			 UpdateStock.setActionCommand("Update Stock Levels");
			 UpdateOrderStatus.setActionCommand("Update Order Status");
			 Cancel.setActionCommand("Cancel");
			 
			 
			 
			 
			 ShowOrders.addActionListener( new BCL());
			 controlPanel.add(ShowOrders);
			 
			 UpdateStock.addActionListener( new BCL());
			 controlPanel.add(UpdateStock);
			 
			 UpdateOrderStatus.addActionListener( new BCL());
			 controlPanel.add(UpdateOrderStatus);
			 
			 Cancel.addActionListener( new BCL());
			 controlPanel.add(Cancel);
			 
			 mainFrame.setVisible(true);
		 }
		 private class BCL implements ActionListener {
			 
			 @Override
			 public void actionPerformed (ActionEvent ae) {
			  String command = ae.getActionCommand();
			  switch (command) {
			  // brings up order buttons
			  	case "Show Orders":
			  		listOfOrders = MySQL.gettingOrderID();
				  for (int i=0; i<listOfOrders.size(); i++) {
					 //  statusLabel.setText("Order ID: "+listOfOrders.get(i).getOrderNumber());
			  		
					   JButton ChooseOrder = new  JButton("Order ID: "+listOfOrders.get(i).getOrderNumber()); 
					   ChooseOrder.setActionCommand("Choose Order");
					   ChooseOrder.addActionListener( new printOrder(i));
						 controlPanel.add(ChooseOrder);
						 mainFrame.setVisible(true);
						 
				   }
				   break;
			 	case "Update Stock Levels":
			  		listOfItems =MySQL.gettingProducts();
			  		for (int i=000; i<listOfItems.size(); i++) {
			  			JButton ChooseProduct = new  JButton(listOfItems.get(i).getItemName()); 
			  			ChooseProduct.setActionCommand("Choose Product");
			  			ChooseProduct.addActionListener( new updateStockLevel(listOfItems.get(i).getItemName()));
							 controlPanel.add(ChooseProduct);
							 mainFrame.setVisible(true);
			  		}
			  		break;
			  	
			  	case "Update Order Status":
			  	
			  		listOfOrders = MySQL.gettingOrderID();
					  for (int i=0; i<listOfOrders.size(); i++) {
						 //  statusLabel.setText("Order ID: "+listOfOrders.get(i).getOrderNumber());
				  		
						   JButton ChooseOrder = new  JButton("Order ID: "+listOfOrders.get(i).getOrderNumber()); 
						   ChooseOrder.setActionCommand("Choose Order");
						   ChooseOrder.addActionListener( new displayOrders(i));
							 controlPanel.add(ChooseOrder);
							 mainFrame.setVisible(true);
					  }
			  		break;
			  	
			  	case "Cancel":
			  		
						  System.exit(0);
			  		
					 
			  		break;
			   
			  }

			 }
			 
			 private class printOrder implements ActionListener{
				 int orderId ;
				 public printOrder(int orderId){
					 this.orderId =orderId+1;
				 }
				 
				 @Override
				 public void actionPerformed  (ActionEvent ae) {
					 String output = "";
					 OrderResults = MySQL.gettingOrders(orderId);
					 for (int j=0; j<OrderResults.size();j++){
						 output += "\n-Product ID: "+OrderResults.get(j).getProduct_Id()+"\nProduct Name:"+OrderResults.get(j).getProduct_Name()+", \nPorous Status: "+OrderResults.get(j).getPorous_Status()+", \nQuant: "+OrderResults.get(j).getQuantity()+", \n";
						 
					 }
					 itemTextArea.setText("Order Number: "+orderId+": "+output);
					 /*
					 for (int j=0; j<listOfOrders.get(orderId).getArraySize();j++){
					 orderStatusLabel.setText("Order Status:"+listOfOrders.get(orderId).getOrderStatus());
					 
				 JButton ChangeOrderStatusP = new JButton("Change Status to Processing");
				 JButton ChangeOrderStatusC = new JButton("Change Status to Completed");
				 ChangeOrderStatusC.setActionCommand("Change Status");
				 ChangeOrderStatusC.addActionListener( new changeStatusC(j));
					 controlPanel.add(ChangeOrderStatusC);
					 mainFrame.setVisible(true);
				 
				 ChangeOrderStatusP.setActionCommand("Change Status");
				 ChangeOrderStatusP.addActionListener( new changeStatusP(j));
					 controlPanel.add(ChangeOrderStatusP);
					 mainFrame.setVisible(true);
					 
					 }*/
			 }
			 
			/*	 private class changeStatus  implements ActionListener{
					 int ChangeOrderstatusP ;
					 public changeStatus(int ChangeOrderstatusP){
						 this.ChangeOrderstatusP =ChangeOrderstatusP;
					 }
					 	@Override
					 	public void actionPerformed (ActionEvent ae) {
					 		listOfOrders.get(orderId).setOrderStatus("processing") ;
					 	}
				 	}*/
				 }//end printOrder
			 
			private class updateStockLevel implements ActionListener{
				String name;
				// private JTextArea enterQuantityNum ;
				 public updateStockLevel(String name){
					 this.name =name;
				 }
				
				@Override
				 public void actionPerformed  (ActionEvent ae) {
					JTextArea enterQuantityNum = new JTextArea("Delete me and enter update value");
					enterQuantityNum.setSize(100, 100);
					
					JButton enterQuantity = new JButton("Update Quantity");
					controlPanel.add(enterQuantity);
					controlPanel.add(enterQuantityNum);
					mainFrame.setVisible(true);
					enterQuantity.addActionListener(new update(name, enterQuantityNum));
				}
			}
			private class update implements ActionListener{
				String name;
				private JTextArea enterQuantityNum ;
				public update(String name, JTextArea textArea){
					this.name =name;
					this.enterQuantityNum = textArea;
				}
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
				 @Override
				 public void actionPerformed  (ActionEvent ae) {
					 String output = "";
					 orderStatus = MySQL.orderStatus(orderId);
					 for (int j=0; j<orderStatus.size();j++){
						 output += "-Order ID: "+orderId+"\n Order Status:"+orderStatus.get(j).getOrderStatus();
						 
						 itemTextArea.setText(output);
						 JButton statusProcessing = new JButton("Change Status to Processing");
						 JButton statusComplete = new JButton("Change Status to Complete");
							controlPanel.add(statusProcessing);
							controlPanel.add(statusComplete);
							mainFrame.setVisible(true);
							statusProcessing.addActionListener(new statusProcessing(orderId));
							statusComplete.addActionListener(new statusComplete(orderId));
					 }
				 }
				 private class statusProcessing implements ActionListener{
						int orderId ;
						 public statusProcessing(int orderId){
							 this.orderId =orderId+1;
						 }
						 @Override
						 public void actionPerformed  (ActionEvent ae) {
							 MySQL.updateStatusProcessing(orderId);
							 String output = "";
							 orderStatus = MySQL.orderStatus(orderId);
							 for (int j=0; j<orderStatus.size();j++){
								 output += "-Order ID: "+orderId+"\n Order Status:"+orderStatus.get(j).getOrderStatus();
								 
								 itemTextArea.setText(output);
							 }
						 }
				 }
				 private class statusComplete implements ActionListener{
						int orderId ;
						 public statusComplete(int orderId){
							 this.orderId =orderId+1;
						 }
						 @Override
						 public void actionPerformed  (ActionEvent ae) {
							 
						 }
				 }
			}
}//end BCL class
	}//end class
