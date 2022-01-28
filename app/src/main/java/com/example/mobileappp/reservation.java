package com.example.mobileappp;

public class reservation {
    private int id;
    private int customer_id;
    private String service;
    private String date;

    // setter and getter

    public int getReservationId(){
        return id;
    }
    public void setReservationId(int r){
        id= r;
    }

    public int getCustomerId(){
        return customer_id;
    }
    public void setCustomerId(int i){
        customer_id= i;
    }

    public String getService(){
        return service;
    }
    public void setService(String i){
        service= i;
    }

    public String getReservationDate(){
        return date;
    }
    public void setReservationDate(String d){
        date= d;
    }
}
