package com.example.dailyexpenseapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etAmount, etDesc;
    Spinner spCategory;
    RadioGroup radioGroup;
    Button btnAdd;
    ListView listView;

    ArrayList<String> expenseList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        etDesc = findViewById(R.id.etDesc);
        spCategory = findViewById(R.id.spCategory);
        radioGroup = findViewById(R.id.radioGroup);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);

        // Spinner Data
        String[] categories = {"Food", "Travel", "Shopping", "Bills", "Others"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categories);
        spCategory.setAdapter(spinnerAdapter);

        // ListView Setup
        expenseList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, expenseList);
        listView.setAdapter(adapter);

        // Button Click
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount = etAmount.getText().toString();
                String desc = etDesc.getText().toString();
                String category = spCategory.getSelectedItem().toString();

                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (amount.isEmpty() || desc.isEmpty() || selectedId == -1) {
                    Toast.makeText(MainActivity.this,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton rb = findViewById(selectedId);
                String paymentMode = rb.getText().toString();

                String expense = "₹" + amount + " | " +
                        desc + " | " +
                        category + " | " +
                        paymentMode;

                expenseList.add(expense);
                adapter.notifyDataSetChanged();

                etAmount.setText("");
                etDesc.setText("");
                radioGroup.clearCheck();
            }
        });
    }
}