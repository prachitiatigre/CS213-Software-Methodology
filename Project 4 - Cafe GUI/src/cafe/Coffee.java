package cafe;
import java.util.ArrayList;

/**
An instance of this class is a menu item in an order. This class must extend MenuItem class
and implement the Customizable interface. This class also takes care of the size of the coffee, 
addIns, and price.
@author Prachiti Atigre, Ujani Patel
*/
public class Coffee extends MenuItem implements Customizable {

	private int quantity = 1;
	private String size = "Short"; 
	
	private ArrayList<String> addins; 
	private ArrayList<Double> addInsTotalCost;
	
	private double sizeCost = SHORT_COST, costOfAddIns, itemPrice;
	private static final int NO_ADDINS_COST = 0, NO_ADDINS = 0;
	private static final double DEFAULT_ITEM_PRICE = 0.0;
	private static final double SHORT_COST = 1.99, TALL_COST = 2.49, GRANDE_COST = 2.99, VENTI_COST = 3.49, ADDINS_COST = 0.2;
	
	/**
	This constructor calls the super class and sets the item name and default quantity and also initializes
	and an arraylist for the add-ins and add-ins total cost 
	*/
	public Coffee() {
	
		super("Coffee", DEFAULT_ITEM_PRICE);
		this.addins = new ArrayList<String>();
		this.addInsTotalCost = new ArrayList<Double>();
	}
	
	/**
	Helper method to get the size of the coffee
	@return the size of coffee
	*/
	public String getSize() {
		return this.size;
	}
	
	/**
	Helper method to set the size of the coffee
	@param size of the coffee
	*/
	public void setSize(String size) {
		this.size = size;
	}
	
	/**
	Helper method to get the cost of size of the coffee
	@return the cost for the size of the coffee given
	*/
	public double getCoffeeSizeCost() {
		return this.sizeCost;
	}
	
	/**
	Helper method to set the size cost for the given Coffee size
	@param sizeCost of the coffee
	*/
	public void setCoffeeSizeCost(double sizeCost) {
		this.sizeCost = sizeCost;
	}
	
	/**
	Helper method to get the cost of the addIns
	@return the cost of addIns
	*/
	public double getCostOfAddIns() { //Returns total of prices
		return this.costOfAddIns;
	}
	
	/**
	Helper method that sets the cost of the addIns
	@param costOfAddIns
	*/
	public void setCostOfAddIns(double costOfAddIns) {
		this.costOfAddIns = costOfAddIns;
	}
	
	/**
	This method creates an ArrayList of type String
	The ArrayList stores the addIns
	@return addIns 
	*/
	public ArrayList<String> getAddIns() {
		return this.addins;
	}
	
	/**
	This method creates an ArrayList of type Double
	The ArrayList stores the addIn cost
	@return addIn cost 
	*/
	public ArrayList<Double> getAddInsCost() { //Returns array of prices
		return this.addInsTotalCost;
	}
	
	/**
	Helper method to get the Quantity of the coffee
	@return the quantity of the coffee
	*/
	public int getQuantity(){
        return this.quantity;
    }
	
	/**
	Helper method that sets the Quantity of the coffee
	@param quantity
	*/
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	This method checks if the obj pass is an instance of the Coffee Object
	@param obj 
	@return false if obj is not an instance of the Coffee Object, otherwise true
	*/
	@Override
	public boolean equals(Object obj) { 
		
		if(obj instanceof Coffee) { 
			
			Coffee coffee = (Coffee) obj;  
			
			return coffee.getItemName().equals(this.getItemName()) &&
					coffee.getSize().equals(this.getSize()) &&
						coffee.getAddIns().equals(this.getAddIns());  
		}
		else {
			return false;
		}
	}
	
	/**
	This method checks if the obj pass is an instance of the String Object.
	It adds the addIn when it's true
	@param obj 
	@return false if obj is not an instance of the String Object or when it's not an addIn, otherwise true
	*/
	@Override
	public boolean add(Object obj) {
		
		if(obj instanceof String) {
			String addIn = (String) obj;
			
			if(addIn.equals("Cream") || addIn.equals("Syrup") || addIn.equals("Milk") || 
					addIn.equals("Caramel") || addIn.equals("Whipped Cream")) {
				
				addins.add(addIn);
				addInsTotalCost.add(ADDINS_COST);
				return true;
			}
			else
				return false;
		}
		else 
			return false;
	}
	
	/**
	This method checks if the obj pass is an instance of the String Object.
	It removes the addIn when it's true
	@param obj 
	@return false if obj is not an instance of the String Object or when it's not an addIn, otherwise true
	*/
	@Override
	public boolean remove(Object obj) {
		
		if(obj instanceof String) {
			
			String addIn = (String) obj;
			
			if(addIn.equals("Cream") || addIn.equals("Syrup") || addIn.equals("Milk") || 
					addIn.equals("Caramel") || addIn.equals("Whipped Cream")) {
				
				addins.remove(addIn);
				addInsTotalCost.remove(ADDINS_COST);
				return true;
			}
			else
				return false;
		}
		else 
			return false;
	}
	
	/**
	This method calculates the price of the coffee size
	@return the sizeCost of the coffee
	*/
	public double calculateSizePrice() {
		
		if(size != null) {
			
			if(size.equals("Short")) {
				sizeCost = SHORT_COST;
			}
			
			else if(size.equals("Tall")) {
				sizeCost = TALL_COST;
			}
			
			else if(size.equals("Grande")) {
				sizeCost = GRANDE_COST;
			}
			
			else if(size.equals("Venti")) {
				sizeCost = VENTI_COST;
			}
		}
		return sizeCost;
	}
	
	/**
	This method calculates the addIn price of the coffee
	*/
	public void calculateAddInsPrice() { 
		
		if(addins != null) {
		
			for(int i = 0; i < addInsTotalCost.size(); i++) {
				costOfAddIns = costOfAddIns + addInsTotalCost.get(i);
			}
		}
		else {
			costOfAddIns = NO_ADDINS_COST;
		}
	}
	
	/**
	This method calculates the price of the coffee size and cost of addIns together
	*/
	@Override
	public void calculateItemPrice() {
		
		calculateAddInsPrice();
		
		itemPrice = calculateSizePrice() + this.getCostOfAddIns();
		
		itemPrice = itemPrice * this.getQuantity();
		
		super.setItemPrice(itemPrice);
	}
	
	/**
	This method creates a string format for the quantity of coffee, the size of coffee and the addIn
	@return the string format of coffee, quantity and addIn or the string format to coffee and quantity 
	when there is no addIns
	*/
	@Override
	public String toString() {
			
		if(addins.size() == NO_ADDINS) {
			return this.getItemName() + "(" + this.getQuantity() + ") " + this.getSize();
		}
		else {
			return this.getItemName() + "(" + this.getQuantity() + ") " + this.getSize() + " " + this.getAddIns();
		}
	}

}
