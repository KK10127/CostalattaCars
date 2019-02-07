package edu.miracostacollege.cs134.costalattacars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.NumberFormat;
import java.util.Locale;

import edu.miracostacollege.cs134.costalattacars.R;
import edu.miracostacollege.cs134.costalattacars.model.CarLoan;

public class MainActivity extends AppCompatActivity {

    // create references to 2 edit texts and 1 radio group
    private EditText carPriceEditText;
    private EditText downPaymentEditText;
    private RadioGroup loanTermRadioGroup;

    // now make a reference to our model (CarLoan)
    private CarLoan loan;

    // lets make formatters for currency and percentage
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link the controller variables to the view
        carPriceEditText = findViewById(R.id.carPriceEditText);
        downPaymentEditText = findViewById(R.id.downPaymentEditText);
        loanTermRadioGroup = findViewById(R.id.loanTermRadioGroup);

        // Instantiate a new carLoan
        loan = new CarLoan();

    }

    public void switchToLoanSummary(View v) {
        // 1) Extract data from 2 edit texts and radio group
        double carPrice = Double.parseDouble(carPriceEditText.getText().toString());
        double downPayment = Double.parseDouble(downPaymentEditText.getText().toString());

        int loanTerm;

        // use a switch statement to determine radio button checked in radio group
        switch (loanTermRadioGroup.getCheckedRadioButtonId())
        {
            case R.id.threeYearsRadioButton:
                loanTerm = 3;
                break;
            case R.id.fourYearsRadioButton:
                loanTerm = 4;
                break;
            case R.id.fiveYearsRadioButton:
                loanTerm = 5;
                break;
            default:
                loanTerm = -1; // obvious bad data to indicate an error
                break;
        }

        // 2) Update the model CarLoan (object: loan)
        loan.setPrice(carPrice);
        loan.setDownPayment(downPayment);
        loan.setLoanTerm(loanTerm);


        // 3) Create a new intent to share data between activities
        Intent loanSummaryIntent = new Intent(this, LoanSummary.class);
        // Share all the data...
        loanSummaryIntent.putExtra("MonthlyPayment", currency.format(loan.monthlyPayment()));
        loanSummaryIntent.putExtra("CarPrice", currency.format(loan.getPrice()));

        // finish sharing all the data

        // 4) start the new activity
        startActivity(loanSummaryIntent);
    }
}
