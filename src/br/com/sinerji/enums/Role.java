package br.com.sinerji.enums;

public enum Role {
    GERENTE(1, "Gerente", 20000.0, 3000.0, 1.0),
    VENDEDOR(2, "Vendedor", 12000.0, 1800.0, 1.3),
    SECRETARIO(3, "Secretario", 7000.0, 1000.0, 1.2);

    Integer id;
    String descricao;
    Double initialSalary;
    Double salaryIncreasePerYear;
    Double benefit;

    Role(Integer id, String descricao, Double initialSalary, Double salaryIncreasePerYear, Double benefit) {
        this.id = id;
        this.descricao = descricao;
        this.initialSalary = initialSalary;
        this.salaryIncreasePerYear = salaryIncreasePerYear;
        this.benefit = benefit;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getInitialSalary() {
        return initialSalary;
    }

    public Double getSalaryIncreasePerYear() {
        return salaryIncreasePerYear;
    }

    public Double getBenefit() {
        return benefit;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", initialSalary=" + initialSalary +
                ", salaryIncreasePerYear=" + salaryIncreasePerYear +
                ", benefit=" + benefit +
                '}';
    }
}
