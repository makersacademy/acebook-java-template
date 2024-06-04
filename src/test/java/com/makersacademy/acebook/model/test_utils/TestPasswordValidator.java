package com.makersacademy.acebook.model.test_utils;


import com.makersacademy.acebook.utils.PasswordValidator;
import org.junit.Assert;
import org.junit.Test;

public class TestPasswordValidator {
    @Test
    public void testValidatePasswordReturnFalseOneChar() {
        boolean result = PasswordValidator.isPasswordValid("a");
        Assert.assertFalse(result);
    }
    @Test
    public void testValidatePasswordReturnTrueMoreThan8Characters() {
        boolean result = PasswordValidator.isPasswordValid("abcd!eKfgh2ijk");
        Assert.assertTrue(result);
    }
    @Test
    public void testValidatePasswordReturnFalseNoCapitalCharacters() {
        boolean result = PasswordValidator.isPasswordValid("abcdefghijk!2");
        Assert.assertFalse(result);
    }
    @Test
    public void testValidatePasswordReturnsFalseNoLowercaseCharacters(){
        boolean result = PasswordValidator.isPasswordValid("!ABCDEGDSA}23");
        Assert.assertFalse(result);
    }
    @Test
    public void testValidatePasswordReturnsFalseNoIntIncluded(){
        boolean result = PasswordValidator.isPasswordValid("abcdEfghijk!!");
        Assert.assertFalse(result);
    }
    @Test
    public void testValidatePasswordReturnsFalseNoSpecialCharacters(){
        boolean result = PasswordValidator.isPasswordValid("abcdEfghijk1234");
        Assert.assertFalse(result);
    }
}

