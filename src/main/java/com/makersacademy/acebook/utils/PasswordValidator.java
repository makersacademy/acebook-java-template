package com.makersacademy.acebook.utils;
 public class PasswordValidator {

     public static boolean isPasswordValid(String password) {
         if (password.length() < 8) {
             return false;
         }

         boolean hasUppercase = false;
         boolean hasLowercase = false;
         boolean hasDigit = false;
         boolean hasSpecialChar = false;

         for (char ch : password.toCharArray()) {
             if (Character.isUpperCase(ch)) {
                 hasUppercase = true;
             } else if (Character.isLowerCase(ch)) {
                 hasLowercase = true;
             } else if (Character.isDigit(ch)) {
                 hasDigit = true;
             } else if (isSpecialCharacter(ch)) {
                 hasSpecialChar = true;
             }

             // If all conditions are met, we can return true early
             if (hasUppercase && hasLowercase && hasDigit && hasSpecialChar) {
                 return true;
             }
         }

         return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
     }

     private static boolean isSpecialCharacter(char ch) {
         String specialChars = "!@#$%^&*()_-+=<>?/{}~|";
         return specialChars.indexOf(ch) != -1;
     }
}
