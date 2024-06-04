package com.makersacademy.acebook.model.test_utils;

import com.makersacademy.acebook.utils.EmailValidator;
import org.junit.Assert;
import org.junit.Test;


public class TestEmailValidator {
    @Test
    public void testValidEmail() {
        boolean result = EmailValidator.isEmailValid("james@mail.com");
        Assert.assertTrue(result);
    }

    @Test
    public void testEmailReturnsFalseNoAtSymbol() {
        boolean result = EmailValidator.isEmailValid("jamesmail.com");
        Assert.assertFalse(result);
    }

    @Test
    public void testEmailReturnsFalseIfAtSymbolAtStart(){
        boolean result = EmailValidator.isEmailValid("@jamesmail.com");
        Assert.assertFalse(result);
    }

    @Test
    public void testEmailReturnsFalseifAtSymbolAtEnd(){
        boolean result = EmailValidator.isEmailValid("james@mail.com@");
        Assert.assertFalse(result);
    }

    @Test
    public void testEmailReturnsFalseSpecialChar(){
        boolean result = EmailValidator.isEmailValid("ja!mes@mail.com");
        Assert.assertFalse(result);
    }

    @Test
    public void testEmailReturnsFalseIfNoDot(){
        boolean result = EmailValidator.isEmailValid("james@mailcom");
        Assert.assertFalse(result);
    }
}
