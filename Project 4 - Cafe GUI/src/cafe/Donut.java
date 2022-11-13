package cafe;

/**
An instance of this class is a menu item in an order. This class must extend MenuItem class.
The Donut class handles the donutType, donutFalvor and quantity that the user chooses. 
It also performs the calculation of the donuts the user has ordered.
@author Prachiti Atigre, Ujani Patel
*/
public class Donut extends MenuItem {
	
	private String donutType;
	private double itemPrice;
	private String donutFlavor;
	private int quantity;
	
	private static final double YEAST_COST = 1.39, CAKE_COST = 1.59, DONUT_HOLE_COST = 0.33;
	private static final double DEFAULT_ITEM_PRICE = 0.0;
	
	/**
  	This method sets the donutType, donutFlavor, and the quantity of donut
  	@param donutType type of donut
  	@param donutFlavor flavor of donut
  	@param quantity quantity of donut
  	*/
	public Donut(String donutType, String donutFlavor, int quantity) {
		
		super("Donut", DEFAULT_ITEM_PRICE);
		this.quantity = quantity;
		this.donutType = donutType;
		this.donutFlavor = donutFlavor;
	}
	
	/**
  	This Helper method gets the donutType
  	@return donutType
  	*/ 
	public String getDonutType() {
		return this.donutType;
	}
	
	/**
  	This Helper method gets the donutFlavor
  	@return donutFlavor
  	*/
	public String getDonutFlavor() {
		return this.donutFlavor;
	}
	
	/**
  	This Helper method gets the donut quantity
  	@return quantity
  	*/
	public int getQuantity(){
        return this.quantity;
    }
	
	/**
	This method checks if the obj pass is an instance of the Donut Object
	@param obj the Donut object
	@return false if obj is not an instance of the Donut Object, otherwise true
	*/
	@Override
	public boolean equals(Object obj) { 
		
		if(obj instanceof Donut) { 
			
			Donut donut = (Donut) obj;  
			
			return donut.getDonutFlavor().equals(this.getDonutFlavor()); 
		}
		return false; 
	}
	
	/**
	This method calculates the price of the Donut Item by its flavor and it's quantity
	*/
	@Override
    public void calculateItemPrice() {
		
		String donutType = this.getDonutType();
		int quantity = this.getQuantity();
		
		if(donutType.equals("Yeast")) {
			itemPrice = YEAST_COST * quantity;
		}
		else if(donutType.equals("Cake")) {
			itemPrice = CAKE_COST * quantity;
		}
		else if(donutType.equals("Donut Hole")) {
			itemPrice = DONUT_HOLE_COST * quantity;
		}
        super.setItemPrice(itemPrice);
    }
	
	/**
	This method creates a string format for the quantity of donut and it's flavor
	@return the donutFlavor and the quantity of Donuts	
	*/
	@Override
	public String toString() {
		return this.getDonutFlavor() + "(" + this.getQuantity() + ")";
	}
}
