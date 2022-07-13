package br.com.test.entity;

import br.com.test.enums.Role;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Employee {
    private Integer id;
    private String name;
    private Role role;
    private YearMonth monthAndYearOfHiring;

    public Employee(Integer id, String name, Role role, String monthAndYearOfHiring) {
        this.id = id;
        this.name = name;
        this.role = role;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        this.monthAndYearOfHiring = YearMonth.parse(monthAndYearOfHiring, formatter);
    }

    public Employee() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public YearMonth getMonthAndYearOfHiring() {
        return monthAndYearOfHiring;
    }

    public void setMonthAndYearOfHiring(YearMonth monthAndYearOfHiring) {
        this.monthAndYearOfHiring = monthAndYearOfHiring;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", monthAndYearOfHiring=" + monthAndYearOfHiring +
                '}';
    }
}
