package PrepInsta;

public class EvenOrOdd {
    public static void main(String[] args) {
       evenOrOdd1(5);
       evenOrOdd2(6);
       int number=29;
       if (isEvenOrOdd3(number))
        System.out.println("The given Number is Even : "+number);
       else
        System.out.println("The given Number is Odd : "+number);

    }

    //Method 1 : Using Brute Force
    public static void evenOrOdd1(int num){
       if(num%2==0)
           System.out.println("The given Number is Even : "+num);
       else
           System.out.println("The given Number is Odd  : "+num);
    }

    // Method 2 : Using Ternary Operator
    public  static  void evenOrOdd2(int num){
        String status=num%2==0?"is Even":"is Odd";
        System.out.println("The given number "+status+" : "+num);
    }

    /*
    Method 3 : Using Bitwise Operator
This Method uses bitwise operators to check if a given number is Even or Odd.

Bitwise Operators
In computer programming, a bitwise operation operates on a bit string, a bit array or a binary numeral at the level of its individual bits. It is a fast and simple action, basic to the higher-level arithmetic operations and directly supported by the processor
Algorithm
The working of the above code is as follows,

If we have any number ‘n‘ doing bitwise ‘&‘ operation will give resultant as
1: If n is odd
0: if n is even
    */
    public static boolean isEvenOrOdd3(int num) {
        return (num & 1) == 0; // Fixed bitwise operation to return boolean
    }
}
