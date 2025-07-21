package PrepInsta;

public class GreatestOfTwoNumbers {
    public static void main(String[] args) {
        greatestOfTwoNumbersUsingIfElse(6, 8);
        greatestOfTwoNumbersUsingTernary(99, 88);
        greatestOfTwoNumbersUsingMaxFunction(45, 45);
    }

    // Method 1: Using if-else Statements
    static void greatestOfTwoNumbersUsingIfElse(int num1, int num2) {
        if (num1 == num2) {
            System.out.println("The given numbers are Equal.");
        } else if (num1 > num2) {
            System.out.println("The First number is Greater than the Second Number: " + num1);
        } else {
            System.out.println("The Second number is Greater than the First Number: " + num2);
        }
    }

    // Method 2: Using Ternary Operator
    static void greatestOfTwoNumbersUsingTernary(int num1, int num2) {
//        int greatest = (num1 > num2) ? num1 : num2;
//        if (num1 == num2) {
//            System.out.println("The given numbers are Equal.");
//        } else {
//            System.out.println("The Greater number is: " + greatest);
//        }
        String answer=(num1==num2)?"Both are Equal Numbers":((num1>num2)?"Num1 "+num1+" is Greater":"Num2 "+num2+" is Greater");
        System.out.println(answer);
    }

    // Method 3: Using inbuilt max Function
    static void greatestOfTwoNumbersUsingMaxFunction(int num1, int num2) {
        if (num1 == num2) {
            System.out.println("Both numbers are Equal.");
        } else {
            System.out.println(Math.max(num1, num2) + " is Greater.");
        }
    }
}
