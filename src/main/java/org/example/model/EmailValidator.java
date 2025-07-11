package org.example.model;

import java.util.regex.Pattern;

public class EmailValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$");

    public static boolean validate(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
