package br.com.sinerji.entity;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class MonthSale {
    private Integer id;
    private Employee vendor;
    private Double totalSalesAmountCash;
    private YearMonth monthAndYearOfSales;

    public MonthSale(Integer id, Employee vendor, Double totalSalesAmountCash, String monthAndYearOfSales) {
        this.id = id;
        this.vendor = vendor;
        this.totalSalesAmountCash = totalSalesAmountCash;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        this.monthAndYearOfSales = YearMonth.parse(monthAndYearOfSales, formatter);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getVendor() {
        return vendor;
    }

    public void setVendor(Employee vendor) {
        this.vendor = vendor;
    }

    public Double getTotalSalesAmountCash() {
        return totalSalesAmountCash;
    }

    public void setTotalSalesAmountCash(Double totalSalesAmountCash) {
        this.totalSalesAmountCash = totalSalesAmountCash;
    }

    public YearMonth getMonthAndYearOfSales() {
        return monthAndYearOfSales;
    }

    public void setMonthAndYearOfSales(YearMonth monthAndYearOfSales) {
        this.monthAndYearOfSales = monthAndYearOfSales;
    }

    @Override
    public String toString() {
        return "MonthSale{" +
                "id=" + id +
                ", vendor=" + vendor.getName() +
                ", totalSalesAmountCash=" + totalSalesAmountCash +
                ", monthAndYearOfSales=" + monthAndYearOfSales +
                '}';
    }
}
