import java.util.ArrayList;


public class Order {

	
		private int orderNumber;
		String orderStatus ;
		

		ArrayList<item> itemsOnOrder;

		public Order(int orderNumber, String orderStatus){
			
			this.orderNumber = orderNumber;
			this.orderStatus = orderStatus;
			itemsOnOrder = new ArrayList<item>();
			
		}
		public Order(int orderNumber){
			
			this.orderNumber = orderNumber;
			
			itemsOnOrder = new ArrayList<item>();
			
		}

		
		public void addItemToOrder(item newItem){
			itemsOnOrder.add(newItem);
			
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
		

}


