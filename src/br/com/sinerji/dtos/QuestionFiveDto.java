package br.com.sinerji.dtos;

import br.com.sinerji.entity.Employee;

public class QuestionFiveDto {
    private Employee employee;
    private Double amountOfBenefit;

    public QuestionFiveDto(Employee employee, Double amountOfBenefit) {
        this.employee = employee;
        this.amountOfBenefit = amountOfBenefit;
    }

    public QuestionFiveDto() {

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getAmountOfBenefit() {
        return amountOfBenefit;
    }

    public void setAmountOfBenefit(Double amountOfBenefit) {
        this.amountOfBenefit = amountOfBenefit;
    }
}
