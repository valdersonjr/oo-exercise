package br.com.test;

import br.com.test.dtos.EmployeeMonthYearDto;
import br.com.test.entity.Employee;
import br.com.test.entity.MonthSale;
import br.com.test.enums.Role;
import br.com.test.services.EmployeeService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();

        employeeService.addNewEmployee(new Employee(1, "Jorge Carvalho", Role.SECRETARIO, "01/2018"));
        employeeService.addNewEmployee(new Employee(2, "Maria Souza", Role.SECRETARIO, "12/2015"));
        employeeService.addNewEmployee(new Employee(3, "Ana Silva", Role.VENDEDOR, "12/2021"));
        employeeService.addNewEmployee(new Employee(4, "João Mendes", Role.VENDEDOR, "12/2021"));
        employeeService.addNewEmployee(new Employee(5, "Juliana Alves", Role.GERENTE, "07/2017"));
        employeeService.addNewEmployee(new Employee(6, "Bento Albino", Role.GERENTE, "03/2014"));

        Employee vendor1 = employeeService.findEmployee(3);

        employeeService.addNewSale(new MonthSale(1, vendor1, 5200.0, "12/2021"));
        employeeService.addNewSale(new MonthSale(2, vendor1, 4000.0, "01/2022"));
        employeeService.addNewSale(new MonthSale(3, vendor1, 4200.0, "02/2022"));
        employeeService.addNewSale(new MonthSale(4, vendor1, 5850.0, "03/2022"));
        employeeService.addNewSale(new MonthSale(5, vendor1, 7000.0, "04/2022"));

        Employee vendor2 = employeeService.findEmployee(4);

        employeeService.addNewSale(new MonthSale(6, vendor2, 3400.0, "12/2021"));
        employeeService.addNewSale(new MonthSale(7, vendor2, 7700.0, "01/2022"));
        employeeService.addNewSale(new MonthSale(8, vendor2, 5000.0, "02/2022"));
        employeeService.addNewSale(new MonthSale(9, vendor2, 5900.0, "03/2022"));
        employeeService.addNewSale(new MonthSale(10, vendor2, 6500.0, "04/2022"));

        questionBonusTest();
        firstQuestionTest();
        secondQuestionTest();
        thirdQuestionTest();
        FourthQuestionTest();
        FifthQuestionTest();
        SixtQuestionTest();
    }

    private static void questionBonusTest(){
        List<EmployeeMonthYearDto> employeeFilterList = new ArrayList<EmployeeMonthYearDto>();
        EmployeeService employeeService = new EmployeeService();

        employeeFilterList.add(new EmployeeMonthYearDto(1, "02/2020"));
        employeeFilterList.add(new EmployeeMonthYearDto(3, "04/2022"));
        employeeFilterList.add(new EmployeeMonthYearDto(5, "12/2022"));

        employeeService.totalAmountPaidToEachEmployeeOnAYearMonth(employeeFilterList);
    }

    private static void firstQuestionTest(){
       List<Integer> listOfEmployeesId = new ArrayList<Integer>();
       EmployeeService employeeService = new EmployeeService();

       listOfEmployeesId.add(1);
       listOfEmployeesId.add(3);
       listOfEmployeesId.add(5);

       employeeService.getAmountOfSalaryAndBenefitPaidToEmployeesInADate(listOfEmployeesId, "02/2020");
    }

    private static void secondQuestionTest(){
        List<Integer> listOfEmployeesId = new ArrayList<Integer>();
        EmployeeService employeeService = new EmployeeService();

        listOfEmployeesId.add(1);
        listOfEmployeesId.add(3);
        listOfEmployeesId.add(5);

        employeeService.getAmountOfSalaryPaidToEmployeesInADate(listOfEmployeesId, "02/2020");
    }

    private static void thirdQuestionTest(){
        List<Integer> listOfEmployeesId = new ArrayList<Integer>();
        EmployeeService employeeService = new EmployeeService();

        listOfEmployeesId.add(1);
        listOfEmployeesId.add(3);
        listOfEmployeesId.add(5);

        employeeService.getAmountOfBenefitPaidToEmployeeInADate(listOfEmployeesId, "02/2020");
    }


    private static void FourthQuestionTest(){
        List<Integer> listOfEmployeesId = new ArrayList<Integer>();
        EmployeeService employeeService = new EmployeeService();

        listOfEmployeesId.add(1);
        listOfEmployeesId.add(3);
        listOfEmployeesId.add(5);

        employeeService.getEmployeeWithBiggestSalaryInADate(listOfEmployeesId, "02/2020");
    }

    private static void FifthQuestionTest(){
        List<Integer> listOfEmployeesId = new ArrayList<Integer>();
        EmployeeService employeeService = new EmployeeService();

        listOfEmployeesId.add(1);
        listOfEmployeesId.add(3);
        listOfEmployeesId.add(5);

        employeeService.getEmployeeWithBiggestBenefitInADate(listOfEmployeesId, "02/2020");
    }

    private static void SixtQuestionTest(){
        List<Integer> listOfEmployeesId = new ArrayList<Integer>();
        EmployeeService employeeService = new EmployeeService();

        listOfEmployeesId.add(3);
        listOfEmployeesId.add(4);
        listOfEmployeesId.add(1);

        employeeService.getVendorWithMostSells(listOfEmployeesId, "02/2020");
    }
}
