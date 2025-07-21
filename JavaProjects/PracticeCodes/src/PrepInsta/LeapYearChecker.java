package PrepInsta;

public class LeapYearChecker {
    public static void main(String[] args) {
        int year1 = 2020;
        int year2 = 2019;
        int year3 = 2024;

        // Method 1: Using if-else
        System.out.println(year1 + " is a Leap Year? " + isLeapYearUsingIfElse(year1));

        // Method 2: Using Ternary Operator
        System.out.println(year2 + " is a Leap Year? " + isLeapYearUsingTernary(year2));

        // Method 3: Using Boolean Variable
        System.out.println(year3 + " is a Leap Year? " + isLeapYearUsingBoolean(year3));
    }

    // Method 1: Using If-Else
    static boolean isLeapYearUsingIfElse(int year) {
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            return true;
        } else {
            return false;
        }
    }

    // Method 2: Using Ternary Operator
    static boolean isLeapYearUsingTernary(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    // Method 3: Using Boolean Variable
    static boolean isLeapYearUsingBoolean(int year) {
        boolean leap = (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
        return leap;
    }
}
