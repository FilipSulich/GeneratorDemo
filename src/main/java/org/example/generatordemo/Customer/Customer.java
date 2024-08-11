package org.example.generatordemo.Customer;

public class Customer {
    private String name;
    private String surname;
    private int age;
    private int NIP;
    private double value;

    public Customer(String name, String surname, int age, int NIP, double value) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.NIP = NIP;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getNIP() {
        return NIP;
    }

    public double getValue() {
        return value;
    }
}
