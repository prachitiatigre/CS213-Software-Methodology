package cafe;

/**
This is the superclass of all menu items, such as donuts and coffee
Any class defined for a menu item must extend this class. 
All the subclasses must include a “itemPrice” method for calculating the price of the menu item.
@author Prachiti Atigre, Ujani Patel
*/
public class MenuItem {

	private String itemName;
	private double itemPrice;
	
	/**
	Helper method to sets the itemName and itemPrice
	@param itemName the name of the item
	@param itemPrice the price of the item
	*/
	public MenuItem(String itemName, double itemPrice) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	
	/**
	Helper method to getItemName
	@return itemName
	*/
	public String getItemName() {
		return this.itemName;
	}
	
	/**
	Helper method to setItemPrice
	@param itemPrice
	*/
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	/**
	Helper method to getItemPrice
	@return itemPrice
	*/
	public double getItemPrice() {
		return this.itemPrice;
	}
	
	/**
	Method that gets called to calculate the Price of an item
	*/
	public void calculateItemPrice() {
		
	}
	
	/**
	This method checks if the obj passed is an instance of MenuItem.
	@param obj 
	@return false if obj is not an instance of the MenuItem, otherwise true
	*/
	@Override
	public boolean equals(Object obj) { 
		
		if(obj instanceof MenuItem) { 
			
			MenuItem item = (MenuItem) obj;  
			
			return item.getItemName().equals(this.getItemName());  
		}
		return false; 
	}		
}
