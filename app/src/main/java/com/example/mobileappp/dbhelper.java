package com.example.mobileappp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

    public class dbhelper extends SQLiteOpenHelper
{
        private static final String DATABASE_NAME = "beautysalon.db";
        private static final int DATABASE_VERSION = 1;

            private static final String CREATE_TABLE_CUSTOMER =
                " create table customer (customer_id integer primary key autoincrement, " +
                "name text not null, phone text not null);";


            private static final String CREATE_TABLE_RESERVATION =
                "create table reservation (reserve_id integer primary key autoincrement, " +
                "customer_id text not null, service text not null, date text not null);";

    //Constructor
    public dbhelper(Context context)
    {
         super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
//initiate the tables

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(CREATE_TABLE_CUSTOMER);
        database.execSQL(CREATE_TABLE_RESERVATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(dbhelper.class.getName(),"upgrade from " +oldVersion+ "to" +newVersion);
        db.execSQL("drop table if exists customer");
        db.execSQL("drop table if exists reservation");

    }
//
}
