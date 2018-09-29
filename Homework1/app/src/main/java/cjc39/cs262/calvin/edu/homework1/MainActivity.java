package cjc39.cs262.calvin.edu.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int value1, value2;
    private Spinner spinner;
    private static final String[] paths = {"+", "-", "*", "/"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /* When the CALCULATE button is pressed, calculate the answer
     *      Takes user input for value 1, value 2, and the operator
     *      And displays the result next to the CALCULATE button
     * @param view The view that triggers this onClick handler.
     */
    public void onCalculateButtonPressed(View view) {
        TextView resultsTV = findViewById(R.id.textView6);
        value1 = Integer.valueOf(
                ((EditText) findViewById(R.id.editText5)).getText().toString());
        value2 = Integer.valueOf(
                ((EditText) findViewById(R.id.editText6)).getText().toString());

        char operatorChar = ((Spinner) findViewById(R.id.spinner2))
                .getSelectedItem().toString().charAt(0);

        switch (operatorChar) {
            case '+': resultsTV.setText(String.valueOf(value1 + value2));
                break;
            case '-': resultsTV.setText(String.valueOf(value1 - value2));
                break;
            case '*': resultsTV.setText(String.valueOf(value1 * value2));
                break;
            case '/': resultsTV.setText(String.valueOf(value1 / value2));
                break;

        }
    }
}
