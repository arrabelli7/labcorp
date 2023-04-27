package com.example.labcorp.model;

public class SalariedEmployee extends Employee {
    public SalariedEmployee(int id,String type) {
        super(id, type);
        setVacationDays(15.0);
    }

    public void work(int daysWorked) {
        if (daysWorked > 260) {
            throw new IllegalArgumentException("Cannot work more than 260 days in a work year.");
        }

        double newVacationDays = getVacationDays() + 0.0577 * daysWorked;
        setVacationDays(Math.min(newVacationDays, 15.0));
    }
}