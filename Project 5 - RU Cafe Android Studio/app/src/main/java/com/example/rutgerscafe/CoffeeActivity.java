package com.example.rutgerscafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

/**
 * This class must extend the AppCompatActivity and implements the AdapterView.OnItemSelectedListener
 * The CoffeeActivity  class takes care of the  activity_coffee.xml.
 * It allows the user to choose their size of coffee, add-ins, and also remove the add-ins
 * as adding their items to the order.
 * @author Prachiti Atigre, Ujani Patel
 */
public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final double SET_TO_ZERO = 0;

    private CheckBox cream, milk, syrup, caramel, whippedCream;
    private RadioButton selectedCoffeeSize;
    private RadioButton shortCoffee;
    private Spinner quantitySpinner;
    private TextView subtotalCoffee;
    private RadioGroup coffeeSize;
    private Button addCoffee;
    private Coffee coffee;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        coffee = new Coffee();

        milk = findViewById(R.id.milk);
        cream = findViewById(R.id.cream);
        syrup = findViewById(R.id.syrup);
        caramel = findViewById(R.id.caramel);
        whippedCream = findViewById(R.id.whippedCream);

        addCoffee = (Button) findViewById(R.id.addCoffee);
        subtotalCoffee = findViewById(R.id.subtotalCoffee);
        quantitySpinner = findViewById(R.id.quantity_spinner);
        coffeeSize = (RadioGroup) findViewById(R.id.coffeeSizeGroup);

        shortCoffee = findViewById(R.id.shortButton);
        shortCoffee.setChecked(true);

        milk.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates on the onClick method for the CheckBox button.
             * If cream is checked, the processAdd method is called to add cream add-in,
             * else the processRemove method is called to remove the cream add-in
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if(milk.isChecked()) {
                    processAdd("Milk");
                    Toast.makeText(CoffeeActivity.this, "Milk is added.", Toast.LENGTH_SHORT).show();
                }
                else {
                    processRemove("Milk");
                    Toast.makeText(CoffeeActivity.this, "Milk is removed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        syrup.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates the onClick method for the CheckBox button.
             * If milk is checked, the processAdd method is called to add milk add-in,
             * else the processRemove method is called to remove the milk add-in
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if(syrup.isChecked()) {
                    processAdd("Syrup");
                    Toast.makeText(CoffeeActivity.this, "Syrup is added.", Toast.LENGTH_SHORT).show();
                }
                else {
                    processRemove("Syrup");
                    Toast.makeText(CoffeeActivity.this, "Syrup is removed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cream.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates the onClick method for the CheckBox button.
             * If syrup is checked, the processAdd method is called to add syrup add-in,
             * else the processRemove method is called to remove the syrup add-in
             @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if(cream.isChecked()) {
                    processAdd("Cream");
                    Toast.makeText(CoffeeActivity.this, "Cream is added.", Toast.LENGTH_SHORT).show();
                }
                else {
                    processRemove("Cream");
                    Toast.makeText(CoffeeActivity.this, "Cream is removed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        caramel.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates the onClick method for the CheckBox button.
             * If caramel is checked, the processAdd method is called to add caramel add-in,
             * else the processRemove method is called to remove the caramel add-in
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if(caramel.isChecked()) {
                    processAdd("Caramel");
                    Toast.makeText(CoffeeActivity.this, "Caramel is added.", Toast.LENGTH_SHORT).show();
                }
                else {
                    processRemove("Caramel");
                    Toast.makeText(CoffeeActivity.this, "Caramel is removed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        whippedCream.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates the onClick method for the CheckBox button.
             * If whipped cream is checked, the processAdd method is called to add whipped cream add-in,
             * else the processRemove method is called to remove the whipped cream add-in
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if(whippedCream.isChecked()) {
                    processAdd("Whipped Cream");
                    Toast.makeText(CoffeeActivity.this, "Whipped Cream is added.", Toast.LENGTH_SHORT).show();
                }
                else {
                    processRemove("Whipped Cream");
                    Toast.makeText(CoffeeActivity.this, "Whipped Cream is removed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addCoffee.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates  the onClick method for adding Coffee
             * It creates an Order object and adds the coffee to the order.
             * Upon clicking the ad to Order button, it navigates the activity_coffee page to activity_main
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {

                Order currOrder = MainActivity.getOrder();
                currOrder.add(coffee);

                Intent intent = new Intent(CoffeeActivity.this, CoffeeActivity.class);
                startActivity(intent);
                Toast.makeText(CoffeeActivity.this, "Coffee added to order", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * The ArrayAdapter help implement the spinner through which the user can select their
         * choice of coffee quantity
         */
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.coffee_quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);
        quantitySpinner.setOnItemSelectedListener(this);
    }

    /**
     * This method creates the onItemSelected method for spinner.
     * The num variable stores the value selected from the spinner and is then called on the
     * processQuantity method.
     * @param parent When an item in this AdapterView is clicked, this method is invoked.
     * @param view The view that was clicked withing the AdapterView.
     * @param position The position of the view in the adapter.
     * @param id The row id of the item that was clicked.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String quantity = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Quantity: " + quantity, Toast.LENGTH_SHORT).show();

        num = Integer.parseInt(parent.getItemAtPosition(position).toString());
        processQuantity(num);
    }

    /**
     * When the selection disappears from this view, this method is invoked.
     * @param parent The AdapterView that contains no selected item now.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * The onClickRadio method is performed when a radioButton for size of the coffee is chosen
     * by the user.
     * The size of the Coffee determined by the section of radioButton and is then called
     * on the processSize method.
     * @param view The view that was clicked.
     */
    public void onClickRadio(View view) {

        int radioButtonID = coffeeSize.getCheckedRadioButtonId();
        selectedCoffeeSize = (RadioButton) findViewById(radioButtonID);

        Toast.makeText(getBaseContext(), "Size: " + selectedCoffeeSize.getText(), Toast.LENGTH_LONG).show();

        if (selectedCoffeeSize.getText().equals("Short")) {
            processSize("Short");
        }
        else if (selectedCoffeeSize.getText().equals("Tall")) {
            processSize("Tall");
        }
        else if (selectedCoffeeSize.getText().equals("Grande")) {
            processSize("Grande");
        }
        else if (selectedCoffeeSize.getText().equals("Venti")) {
            processSize("Venti");
        }
    }

    /**
     * This method processes the size of the coffee that the user selected and it's cost.
     * The price is displayed dynamically on the textView.
     * @param size The size of the coffee
     */
    private void processSize(String size) {

        coffee.setSize(size);
        coffee.setItemPrice(SET_TO_ZERO);
        subtotalCoffee.setText("$" + String.format("%.2f", coffee.getItemPrice()));
    }

    /**
     * This method checks if the unselected checkBox is true and then calls the calculateItemPrice
     * the price is displayed dynamically on the textView.
     * @param addIn The coffee add-in
     */
    private void processRemove(String addIn) {

        coffee.setItemPrice(SET_TO_ZERO);
        boolean check = coffee.remove(addIn);

        if (check == true) {
            subtotalCoffee.setText("$" + String.format("%.2f", coffee.getItemPrice()));
        }
    }

    /**
     * This method checks if the selected checkBox is true and then calls the calculateItemPrice
     * the price is displayed dynamically on the textView.
     * @param addIn The coffee add-in
     */
    private void processAdd(String addIn) {

        coffee.setItemPrice(SET_TO_ZERO);
        boolean check = coffee.add(addIn);

        if (check == true) {
            subtotalCoffee.setText("$" + String.format("%.2f", coffee.getItemPrice()));
        }
    }

    /**
     * This method processes the quantity of the coffee that the user selected and it's cost.
     * The price is displayed dynamically on the textView.
     * @param num The quantity of coffee
     */
    private void processQuantity(int num) {

        coffee.setItemPrice(SET_TO_ZERO);
        coffee.setCoffeeQuantity(num);
        subtotalCoffee.setText("$" + String.format("%.2f", coffee.getItemPrice()));
    }
}