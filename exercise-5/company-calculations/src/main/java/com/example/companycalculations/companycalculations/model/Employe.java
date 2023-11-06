package main.java.com.example.companycalculations.companycalculations.model;

import java.io.Serializable;
import java.util.Date;

public class Employe implements Serializable {
    private String name;

    public Employe() {
    }

    public Employe(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
