package com.example.rutgerscafe;
import java.util.ArrayList;

/**
 * An instance of this class is a menu item in an order. This class must extend MenuItem class
 * and implement the Customizable interface. This class also takes care of the size of the coffee,
 * addIns, and price.
 * @author Prachiti Atigre, Ujani Patel
 */
public class Coffee extends MenuItem implements Customizable {

    private ArrayList<String> addins;
    private String size;
    private int quantity;
    private static final double EMPTY = 0;
    private static final double SET_TO_ZERO = 0;
    private static final int DEFAULT_QUANTITY = 1;
    private static final double DEFAULT_COFFEE_PRICE = 1.99;
    private static final double NO_ADDINS_COST = 0.0, ADDINS_COST = 0.2;
    private static final double SHORT_COST = 1.99, TALL_COST = 2.49, GRANDE_COST = 2.99, VENTI_COST = 3.49;

    /**
     * This constructor calls the super class and sets the default coffee price and also initializes the quantity, size
     * and an array-list for the add-ins
     */
    public Coffee() {
        super(DEFAULT_COFFEE_PRICE);
        this.quantity = DEFAULT_QUANTITY;
        this.size = "Short";
        this.addins = new ArrayList<String>();
    }

    /**
     * Getter method for getting the coffee add-ins
     * @return The coffee addins
     */
    public ArrayList<String> getAddIns() {
        return this.addins;
    }

    /**
     * Setter method for setting the the size of the coffee
     * @param size The size of the coffee
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Getter method for getting the size of the coffee
     * @return The size of the coffee
     */
    public String getSize() {
        return this.size;
    }

    /**
     * Setter method for setting the quantity of coffee
     * @param quantity The quantity of coffee
     */
    public void setCoffeeQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter method for getting the quantity of coffee
     * @return The quantity of coffee
     */
    public int getCoffeeQuantity() {
        return this.quantity;
    }

    /**
     * This method calculates and returns the price of the coffee size
     * @return The cost of the coffee based on size
     */
    public double calculateSizePrice() {

        String coffeeSize = this.getSize();
        double sizeCost = SET_TO_ZERO;

        if(coffeeSize != null) {

            if(coffeeSize.equals("Short")) {
                sizeCost = SHORT_COST;
            }
            else if(coffeeSize.equals("Tall")) {
                sizeCost = TALL_COST;
            }
            else if(coffeeSize.equals("Grande")) {
                sizeCost = GRANDE_COST;
            }
            else if(coffeeSize.equals("Venti")) {
                sizeCost = VENTI_COST;
            }
        }
        return sizeCost;
    }

    /**
     * This method calculates and returns the price based on the coffee add-ins
     * @return The cost of the coffee based on add-ins
     */
    public double calculateAddInsPrice() {

        ArrayList<String> coffeeAddins = this.getAddIns();
        double costOfAddIns = SET_TO_ZERO;

        if(coffeeAddins != null) {
            costOfAddIns = coffeeAddins.size() * ADDINS_COST;
        }
        else {
            costOfAddIns = NO_ADDINS_COST;
        }

        return costOfAddIns;
    }

    /**
     * This method calculates and returns the price of the coffee
     * @return The price of the coffee
     */
    @Override
    public double getItemPrice() {

        double itemPrice = SET_TO_ZERO;
        double total = SET_TO_ZERO;

        double coffeeSizePrice = this.calculateSizePrice();
        double coffeeAddInsPrice = this.calculateAddInsPrice();
        itemPrice = coffeeSizePrice + coffeeAddInsPrice;

        total = itemPrice * this.getCoffeeQuantity();

        super.setItemPrice(total);
        return super.getItemPrice();
    }

    /**
     * This method checks if the obj passed is an instance of the String Object.
     * It adds the coffee add-in when it's true
     * @param obj The add-in that needs to be added to the coffee order
     * @return false if obj is not an instance of the String Object or when it's not an add-in, otherwise returns true
     */
    @Override
    public boolean add(Object obj) {

        ArrayList<String> coffeeAddIns = this.getAddIns();

        if(obj instanceof String) {
            String addIn = (String) obj;

            if(addIn.equals("Cream") || addIn.equals("Syrup") || addIn.equals("Milk") ||
                    addIn.equals("Caramel") || addIn.equals("Whipped Cream")) {
                coffeeAddIns.add(addIn);
                return true;
            }
            else return false;
        }
        else return false;
    }

    /**
     * This method checks if the obj passed is an instance of the String Object.
     * It removes the addIn when it's true
     * @param obj The add-in that needs to be removed to the coffee order
     * @return false if obj is not an instance of the String Object or when it's not an add-in, otherwise returns true
     */
    @Override
    public boolean remove(Object obj) {

        ArrayList<String> coffeeAddIns = this.getAddIns();

        if(obj instanceof String) {
            String addIn = (String) obj;

            if(addIn.equals("Cream") || addIn.equals("Syrup") || addIn.equals("Milk") ||
                    addIn.equals("Caramel") || addIn.equals("Whipped Cream")) {
                coffeeAddIns.remove(addIn);
                return true;
            }
            else return false;
        }
        else return false;
    }

    /**
     * This method creates a string format for the the add-in, quantity and size of coffee
     * @return the string format of coffee, quantity and addIn when the order has add-ins otherwise
     * returns the string format of coffee and quantity
     */
    @Override
    public String toString() {

        ArrayList<String> coffeeAddIns = this.getAddIns();
        String coffeeSize = this.getSize();
        int quantity = this.getCoffeeQuantity();
        double itemPrice = this.getItemPrice();

        if(coffeeAddIns.size() == EMPTY) {
            return "Coffee (" + quantity + ") " + coffeeSize + " $" + String.format("%.2f", itemPrice);
        }
        else {
            return "Coffee (" + quantity + ") " + coffeeSize + " " + coffeeAddIns + " $" + String.format("%.2f", itemPrice);
        }
    }
}
