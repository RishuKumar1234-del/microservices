package com.Moch.programs;

public class PalindromeChecker {

   
    public static boolean isPalindrome(String str) {
        
        String cleanedStr = str.replaceAll("\\s", "").toLowerCase();
        
        
        String reversedStr = new StringBuilder(cleanedStr).reverse().toString();
        
       
        return cleanedStr.equals(reversedStr);
    }

    public static void main(String[] args) {
        
        String[] testStrings = {"malayalam", "geek", "A man a plan a canal Panama", "racecar", "hello"};
        
        for (String testString : testStrings) {
            System.out.println("Is \"" + testString + "\" a palindrome? " + isPalindrome(testString));
        }
    }
}
