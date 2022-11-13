package com.example.rutgerscafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * This class must extend  AppCompatActivity
 * The DonutActivity  class takes care of the  activity_donut.xml.It allows the user to choose their
 * flavor of donut.
 * @author Prachiti Atigre, Ujani Patel
 */
public class DonutActivity extends AppCompatActivity { //implements AdapterView.OnItemSelectedListener {

    private String flavor;
    private Button jelly, glazed, chocolate, vanilla, strawberry;

    private static final String jellyFlavor = "Jelly", glazedFlavor = "Glazed", chocolateFlavor = "Chocolate",
            vanillaFlavor = "Vanilla", strawberryFlavor = "Strawberry";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);

        jelly = findViewById(R.id.jellyButton);
        glazed = findViewById(R.id.glazedButton);
        chocolate = findViewById(R.id.chocolateButton);
        vanilla = findViewById(R.id.vanillaButton);
        strawberry = findViewById(R.id.strawberryButton);

        jelly.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates  the onClick method for adding jelly flavor donut
             * Upon clicking the Jelly button , it navigates the activity_donut page to
             * activity_add_donut_to_order page
             * @param v The view that was clicked
             */
            @Override
            public void onClick(View v) {
                flavor = jellyFlavor;
                Intent intent = new Intent(DonutActivity.this, AddDonutToOrderActivity.class);
                intent.putExtra("flavorname", flavor);
                startActivity(intent);
                Toast.makeText(DonutActivity.this, flavor, Toast.LENGTH_SHORT).show();
            }
        });


        glazed.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates  the onClick method for adding glazed flavor donut
             * Upon clicking the Glazed button , it navigates the activity_donut page to
             * activity_add_donut_to_order page
             * @param v The view that was clicked
             */
            @Override
            public void onClick(View v) {
                flavor = glazedFlavor;
                Intent intent = new Intent(DonutActivity.this, AddDonutToOrderActivity.class);
                intent.putExtra("flavorname", flavor);
                startActivity(intent);
                Toast.makeText(DonutActivity.this, flavor, Toast.LENGTH_SHORT).show();
            }
        });

        chocolate.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates  the onClick method for adding chocolate flavor donut
             * Upon clicking the Chocolate button , it navigates the activity_donut page to
             * activity_add_donut_to_order page
             * @param v The view that was clicked
             */
            @Override
            public void onClick(View v) {
                flavor = chocolateFlavor;
                Intent intent = new Intent(DonutActivity.this, AddDonutToOrderActivity.class);
                intent.putExtra("flavorname", flavor);
                startActivity(intent);
                Toast.makeText(DonutActivity.this, flavor, Toast.LENGTH_SHORT).show();
            }
        });

        vanilla.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates  the onClick method for adding vanilla flavor donut
             * Upon clicking the Vanilla button , it navigates the activity_donut page to
             * activity_add_donut_to_order page
             * @param v The view that was clicked
             */
            @Override
            public void onClick(View v) {
                flavor = vanillaFlavor;
                Intent intent = new Intent(DonutActivity.this, AddDonutToOrderActivity.class);
                intent.putExtra("flavorname", flavor);
                startActivity(intent);
                Toast.makeText(DonutActivity.this, flavor, Toast.LENGTH_SHORT).show();
            }
        });

        strawberry.setOnClickListener(new View.OnClickListener() {

            /**
             * This method creates  the onClick method for adding vanilla flavor donut
             * Upon clicking the Vanilla button , it navigates the activity_donut page to
             * activity_add_donut_to_order page
             * @param v The view that was clicked
             */
            @Override
            public void onClick(View v) {
                flavor = strawberryFlavor;
                Intent intent = new Intent(DonutActivity.this, AddDonutToOrderActivity.class);
                intent.putExtra("flavorname", flavor);
                startActivity(intent);
                Toast.makeText(DonutActivity.this, flavor, Toast.LENGTH_SHORT).show();
            }
        });
    }
}