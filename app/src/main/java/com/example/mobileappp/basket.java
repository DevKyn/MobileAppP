package com.example.mobileappp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;


public class basket extends AppCompatActivity {

        private static final String TAG= "basket";
    ArrayList<String> list;
    String selectedDate;
        private DatePickerDialog.OnDateSetListener dateSetListener;
        private TextView date;
        private Button checkOutbtn;
        reservation reservation;
        dataSource ds;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_basket);
            Intent intent = getIntent();
            Bundle b= intent.getExtras();
            list= b.getStringArrayList("services");
            ListView lv = (ListView) findViewById(R.id.basketlist);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    list );
            lv.setAdapter(arrayAdapter);

            date = (TextView) findViewById(R.id.datePicker);
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog dialog = new DatePickerDialog(basket.this,
                            android.R.style.Theme_Holo_Light_Dialog,
                            dateSetListener, year, month, day);
                    dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });
            dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);
                    selectedDate = month + "/" + day + "/" + year;
                    date.setText(selectedDate);
                }
            };
            checkOutbtn = (Button) findViewById(R.id.Checkoutbtn);
            checkOutbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ds = new dataSource(basket.this);
                    ds.open();
                    boolean wasSuccessful = false;
                    reservation = new reservation();
                    int id = ds.getLastCustomerId();

                    if (selectedDate == null) {
                            date.setError("Date is required!");
                            Toast.makeText(basket.this,"Please Select Date!",Toast.LENGTH_LONG).show();
                    }

                    for(int i=0; i<list.size();i++){
                        reservation.setCustomerId(id);
                        reservation.setService(list.get(i));
                        reservation.setReservationDate(selectedDate);

                        wasSuccessful = ds.insertReservation(reservation);
                    }
                        if(wasSuccessful){
                            new AlertDialog.Builder(basket.this)
                            .setTitle("Thanke you!")
                            .setMessage("Reservation done successfully")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(basket.this, main.class);
                                    startActivity(i);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();
                }
                        ds.close();

            }});
        }



}



