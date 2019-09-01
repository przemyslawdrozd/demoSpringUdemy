package com.przemo.demo;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailValidationTest  {

    private final EmailValidation underTest = new EmailValidation();

    @Test
    public void isShouldValidateCorrectEmail() {
        assertThat(underTest.test("hello@gmail.com")).isTrue();
    }

    @Test
    public void isShouldValidateAnIncorrectEmail() {
        assertThat(underTest.test("hellogmail.com")).isFalse();
    }

    @Test
    public void isShouldValidateAnIncorrectEmailWithoutDotAtTheEnd() {
        assertThat(underTest.test("hello@gmail")).isFalse();
    }
}