package com.epam.courses.hr.Stub;

/**
 * Department Stub.
 */
public class DepartmentStub {

    private Integer id;

    private String name;

    private Integer avgSalary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepartmentStub id(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentStub name(String name) {
        this.name = name;
        return this;
    }

    public Integer getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Integer avgSalary) {
        this.avgSalary = avgSalary;
    }

    public DepartmentStub avgSalary(Integer avgSalary) {
        this.avgSalary = avgSalary;
        return this;
    }

    @Override
    public String toString() {
        return "DepartmentStub{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avgSalary=" + avgSalary +
                '}';
    }
}
