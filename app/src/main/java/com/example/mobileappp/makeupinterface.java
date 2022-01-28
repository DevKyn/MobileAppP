package com.example.mobileappp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class makeupinterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeupinterface);
        initReserveMakeupButton();
    }
    public void initReserveMakeupButton(){
        ArrayList<String> list = new ArrayList<>();
        Button reserve= (Button) findViewById(R.id.reserveMakeup);
        CheckBox cb1 = (CheckBox) findViewById(R.id.weddingMakeup);
        CheckBox cb2 = (CheckBox) findViewById(R.id.bridalMakeup);
        CheckBox cb3 = (CheckBox) findViewById(R.id.dramaticMakeup);
        CheckBox cb4 = (CheckBox) findViewById(R.id.naturalMakeup);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        list.clear();
                        if(cb1.isChecked())
                            list.add(cb1.getText().toString());
                        if(cb2.isChecked())
                            list.add(cb2.getText().toString());
                        if(cb3.isChecked())
                            list.add(cb3.getText().toString());
                        if(cb4.isChecked())
                            list.add(cb4.getText().toString());

                        Intent toBasket = new Intent(makeupinterface.this, basket.class);
                        Bundle b=new Bundle();
                        b.putStringArrayList("services", (ArrayList<String>) list);
                        toBasket.putExtras(b);
                        startActivity(toBasket);
            }
        });
    }
}
