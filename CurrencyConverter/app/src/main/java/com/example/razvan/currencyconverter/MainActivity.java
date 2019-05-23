package com.example.razvan.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
{

    // declare objects for spinners, editTexts, buttons
    private Spinner fromSpinner = null;
    private Spinner toSpinner = null;
    private EditText fromEditText = null;
    private EditText toEditText = null;
    private Button convertButton = null;
    private Button clearButton = null;

    private ArrayAdapter<String> adapter = null;

    String[] currencyNames = {"EUR", "USD", "UKP", "RON", "CHY"};
    double[] currencyRates = { 1.0, 0.89, 1.2, 0.34, 0.1};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //wire objects with digits
        fromSpinner = (Spinner)findViewById(R.id.spinner2);
        toSpinner = (Spinner)findViewById(R.id.spinner3);
        fromEditText = (EditText)findViewById(R.id.editText2);
        toEditText = (EditText)findViewById(R.id.editText3);
        convertButton = (Button)findViewById(R.id.button);
        clearButton = (Button)findViewById(R.id.button2);

        //make the adaptor and its settings
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencyNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                fromEditText.setText("");
                toEditText.setText("");

                fromSpinner.setAdapter(adapter);
                toSpinner.setAdapter(adapter);
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //find spiiners' selections
                int fromSpinnerId =(int) fromSpinner.getSelectedItemId();
                int toSpinnerId = (int) toSpinner.getSelectedItemId();

                //calculate the rate
                double rate = currencyRates[fromSpinnerId]/currencyRates[toSpinnerId];

                //deal with conversion
                double fromValue = Double.parseDouble(fromEditText.getText().toString());
                double toValue = (int)(fromValue * rate * 100)/100.0;

                toEditText.setText(Double.toString(toValue));
            }
        });

    }
}
