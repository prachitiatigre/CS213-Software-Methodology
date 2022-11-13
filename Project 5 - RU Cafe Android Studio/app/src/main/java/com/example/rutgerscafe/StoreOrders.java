package com.example.rutgerscafe;

import java.util.ArrayList;

/**
 * An instance of this class keeps the list of orders placed by the user. This class must
 * implement the Customizable interface above
 * @author Prachiti Atigre, Ujani Patel
 */
public class StoreOrders implements Customizable {

    private int count = 1;
    private static final int INCREMENT_ONE = 1;
    ArrayList<Order> orders = new ArrayList<>();

    /**
     * This body of order constructor is taking an ArrayList which will be used
     * by the StoreOrdersActivity to store orders of the store
     */
    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    /**
     * Getter method to get the total payment of the order
     * @param i The array-list index
     * @return The total payment of the order at index i
     */
    public double getPayment(int i){
        return this.orders.get(i).calculatePayment();
    }

    /**
     * Getter method to get the order at index i
     * @param i the array-list index
     * @return The order at index i
     */
    public Order getOrder(int i){
        return this.orders.get(i);
    }

    /**
     * Getter method to get the number of store orders
     * @return The number of store orders
     */
    public int getNumOfOrders(){
        return this.orders.size();
    }

    /**
     * Getter method to get the number of orders added to store to
     * set the order number of the order
     * @return The number of orders added to store
     */
    public int getOrderItemsCount() {
        return count;
    }

    /**
     * This method checks if the obj passed is an instance of the Order Object
     * If it is, it gets added to the the Order list
     * @param obj The order object
     * @return true if order is added, false otherwise
     */
    @Override
    public boolean add(Object obj) {

        if(obj instanceof Order) {
            ArrayList<Order> orderItems = this.orders;
            Order order = (Order) obj;
            orderItems.add(order);
            count = count + INCREMENT_ONE;
            return true;
        }
        else
            return false;
    }

    /**
     * This method checks if the obj pass is an instance of the Order Object
     * If it is, it gets removed from the Order List
     * @param obj The order object
     * @return true if the order is removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {

        if(obj instanceof Order) {
            ArrayList<Order> orderItems = this.orders;
            Order order = (Order) obj;
            if(orderItems.contains(order)){
                orderItems.remove(order);
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
}
