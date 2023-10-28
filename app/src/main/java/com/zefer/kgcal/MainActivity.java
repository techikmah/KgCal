package com.zefer.kgcal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editPriceperKg, editWeight, editAmount;
    private Button calculateButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPriceperKg = findViewById(R.id.editTodayPrice);
        editWeight = findViewById(R.id.editWeight);
        editAmount = findViewById(R.id.editAmount);
        calculateButton = findViewById(R.id.calculateButton);
        resetButton = findViewById(R.id.resetButton); // Initialize the reset button

        // Set default values of "0" in the EditText fields
        editPriceperKg.setText("0");
        editWeight.setText("0.");
        editAmount.setText("0.");

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCost();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset the weight and amount fields to zero
                editWeight.setText("0.");
                editAmount.setText("0.");
            }
        });

    }

    private void calculateCost() {
        String priceperKgStr = editPriceperKg.getText().toString();
        String weightStr = editWeight.getText().toString();
        String amountStr = editAmount.getText().toString();

        if (priceperKgStr.isEmpty() || weightStr.isEmpty() || amountStr.isEmpty()) {
            // Show a Toast message when any of the fields are empty
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        } else {
            float priceperKg = Float.parseFloat(priceperKgStr);
            float weight = Float.parseFloat(weightStr);
            float amount = Float.parseFloat(amountStr);

            // Perform the calculations if all fields are filled
            // Condition 1: Calculate amount based on price and weight
            float calculatedAmount = (priceperKg != 0.0f) ? priceperKg * weight : 0.0f;

            // Condition 2: Calculate weight based on price and amount
            float calculatedWeight = (priceperKg != 0.0f) ? amount / priceperKg : 0.0f;

            // Display the calculated values as needed

            editAmount.setText(String.format("%.2f", calculatedAmount));
            editWeight.setText(String.format("%.2f", calculatedWeight));
            editWeight.setTextColor(getResources().getColor(R.color.colorResultText));
            editAmount.setTextColor(getResources().getColor(R.color.colorResultText));

        }
    }
}
