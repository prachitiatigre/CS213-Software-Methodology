package cafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class StoreOrderController {

    @FXML
    private ComboBox<Integer> orderNumber;

    @FXML
    private ListView<MenuItem> listOfStoreOrders;

    @FXML
    private Button exportOrder;

    @FXML
    private Button cancelOrder;

    @FXML
    private TextArea totalStoreOrderPrice;

    private static final int ONE = 1, TWO = 2, THREE = 3, FOUR = 4, FIVE = 5;
    
    @FXML
    public void initialize() {
    	
    	ObservableList<Integer> orderNumbers = 
    			FXCollections.observableArrayList(ONE, TWO, THREE, FOUR, FIVE);
    	orderNumber.setItems(orderNumbers);
    }
    
    /**
    Cancel a store order
    @param event
    */
    @FXML
    void cancelOrder(ActionEvent event) {

    }

    /**
	Exports the store orders
    */
    @FXML
    void exportOrder(ActionEvent event) {

    }

    /**
    Sets the order number of the order
    @param event
    */
    @FXML
    void setOrderNumber(ActionEvent event) {
    	
    }
}
