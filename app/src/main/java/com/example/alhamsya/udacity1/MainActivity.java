package com.example.alhamsya.udacity1;

import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrderr(View view) {
        EditText namefieald = (EditText)findViewById(R.id.namefieald);
        String name = namefieald.getText().toString();

        CheckBox whippedcreamCheckBox = (CheckBox) findViewById(R.id.whipped_checkBox);
        boolean hasWhippedCream = whippedcreamCheckBox.isChecked();

        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.choccolate_checkBox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, hasWhippedCream, hasChocolate, quantity, price);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view){
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity +1;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;}
        quantity = quantity -1;
        displayQuantity(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */


//    private void price(int number){
//        TextView PriceMessageTextView = (TextView) findViewById(R.id.price_text_view);
//        PriceMessageTextView.setText("$" + number);
//    }

    private int calculatePrice(boolean addChocolate,boolean addWhippedCream) {
        int basePrice =  5;
        if (addChocolate){
            basePrice = basePrice +2;
        }
        if (addWhippedCream){
            basePrice = basePrice+1;
        }
        return quantity * basePrice;
    }
    private String  createOrderSummary(String name, boolean addWhippadCream, boolean addChocolate, int quantity, int price){
        String priceMessage = "Name : " + name;
        priceMessage += "\nAdd Whippad Cream?"+ addWhippadCream;
        priceMessage += "\nAdd Chocolate?"+ addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal : $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    private void displayQuantity(int numberOfCoffe) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffe);
    }
}
