package com.example.discoverring;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddPostReportItems extends AppCompatActivity 
{
    // item type
    String[] itemList = {"Electronics", "Stationary", "Book", "Purse", "Bag", "Other"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    // lost date
    DatePickerDialog datePickerDialog;
    Button dateButton;
    TextView dateFoundTextView;

    // store in db
    String itemName2, itemDesc2, itemType2, foundLoc, foundDate, userUID;
    FirebaseDatabase db;
    DatabaseReference reference;
    TextInputEditText nameOfItem2, descOfItem2, found;
    TextView dateFound;
    FirebaseUser firebaseUser;
    Button postButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_post_report_items);
        initDatePicker();

        // item type drop down
        autoCompleteTextView = findViewById(R.id.itemType2);
        adapterItems = new ArrayAdapter<String>(this, R.layout.item_list, itemList);
        autoCompleteTextView.setAdapter(adapterItems);

        // lost item date picker
        dateButton = findViewById(R.id.foundDateImg);
        dateFoundTextView = findViewById(R.id.foundDate);
//        dateFoundTextView.setText(getTodaysDate());

        // store in db
        reference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                itemType2 = adapterView.getItemAtPosition(i).toString();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("FoundItems");
        postButton2 = findViewById(R.id.foundItemPostBtn);

        postButton2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(firebaseUser != null)
                    userUID = firebaseUser.getUid();

                nameOfItem2 = findViewById(R.id.itemName2);
                itemName2 = nameOfItem2.getText().toString();
                descOfItem2 = findViewById(R.id.itemDesc2);
                itemDesc2 = descOfItem2.getText().toString();
                found = findViewById(R.id.foundLocation);
                foundLoc = found.getText().toString();
                dateFound = findViewById(R.id.foundDate);
                foundDate = dateFound.getText().toString();

                ItemsFound itemsFound = new ItemsFound(userUID, itemName2, itemDesc2, itemType2, foundLoc, foundDate);

                reference.push().setValue(itemsFound, new DatabaseReference.CompletionListener()
                {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref)
                    {
                        if(error == null)
                        {
                            Toast.makeText(AddPostReportItems.this, "Item details posted!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ReportItems.class);
                            startActivity(intent);
                            finish();
                        }

                        else
                        {
                            Toast.makeText(AddPostReportItems.this, "Failed to post!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ReportItems.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }

//    private String getTodaysDate()
//    {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        month += 1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//
//        return makeDateString(day, month, year);
//    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month += 1;
                String date = makeDateString(day, month, year);
                dateFoundTextView.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year)
    {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if (month == 1)
            return "JAN";

        if (month == 2)
            return "FEB";

        if (month == 3)
            return "MAR";

        if (month == 4)
            return "APR";

        if (month == 5)
            return "MAY";

        if (month == 6)
            return "JUN";

        if (month == 7)
            return "JUL";

        if (month == 8)
            return "AUG";

        if (month == 9)
            return "SEPT";

        if (month == 10)
            return "OCT";

        if (month == 11)
            return "NOV";

        if (month == 12)
            return "DEC";

        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}
