package com.github.JuiceEye.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.JuiceEye.models.Employee;

import java.io.IOException;

public class EmployeeSerializer extends StdSerializer<Employee> {
    public EmployeeSerializer() {
        super(Employee.class);
    }

    @Override
    public void serialize(Employee employee, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("employeeId", String.valueOf(employee.getEmployeeId()));
        jsonGenerator.writeStringField("employeeFirstName", employee.getEmployeeFirstName());
        jsonGenerator.writeStringField("employeeLastName", employee.getEmployeeLastName());
        jsonGenerator.writeStringField("employeeDepartment", String.valueOf(employee.getEmployeeDepartment()));
        jsonGenerator.writeStringField("employeeEmail", employee.getEmployeeEmail());
        jsonGenerator.writeStringField("employeePhoneNumber", employee.getEmployeePhoneNumber());
        jsonGenerator.writeEndObject();
    }
}

/*done*/
