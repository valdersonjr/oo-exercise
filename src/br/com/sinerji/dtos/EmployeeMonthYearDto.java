package br.com.sinerji.dtos;

import br.com.sinerji.entity.Employee;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class EmployeeMonthYearDto {
    private Integer employeeId;
    private YearMonth monthYear;

    public EmployeeMonthYearDto(Integer employeeId, String monthYear) {
        this.employeeId = employeeId;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        this.monthYear = YearMonth.parse(monthYear, formatter);;
    }

    public EmployeeMonthYearDto() {

    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public YearMonth getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(YearMonth monthYear) {
        this.monthYear = monthYear;
    }
}
