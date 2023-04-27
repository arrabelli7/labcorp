package com.example.labcorp.model;

public class Manager extends SalariedEmployee {
    public Manager(int id) {
        super(id, "Manager");
        setVacationDays(30.0);
    }
}
