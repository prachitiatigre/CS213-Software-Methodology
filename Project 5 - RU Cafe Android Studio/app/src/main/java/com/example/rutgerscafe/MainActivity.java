package com.example.rutgerscafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * This class must extends the  AppCompatActivity.
 * The MainActivity class takes care of the  activity_main.xml
 * @author Prachiti Atigre, Ujani Patel
 */
public class MainActivity extends AppCompatActivity {

    private static Order currentOrder = new Order();
    private static StoreOrders storeOrder = new StoreOrders();

    /**
     * Getter method to get the current order
     * @return The current order object
     */
    public static Order getOrder() {
        return currentOrder;
    }

    /**
     * Setter method to set the current order
     * @param order The current order object
     */
    public static void setOrder(Order order) {
        currentOrder = order;
    }

    /**
     * Getter method to get all the store orders
     * @return The store order object
     */
    public static StoreOrders getStoreOrders() {
        return storeOrder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Upon clicking Order Coffee button, the main activity window navigates to coffee activity window
     * @param view The view that was clicked.
     */
    public void navigateCoffeeOrder(View view) {
        Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
        Toast.makeText(MainActivity.this, "Order Coffee", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    /**
     * Upon clicking the Order Donut button, the main activity window navigates to donut activity window
     * @param view The view that was clicked.
     */
    public void navigateDonutOrder(View view) {
        Intent intent = new Intent(MainActivity.this, DonutActivity.class);
        Toast.makeText(MainActivity.this, "Select Donut Flavor", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    /**
     * Upon clicking the Your Order Donut button, the main activity window navigates to your order activity window
     * @param view The view that was clicked
     */
    public void navigateYourOrder(View view) {
        Intent intent = new Intent(MainActivity.this, YourOrderActivity.class);
        Toast.makeText(MainActivity.this, "View Your Order", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    /**
     * Upon clicking the Store Orders Donut button, the main activity window navigates to store orders activity window
     * @param view The view that was clicked
     */
    public void navigateStoreOrder(View view) {
        Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
        Toast.makeText(MainActivity.this, "View Store Orders", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
