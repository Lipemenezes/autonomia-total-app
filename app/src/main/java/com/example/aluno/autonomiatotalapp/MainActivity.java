package com.example.aluno.autonomiatotalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editCarName;
    private EditText editDistanceKm;
    private EditText editFuelQuantity;
    private LinearLayout layoutCarInformation;
    private TextView editAverageValueFleet;
    private Double fleetTotalCost = 0D;
    private int fleetSize = 0;
    private DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.init();
    }

    public void calculate(View view) {
        String carName = editCarName.getText().toString();
        Double distanceKm = Double.parseDouble(editDistanceKm.getText().toString());
        Double fuelQuantity = Double.parseDouble(editFuelQuantity.getText().toString());
        Double averageKmPerLiter = distanceKm / fuelQuantity;

        fleetTotalCost += averageKmPerLiter;
        fleetSize += 1;

        Double fleetAverageCostPerLiter = fleetTotalCost / fleetSize;

        TextView textCarInformation = new TextView(this);
        textCarInformation.setText(
                carName + "   " + decimalFormat.format(averageKmPerLiter) + " km/L"
        );

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        textCarInformation.setLayoutParams(layoutParams);
        textCarInformation.setGravity(Gravity.CENTER);
        layoutCarInformation.addView(textCarInformation);

        editAverageValueFleet.setText(decimalFormat.format(fleetAverageCostPerLiter));
    }

    public void clear(View view) {
        layoutCarInformation.removeAllViews();
        fleetSize = 0;
        fleetTotalCost = 0D;
        editCarName.setText("");
        editDistanceKm.setText("");
        editFuelQuantity.setText("");
        editAverageValueFleet.setText("");
    }

    private void init() {
        this.editCarName = findViewById(R.id.editCarName);
        this.editDistanceKm = findViewById(R.id.editDistanceKm);
        this.editFuelQuantity = findViewById(R.id.editFuelQuantity);
        this.layoutCarInformation = findViewById(R.id.layoutCarInformation);
        this.editAverageValueFleet = findViewById(R.id.average_value_fleet);
    }

}
