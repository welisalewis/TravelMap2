package com.vacuum.routedirectionmap;

public class Fare {
    private String currency;
    private String text;
    private int value;


public String getCurrency(){
        return currency;
}

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getText(){
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }




}
