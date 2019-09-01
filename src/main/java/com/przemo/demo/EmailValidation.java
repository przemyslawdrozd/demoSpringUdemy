package com.przemo.demo;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidation implements Predicate<String> {

    public static final Predicate<String> IS_EMAIL_VALID =
            Pattern.compile(
                    "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE)
            .asPredicate();

    @Override
    public boolean test(String email) {
        return IS_EMAIL_VALID.test(email);
    }
}
