package com.wp.beans;

public class Address {
    String city;
    String street;

    public String getCity() {
        return city;
    }


    //NOTE: while passing the city1 parameter through the XML we need to mention the setter with the setCity1 only
    public void setCity1(String city1) {
        System.out.println("I am in city SETTER which gets called implicitly while setting the city value through XML");
        this.city = city1;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
