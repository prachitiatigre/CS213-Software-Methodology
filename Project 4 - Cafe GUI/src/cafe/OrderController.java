package cafe;

import java.net.URL;
import javafx.fxml.FXML;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
The OrderController class takes care of the OrderView.fxml .
The OrderView allows user to see their order as well as remove their order.
The OrderView also displays the subTotal, salesTax and the total price of the order
@author Prachiti Atigre, Ujani Patel
*/
public class OrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<MenuItem> listOfItems;

    @FXML
    private Button removeSelectedItem, placeOrder;

    @FXML
    private TextArea subtotal, salextax, total;
    
    private static final double SALES_TAX_RATE = 0.06625, ZERO_AMOUNT = 0.0;
    DecimalFormat df = new DecimalFormat("#,##0.00");
    
    private double orderTotal, orderSalesTax, orderPayment;
    
    /**
	This method creates an observableList to store the orders and calculate the total amount of the order
	*/
    @FXML
    public void initialize() {
    	
    	Order order = MainMenuController.getOrder();
    	
    	order.calculatePayment();
    	orderTotal = order.getOrderTotal();
        orderSalesTax = order.getSalesTax();
        orderPayment = order.getPayment();

        printSub();
        printSalesTax();
        printTotal();
        
        ObservableList<MenuItem> list = order.getList();
        listOfItems.setItems(list);
    }
    
    /**
  	This method places the order of the user. The method calls the
  	MainMenuController to get the order and adds the order to the StoreOrders.
  	@param event upon hitting the placeOrder button
  	*/
    @FXML
    void placeOrder(ActionEvent event) {
    	
    	try {
    		
    		Order order = MainMenuController.getOrder();
            StoreOrders storeOrders =  MainMenuController.getStoreOrders();
        	
            storeOrders.add(order);
        	
            MainMenuController.setOrder(new Order());
            order = MainMenuController.getOrder();
            order.calculatePayment();
            
        	new Alert(Alert.AlertType.CONFIRMATION, "Order added to Store Orders").show();
        	
    	}
    	catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Enter all fields correctly").show();
        }
	}

    /**
  	This method removes the selectedItem from the viewList.
  	@param event upon selecting the item to be removed
  	*/
    @FXML
    void removeSelectedItem(ActionEvent event) {
    
    	MenuItem item = listOfItems.getSelectionModel().getSelectedItem();
    	
    	if(item instanceof Donut) {
        		
        		Donut donutCheck = (Donut) item;
        	
        		String donutFlavor = donutCheck.getDonutFlavor();
            	String typeOfDonut = donutCheck.getDonutType();
        		
            	Donut donut = new Donut(typeOfDonut, donutFlavor, donutCheck.getQuantity());
            	
            	donut.calculateItemPrice();
            	double cost = donut.getItemPrice();
            	
            	orderTotal = orderTotal - cost;
            	double salesTax = SALES_TAX_RATE * cost;
            	
            	orderSalesTax = orderSalesTax - salesTax;
            	orderPayment = orderTotal + orderSalesTax;
        	}
        	
        	else if(item instanceof Coffee) {
        		
        		Coffee coffee = (Coffee) item;
        		coffee.calculateItemPrice();
            	double cost = coffee.getItemPrice();
            	
            	orderTotal = orderTotal - cost;
            	double salesTax = SALES_TAX_RATE * cost;
            	
            	orderSalesTax = orderSalesTax - salesTax;
            	orderPayment = orderTotal + orderSalesTax;
        	}
        
        	printSub();
        	printSalesTax();
        	printTotal();
        	
        	int selectedItem = listOfItems.getSelectionModel().getSelectedIndex();
            listOfItems.getItems().remove(selectedItem); 
    }
    	
    /**
    Prints the order total in the appropriate text area
    */
    public void printSub() {
    	
    	if(orderTotal < ZERO_AMOUNT) {
    		subtotal.clear();
    		subtotal.appendText("$" + df.format(ZERO_AMOUNT));
    	}
    	else {
    		subtotal.clear();
    		subtotal.appendText("$" + df.format(orderTotal));
    	}
    	
    }
    
    /**
    Prints the order sales tax in the appropriate text area
    */
    public void printSalesTax() {
    	
    	if(orderTotal < ZERO_AMOUNT) {
    		salextax.clear();
    		salextax.appendText("$" + df.format(ZERO_AMOUNT));
    	}
    	else {
    		salextax.clear();
    		salextax.appendText("$" + df.format(orderSalesTax));
    	}
    }
    
    /**
    Prints the total order payment in the appropriate text area
    */
    public void printTotal() {
    	
    	if(orderTotal < ZERO_AMOUNT) {
    		total.clear();
    		total.appendText("$" + df.format(ZERO_AMOUNT));
    	}
    	else {
    		total.clear();
    		total.appendText("$" + df.format(orderPayment));
    	}
    } 	
}
