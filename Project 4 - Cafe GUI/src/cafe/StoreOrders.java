package cafe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
An instance of this class keeps the list of orders placed by the user. This class must
implement the Customizable interface above
@author Prachiti Atigre, Ujani Patel
*/
public class StoreOrders implements Customizable {
	
	private ObservableList<Order> storeOrders;
	
	/**
   	This body of order constructor is taking an observableArrayList which will be used 
   	by the StoreOrdersController to store orders of the store
   	*/
	public StoreOrders() {
		this.storeOrders = FXCollections.observableArrayList();
	}
	
	/**
	This method checks if the obj pass is an instance of the StoreOrders
	If it is, it gets added to the the storeOrders list
	@param obj 
	@return false if obj is not an instance of StoreOrders, otherwise true
	*/
	@Override
	public boolean add(Object obj) {
		
		if(obj instanceof StoreOrders) {
			
			Order order = (Order) obj;
			storeOrders.add(order);
			return true;
		}
		else
			return false;
	}

	/**
	This method checks if the obj pass is an instance of the StoreOrders
	If it is, it gets removed from the storeOrders list
	@param obj 
	@return false if obj is not an instance of StoreOrders, otherwise true
	*/
	@Override
	public boolean remove(Object obj) {
		
		if(obj instanceof StoreOrders) {
			
			Order order = (Order) obj;
			storeOrders.remove(order);
			return true;
		}
		else
			return false;
	}
}

