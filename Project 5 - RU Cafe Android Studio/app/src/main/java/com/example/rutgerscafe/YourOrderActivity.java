package com.example.rutgerscafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class must extend AppCompatActivity
 * The YourOrderActivity class takes care of the activity_your_order.xml
 * It includes the list of items a customer ordered and they can remove
 * the items they'd like to remove.
 * @author Prachiti Atigre, Ujani Patel
 */
public class YourOrderActivity extends AppCompatActivity {

    private ListView list;
    private Button placeOrder;
    private TextView orderSubtotal, orderSalesTax, orderPayment;
    ArrayAdapter<MenuItem> adapter;
    ArrayList<MenuItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);

        list = findViewById(R.id.orderlist);

        Order currentOrder = MainActivity.getOrder();

        adapter = new ArrayAdapter<MenuItem>(YourOrderActivity.this, android.R.layout.simple_list_item_1, items);
        list.setAdapter(adapter);
        int size = currentOrder.getNumOfItemsInOrder();

        for(int i = 0; i < size; ++i) {
            items.add(currentOrder.getOrderItemsList().get(i));
        }

        orderSubtotal = findViewById(R.id.orderSubtotal);
        orderSalesTax = findViewById(R.id.orderSalesTax);
        orderPayment = findViewById(R.id.orderPayment);

        orderSubtotal.setText("$" + String.format("%.2f", currentOrder.calculateOrderTotal()));
        orderSalesTax.setText("$" + String.format("%.2f", currentOrder.calculateSalesTax()));
        orderPayment.setText("$" + String.format("%.2f", currentOrder.calculatePayment()));

        placeOrder = findViewById(R.id.addToStore);
        placeOrder.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates on the onClick method for the placeOrder Button.
             * The  orders are taken from the Order class and is then added into the Store Order Class.
             * Upon clicking the place to Order button, the YourOrderActivity page navigates to YourActivity page
             * @param v The view that was clicked
             */
            @Override
            public void onClick(View v) {

                if(adapter.isEmpty()) {
                    Toast.makeText(YourOrderActivity.this, "Error! There are no items in your order", Toast.LENGTH_SHORT).show();
                }

                else {
                    Order currentOrder = MainActivity.getOrder();
                    StoreOrders storeorders = MainActivity.getStoreOrders();

                    int count = storeorders.getOrderItemsCount();
                    currentOrder.setOrderNum(count);
                    storeorders.add(currentOrder);

                    Order newOrder = new Order();
                    MainActivity.setOrder(newOrder);
                    currentOrder = MainActivity.getOrder();
                    currentOrder.removeAllItems();

                    Intent intent = new Intent(YourOrderActivity.this, YourOrderActivity.class);
                    startActivity(intent);
                    Toast.makeText(YourOrderActivity.this, "Order placed successfully.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * This method creates the onsetItemClickListener method for the ListView.
             * Upon clicking an item to remove from the order, the user is asked
             * if they want to remove their order or not.
             * @param parent When an item in this AdapterView is clicked, this method is invoked.
             * @param view The view that was clicked withing the AdapterView.
             * @param position The position of the view in the adapter.
             * @param id The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order currOrder = MainActivity.getOrder();
                final int selectedItem = position;
                new AlertDialog.Builder(YourOrderActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to remove this item from your order?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            /**
                             * Upon selecting an item to remove, the user is asked for confirmation
                             * upon selecting yes, the user's order is removed.
                             * It also outputs the subtotal, salesTax and total Payment.
                             * @param dialog The dialog that received the click
                             * @param which The option that was selected
                             */
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                items.remove(currOrder.getOrderItemsList().get(selectedItem));
                                currOrder.remove(currOrder.getOrderItemsList().get(selectedItem));
                                adapter.notifyDataSetChanged();

                                orderSubtotal = findViewById(R.id.orderSubtotal);
                                orderSalesTax = findViewById(R.id.orderSalesTax);
                                orderPayment = findViewById(R.id.orderPayment);

                                orderSubtotal.setText("$" + String.format("%.2f", currentOrder.calculateOrderTotal()));
                                orderSalesTax.setText("$" + String.format("%.2f", currentOrder.calculateSalesTax()));
                                orderPayment.setText("$" + String.format("%.2f", currentOrder.calculatePayment()));
                            }
                })
                .setNegativeButton("No", null)
                .show();
            }
        });
    }
}