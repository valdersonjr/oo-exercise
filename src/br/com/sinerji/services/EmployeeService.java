package br.com.sinerji.services;

import br.com.sinerji.dtos.EmployeeMonthYearDto;
import br.com.sinerji.dtos.QuestionBonusDto;
import br.com.sinerji.dtos.QuestionFiveDto;
import br.com.sinerji.dtos.QuestionSixDto;
import br.com.sinerji.entity.Employee;
import br.com.sinerji.entity.MonthSale;
import br.com.sinerji.enums.Role;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private static List<Employee> listOfEmployee = new ArrayList<Employee>();
    private static List<MonthSale> listOfSales = new ArrayList<MonthSale>();

    public void addNewEmployee(Employee employee){
        listOfEmployee.add(employee);
    }

    public void addNewSale(MonthSale sale){
        listOfSales.add(sale);
    }

    public Employee findEmployee(Integer idEmployee){
        Employee employee = listOfEmployee.stream().filter(props -> idEmployee.equals(props.getId())).findFirst().orElse(null);
        return employee;
    }

    public Double getTotalAmountOfVendorsSales(Integer idVendor, YearMonth dateOfSale){
        Double totalAmountVendorSale = 0.0;

        for(MonthSale k:listOfSales){
            if(k.getVendor().getId().equals(idVendor) && k.getMonthAndYearOfSales().equals(dateOfSale)){
                totalAmountVendorSale = totalAmountVendorSale + k.getTotalSalesAmountCash();
            }
        }

        return totalAmountVendorSale;
    }

    public Integer getEmployeeYearsOfService(YearMonth monthYearOfHiring, YearMonth monthYearSearched){
        Integer differenceBetweenYears = (monthYearSearched.getYear() - monthYearOfHiring.getYear())*12;
        Integer differenceBetweenMonths = monthYearSearched.getMonthValue() - monthYearOfHiring.getMonthValue();

        return (differenceBetweenYears + differenceBetweenMonths)/12;
    }

    public List<QuestionBonusDto> totalAmountPaidToEachEmployeeOnAYearMonth(List<EmployeeMonthYearDto> employeeFilterList){
        List<QuestionBonusDto> questionBonusDtoList = new ArrayList<QuestionBonusDto>();

        for (EmployeeMonthYearDto k:employeeFilterList){
            QuestionBonusDto questionBonusDto = new QuestionBonusDto();

            questionBonusDto.setEmployee(findEmployee(k.getEmployeeId()));

            Integer totalOfYears = getEmployeeYearsOfService(questionBonusDto.getEmployee().getMonthAndYearOfHiring(), k.getMonthYear());

            if(questionBonusDto.getEmployee().getRole().equals(Role.GERENTE)){
                questionBonusDto.setTotalAmountOfTheMonth((Role.GERENTE.getInitialSalary() + Role.GERENTE.getSalaryIncreasePerYear()*totalOfYears)*Role.GERENTE.getBenefit());
            }
            else if (questionBonusDto.getEmployee().getRole().equals(Role.SECRETARIO)){
                questionBonusDto.setTotalAmountOfTheMonth((Role.SECRETARIO.getInitialSalary() + Role.SECRETARIO.getSalaryIncreasePerYear()*totalOfYears)*Role.SECRETARIO.getBenefit());

            } else if (questionBonusDto.getEmployee().getRole().equals(Role.VENDEDOR)) {
                Double totalVendorSales = getTotalAmountOfVendorsSales(questionBonusDto.getEmployee().getId(), k.getMonthYear());

                questionBonusDto.setTotalAmountOfTheMonth((Role.VENDEDOR.getInitialSalary() + Role.VENDEDOR.getSalaryIncreasePerYear()*totalOfYears) + totalVendorSales*(Role.VENDEDOR.getBenefit()-1));
            }
            else {
                System.out.println("Employee does not have a role");
            }

            questionBonusDtoList.add(questionBonusDto);
        }

    return questionBonusDtoList;
    }

    public Double getAmountOfSalaryAndBenefitPaidToEmployeesInADate(List<Integer> idEmployeesList, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth yDate = YearMonth.parse(date, formatter);
        Double totalPaid = 0.0;

        for(Integer k:idEmployeesList){
            Employee employee = findEmployee(k);
            Integer totalOfYears = getEmployeeYearsOfService(employee.getMonthAndYearOfHiring(), yDate);
            
            if(employee.getRole().equals(Role.GERENTE)){
                totalPaid = totalPaid + (Role.GERENTE.getInitialSalary() + Role.GERENTE.getSalaryIncreasePerYear()*totalOfYears)*Role.GERENTE.getBenefit();
            } else if (employee.getRole().equals(Role.SECRETARIO)) {
                totalPaid = totalPaid + (Role.SECRETARIO.getInitialSalary() + Role.SECRETARIO.getSalaryIncreasePerYear()*totalOfYears)*Role.SECRETARIO.getBenefit();
            } else if (employee.getRole().equals(Role.VENDEDOR)) {
                Double totalVendorSales = getTotalAmountOfVendorsSales(employee.getId(), yDate);

                totalPaid = totalPaid + (Role.VENDEDOR.getInitialSalary() + Role.VENDEDOR.getSalaryIncreasePerYear()*totalOfYears) + totalVendorSales*(Role.VENDEDOR.getBenefit()-1);
            }

        }

        return totalPaid;
    }

    public Double getAmountOfSalaryPaidToEmployeesInADate(List<Integer> idEmployeesList, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth yDate = YearMonth.parse(date, formatter);
        Double totalPaid = 0.0;

        for(Integer k:idEmployeesList){
            Employee employee = findEmployee(k);
            Integer totalOfYears = getEmployeeYearsOfService(employee.getMonthAndYearOfHiring(), yDate);

            if(employee.getRole().equals(Role.GERENTE)){
                totalPaid = totalPaid + (Role.GERENTE.getInitialSalary() + Role.GERENTE.getSalaryIncreasePerYear()*totalOfYears);
            } else if (employee.getRole().equals(Role.SECRETARIO)) {
                totalPaid = totalPaid + (Role.SECRETARIO.getInitialSalary() + Role.SECRETARIO.getSalaryIncreasePerYear()*totalOfYears);
            } else if (employee.getRole().equals(Role.VENDEDOR)) {
                Double totalVendorSales = getTotalAmountOfVendorsSales(employee.getId(), yDate);

                totalPaid = totalPaid + (Role.VENDEDOR.getInitialSalary() + Role.VENDEDOR.getSalaryIncreasePerYear()*totalOfYears);
            }

        }
        return totalPaid;
    }

    public Double getAmountOfBenefitPaidToEmployeeInADate(List<Integer> idEmployeesList, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth yDate = YearMonth.parse(date, formatter);
        Double totalPaid = 0.0;

        for(Integer k:idEmployeesList){
            Employee employee = findEmployee(k);
            Integer totalOfYears = getEmployeeYearsOfService(employee.getMonthAndYearOfHiring(), yDate);

            if(employee.getRole().equals(Role.GERENTE)){
                totalPaid = totalPaid + (Role.GERENTE.getInitialSalary() + Role.GERENTE.getSalaryIncreasePerYear()*totalOfYears)*(Role.GERENTE.getBenefit()-1);
            } else if (employee.getRole().equals(Role.SECRETARIO)) {
                totalPaid = totalPaid + (Role.SECRETARIO.getInitialSalary() + Role.SECRETARIO.getSalaryIncreasePerYear()*totalOfYears)*(Role.SECRETARIO.getBenefit()-1);
            } else if (employee.getRole().equals(Role.VENDEDOR)) {
                Double totalVendorSales = getTotalAmountOfVendorsSales(employee.getId(), yDate);

                totalPaid = totalPaid + totalVendorSales*(Role.VENDEDOR.getBenefit()-1);
            }

        }
        return totalPaid;
    }

    public Employee getEmployeeWithBiggestSalaryInADate(List<Integer> idEmployeesList, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth yDate = YearMonth.parse(date, formatter);
        Double biggestSalary;

        Employee employee;
        List<QuestionBonusDto> questionBonusDtoList;

        List<EmployeeMonthYearDto> employeeMonthYearDtosList = new ArrayList<EmployeeMonthYearDto>();

        for(Integer k:idEmployeesList){
            EmployeeMonthYearDto employeeMonthYearDto = new EmployeeMonthYearDto();
            employeeMonthYearDto.setEmployeeId(k);
            employeeMonthYearDto.setMonthYear(yDate);

            employeeMonthYearDtosList.add(employeeMonthYearDto);
        }

        questionBonusDtoList = totalAmountPaidToEachEmployeeOnAYearMonth(employeeMonthYearDtosList);

        employee = questionBonusDtoList.get(0).getEmployee();
        biggestSalary = questionBonusDtoList.get(0).getTotalAmountOfTheMonth();
        for(QuestionBonusDto k:questionBonusDtoList){
            if(biggestSalary < k.getTotalAmountOfTheMonth()){
                employee = k.getEmployee();
            }
        }

        return employee;
    }

    public String getEmployeeWithBiggestBenefitInADate(List<Integer> idEmployeesList, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth yDate = YearMonth.parse(date, formatter);
        Double biggestBenefit;

        List<QuestionFiveDto> questionFiveDtoList = new ArrayList<QuestionFiveDto>();

        for(Integer k:idEmployeesList){
            Employee employee = findEmployee(k);
            Integer totalOfYears = getEmployeeYearsOfService(employee.getMonthAndYearOfHiring(), yDate);

            QuestionFiveDto questionFiveDto = new QuestionFiveDto();

            if(employee.getRole().equals(Role.GERENTE)){
//                System.out.println("O funcionário "+ employee.getName() + " é um gerente e não recebe benefício.");
            } else if (employee.getRole().equals(Role.SECRETARIO)) {
                questionFiveDto.setEmployee(employee);
                questionFiveDto.setAmountOfBenefit((Role.SECRETARIO.getInitialSalary() + Role.SECRETARIO.getSalaryIncreasePerYear()*totalOfYears)*(Role.SECRETARIO.getBenefit()-1));
                questionFiveDtoList.add(questionFiveDto);
            } else if (employee.getRole().equals(Role.VENDEDOR)) {
                Double totalVendorSales = getTotalAmountOfVendorsSales(employee.getId(), yDate);

                questionFiveDto.setAmountOfBenefit(totalVendorSales*(Role.VENDEDOR.getBenefit()-1));
                questionFiveDto.setEmployee(employee);
                questionFiveDtoList.add(questionFiveDto);
            }
        }

        String name = questionFiveDtoList.get(0).getEmployee().getName();
        biggestBenefit = questionFiveDtoList.get(0).getAmountOfBenefit();
        for(QuestionFiveDto k:questionFiveDtoList){
            if(k.getAmountOfBenefit() > biggestBenefit){
                biggestBenefit = k.getAmountOfBenefit();
                name = k.getEmployee().getName();
            }
        }
        return name;
    }

    public Employee getVendorWithMostSells(List<Integer> idEmployeesList, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth yDate = YearMonth.parse(date, formatter);

        List<QuestionSixDto> questionSixDtoList = new ArrayList<QuestionSixDto>();

        for(Integer k:idEmployeesList){
            Employee employee = findEmployee(k);
            QuestionSixDto questionSixDto = new QuestionSixDto();

            if (employee.getRole().equals(Role.VENDEDOR)){
                Double totalVendorSales = getTotalAmountOfVendorsSales(employee.getId(), yDate);

                questionSixDto.setEmployee(employee);
                questionSixDto.setAmountOfSales(totalVendorSales);
                questionSixDtoList.add(questionSixDto);
            }
            else {
//                System.out.println("O funcionário " + employee.getName() + " não é um vendedor.");
            }

        }
        Employee employee = questionSixDtoList.get(0).getEmployee();
        Double topSaleOfTheMonth = questionSixDtoList.get(0).getAmountOfSales();
        for(QuestionSixDto k:questionSixDtoList){
            if(topSaleOfTheMonth < k.getAmountOfSales()){
                topSaleOfTheMonth = k.getAmountOfSales();
                employee = k.getEmployee();
            }
        }

        return employee;
    }
}
