package com.example.labcorp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.labcorp.model.Employee;
import com.example.labcorp.model.HourlyEmployee;
import com.example.labcorp.model.Manager;
import com.example.labcorp.model.SalariedEmployee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EmployeeService {
    private static Map<Integer, Employee> employees;

    public static void main(String[] args) {
        employees = new HashMap<>();
        int id = 0;
        for (int i = 0; i < 10; i++) {
            employees.put(id++, new HourlyEmployee(id));
            employees.put(id++, new SalariedEmployee(id, "Salaried"));
            employees.put(id++, new Manager(id));
        }

        SpringApplication.run(EmployeeService.class, args);
    }

    @RequestMapping("/employees")
    public List<Map<String, Object>> getEmployees() {
        List<Map<String, Object>> employeeList = new ArrayList<>();
        for (Employee employee : employees.values()) {
            Map<String, Object> employeeMap = new HashMap<>();
            employeeMap.put("id", employee.getId());
            employeeMap.put("type", employee.getEmployeeType());
            employeeMap.put("vacationDays", employee.getVacationDays());
            employeeList.add(employeeMap);
        }
        return employeeList;
    }

    @PutMapping("/employees/{id}/work/{daysWorked}")
    public ResponseEntity<Void> work(@PathVariable int id, @PathVariable int daysWorked) {
        Employee employee = employees.get(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            employee.work(daysWorked);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/employees/{id}/vacation/{daysTaken}")
    public ResponseEntity<Void> takeVacation(@PathVariable int id, @PathVariable double daysTaken) {
        Employee employee = employees.get(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            employee.takeVacation(daysTaken);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
