package com.example.labcorp.model;

public abstract class Employee {
    private int id;
    private double vacationDays;
    private String employeeType;

    public Employee(int id, String employeeType) {
        this.id = id;
        vacationDays = 0.0;
        this.employeeType = employeeType;
    }

    public int getId() {
        return id;
    }

    public double getVacationDays() {
        return vacationDays;
    }

    protected void setVacationDays(double vacationDays) {
        this.vacationDays = vacationDays;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public abstract void work(int daysWorked);

    public void takeVacation(double daysTaken) {
        if (daysTaken <= vacationDays) {
            vacationDays -= daysTaken;
        } else {
            throw new IllegalArgumentException("Not enough vacation days available.");
        }
    }
}




