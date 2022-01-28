package com.example.mobileappp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dataSource {
    private SQLiteDatabase db;
    private dbhelper dbHelper;
    public dataSource (Context context)
    {
        dbHelper= new dbhelper(context);
    }
    //purpose open the database
    public void open() throws SQLException
    {
       db= dbHelper.getWritableDatabase();
    }
    //purpose close database
    public void close() throws SQLException
    {
       dbHelper.close();
    }
    // the parameter shld be the class that i want to add the data to, right?
    public boolean insertCustomer (customer c){
         boolean didSucceed = false;
        try
        {
            ContentValues customerInfo = new ContentValues();
            customerInfo.put("name", c.getCustomerName());
            customerInfo.put("phone", c.getCustomerPhone());
            didSucceed = db.insert("customer", null, customerInfo) > 0;
        }
        catch(Exception e){
        }
        return didSucceed;
    }

    public int getLastCustomerId() {
        int lastId = -1;
        try
        {
            String query = "Select MAX(customer_id) from customer";
            Cursor cursor = db.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }

    public boolean insertReservation (reservation r){
        boolean didSucceed = false;
        try
        {
            ContentValues reservationInfo = new ContentValues();
            reservationInfo.put("service_id", r.getService());
            reservationInfo.put("date", r.getReservationDate());
            reservationInfo.put("customer_id", r.getCustomerId());
            didSucceed = db.insert("reservation", null, reservationInfo) > 0;
        }
        catch(Exception e){
        }
        return didSucceed;
    }


}
