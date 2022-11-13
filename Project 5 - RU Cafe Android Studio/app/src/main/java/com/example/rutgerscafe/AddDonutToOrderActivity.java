package com.example.rutgerscafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class must extends the AppCompatActivity and implements the AdapterView.OnItemSelectedListener
 * The AddDonutToOrderActivity  class takes care of the activity_add_donut_to_order.xml
 * It allows the user to choose their quantity of donut for the flavor they chose
 * and adds it to the order
 * @author Prachiti Atigre, Ujani Patel
 */
public class AddDonutToOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int num;
    private double amount;
    private Button addDonut;
    private TextView printflavor, printAmount;
    private static double DONUT_COST = 1.39;
    private TextView printI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donut_to_order);

        String flavor = getIntent().getStringExtra("flavorname");
        printflavor = findViewById(R.id.donutFlavorName);
        printflavor.setText(flavor);

        Spinner quantitySpinner = (Spinner) findViewById(R.id.donutQuantity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.donut_quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);
        quantitySpinner.setOnItemSelectedListener(this);

        addDonut = findViewById(R.id.addDonut);
        addDonut.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates on the onClick method for the addDonut Button.
             * It creates an Order and Donut objects and sets the flavor and quantity by the user
             * and add the donut to the Order object.
             * Upon clicking the addDonut button, the AddDonutToOrderActivity page navigates to
             * DonutActivity page.
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {

                Order currOrder = MainActivity.getOrder();

                Donut donut = new Donut();
                donut.setDonutQuantity(num);
                donut.setDonutFlavor(flavor);
                currOrder.add(donut);

                Intent intent = new Intent(AddDonutToOrderActivity.this, DonutActivity.class);
                startActivity(intent);

                Toast.makeText(AddDonutToOrderActivity.this, flavor + " added to order.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This method creates the onItemSelected method for spinner for the donutQuantity.
     * The num variable stores the value selected from the spinner and is used to
     * calculate the payment of the Donut and prints out the amount on textView.
     * @param parent When an item in this AdapterView is clicked, this method is invoked.
     * @param view The view that was clicked withing the AdapterView.
     * @param position The position of the view in the adapter.
     * @param id The row id of the item that was clicked.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String quantity = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), quantity, Toast.LENGTH_SHORT).show();

        num = Integer.parseInt(parent.getItemAtPosition(position).toString());
        amount = num * DONUT_COST;

        printAmount = findViewById(R.id.donutPrice);
        printAmount.setText("$" + String.format("%.2f", amount));
    }

    /**
     * When the selection disappears from this view, this method is invoked.
     * @param parent The AdapterView that contains no selected item now.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}