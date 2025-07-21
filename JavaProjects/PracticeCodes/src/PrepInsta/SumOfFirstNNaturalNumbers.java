package PrepInsta;

public class SumOfFirstNNaturalNumbers {
    public static void main(String[] args) {
        int number = 6;

        // Calling Method 1 -> sumUsingLoop()
        System.out.println("The Sum of First N Natural numbers is: " + sumUsingLoop(number));

        // Calling Method 2 -> sumUsingFormula()
        System.out.println("The Sum of First N Natural numbers is: " + sumUsingFormula(number));

        // Calling Method 3 -> sumUsingRecursion()
        System.out.println("The Sum of First N Natural numbers is: " + sumUsingRecursion(number));
    }

    // Method 1: Using a for loop
    static int sumUsingLoop(int num) {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            sum += i;
        }
        return sum;
    }

    /*
       Method 2: Using the formula for the sum of the first N natural numbers
       Formula: Sum = (Num * (Num + 1)) / 2
    */
    static int sumUsingFormula(int num) {
        return (num * (num + 1)) / 2;
    }

    // Method 3: Using recursion
    static int sumUsingRecursion(int num) {
        if (num == 0)
            return 0;
        return num + sumUsingRecursion(num - 1);
    }
}
