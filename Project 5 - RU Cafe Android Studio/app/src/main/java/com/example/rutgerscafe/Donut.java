package com.example.rutgerscafe;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * An instance of this class is a menu item in an order. This class must extend MenuItem class.
 * The Donut class handles the donutType, donutFlavor and quantity that the user chooses.
 * It also performs the calculation of the donuts the user has ordered.
 * @author Prachiti Atigre, Ujani Patel
 */
public class Donut extends MenuItem {

    private int donutQuantity;
    private String donutFlavor;

    private static final double DONUT_COST = 1.39;
    private static final double DEFAULT_ITEM_PRICE = 0.0;

    /**
     * This constructor calls the super class and sets the default donut price
     */
    public Donut() {
        super(DEFAULT_ITEM_PRICE);
    }

    /**
     * Setter method to set the donut flavor
     * @param donutFlavor The flavor of the donut
     */
    public void setDonutFlavor(String donutFlavor) {
        this.donutFlavor = donutFlavor;
    }

    /**
     * Getter method to get the donut flavor
     * @return The flavor of the donut
     */
    public String getDonutFlavor() {
        return this.donutFlavor;
    }

    /**
     * Setter method to set the quantity of donut
     * @param donutQuantity The quantity of donut
     */
    public void setDonutQuantity(int donutQuantity) {
        this.donutQuantity = donutQuantity;
    }

    /**
     * Getter method to get the quantity of donut
     * @return The quantity of donut
     */
    public int getDonutQuantity() {
        return this.donutQuantity;
    }

    /**
     * Calculates and returns the price of donut
     * @return The price of donut
     */
    @Override
    public double getItemPrice() {
        double total = DONUT_COST * this.getDonutQuantity();
        super.setItemPrice(total);
        return super.getItemPrice();
    }

    /**
     * This method creates a string format for the quantity of donut and it's flavor
     * @return The donut flavor, quantity and price in string format
     */
    @Override
    public String toString() {
        return this.getDonutFlavor() + " (" + this.getDonutQuantity() + ")" + " $" + String.format("%.2f", this.getItemPrice());
    }
}

