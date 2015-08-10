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
		 ArrayList<Order> listOfOrders ;
		 ArrayList<Order_Line> OrderResults;
		// ArrayList<Order> orderStatus;
		 ArrayList<item> listOfItems;
		
		
		 Scanner input = new Scanner(System.in);
		public printOrdersWO(){
			prepareGUI();
			showEvent();
			
			listOfOrders = new ArrayList<Order>();
			OrderResults = new ArrayList<Order_Line>();
			
		
			

		}
		
	
		 private void prepareGUI() {
			 mainFrame = new JFrame("Warehouse IMS");
			 mainFrame.setSize(500, 500);
			 mainFrame.setLayout(new GridLayout(3, 1));
			 headerLabel = new JLabel("", JLabel.CENTER);
			 itemTextArea = new JTextArea("");
			 itemTextArea.setWrapStyleWord(true);
			 itemTextArea.setLineWrap(true);
			 itemTextArea.setSize(350, 200);
			 //Changes the order from waiting to processing on button click
			 orderStatusLabel = new JLabel("", JLabel.RIGHT);
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
			 headerLabel.setText("press button to show orders");
			 JButton ShowOrders = new  JButton("Show Orders");
			 
			 ShowOrders.setActionCommand("Show Orders");

			 ShowOrders.addActionListener( new BCL());
			 controlPanel.add(ShowOrders);
			 mainFrame.setVisible(true);
		 }
		 private class BCL implements ActionListener {
			 
			 @Override
			 public void actionPerformed (ActionEvent ae) {
			  String command = ae.getActionCommand();
			  switch (command) {
			  
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
			   
			  }

			 }
			 
			 private class printOrder implements ActionListener{
				 int orderId ;
				 public printOrder(int orderId){
					 this.orderId =orderId;
				 }
				 
				 @Override
				 public void actionPerformed  (ActionEvent ae) {
					 String output = "";
					 OrderResults = MySQL.gettingOrders(orderId);
					 for (int j=0; j<OrderResults.size();j++){
						 output += "Product ID: "+OrderResults.get(j).getProduct_Id()+"Product Name:"+OrderResults.get(j).getProduct_Name()+", Porous Status: "+OrderResults.get(j).getPorous_Status()+", Quant: "+OrderResults.get(j).getQuantity()+", ";
						 
					 }
					 itemTextArea.setText("Order Number: "+orderId+": "+output);
					 
					 for (int j=0; j<listOfOrders.get(orderId).getArraySize();j++){
					 orderStatusLabel.setText("Order Status:"+listOfOrders.get(orderId).getOrderStatus());
					 
				
				 
				 
				 JButton ChangeOrderstatus = new JButton("Change Status to Processing");
				 ChangeOrderstatus.setActionCommand("Choose Order");
				 ChangeOrderstatus.addActionListener( new changeStatus(j));
					 controlPanel.add(ChangeOrderstatus);
					 mainFrame.setVisible(true);
					 
					 }
			 }
				 
				 private class changeStatus  implements ActionListener{
					 int ChangeOrderstatus ;
					 public changeStatus(int ChangeOrderstatus){
						 this.ChangeOrderstatus =ChangeOrderstatus;
					 }
					 	@Override
					 	public void actionPerformed (ActionEvent ae) {
					 		listOfOrders.get(orderId).setOrderStatus("processing") ;
					 	}
				 	}
				 }
			
			 
}//end main
	}//end class
