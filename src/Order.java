import java.util.ArrayList;


public class Order {

	
		private int orderNumber;
		String orderStatus = "Waiting";
		
	//	String orderContents ;

		ArrayList<item> itemsOnOrder;

		public Order(int orderNumber, String orderStatus){
			//this.orderContents = orderContents;
			this.orderNumber = orderNumber;
			
			itemsOnOrder = new ArrayList<item>();
			
		}
		public Order(int orderNumber){
			//this.orderContents = orderContents;
			this.orderNumber = orderNumber;
			
			itemsOnOrder = new ArrayList<item>();
			
		}
//		public void addOrderStatus(Order orderStatus){
//			orderStatus.add(orderStatus);
//		}
		
		public void addItemToOrder(item newItem){
			itemsOnOrder.add(newItem);
			//System.out.println("name of item " +newItem.getItemName());
		}
	
	
		public int getOrderNumber() {
			return orderNumber;
		}



		public void setOrderNumber(int orderNumber) {
			this.orderNumber = orderNumber;
		}

		public ArrayList<item> getItemsOnOrder() {
			return itemsOnOrder;
		}

		public void setItemsOnOrder(ArrayList<item> itemsOnOrder) {
			this.itemsOnOrder = itemsOnOrder;
		}

		public int getArraySize(){
			return itemsOnOrder.size();
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
	}
		
/* 		

		public String getOrderContents() {
			return orderContents;
		}



		public void setOrderContents(String orderContents) {
			this.orderContents = orderContents;
		}
		*/
}


