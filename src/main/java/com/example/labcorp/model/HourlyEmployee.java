package com.example.labcorp.model;

public class HourlyEmployee extends Employee {
    public HourlyEmployee(int id) {
        super(id, "Hourly");
        setVacationDays(10.0);
    }

    public void work(int daysWorked) {
        if (daysWorked > 260) {
            throw new IllegalArgumentException("Cannot work more than 260 days in a work year.");
        }

        double newVacationDays = getVacationDays() + 0.0385 * daysWorked;
        setVacationDays(Math.min(newVacationDays, 10.0));
    }
}