package PrepInsta;

public class GreatestOfThreeNumbers {
    public static void main(String[] args) {
        int num1 = 10, num2 = 20, num3 = 30;

        // Method 1: Using if-else statements
        System.out.println("Using If-Else: " + findGreatestUsingIfElse(num1, num2, num3));

        // Method 2: Using Ternary Operator
        System.out.println("Using Ternary Operator: " + findGreatestUsingTernary(num1, num2, num3));

        // Method 3: Using Math.max function
        System.out.println("Using Math.max(): " + findGreatestUsingMathMax(num1, num2, num3));
    }

    // Method 1: Using if-else statements
    static int findGreatestUsingIfElse(int num1, int num2, int num3) {
        if (num1 >= num2 && num1 >= num3) {
            return num1;
        } else if (num2 >= num1 && num2 >= num3) {
            return num2;
        } else {
            return num3;
        }
    }

    // Method 2: Using Ternary Operator
    static int findGreatestUsingTernary(int num1, int num2, int num3) {
        int temp = (num1 > num2) ? num1 : num2;
        return (temp > num3) ? temp : num3;
    }

    // Method 3: Using Math.max function
    static int findGreatestUsingMathMax(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }
}
