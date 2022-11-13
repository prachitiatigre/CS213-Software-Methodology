package cafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
This Order class implements the Customizable interface. This class takes care of the orders 
from the user as well as tracking the prices, and orders to be added and removed.
@author Prachiti Atigre, Ujani Patel
*/
public class Order implements Customizable {
	
	private double orderTotal, payment, salesTax;
	
	private static final double SALES_TAX_RATE = 0.06625;
	
	private ObservableList<MenuItem> list;
	
	/**
   	This body of order constructor is taking an observableArrayList which will be used 
   	by the OrderController to store orders of coffee and donuts
   	*/
	public Order() {
		this.list = FXCollections.observableArrayList();
	}

	/**
	This method checks if the obj pass is an instance of the Coffee Object or
	checks if the obj pass is an instance of the Donut Object
	If it is an Object of donut or coffee, it gets added into the ObservableList
	@param obj the Donut or Coffee object
	@return false if obj is not an instance of the Coffee Object or Donut Object, otherwise true
	*/
	@Override
	public boolean add(Object obj) {
		
		MenuItem item = (MenuItem) obj;  
		
		if(item instanceof Coffee) {
		
			Coffee coffee = (Coffee) item;
			this.list.add(coffee);
			return true;
		}
		else if(item instanceof Donut) {
			
			Donut donut = (Donut) item;
			this.list.add(donut);
			return true;
		}
		return false;
	}

	/**
	This method checks if the obj pass is an instance of the Coffee Object or
	checks if the obj pass is an instance of the Donut Object
	If it is an Object of donut or coffee, it gets removed from the ObservableList
	@param obj the Donut or Coffee object
	@return false if obj is not an instance of the Coffee Object or Donut Object, otherwise true
	*/
	@Override
	public boolean remove(Object obj) {
		
		MenuItem item = (MenuItem) obj;  
				
		if(item instanceof Coffee) {
			
			Coffee coffee = (Coffee) item;
			this.list.add(coffee);
			return true;
		}
		else if(item instanceof Donut) {
			
			Donut donut = (Donut) item;
			this.list.add(donut);
			return true;
		}
		return false;
	}

	/**
	Method that calculates the SalesTax
	*/
	public void calculateSalesTax() {
		
		salesTax = SALES_TAX_RATE * this.getOrderTotal();
		this.setSalesTax(salesTax);
	}

	/**
	This method calculates the totalPrice of the order which includes the Coffee Order and Donut Order
	from the observableList
	*/
	public void calculateOrderTotal() {
	
		if(this.list.size() >= 0) {
			
			for(int i = 0; i < this.list.size(); i++) {
				orderTotal = orderTotal + this.list.get(i).getItemPrice();
			}
			
			//orderTotal = orderTotal + this.getSalesTax();
			this.setOrderTotal(orderTotal);
		}
	}
	
	/**
	This method helps calculate payment 
	It calls the calculateOrderTotal and calculateSalesTax to calculate the payment.
	*/
	public void calculatePayment() {
		
		calculateOrderTotal();
    	calculateSalesTax();
		
		payment = this.getOrderTotal() + this.getSalesTax();
		this.setPayment(payment);
	}
	
	/**
	Helper method to set the OrderTotal
	@param subtotal the order total of an order without sales tax
	*/
	private void setOrderTotal(double subtotal) {
		this.orderTotal = subtotal;
	}

	/**
	Helper method to get the OrderTotal
	@return orderTotal 
	*/
	public double getOrderTotal() {
	
		return this.orderTotal;
	}
	
	/**
	Helper method to set SalesTax
	@param salesTax the sales tax applied on the order
	*/
	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}
	
	/**
	Helper method to get Sales Tax
	@return salesTax
	*/
	public double getSalesTax() {
		return this.salesTax;
	}
	
	/**
	Helper method to set Payment
	@param payment the total payment of the order including sales tax
	*/
	public void setPayment(double payment) {
		this.payment = payment;
	}
	
	/**
	Helper method to get Payment
	@return payment 
	*/
	public double getPayment() {
		return this.payment;
	}

	/**
	This method returns the list of menuitems
	@return list the list of menu items
	*/
	public ObservableList<MenuItem> getList() {
		return this.list;
	}
	
	/**
	This method formats the list with orderItems into a string format
	@return string format
	*/
    @Override
    public String toString() {
 
    	String output = "";
    	for(int i = 0; i < this.list.size(); i++) {
    		
    		if(this.list.get(i) instanceof MenuItem) {
    			MenuItem item = (MenuItem) this.list.get(i);
    			
    			if (item instanceof Donut) {
    				
    				Donut d = (Donut) item;
    				output = output + d.toString() + "\n";
    			}
    			else if(item instanceof Donut) {
    				
    				Coffee c = (Coffee) item;
    				output = output + c.toString() + "\n";
    			}
    		}
    	}
    	return output + "$" + this.getPayment() + "\n";
    }
}