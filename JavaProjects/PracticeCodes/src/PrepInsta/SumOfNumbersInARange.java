package PrepInsta;

public class SumOfNumbersInARange {
    public static void main(String[] args) {
        // Calling Method 1 -> sumUsingLoop()
        System.out.println("The Sum of numbers in a given range is: " + sumUsingLoop(12, 15));

        // Calling Method 2 -> sumUsingFormula()
        System.out.println("The Sum of numbers in a given range is: " + sumUsingFormula(12, 15));

        // Calling Method 3 -> sumUsingRecursion()
        int a = 5;
        int b = 10;
        int sum = sumUsingRecursion(a, b);
        System.out.println("The sum using recursion is: " + sum);
    }

    // Method 1: Using a for loop (Brute Force)
    static int sumUsingLoop(int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }

    /*
       Method 2: Using the Formula
       Formula: sum = (end * (end + 1) / 2) - ((start - 1) * start / 2)
       This formula calculates the sum of numbers from 1 to `end` and subtracts the sum from 1 to `start - 1`
    */
    static int sumUsingFormula(int start, int end) {
        return (end * (end + 1) / 2) - ((start - 1) * start / 2);
    }

    // Method 3: Using Recursion
    static int sumUsingRecursion(int start, int end) {
        if (start > end)  // Base case: If start exceeds end, return 0
            return 0;
        return start + sumUsingRecursion(start + 1, end);
    }
}
