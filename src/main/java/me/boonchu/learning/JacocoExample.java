package me.boonchu.learning.JacocoExample;

public class Palindrome {
    public boolean isPalindrome(String inputString) {
        if (inputString.length() == 0) {
            return true;
        } else {
            String rev = "";
            for (int i = inputString.length() - 1; i >= 0; i--)
                rev += inputString.charAt(i);
            if (inputString.equals(rev))
                return true;
            else
                return false;
        }
    }

}