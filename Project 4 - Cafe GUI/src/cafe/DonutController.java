package cafe;

import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

/**
The DonutController class takes care of the  DonutView.fxml .
The DonutView allows the user to choose their typeOfDonut, flavorOfDonut, and quantity from the comboBox. 
The DonutView also allows the user to add or remove the donuts the don't want. 
as adding their items to the order.
@author Prachiti Atigre, Ujani Patel
*/
public class DonutController {

    @FXML
    private ComboBox<String> donutType;

    @FXML
    private ListView<String> selectDonutFlavor; //Left List View

    @FXML
    private ListView<MenuItem> finalDonut; //Right List View

    @FXML
    private ComboBox<Integer> donutsQuantity;

    @FXML
    private Button removeDonuts, addDonuts;
    
    @FXML
    private TextArea subTotalDonuts;

    private double amount;

    DecimalFormat df = new DecimalFormat("#,##0.00");
    private static final int ONE = 1, TWO = 2, THREE = 3, FOUR = 4, FIVE = 5, SIZE_GREATER_THAN_ZERO = 0;
    private static final double ZERO_AMOUNT = 0.0;
    
    /**
	This method creates an observableList of type String to store the donutTyes and Quantity of the Donut 
	from the comboBox so that the user can pick their donutType, flavor and quantity of donut.
	*/
    @FXML
    public void initialize() {
    
    	ObservableList<String> donutTypes = 
    			FXCollections.observableArrayList("Yeast", "Cake", "Donut Hole");
    	donutType.setItems(donutTypes);
    	
    	ObservableList<Integer> quantity = 
    			FXCollections.observableArrayList(ONE, TWO, THREE, FOUR, FIVE);
    	donutsQuantity.setItems(quantity); 	
    }
    
    /**
	This method add's the Donut to the listView where the user can see what they currently ordered.
	The method adds the typeofDonut, flavor and quantity that user chose onto the viewList 
	it also dynamically calculates the total price of the donuts according to donutType on the textArea with
	the help of the donutClass
	@param event upon selecting donut,flavor and quantity
    */
    @FXML
    void addDonuts(ActionEvent event) {
    	
    	int quantity;
    	if(donutsQuantity.getValue() == null) {
			quantity = ONE; 
		}
    	else {
    		quantity = donutsQuantity.getValue();
    	}
    	
    	String flavor = selectDonutFlavor.getSelectionModel().getSelectedItem();
    	
    	
    	if(flavor != null) {
    		
    		String typeOfDonut = donutType.getValue();
        	
    		Donut donut = new Donut(typeOfDonut, flavor, quantity);
    		donut.calculateItemPrice();
    		amount = amount + donut.getItemPrice();
    		
    		subTotalDonuts.clear();
    		subTotalDonuts.appendText("$" + df.format(amount));
    		
    		finalDonut.getItems().add(donut);
    	}
    }
    
    /**
  	This method removes Donuts that the user doesn't want anymore. 
  	This removes the DonutItem after the user selects the item they want to remove and
  	hit the remove button. 
  	This method then calls and calculateItem price and decrements the total cost. 
  	The price is updated and dynamically shown on the textArea.
  	@param event upon selecting the remove button.
  	*/
    @FXML
    void removeDonuts(ActionEvent event) {
    	
    	int quantity;
    	if(donutsQuantity.getValue() == null) {
			quantity = ONE; 
		}
    	else {
    		quantity = donutsQuantity.getValue();
    	}
    	
    	MenuItem flavor = finalDonut.getSelectionModel().getSelectedItem();
    	
    	if(flavor instanceof Donut) {
    		
    		Donut donutCheck = (Donut) flavor;
    	
    		String donutFlavor = donutCheck.getDonutFlavor();
        	String typeOfDonut = donutCheck.getDonutType();
    		
    		finalDonut.getItems().remove(flavor);
        	
    		Donut donut = new Donut(typeOfDonut, donutFlavor, quantity);
    		donut.calculateItemPrice();
    		amount = amount - donut.getItemPrice();
    		
    		subTotalDonuts.clear();
    		if(amount < 0) {
    			subTotalDonuts.appendText("$" + ZERO_AMOUNT);
    		}
    		else {
    			subTotalDonuts.appendText("$" + df.format(amount));
    		}
    		
    		finalDonut.getItems().remove(donut);
    	}
    }

    /**
  	This method sets the type of donut the user chose and implements the flavors of that 
  	donutType
  	@param event upon selecting a donutType	
    */
    @FXML
    void setDonutType(ActionEvent event) {
    	
    	if(donutType.getValue().equals("Yeast")) {
    		
    		selectDonutFlavor.getItems().clear();
    		selectDonutFlavor.getItems().addAll("Cinnamon", "Apple Crumb", "Jelly");
    		selectDonutFlavor.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	}
    	else if(donutType.getValue().equals("Cake")) {
    		
    		selectDonutFlavor.getItems().clear();
    		selectDonutFlavor.getItems().addAll("Blueberry", "Coconut", "Glazed");
    		selectDonutFlavor.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	}
    	else if(donutType.getValue().equals("Donut Hole")) {
    		
    		selectDonutFlavor.getItems().clear();
    		selectDonutFlavor.getItems().addAll("Chocolate", "Vanilla", "Strawberry");
    		selectDonutFlavor.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	}
    }

    /**
  	This method loads the OrderView.fxml so that information from the DonutView can be passed onto the 
  	OrderView. 
  	It passes the information of donutType, donutFlavor and quantity that the user ordered
  	in the form of observableLisinto the OrderView. 
  	@param event, upon the event of selecting the add to order button to add the donut Order. 
  	*/
    @FXML
    void addDonutToOrder(ActionEvent event) {

    	try {
    		
    		Order order = MainMenuController.getOrder();
        	ObservableList<MenuItem> donuts = finalDonut.getItems();
        	
        	if(donuts.size() > SIZE_GREATER_THAN_ZERO) {
                
        		for(int i = 0; i < donuts.size(); i++) {
        			
        			MenuItem item = (MenuItem) donuts.get(i);
        			order.add(item);
        		}
        	}
        	
        	new Alert(Alert.AlertType.CONFIRMATION, "Donut added to order").show();
        	
        	finalDonut.getItems().clear();
        	selectDonutFlavor.getItems().clear();
        	donutType.getSelectionModel().selectFirst();
        	selectDonutFlavor.getItems().clear();
    		selectDonutFlavor.getItems().addAll("Cinnamon", "Apple Crumb", "Jelly");
        	donutsQuantity.setValue(1);
        	subTotalDonuts.clear();
    	}
    	catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Enter all fields correctly").show();
        }
    	
    }
}

