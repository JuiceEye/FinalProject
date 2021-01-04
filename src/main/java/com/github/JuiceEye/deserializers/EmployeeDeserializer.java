package com.github.JuiceEye.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.JuiceEye.exceptions.InvalidEmailException;
import com.github.JuiceEye.exceptions.InvalidPhoneNumberException;
import com.github.JuiceEye.models.Employee;
import com.github.JuiceEye.models.Department;
import com.github.JuiceEye.services.EmployeeService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class EmployeeDeserializer extends StdDeserializer<Employee> {

    public EmployeeDeserializer() {
        super(Employee.class);
    }

    @Override
    public Employee deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode root = p.getCodec().readTree(p);
//      int employeeId = Integer.valueOf(root.get("employeeId").asText());
        String employeeEmail = null;
        String employeeFirstName = root.get("employeeFirstName").asText();
        String employeeLastName = root.get("employeeFirstName").asText();
        //Хэширование пароля
        String password = BCrypt.hashpw(root.get("employeePassword").asText(), BCrypt.gensalt());
        Department department = Department.valueOf(root.get("department").asText());
        String employeePhoneNumber = null;
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber number = null;

        //Проверка валидности номера
        try {
            number = phoneNumberUtil.parse(root.get("employeePhoneNumber").asText(), Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        if ((phoneNumberUtil.isPossibleNumber(number))) {
            employeePhoneNumber = root.get("employeePhoneNumber").asText();
        } else {
            throw new InvalidPhoneNumberException("Invalid phone number");
        }

        //Проверка валидности почты
        String email = root.get("employeeEmail").asText();
        if (EmployeeService.isValid(email)) {
            employeeEmail = root.get("employeeEmail").asText();
        } else {
            throw new InvalidEmailException("Invalid email");
        }
        return new Employee(/*userId*/0, employeeFirstName, employeeLastName, employeeEmail, password, department, employeePhoneNumber);
    }
}

/*done*/
