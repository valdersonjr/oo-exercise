package br.com.sinerji.dtos;

import br.com.sinerji.entity.Employee;

public class QuestionBonusDto {
    private Employee employee;
    private Double totalAmountOfTheMonth;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getTotalAmountOfTheMonth() {
        return totalAmountOfTheMonth;
    }

    public void setTotalAmountOfTheMonth(Double totalAmountOfTheMonth) {
        this.totalAmountOfTheMonth = totalAmountOfTheMonth;
    }

    @Override
    public String toString() {
        return "Question1Dto{" +
                "employee=" + employee.getName() +
                ", totalAmountOfTheMonth=" + totalAmountOfTheMonth +
                '}';
    }
}
