
public class item {
 

 private String ProductName ;
 private int Product_ID;
 private int Product_Quantity;
 public item(String itemName) {
		
		this.ProductName = itemName;
		
	}


public String getItemName() {
	return ProductName;
}


public void setItemName(String itemName) {
	this.ProductName = itemName;
}


public int getProduct_ID() {
	return Product_ID;
}


public void setProduct_ID(int product_ID) {
	Product_ID = product_ID;
}


public String getProductName() {
	return ProductName;
}


public void setProductName(String productName) {
	ProductName = productName;
}


public int getProduct_Quantity() {
	return Product_Quantity;
}


public void setProduct_Quantity(int product_Quantity) {
	Product_Quantity = product_Quantity;
}
}

