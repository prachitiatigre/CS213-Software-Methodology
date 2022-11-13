package com.example.rutgerscafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class must extend AppCompatActivity
 * The StoreOrdersActivity class takes care of the activity_store_order.xml
 * It includes the list of items orders by individual customers
 * @author Prachiti Atigre, Ujani Patel
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private ListView list;
    ArrayAdapter<Order> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);

        StoreOrders storeorders = MainActivity.getStoreOrders();

        list = findViewById(R.id.storeorders);

        ArrayList<Order> items = new ArrayList<>();
        adapter = new ArrayAdapter<Order>(StoreOrdersActivity.this, android.R.layout.simple_list_item_1, items);
        list.setAdapter(adapter);

        int size = storeorders.getNumOfOrders();

        for(int i = 0; i < size; i++) {
            items.add(storeorders.getOrder(i));
        }

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

                StoreOrders storeorders = MainActivity.getStoreOrders();
                final int selectedItem = position;

                new AlertDialog.Builder(StoreOrdersActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to remove this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            /**
                             * Upon selecting an oder to remove, the user is asked for confirmation
                             * upon selecting yes, the user's order is removed.
                             * @param dialog The dialog that received the click
                             * @param which The option that was selected
                             */
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                items.remove(storeorders.getOrder(selectedItem));
                                storeorders.remove(storeorders.getOrder(selectedItem));
                                adapter.notifyDataSetChanged();
                                Toast.makeText(StoreOrdersActivity.this, "Order Removed", Toast.LENGTH_SHORT).show();
                            }
                })
                .setNegativeButton("No", null)
                .show();
            }
        });
    }
}