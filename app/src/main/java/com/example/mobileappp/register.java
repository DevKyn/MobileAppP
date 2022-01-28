package com.example.mobileappp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.*;
import android.os.Bundle;
import android.view.View;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;

public class register extends AppCompatActivity{
    private customer currentCustomer;
     EditText customerPhone,customerName ;
   // private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        currentCustomer = new customer();
        initRegisterButton();
        initTextChangedEvents();
        initCallIcon();
// Battery sensor
        BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                double batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                double levelScale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
                int batteryPercent = (int) Math.floor(batteryLevel / levelScale * 100);
                TextView textBatteryState = (TextView) findViewById(R.id.Battery);
                textBatteryState.setText(batteryPercent + "%");
            }
        };
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);
    }
// code for
    private void initRegisterButton() {
        Button register = (Button) findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   hideKeyboard();
                dataSource ds = new dataSource(register.this);
                ds.open();
                boolean wasSuccessful = false;
                if (currentCustomer.getCustomerId() == -1) {
                    wasSuccessful = ds.insertCustomer(currentCustomer);
                    int newId = ds.getLastCustomerId();
                    currentCustomer.setCustomerId(newId);
                    if(wasSuccessful){
                        Toast toast = Toast.makeText(getApplicationContext(), " Welcome "+ currentCustomer.getCustomerName(), Toast.LENGTH_LONG);
                        toast.show();
                }
                ds.close();

                    if( TextUtils.isEmpty(customerName.getText())) {
                       // Toast.makeText(getApplicationContext(), "Please fill your name", Toast.LENGTH_LONG).show();
                        customerName.setError("First name is required!");}

                    if( TextUtils.isEmpty(customerPhone.getText())) {
                      //  Toast.makeText(getApplicationContext(), "Please fill your phone", Toast.LENGTH_LONG).show();
                        customerPhone.setError("Phone is required!");}

                    if( !TextUtils.isEmpty(customerName.getText()) && !TextUtils.isEmpty(customerPhone.getText())) {
                    Intent toMain= new Intent(register.this,main.class);
                    startActivity(toMain);
                    }


        }}});
    }

    public void initTextChangedEvents(){
        customerName = (EditText) findViewById(R.id.editTextName);
        customerName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentCustomer.setCustomerName(customerName.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                //  Auto-generated method stub

            }
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //  Auto-generated method stub
            }
        });
        customerPhone = (EditText) findViewById(R.id.editNumber);
        customerPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher(){
            public void afterTextChanged(Editable s) {
                currentCustomer.setCustomerPhone(customerPhone.getText().toString());
            }

        });
}
// map sensor
 private void initMapIcon(){

//        ImageButton map = (ImageButton) findViewById(R.id.mapicon);
//        map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //AIzaSyDXeHVrJovJF9ZYAcNNA0T4ZJvcjuOK02g
//
//                mMap = googleMap;
//
//                // Add a marker in Sydney and move the camera
//                LatLng sydney = new LatLng(-34, 151);
//                mMap.addMarker(new MarkerOptions()
//                        .position(sydney)
//                        .title("Marker in Sydney"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//            }
//        });
 }
 // call sensor
 private void initCallIcon(){
        ImageButton call= (ImageButton) findViewById(R.id.callicon);
        call.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+"12345"));
                startActivity(intent);
                return true;
            }
        });
 }
}