package labs.java_lab_2;

import labs.java_lab_2.calculator.Calculator;
import java.util.Scanner;

/**
 * Demonstration of expression calculator usage
 */
public class Main {
    
    /**
     * Application entry point
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        // Adding custom summation function
        calc.addFunction("sum", params -> {
            double total = 0;
            for (double value : params) {
                total += value;
            }
            return total;
        });

        Scanner scanner = new Scanner(System.in);
        boolean continueWork = true;

        System.out.println("Mathematical Expression Calculator ");
        System.out.println("Enter empty line to exit");
        System.out.println();

        while (continueWork) {
            System.out.print("Expression: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continueWork = false;
                System.out.println("Terminating.");
            } else {
                try {
                    double result = calc.processExpression(input);
                    System.out.println("Result: " + result);
                } catch (Exception error) {
                    System.err.println("Error: " + error.getMessage());
                }
                System.out.println();
            }
        }
        
        scanner.close();
    }
}