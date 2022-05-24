package com.example.restaurantmanagement.owner.Boundary;


import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.customer.Entity.OrderObject;
import com.example.restaurantmanagement.owner.Controller.ViewAnalytics;
import com.example.restaurantmanagement.R;

import java.util.ArrayList;
import java.util.Objects;


public class OwnerView extends AppCompatActivity {
    String searchRequirement = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewAnalytics viewAnalytics = new ViewAnalytics(this);
        setContentView(R.layout.activity_analytics);

        EditText searchText = findViewById(R.id.search_date);
        Button searchBtn = findViewById(R.id.searchDateBtn);
        TextView summaryText = findViewById(R.id.analyticsTextView);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinnerDate = (Spinner) findViewById(R.id.spinner_date);
        spinner.setSelection(0);
        spinnerDate.setSelection(0);

        //spinner for filter selection
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(this, R.array.filterby, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(filterAdapter);

        //spinner for yearmth selection
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerDate.setAdapter(statusAdapter);

        searchBtn.setOnClickListener(v -> {
            String finalFilter;
            searchRequirement = searchText.getText().toString();

            if (Objects.equals(searchRequirement, new String(""))){
                Toast.makeText(OwnerView.this, "Invalid input", Toast.LENGTH_LONG).show();
                return;
            }

            String filtertype = spinner.getSelectedItem().toString();

            String yearMthSelection = spinnerDate.getSelectedItem().toString();
            if (Objects.equals(filtertype, new String("View spending pattern"))){
                ArrayList<OrderObject> spendings = viewAnalytics.getAllSpending();
                StringBuilder cartSummary = new StringBuilder((String.format("%-13s %-6s %-5s %n", "Customer", "Date", "Price")));
                for (OrderObject val : spendings) // "val" will be each Double value within the object.
                {
                    cartSummary.append(String.format("%-13s %-13s $%-5.2f %n", val.getCustomerName(), val.getOrderDate(),val.getPrice()));
                }
                summaryText.setTypeface(Typeface.MONOSPACE);
                summaryText.setText(cartSummary);
            }
            else if (Objects.equals(yearMthSelection, new String("Year"))){
                if (Objects.equals(filtertype, new String("View preference"))){
                    String recommendedFood = viewAnalytics.getMenuRecommendationYear(searchRequirement);
                    StringBuilder cartSummary = new StringBuilder((String.format("%-13s", "Recommended item: ")));
                    cartSummary.append(String.format("%-13s", recommendedFood));
                    summaryText.setTypeface(Typeface.MONOSPACE);
                    summaryText.setText(cartSummary);
                    System.out.print(recommendedFood);

                }
                else if (Objects.equals(filtertype, new String("View earning"))){
                    int totalEarnings = viewAnalytics.getYearlyEarnings(searchRequirement);
                    StringBuilder cartSummary = new StringBuilder((String.format("%-13s", "Earnings yearly: $")));
                    cartSummary.append(String.format("%-13s", totalEarnings));
                    summaryText.setTypeface(Typeface.MONOSPACE);
                    summaryText.setText(cartSummary);
                }
                else if (Objects.equals(filtertype, new String("View frequency of visit"))){
                    int freqVisit = viewAnalytics.getFrequencyYear(searchRequirement);
                    StringBuilder cartSummary = new StringBuilder((String.format("%-13s", "Frequency of visit: ")));
                    cartSummary.append(String.format("%-13s", freqVisit));
                    summaryText.setTypeface(Typeface.MONOSPACE);
                    summaryText.setText(cartSummary);
                }
                else{

                }
            }else if (Objects.equals(yearMthSelection, new String("Month"))){
                if (Objects.equals(filtertype, new String("View preference"))){
                    String recommendedFood = viewAnalytics.getMenuRecommendationMonth(searchRequirement);
                    StringBuilder cartSummary = new StringBuilder((String.format("%-13s", "Recommended item: ")));
                    cartSummary.append(String.format("%-13s", recommendedFood));
                    summaryText.setTypeface(Typeface.MONOSPACE);
                    summaryText.setText(cartSummary);
                    System.out.print(recommendedFood);

                }
                else if (Objects.equals(filtertype, new String("View earning"))){
                    int totalEarnings = viewAnalytics.getMonthlyEarnings(searchRequirement);
                    StringBuilder cartSummary = new StringBuilder((String.format("%-13s", "Earnings monthly: $")));
                    cartSummary.append(String.format("%-13s", totalEarnings));
                    summaryText.setTypeface(Typeface.MONOSPACE);
                    summaryText.setText(cartSummary);
                }
                else if (Objects.equals(filtertype, new String("View frequency of visit"))){
                    int freqVisit = viewAnalytics.getFrequencyMonth(searchRequirement);
                    StringBuilder cartSummary = new StringBuilder((String.format("%-13s", "Frequency of visit: ")));
                    cartSummary.append(String.format("%-13s", freqVisit));
                    summaryText.setTypeface(Typeface.MONOSPACE);
                    summaryText.setText(cartSummary);
                }
            }else{
                if (Objects.equals(filtertype, new String("View preference"))){
                    String recommendedFood = viewAnalytics.getMenuRecommendationToday(searchRequirement);
                    StringBuilder cartSummary = new StringBuilder((String.format("%-13s", "Recommended item: ")));
                    cartSummary.append(String.format("%-13s", recommendedFood));
                    summaryText.setTypeface(Typeface.MONOSPACE);
                    summaryText.setText(cartSummary);
                    System.out.print(recommendedFood);

                }
                else if (Objects.equals(filtertype, new String("View earning"))){
                    int totalEarnings = viewAnalytics.getTodayEarnings(searchRequirement);
                    StringBuilder cartSummary = new StringBuilder((String.format("%-13s", "Earnings daily: $")));
                    cartSummary.append(String.format("%-13s", totalEarnings));
                    summaryText.setTypeface(Typeface.MONOSPACE);
                    summaryText.setText(cartSummary);
                }
                else if (Objects.equals(filtertype, new String("View frequency of visit"))){
                    int freqVisit = viewAnalytics.getFrequencyToday(searchRequirement);
                    StringBuilder cartSummary = new StringBuilder((String.format("%-13s", "Frequency of visit: ")));
                    cartSummary.append(String.format("%-13s", freqVisit));
                    summaryText.setTypeface(Typeface.MONOSPACE);
                    summaryText.setText(cartSummary);
                }

            }

        });
    }
}