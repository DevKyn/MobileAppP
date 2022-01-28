package com.example.mobileappp;

public class customer {
    private int id;
    private String name;
    private String phone;

    public customer() {
        id= -1;
    }
    // setter and getter
    public int getCustomerId(){
        return id;
    }

    public void setCustomerId(int i){
        id=i;
    }
    public String getCustomerName(){
        return name;
    }
    public void setCustomerName(String n){
        name=n;
    }
    public String getCustomerPhone(){
        return phone;
    }
    public void setCustomerPhone(String ph){
        phone= ph;
    }

}