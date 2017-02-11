package com.azaharzafra.directorio;

/* Created by Azahar on 06/12/2016. */

public class Contacto {
    private int id;
    private String name;
    private int number;

    public Contacto() {
        this.id = -1;
        this.name = "";
        this.number = -1;
    }
    public Contacto(String name, int number) {
        this.id = -1;
        this.name = name;
        this.number = number;
    }
    public Contacto(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String n) {
        name=n;
    }

    public void setNumber(int n) {
        number=n;
    }

    public String toString(){
        return name + ": " + number;
    }
}
