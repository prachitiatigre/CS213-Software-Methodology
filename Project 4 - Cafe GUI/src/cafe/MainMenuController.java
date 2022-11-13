package cafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
This class is the Parent GUI to the rest of the GUI 
It has four buttons: Order coffee, Order Donut, Your order and Store Orders
upon selecting a button, it takes you to that respective GUI
@author Prachiti Atigre, Ujani Patel
*/
public class MainMenuController {

    @FXML
    private Button orderDonutsButton, yourOrderButton, orderCoffeeButton, storeOrdersButton;
    
    private static Order order = new Order();
    private static StoreOrders storeOrder = new StoreOrders();

    /**
    Method to get the store order
    @return order the current order
    */
    public static Order getOrder() {
        return order;
    }

    /**
	This static method sets the Order from the user 
	@param addingOrder order to be set
	*/
    public static void setOrder(Order addingOrder) {
    	order = addingOrder;
    }

    /**
   	This static method get the orders from the store 
   	@return storeOrder order requested by user
   	*/
    public static StoreOrders getStoreOrders() {
        return storeOrder;
    }
    
    /**
   	This method loads the CoffeeView.fxml so the Order Coffee GUI can be presented. 
   	@param event upon selecting the Order Coffee button
   	*/
    @FXML
    void callOrderCoffee(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CoffeeView.fxml"));
    		Parent root1 = loader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Ordering Coffee");
    		stage.setScene(new Scene(root1));
    		stage.show();	
    	}
    	
    	catch(Exception e) {
    		
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Warning Dialog");
    		alert.setContentText("Cannot open Ordering Coffee.");
    		alert.showAndWait();
    		return;
    	}
    }

    /**
   	This method loads the DonutView.fxml so the Order Donut GUI can be presented. 
   	@param event upon selecting the OrderDonut button
   	*/
    @FXML
    void callOrderDonuts(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("DonutView.fxml"));
    		Parent root2 = loader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Ordering Donuts");
    		stage.setScene(new Scene(root2));
    		stage.show();	
    	}
    	
    	catch(Exception e) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Warning Dialog");
    		alert.setContentText("Cannot open Ordering Donuts.");
    		alert.showAndWait();
    		return;
    	}
    }

    /**
   	This method loads the OrderView.fxml so the Store Order GUI can be presented. 
   	@param event upon selecting the Your Order button
   	*/
    @FXML
    void callYourOrder(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderView.fxml"));
    		Parent root3 = loader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Your Order");
    		stage.setScene(new Scene(root3));
    		stage.show();	
    	}
    	
    	catch(Exception e) {
    		
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Warning Dialog");
    		alert.setContentText("Cannont open Your Order.");
    		alert.showAndWait();
    		return;
    	}
    }
    
    /**
   	This method loads the StoreOrderView.fxml so the Store Order GUI can be presented. 
   	@param event upon selecting the Store Order button
   	*/
    @FXML
    void callStoreOrders(ActionEvent event) {
    	
    	try {
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrderView.fxml"));
    		Parent root4 = loader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Store Orders");
    		stage.setScene(new Scene(root4));
    		stage.show();	
    	}
    	
    	catch(Exception e) {
    		
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Warning Dialog");
    		alert.setContentText("Cannont open store orders");
    		alert.showAndWait();
    		return;
    	}
    }
}


