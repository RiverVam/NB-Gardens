
public class Order_Line {
private int Order_ID;
private int Product_Id;
private int Quantity;
private String Product_Name;
private String Porous_Status;
private ProductLocationTSP location;

Order_Line(){
	location= new ProductLocationTSP(0,0);
}
public int getOrder_ID() {
	return Order_ID;
}
public void setOrder_ID(int order_ID) {
	Order_ID = order_ID;
}
public int getProduct_Id() {
	return Product_Id;
}
public void setProduct_Id(int product_Id) {
	Product_Id = product_Id;
}
public int getQuantity() {
	return Quantity;
}
public void setQuantity(int quantity) {
	Quantity = quantity;
}
public String getProduct_Name() {
	return Product_Name;
}
public void setProduct_Name(String product_Name) {
	Product_Name = product_Name;
}
public String getPorous_Status() {
	return Porous_Status;
}
public void setPorous_Status(String porous_Status) {
	Porous_Status = porous_Status;
}
public int getProduct_LocationX() {
	return location.getX();
}


public void setProduct_LocationX(int product_LocationX) {
	location.setX(product_LocationX);
}


public int getProduct_LocationY() {
	return location.getY();
}


public void setProduct_LocationY(int product_LocationY) {
	location.setY(product_LocationY);
}
public ProductLocationTSP getLocation() {
	return location;
}
public void setLocation(ProductLocationTSP location) {
	this.location = location;
}
}
