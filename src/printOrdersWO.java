import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		 ArrayList<Order> listOfOrders;
		// ArrayList<Order> orderStatus;
		 ArrayList<item> listOfItems;
		
		 Scanner input = new Scanner(System.in);
		public printOrdersWO(){
			prepareGUI();
			showEvent();
			addItems();
			
		}
		
	private void addItems(){
		 item itemYellowGnome, itemBlueGnome, itemGreenGnome;
		 itemYellowGnome = new item("Yellow Gnome, ");
		  itemBlueGnome = new item("Blue Gnome, ");
		  itemGreenGnome= new item( "Green Gnome, ");
		  listOfItems = new ArrayList<item>();
		  listOfItems.add(itemYellowGnome);
		  listOfItems.add(itemBlueGnome);
		  listOfItems.add(itemGreenGnome);
		  
		  listOfOrders = new ArrayList<Order>();
		  Order newOrder = new Order(listOfOrders.size());
		  newOrder.addItemToOrder(itemYellowGnome);
		  newOrder.addItemToOrder(itemBlueGnome);
		  newOrder.getOrderStatus();
		  listOfOrders.add(newOrder);
		  
		 
		  
		  
		  Order Order1 = new Order(listOfOrders.size());
		  Order1.addItemToOrder(itemGreenGnome);
		  Order1.addItemToOrder(itemBlueGnome);
		  Order1.getOrderStatus();
		  listOfOrders.add(Order1);
		  
		  
		  
		  //listOfOrders.add(new Order(listOfOrders.size()).addItemToOrder(itemYellowGnome));
		  
		  //loop that counts entries in the array of orders and prints out a list
		  for (int i=0; i<listOfOrders.size(); i++){
			  System.out.println("Order ID: "+listOfOrders.get(i).getOrderNumber());
		  }
		  for (int i=0; i<listOfOrders.size(); i++){
		//WareHouse opperative selects 	  
			  System.out.print("Enter Order Id to view: ");
			  int userInput = input.nextInt();
		  //put in for loop "j" to bring up list of items in the array
		 for (int j=0; j<listOfOrders.get(userInput).getArraySize();j++){
		  System.out.print( listOfOrders.get(userInput).getItemsOnOrder().get(j).getItemName()+"\n");
		  System.out.print( listOfOrders.get(userInput).getOrderStatus());
		  }
	}
}
	
		 private void prepareGUI() {
			 mainFrame = new JFrame("Warehouse IMS");
			 mainFrame.setSize(500, 500);
			 mainFrame.setLayout(new GridLayout(3, 1));
			 headerLabel = new JLabel("", JLabel.CENTER);
			// itemLabel = new JLabel("", JLabel.CENTER);
			 itemTextArea = new JTextArea("");
			 itemTextArea.setWrapStyleWord(true);
			 itemTextArea.setLineWrap(true);
			 itemTextArea.setSize(350, 200);
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
			  		
				   for (int i=0; i<listOfOrders.size(); i++) {
					 //  statusLabel.setText("Order ID: "+listOfOrders.get(i).getOrderNumber());
					   JButton ChooseOrder = new  JButton("Order ID: "+listOfOrders.get(i).getOrderNumber()); 
					   ChooseOrder.setActionCommand("Choose Order");
					   ChooseOrder.addActionListener( new printOrder(i));
						 controlPanel.add(ChooseOrder);
						 mainFrame.setVisible(true);
						 
				   }
				  
							// for (int j=0; j<listOfOrders.get(JButton.ChooseOrder).getArraySize();j++){
							//	 statusLabel.setText( listOfOrders.get(ChooseOrder).getItemsOnOrder().get(j).getItemName()+"\n");
							//	  }
				  
			   break;
			   
			  }

			 }
			 
			 private class printOrder implements ActionListener{
				 int orderId ;
				 public printOrder(int orderId){
					 this.orderId =orderId;
				 }
				 
				 @Override
				 public void actionPerformed (ActionEvent ae) {
					 String output = "";
					 for (int j=0; j<listOfOrders.get(orderId).getArraySize();j++){
						 output += listOfOrders.get(orderId).getItemsOnOrder().get(j).getItemName()+"";
						 
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
			
				/*	  
					  
					  item itemYellowGnome, itemBlueGnome, itemGreenGnome;
				 itemYellowGnome = new item(1, "Yellow Gnome", "Shelf 2", 20, " a bright yellow gnome");
				  itemBlueGnome = new item(1, "Blue Gnome", "Shelf 1", 20, " a bright blue gnome");
				  itemGreenGnome= new item(1, "Green Gnome", "Shelf 3", 20, " a bright green gnome");
				  
				  Orders Order1, Order2, Order3, Order4, order5;
				  Order1 = new Orders(itemYellowGnome.getItemName());
			
			*/
			
			 
}//end main
	}//end class
