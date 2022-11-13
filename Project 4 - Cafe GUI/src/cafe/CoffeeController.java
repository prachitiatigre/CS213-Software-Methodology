package cafe;

import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
The CoffeeController class takes care of the  CoffeView.fxml.
The CoffeView allows the user to choose their size of coffee, addIns, remove the addIns as well
as adding their items to the order.
@author Prachiti Atigre, Ujani Patel
*/
public class CoffeeController {

    @FXML
    private CheckBox cream, milk, whippedCream, syrup, caramel;
    
    @FXML
    private ComboBox<String> size;

    @FXML
    private ComboBox<Integer> quantity;

    @FXML
    private Button addCoffee;

    @FXML
    private TextArea coffeePrice;
    
    private Coffee coffee;
    DecimalFormat df = new DecimalFormat("#,##0.00");
    
    private static final double SET_TO_ZERO = 0.0;
    private static final int ONE = 1, TWO = 2, THREE = 3, FOUR = 4, FIVE = 5;
    
    /**
	This method creates an observableList of type String to store the coffeeSize and of type integer to store the Quantity of the coffee 
	from the comboBox so that the user can pick their size and quantity of coffee
	*/
    @FXML
    public void initialize() {
    	
    	ObservableList<String> coffeeSize = 
    			FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
    	size.setItems(coffeeSize);
    	
    	ObservableList<Integer> coffeeQuantity = 
    			FXCollections.observableArrayList(ONE, TWO, THREE, FOUR, FIVE);
    	quantity.setItems(coffeeQuantity);
    	
    	coffee = new Coffee();
    }
    
    /**
  	This method adds or removes the selected checkBox of the Caramel addIn.
  	If the check is present in the checkBox, Caramel is called by the "processAdd" method. 
  	If the check is unselected in the checkBox, Caramel is called but the "processRemove" method. 
  	@param event upon selecting the caramel checkBox
  	*/
    @FXML
    void caramelAddIn(ActionEvent event) {

    	if(caramel.isSelected() == true) {
    		
    		processAdd("Caramel");
    	}
    	else if(caramel.isSelected() == false) {
    		
    		processRemove("Caramel");
    	}
    }

    /**
  	This method adds or removes the selected checkBox of the cream addIn.
  	If the check is present in the checkBox, cream is called by the "processAdd" method. 
  	If the check is unselected in the checkBox, cream is called but the "processRemove" method. 
  	@param event upon selecting the cream checkBox
  	*/ 
    @FXML
    void creamAddIn(ActionEvent event) {

    	if(cream.isSelected() == true) {
    		
    		processAdd("Cream");
    	}
    	else if(cream.isSelected() == false) {
    		
    		processRemove("Cream");
    	}
    }

    /**
  	This method adds or removes the selected checkBox of the milk addIn.
  	If the check is present in the checkBox, milk is called by the "processAdd" method. 
  	If the check is unselected in the checkBox, milk is called but the "processRemove" method. 
  	@param event upon selecting the milk checkBox
  	*/
    @FXML
    void milkAddIn(ActionEvent event) {
    	
    	if(milk.isSelected() == true) {
    		
    		processAdd("Milk");
    	}
    	else if(milk.isSelected() == false) {
    		
    		processRemove("Milk");
    	}
    }

    /**
  	This method adds or removes the selected checkBox of syrup addIn.
  	If the check is present in the checkBox, syrup is called by the "processAdd" method. 
  	If the check is unselected in the checkBox, syrup is called but the "processRemove" method. 
  	@param event upon selecting the syrup checkBox
  	*/
    @FXML
    void syrupAddIn(ActionEvent event) {
    	
    	if(syrup.isSelected() == true) {
    		
    		processAdd("Syrup");
    	}
    	else if(syrup.isSelected() == false) {
    		
    		processRemove("Syrup");
    	}
    }

    /**
  	This method adds or removes the selected checkBox of the whippedCream addIn.
  	If the check is present in the checkBox, whippedCream is called by the "processAdd" method. 
  	If the check is unselected in the checkBox, whippedCream is called but the "processRemove" method. 
  	@param event upon selecting the whippedCream checkBox
  	*/
    @FXML
    void whippedCreamAddIn(ActionEvent event) {
    	
    	if(whippedCream.isSelected() == true) {
    		processAdd("Whipped Cream");
    	}
    	else if(whippedCream.isSelected() == false) {
    		processRemove("Whipped Cream");
    	}
    }
    
