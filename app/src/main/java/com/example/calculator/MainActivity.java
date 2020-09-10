package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    //Variables to hold the operand and type of calculations
    private Double operand1 = null;
    private String pendingOperation = "=";

    //array to hold numerical buttons
    ArrayList<Button> numericalButtons = new ArrayList<>();

    //array to hold operational buttons
    ArrayList<Button> operationalButtons = new ArrayList<>();

    //array to hold specialty buttons
    ArrayList<Button> specialtyButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.resultBox);
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

        //get buttons that perform operations and add to array
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        operationalButtons.add(buttonDivide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        operationalButtons.add(buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        operationalButtons.add(buttonMinus);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        operationalButtons.add(buttonPlus);
        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        operationalButtons.add(buttonEquals);
        final Button buttonNeg = (Button) findViewById(R.id.buttonNeg);
        operationalButtons.add(buttonNeg);
        buttonNeg.setEnabled(false);

        //get specialty buttons
        Button buttonClear = (Button) findViewById(R.id.buttonClear);
        specialtyButtons.add(buttonClear);

        //Define a listener for number button press
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get button that was pressed
                Button b = (Button) v;
                //get the text (i.e number) of that button and add it to the newNumber TextView
                newNumber.append(b.getText().toString());

                if (newNumber.length() > 0){
                    buttonNeg.setEnabled(true);
                }
                else{
                    buttonNeg.setEnabled(false);
                }
            }
        };
        //assign the listener to all the buttons in the list
        for (Button b : numericalButtons){
            b.setOnClickListener(numberListener);
        }

        //Define a listener for operation button press
        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String operation = b.getText().toString();
                String value = newNumber.getText().toString();
                buttonNeg.setEnabled(false);

                if (operation.equalsIgnoreCase("Neg")){
                    try {
                        Double doubleValue = Double.valueOf(value);
                        doubleValue *= -1;
                        performOperation(doubleValue, operation);
                    }
                    catch (NumberFormatException e) {
                        newNumber.setText("");
                    }
                }
                else {
                    try {
                        Double doubleValue = Double.valueOf(value);
                        performOperation(doubleValue, operation);
                    }
                    catch (NumberFormatException e) {
                        newNumber.setText("");
                    }
                }
                pendingOperation = operation;
                displayOperation.setText(pendingOperation);

            }
        };
        //assign a listner to all the buttons in the list
        for(Button b : operationalButtons){
            b.setOnClickListener(operationListener);
        }

        //define a listener for specialty buttons
        View.OnClickListener specialtyListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String specialty = b.getText().toString();

                if (specialty.equalsIgnoreCase("Clear")){
                    newNumber.setText("");
                    result.setText("");
                    displayOperation.setText("");
                    operand1 = null;
                }
            }
        };

        //assign a listner to all buttons in the list
        for (Button b : specialtyButtons){
            b.setOnClickListener(specialtyListener);
        }

        //retrieves the state on rotate
        if (savedInstanceState != null){
            operand1 = savedInstanceState.getDouble("firstNumber");
            result.setText(operand1.toString());
        }
    }

    /*Performs the arithmetic operation
    *@Param The current value
    *@Param The operation to perform
     */
    private void performOperation(Double value, String operation){

        displayOperation.setText(operation);

        if (operand1 == null) {
            operand1 = value;
        }
        else {

            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0){
                        operand1 = 0.0;
                    }
                    else {
                        operand1 /= value;
                    }
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }

    /*Saves the state of the device on rotate
     *@Param The out state of the app
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("firstNumber", operand1);
    }

}