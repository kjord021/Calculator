package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    //Variables to hold the operands and type of calculations
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";

    //array to hold numerical buttons
    ArrayList<Button> numericalButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        //get buttons that can be appended and add them to the array
        Button button0 = (Button) findViewById(R.id.button0);
        numericalButtons.add(button0);
        Button button1 = (Button) findViewById(R.id.button1);
        numericalButtons.add(button1);
        Button button2 = (Button) findViewById(R.id.button2);
        numericalButtons.add(button2);
        Button button3 = (Button) findViewById(R.id.button3);
        numericalButtons.add(button3);
        Button button4 = (Button) findViewById(R.id.button4);
        numericalButtons.add(button4);
        Button button5 = (Button) findViewById(R.id.button5);
        numericalButtons.add(button5);
        Button button6 = (Button) findViewById(R.id.button6);
        numericalButtons.add(button6);
        Button button7 = (Button) findViewById(R.id.button7);
        numericalButtons.add(button7);
        Button button8 = (Button) findViewById(R.id.button8);
        numericalButtons.add(button8);
        Button button9 = (Button) findViewById(R.id.button9);
        numericalButtons.add(button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);
        numericalButtons.add(buttonDot);

        //buttons that perform operations
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);

        //Define a listener for number button press
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get button that was pressed
                Button b = (Button) v;
                //get the text (i.e number) of that button and add it to the newNumber TextView
                newNumber.append(b.getText().toString());
            }
        };
        //assign the listener to all the buttons in the list
        for (Button b : numericalButtons){
            b.setOnClickListener(numberListener);
        }
    }
}