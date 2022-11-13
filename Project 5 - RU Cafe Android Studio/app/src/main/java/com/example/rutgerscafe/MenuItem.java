package com.example.rutgerscafe;

/**
 * This is the superclass of all menu items, such as donuts and coffee
 * Any class defined for a menu item must extend this class.
 * All the subclasses must include a “itemPrice” method for calculating the price of the menu item.
 * @author Prachiti Atigre, Ujani Patel
 */
public class MenuItem {

    private double itemPrice;

    /**
     * Constructor that sets the item price
     * @param itemPrice The price of the menu item (Donut or Coffee)
     */
    public MenuItem(double itemPrice){
        this.itemPrice = itemPrice;
    }

    /**
     * Getter method to get the price of the menu item
     * @return The price of the menu item
     */
    public double getItemPrice() {
        return this.itemPrice;
    }

    /**
     * Setter method to set the price of the menu item
     * @param itemPrice The price of the menu item
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}


