import java.util.ArrayList;


public class routeManager {
	
	 // Holds our product locations
    private static ArrayList destinationProducts = new ArrayList<ProductLocationTSP>();
    		
    // Adds a destination location
    public static void addProductLocationTSP(ProductLocationTSP location) {
    	destinationProducts.add(location);
    }
    
    // Get a location
    public static ProductLocationTSP getProductLocationTSP(int index){
        return (ProductLocationTSP)destinationProducts.get(index);
    }
    
    // Get the number of destination locations
    public static int numberOfProductLocations(){
        return destinationProducts.size();
    }
}
