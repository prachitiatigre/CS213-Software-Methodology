package com.example.rutgerscafe;

import java.util.ArrayList;

/**
 * This Order class implements the Customizable interface. This class takes care of the orders
 * from the user as well as tracking the prices, and orders to be added and removed.
 * @author Prachiti Atigre, Ujani Patel
 */
public class Order implements Customizable {

    ArrayList<MenuItem> orderItems = new ArrayList<>();
    public static final double SALES_TAX_RATE = 0.06625;
    public static final double SET_TO_ZERO = 0;
    int orderNum;

    /**
     * Constructor that initializes an array-list to store the order items
     */
    public Order() {
        this.orderItems = new ArrayList<>();
    }

    /**
     * This method checks if the obj passed is an instance of the Donut or String Object.
     * If it is, it adds the coffee or donut to the order
     * @param obj The menu item object to be added
     * @return true if item is added to the order, false otherwise
     */
    @Override
    public boolean add(Object obj) {

        if(obj instanceof Donut) {
            Donut donut = (Donut) obj;
            this.orderItems.add(donut);
            return true;
        }
        else if(obj instanceof Coffee) {
            Coffee coffee = (Coffee) obj;
            this.orderItems.add(coffee);
            return true;
        }
        else
            return false;
    }

    /**
     * This method checks if the obj passed is an instance of the Donut or String Object.
     * If it is, it checks if the order contains that item.
     * If it does, it removes the item
     * @param obj The menu item object to be removed
     * @return true if item is removed to the order, false otherwise
     */
    @Override
    public boolean remove(Object obj) {

        if(obj instanceof Donut) {
            Donut donut = (Donut) obj;
            this.orderItems.remove(donut);
            return true;
        }
        else if(obj instanceof Coffee) {
            Coffee coffee = (Coffee) obj;
            this.orderItems.remove(coffee);
            return true;
        }
        else
            return false;
    }

    /**
     * This method calculates and returns the order total excluding taxes
     * @return The total price of the order excluding taxes
     */
    public double calculateOrderTotal() {

        ArrayList<MenuItem> list = this.orderItems;

        double orderTotal = SET_TO_ZERO;

        for(int i = 0; i < list.size(); i++) {
            orderTotal = orderTotal + list.get(i).getItemPrice();
        }
        return orderTotal;
    }

    /**
     * This method calculates and returns the sales tax of the order
     * @return The sales tax of the order
     */
    public double calculateSalesTax() {
        return SALES_TAX_RATE * this.calculateOrderTotal();
    }

    /**
     * This method calculates the total payment of the order (i.e. item price + sales tax)
     * @return The total payment of the order (i.e. item price + sales tax)
     */
    public double calculatePayment() {

        double payment = this.calculateOrderTotal() + this.calculateSalesTax();
        return payment;
    }


    /**
     * Getter method to get the number of items in an order
     * @return The number of items in an order
     */
    public int getNumOfItemsInOrder(){
        return this.orderItems.size();
    }

    /**
     * Getter method to get the list of items in an order
     * @return The list of items in an order
     */
    public ArrayList<MenuItem> getOrderItemsList() {
        return this.orderItems;
    }

    /**
     * This method formats the list of order items into a string format
     * @return The order number, order total, subtotal, and sales tax of an order
     * in the string format
     */
    @Override
    public String toString() {

        String output = "";
        for(int i = 0; i < this.orderItems.size(); i++) {

            if(this.orderItems.get(i) instanceof MenuItem) {
                MenuItem item = (MenuItem) this.orderItems.get(i);

                if (item instanceof Donut) {

                    Donut d = (Donut) item;
                    output = output + d.toString() + "\n";
                }
                else {
                    Coffee c = (Coffee) item;
                    output = output + c.toString() + "\n";
                }

            }
        }

        double orderPayment = this.calculatePayment();
        double orderTotal = this.calculateOrderTotal();
        double orderSalesTax = this.calculateSalesTax();

        return "Order #" + this.orderNum + "\n" + output + "Order Total: $" + String.format("%.2f", orderPayment) + "\n" +
                "(Subtotal: $" + String.format("%.2f", orderTotal) +
                ", Sales Tax: $" + String.format("%.2f", orderSalesTax) + ")";
    }

    /**
     * Removes all items from the list of order items when an order is removed and
     * recalculates the payments
     */
    public void removeAllItems() {
        orderItems.clear();
        calculatePayment();
    }

    /**
     * Setter method to set the order number of an order
     * @param orderNum The order number
     */
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * Getter method to get the order number of an order
     * @return The order number of an order
     */
    public int getOrderNum() {
        return this.orderNum;
    }
}
