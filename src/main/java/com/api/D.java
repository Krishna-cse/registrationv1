package com.api;

import java.util.Scanner;

public class D{

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Get input from the user
            System.out.print("Enter a string: ");
            String original = scanner.nextLine();

            // Remove spaces and convert to lowercase for uniform comparison
            String cleanedString = original.replaceAll("\\s+", "").toLowerCase();

            // Check if the string is a palindrome
            if (isPalindrome(cleanedString)) {
                System.out.println(original + " is a palindrome.");
            } else {
                System.out.println(original + " is not a palindrome.");
            }
        }

        // Function to check if a string is a palindrome
        public static boolean isPalindrome(String str) {
            int left = 0;
            int right = str.length() - 1;

            // Compare characters from both ends
            while (left < right) {
                if (str.charAt(left) != str.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }

            return true;
        }
    }


