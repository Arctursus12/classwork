/* Terminal Calculator is a simple application meant to take as little space as possible, 
while providing as much function as possible. The following is a list of all usable keywords within the application.

add - is used to add two values.
sub - is used to subtract one value from another, where minuend is entered first, subtrahend second.
div - is used to divide a value by another value, where dividend is entered first, divisor second.
mul - is used to multiply two values.
mod - is used to calculate the modulus remainder of a value, where 101 mod 10 is 1.
abs - will return the Absolute value of the given value.
round - used to round of the decimal numbers to the nearest value.
sqrt - used to return the square root of a number.
cbrt - used to return the cube root of a number.
pow - returns the value of first argument raised to the power to second argument.
random - returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0.
log - returns the natural logarithm of a double value.
log10 - is used to return the base 10 logarithm of a double value.
log1p - returns the natural logarithm of the sum of the argument and 1.
exp - returns E raised to the power of a double value, where E is Euler's number and it is approximately equal to 2.71828.
sin - is used to return the trigonometric Sine value of a given double value.
cos - is used to return the trigonometric Cosine value of a given double value.
tan - is used to return the trigonometric Tangent value of a given double value.
asin - is used to return the trigonometric Arc Sine value of a given double value
acos - is used to return the trigonometric Arc Cosine value of a given double value.
atan - is used to return the trigonometric Arc Tangent value of a given double value.
sinh - is used to return the trigonometric Hyperbolic Cosine value of a given double value.
cosh - is used to return the trigonometric Hyperbolic Sine value of a given double value.
tanh - is used to return the trigonometric Hyperbolic Tangent value of a given double value.
degrees	- is used to convert the specified Radians angle to equivalent angle measured in Degrees.
radians	- is used to convert the specified Degrees angle to equivalent angle measured in Radians. 
break - is used to close the application.*/

import java.lang.Math;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Please input the operation you would like to perform.");
            System.out.println("Refer to howto.txt or top of file for keyword reference.");
            String input = in.nextLine();

            // Add more variables here if you intend to add more operations
            double x;
            double y;

            // Follow the general template if you intend to add more operations
            // if(input.equals("operation")) { get x, get y (optional), print result }
            if (input.equals("add")) {
                x = in.nextDouble();
                y = in.nextDouble();
                System.out.println(x + y);
            } else if (input.equals("sub")) {
                x = in.nextDouble();
                y = in.nextDouble();
                System.out.println(x - y);
            } else if (input.equals("div")) {
                x = in.nextDouble();
                y = in.nextDouble();
                System.out.println(x / y);
            } else if (input.equals("mul")) {
                x = in.nextDouble();
                y = in.nextDouble();
                System.out.println(x * y);
            } else if (input.equals("mod")) {
                x = in.nextDouble();
                y = in.nextDouble();
                System.out.println(x % y);
            } else if (input.equals("abs")) {
                x = in.nextDouble();
                System.out.println(Math.abs(x));
            } else if (input.equals("round")) {
                x = in.nextDouble();
                System.out.println(Math.round(x));
            } else if (input.equals("sqrt")) {
                x = in.nextDouble();
                System.out.println(Math.sqrt(x));
            } else if (input.equals("cbrt")) {
                x = in.nextDouble();
                System.out.println(Math.cbrt(x));
            } else if (input.equals("pow")) {
                x = in.nextDouble();
                y = in.nextDouble();
                System.out.println(Math.pow(x, y));
            } else if (input.equals("random")) {
                System.out.println(Math.random());
            } else if (input.equals("log")) {
                x = in.nextDouble();
                System.out.println(Math.log(x));
            } else if (input.equals("log10")) {
                x = in.nextDouble();
                System.out.println(Math.log10(x));
            } else if (input.equals("log1p")) {
                x = in.nextDouble();
                System.out.println(Math.log1p(x));
            } else if (input.equals("exp")) {
                x = in.nextDouble();
                System.out.println(Math.exp(x));
            } else if (input.equals("sin")) {
                x = in.nextDouble();
                System.out.println(Math.sin(x));
            } else if (input.equals("cos")) {
                x = in.nextDouble();
                System.out.println(Math.cos(x));
            } else if (input.equals("tan")) {
                x = in.nextDouble();
                System.out.println(Math.tan(x));
            } else if (input.equals("asin")) {
                x = in.nextDouble();
                System.out.println(Math.asin(x));
            } else if (input.equals("acos")) {
                x = in.nextDouble();
                System.out.println(Math.acos(x));
            } else if (input.equals("atan")) {
                x = in.nextDouble();
                System.out.println(Math.atan(x));
            } else if (input.equals("sinh")) {
                x = in.nextDouble();
                System.out.println(Math.sinh(x));
            } else if (input.equals("cosh")) {
                x = in.nextDouble();
                System.out.println(Math.cosh(x));
            } else if (input.equals("tanh")) {
                x = in.nextDouble();
                System.out.println(Math.tanh(x));
            } else if (input.equals("degrees")) {
                x = in.nextDouble();
                System.out.println(Math.toDegrees(x));
            } else if (input.equals("radians")) {
                x = in.nextDouble();
                System.out.println(Math.toRadians(x));
            } else if (input.equals("break")) {
                System.exit(0);
            } else {
                System.out.println("Invalid operation");
            }
            in.nextLine(); // Consume the new line character
            in.close();
        }
    }
}
