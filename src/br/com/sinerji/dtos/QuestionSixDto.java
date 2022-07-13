package br.com.sinerji.dtos;

import br.com.sinerji.entity.Employee;

public class QuestionSixDto {
    private Employee employee;
    private Double amountOfSales;

    public QuestionSixDto(Employee employee, Double amountOfSales) {
        this.employee = employee;
        this.amountOfSales = amountOfSales;
    }

    public QuestionSixDto() {

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getAmountOfSales() {
        return amountOfSales;
    }

    public void setAmountOfSales(Double amountOfSales) {
        this.amountOfSales = amountOfSales;
    }
}
