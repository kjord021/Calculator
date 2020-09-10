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
    private Double firstNumber = null;
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
        Button btn0 = (Button) findViewById(R.id.btn0);
        numericalButtons.add(btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        numericalButtons.add(btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        numericalButtons.add(btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        numericalButtons.add(btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        numericalButtons.add(btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        numericalButtons.add(btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        numericalButtons.add(btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        numericalButtons.add(btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        numericalButtons.add(btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);
        numericalButtons.add(btn9);
        Button btnDot = (Button) findViewById(R.id.btnDot);
        numericalButtons.add(btnDot);

        //get buttons that perform operations and add to array
        Button btnDivide = (Button) findViewById(R.id.btnDivide);
        operationalButtons.add(btnDivide);
        Button btnMultiply = (Button) findViewById(R.id.btnMultiply);
        operationalButtons.add(btnMultiply);
        Button btnSubtract = (Button) findViewById(R.id.buttonSubtract);
        operationalButtons.add(btnSubtract);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        operationalButtons.add(btnAdd);
        Button btnEquals = (Button) findViewById(R.id.btnEquals);
        operationalButtons.add(btnEquals);
        final Button btnNeg = (Button) findViewById(R.id.btnNeg);
        operationalButtons.add(btnNeg);
        btnNeg.setEnabled(false);

        //get specialty buttons
        Button btnClear = (Button) findViewById(R.id.btnClear);
        specialtyButtons.add(btnClear);

        //Define a listener for number button press
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get button that was pressed
                Button b = (Button) v;
                //get the text (i.e number) of that button and add it to the newNumber TextView
                newNumber.append(b.getText().toString());

                if (newNumber.length() > 0){
                    btnNeg.setEnabled(true);
                }
                else{
                    btnNeg.setEnabled(false);
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
                btnNeg.setEnabled(false);

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
                    firstNumber = null;
                }
            }
        };

        //assign a listner to all buttons in the list
        for (Button b : specialtyButtons){
            b.setOnClickListener(specialtyListener);
        }

        //retrieves the state on rotate
        if (savedInstanceState != null){
            firstNumber = savedInstanceState.getDouble("firstNumber");
            result.setText(firstNumber.toString());
        }
    }

    /*Performs the arithmetic operation
    *@Param The current value
    *@Param The operation to perform
     */
    private void performOperation(Double value, String operation){

        displayOperation.setText(operation);

        if (firstNumber == null) {
            firstNumber = value;
        }
        else {

            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "=":
                    firstNumber = value;
                    break;
                case "/":
                    if (value == 0){
                        firstNumber = 0.0;
                    }
                    else {
                        firstNumber /= value;
                    }
                    break;
                case "*":
                    firstNumber *= value;
                    break;
                case "-":
                    firstNumber -= value;
                    break;
                case "+":
                    firstNumber += value;
                    break;
            }
        }
        result.setText(firstNumber.toString());
        newNumber.setText("");
    }

    /*Saves the state of the device on rotate
     *@Param The out state of the app
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("firstNumber", firstNumber);
    }

}