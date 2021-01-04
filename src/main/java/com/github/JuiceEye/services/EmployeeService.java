package com.github.JuiceEye.services;

import com.github.JuiceEye.config.Constants;
import com.github.JuiceEye.config.DatabaseUtils;
import com.github.JuiceEye.exceptions.InvalidLoginCredentialsException;
import com.github.JuiceEye.models.Employee;
import com.github.JuiceEye.models.Department;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class EmployeeService implements Handler {
    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +  //part before @
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public Dao<Employee, Integer> employeeDao() throws SQLException {
        return DaoManager.createDao(DatabaseUtils.CONNECTION_SOURCE, Employee.class);
    }


    @Override
    public void create(Object objectEmployee) throws SQLException {
        employeeDao().create((Employee) objectEmployee);
    }

    @Override
    public Object read(int id) throws SQLException {
        return employeeDao().queryForId(id);
    }

    @Override
    public void update(Object objectEmployee) throws SQLException {
        employeeDao().update((Employee) objectEmployee);
    }

    @Override
    public void delete(int id) throws SQLException {
        employeeDao().delete(employeeDao().queryForId(id));
    }

    @Override
    public List<?> getAll() throws SQLException {
        return employeeDao().queryForAll();
    }

    public Department defineEmployeeDepartment(Context context) throws SQLException, InvalidLoginCredentialsException {
        List<Employee> employeeList;
        QueryBuilder<Employee, Integer> queryBuilder = employeeDao().queryBuilder();
        queryBuilder.where().eq("employeeEmail", context.basicAuthCredentials().getUsername());
        PreparedQuery<Employee> preparedQuery = queryBuilder.prepare();
        employeeList = employeeDao().query(preparedQuery);
        Department role = Department.UNDEFINED;
        Random random = new Random();
        long elapsedTime = System.currentTimeMillis() - employeeList.get(0).getTokenSettingTime();
        long elapsedSeconds = elapsedTime / 1000;
        long elapsedMinutes = elapsedSeconds / 60;
        if (elapsedMinutes >= 60) {
            if (BCrypt.checkpw(context.basicAuthCredentials().getPassword(), employeeList.get(0).getEmployeePassword())) {
                String token = "";
                for (int i = 0; i < 20; i++) {
                    token = token.concat(String.valueOf(Constants.ALPHABET.charAt(random.nextInt(Constants.ALPHABET.length()))));
                }
                employeeList.get(0).setToken(token);
                employeeList.get(0).setTokenSettingTime(System.currentTimeMillis());
                role = employeeList.get(0).getEmployeeDepartment();
            } else {
                throw new InvalidLoginCredentialsException("Invalid login credentials");
            }
        } else {
            if (context.header("Token").equals(employeeList.get(0).getToken())) {
                role = employeeList.get(0).getEmployeeDepartment();
            } else {
                throw new InvalidLoginCredentialsException("Invalid login credentials");
            }
        }
        return role;
    }
}

/*done*/