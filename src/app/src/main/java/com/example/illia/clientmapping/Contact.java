package com.example.illia.clientmapping;


public class Contact {

    private String id0;

    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String zametki;

    public Contact( String id0, String firstName, String lastName,
                   String phone, String address, String zametki){
        this.id0 = id0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.zametki = zametki;
    }

    public String getID0(){
        return this.id0;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getAddress(){
        return this.address;
    }

    public String getZametki(){
        return this.zametki;
    }
}
