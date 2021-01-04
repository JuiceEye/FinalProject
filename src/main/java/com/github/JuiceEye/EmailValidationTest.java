package com.github.JuiceEye;

import java.util.regex.Pattern;

public class EmailValidationTest {
    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +  //part before @
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static void main(String[] args) {
        if (isValid("cool.faruh42@gmail.com")) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
}
