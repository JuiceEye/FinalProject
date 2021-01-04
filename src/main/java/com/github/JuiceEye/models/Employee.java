package com.github.JuiceEye.models;

import com.j256.ormlite.field.DatabaseField;
import java.util.Objects;

public class Employee {
    @DatabaseField(generatedId = true)
    private int employeeId;
    @DatabaseField(columnName = "employeeFirstName")
    private String employeeFirstName;
    @DatabaseField(columnName = "employeeLastName")
    private String employeeLastName;
    @DatabaseField(columnName = "employeeEmail")
    private String employeeEmail;
    @DatabaseField(columnName = "employeePassword")
    private String employeePassword;
    @DatabaseField(columnName = "employeePhoneNumber")
    private String employeePhoneNumber;
    @DatabaseField(columnName = "department")
    private Department employeeDepartment;
    private String token;
    private long tokenSettingTime;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public Department getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(Department employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTokenSettingTime() {
        return tokenSettingTime;
    }

    public void setTokenSettingTime(long tokenSettingTime) {
        this.tokenSettingTime = tokenSettingTime;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public Employee() {

    }

    public Employee(int employeeId, String employeeFirstName, String employeeLastName, String employeeEmail, String employeePassword, Department employeeDepartment, String employeePhoneNumber) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeEmail = employeeEmail;
        this.employeePassword = employeePassword;
        this.employeeDepartment = employeeDepartment;
        this.employeePhoneNumber = employeePhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                Objects.equals(employeeEmail, employee.employeeEmail) &&
                Objects.equals(employeePassword, employee.employeePassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeEmail, employeePassword);
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePassword='" + employeePassword + '\'' +
                '}';
    }
}

/*done*/