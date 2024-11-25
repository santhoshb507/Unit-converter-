package com.example.cc;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner categorySpinner, fromUnitSpinner, toUnitSpinner;
    private EditText inputValue;
    private TextView resultText;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorySpinner = findViewById(R.id.category_spinner);
        fromUnitSpinner = findViewById(R.id.from_unit_spinner);
        toUnitSpinner = findViewById(R.id.to_unit_spinner);
        inputValue = findViewById(R.id.input_value);
        resultText = findViewById(R.id.result_text);
        convertButton = findViewById(R.id.convert_button);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] units;
                switch (position) {
                    case 0: // Length
                        units = getResources().getStringArray(R.array.length_units);
                        break;
                    case 1: // Mass
                        units = getResources().getStringArray(R.array.mass_units);
                        break;
                    case 2: // Time
                        units = getResources().getStringArray(R.array.time_units);
                        break;
                    default:
                        units = new String[]{};
                }
                setUnitAdapters(units);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        convertButton.setOnClickListener(v -> convertUnits());
    }

    private void setUnitAdapters(String[] units) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);
    }

    private void convertUnits() {
        try {
            String category = categorySpinner.getSelectedItem().toString();
            String fromUnit = fromUnitSpinner.getSelectedItem().toString();
            String toUnit = toUnitSpinner.getSelectedItem().toString();
            double input = Double.parseDouble(inputValue.getText().toString());
            double result = 0;

            // Conversion logic
            if (category.equals("Length")) {
                result = convertLength(input, fromUnit, toUnit);
            } else if (category.equals("Mass")) {
                result = convertMass(input, fromUnit, toUnit);
            } else if (category.equals("Time")) {
                result = convertTime(input, fromUnit, toUnit);
            }

            resultText.setText("Result: " + result);
        } catch (Exception e) {
            resultText.setText("Error: Please check inputs!");
        }
    }

    private double convertLength(double value, String from, String to) {
        double meters = 0;
        switch (from) {
            case "Meter":
                meters = value;
                break;
            case "Inch":
                meters = value * 0.0254;
                break;
            case "Foot":
                meters = value * 0.3048;
                break;
            case "Mile":
                meters = value * 1609.34;
                break;
            default:
                meters = 0;
        }

        double result = 0;
        switch (to) {
            case "Meter":
                result = meters;
                break;
            case "Inch":
                result = meters / 0.0254;
                break;
            case "Foot":
                result = meters / 0.3048;
                break;
            case "Mile":
                result = meters / 1609.34;
                break;
            default:
                result = 0;
        }
        return result;
    }

    private double convertMass(double value, String from, String to) {
        double kilograms = 0;
        switch (from) {
            case "Kilogram":
                kilograms = value;
                break;
            case "Gram":
                kilograms = value / 1000;
                break;
            case "Pound":
                kilograms = value * 0.453592;
                break;
            default:
                kilograms = 0;
        }

        double result = 0;
        switch (to) {
            case "Kilogram":
                result = kilograms;
                break;
            case "Gram":
                result = kilograms * 1000;
                break;
            case "Pound":
                result = kilograms / 0.453592;
                break;
            default:
                result = 0;
        }
        return result;
    }

    private double convertTime(double value, String from, String to) {
        double seconds = 0;
        switch (from) {
            case "Second":
                seconds = value;
                break;
            case "Minute":
                seconds = value * 60;
                break;
            case "Hour":
                seconds = value * 3600;
                break;
            case "Day":
                seconds = value * 86400;
                break;
            default:
                seconds = 0;
        }

        double result = 0;
        switch (to) {
            case "Second":
                result = seconds;
                break;
            case "Minute":
                result = seconds / 60;
                break;
            case "Hour":
                result = seconds / 3600;
                break;
            case "Day":
                result = seconds / 86400;
                break;
            default:
                result = 0;
        }
        return result;
    }
}
