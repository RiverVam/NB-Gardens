import java.util.ArrayList;


public class ProductLocationTSP {
	int x;
	int y;
	
	//constructs a products location
	public ProductLocationTSP(int x, int y){
		this.x = x;
		this.y = y;
	}
	// setters and getters for the product location
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
		
	
	//finds the distance to the product location
	public double distanceTo(ProductLocationTSP location){
		int xDistance = Math.abs(getX() - location.getX());
        int yDistance = Math.abs(getY() - location.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        
        return distance;
	}
		
	
	
}//end ProductLocationTSP class