    /**
  	This method sets the size of the coffee the user chooses.
  	@param event upon selecting the size from the size comboBox
  	*/
    @FXML
    void setSize(ActionEvent event) {

    	if(size.getValue().equals("Short")) {
    		processSize("Short");
    	}
    	
    	else if(size.getValue().equals("Tall")) {
    		processSize("Tall");
    	}
    	
    	else if(size.getValue().equals("Grande")) {
    		processSize("Grande");
    	}
    	
    	else if(size.getValue().equals("Venti")) {
    		processSize("Venti");
    	}
    }
    
    /**
  	This method sets the quantity of the coffee the user chooses.
  	@param event upon selecting the quantity of coffee from the quantity comboBox
  	*/
    @FXML
    void setQuantity(ActionEvent event) {
    	
    	if(quantity.getValue() == ONE) {
    		processQuantity(ONE);
    	}
    	
    	else if(quantity.getValue() == TWO) {
    		processQuantity(TWO);
    	}
    	
    	else if(quantity.getValue() == THREE) {
    		processQuantity(THREE);
    	}
    	
    	else if(quantity.getValue() == FOUR) {
    		processQuantity(FOUR);
    	}
    	
    	else if(quantity.getValue() == FIVE) {
    		processQuantity(FIVE);
    	}
    	else
    		processQuantity(ONE);
    }
    
    /**
  	This method checks if the selected checkBox is true and then calls the calculateItemPrice 
  	the price is displayed dynamically on the textArea. 
  	@param name upon selecting the addIn checkBox.
  	*/
    public void processAdd(String name) {
    	
    	coffee.setCostOfAddIns(SET_TO_ZERO);
		coffee.setItemPrice(SET_TO_ZERO);
		boolean check = coffee.add(name);
		
		if(check == true) {
			
			coffee.calculateItemPrice();
			
			double payment = coffee.getItemPrice();
			coffeePrice.clear();
			coffeePrice.appendText("$" + df.format(payment));
		}
    }

    /**
  	This method checks if the unselected checkBox is true and then calls the calculateItemPrice 
  	the price is displayed dynamically on the textArea. 
  	@param name upon selecting the addIn checkBox.
  	*/
    public void processRemove(String name) {
    	
    	coffee.setCostOfAddIns(SET_TO_ZERO);
		coffee.setItemPrice(SET_TO_ZERO);
		boolean check = coffee.remove(name);
	
		if(check == true) {
			
			coffee.calculateItemPrice();
			
			double payment = coffee.getItemPrice();
			coffeePrice.clear();
			coffeePrice.appendText("$" + df.format(payment));
		}
    }
   
    /**
  	This method processes the size of the coffee that the user selected and it's cost.
  	The price is displayed dynamically on the textArea. 
  	@param name upon selecting the size of the coffee
  	*/ 
    public void processSize(String name) {
    	
    	coffee.setCostOfAddIns(SET_TO_ZERO);
		coffee.setItemPrice(SET_TO_ZERO);
    	coffee.setSize(name);
		coffeePrice.clear();
		coffee.calculateItemPrice();
		coffeePrice.appendText("$" + df.format(coffee.getItemPrice()));
    }
    
    /**
  	This method processes the quantity of the coffee that the user selected and it's cost.
  	The price is displayed dynamically on the textArea. 
  	@param num upon selecting the quantity of the coffee
  	*/
    public void processQuantity(int num) {
    	
    	coffee.setCostOfAddIns(SET_TO_ZERO);
		coffee.setItemPrice(SET_TO_ZERO);
    	coffee.setQuantity(num);
		coffeePrice.clear();
		coffee.calculateItemPrice();
		coffeePrice.appendText("$" + df.format(coffee.getItemPrice()));
    }
    
    /**
  	This method loads the OrderView.fxml so that information from the CoffeView can be passed onto the 
  	OrderView. 
  	It passes the information of addIns, cofeeSize, cofeeQuantity and the coffee that is to be ordered 
  	into the OrderView. 
  	@param event, upon the event of selecting the add to order button to add the coffee order. 
  	*/
    @FXML
    void addCoffeeOrder(ActionEvent event) {

    	try {
	    	Order order = MainMenuController.getOrder();
	    	order.add(coffee);
	    	
	        new Alert(Alert.AlertType.CONFIRMATION, "Coffee added to order").show();
	        
	        coffeePrice.clear();
	        size.getSelectionModel().selectFirst();
	        quantity.getSelectionModel().selectFirst();
	        cream.setSelected(false);
	        milk.setSelected(false);
	        whippedCream.setSelected(false);
	        syrup.setSelected(false);
	        caramel.setSelected(false);
	        
	        coffee = new Coffee();
	        coffeePrice.clear();
	        coffeePrice.appendText("$" + coffee.getCoffeeSizeCost());
	        
    	}
    	catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Enter all fields correctly").show();
        }
    }
}
