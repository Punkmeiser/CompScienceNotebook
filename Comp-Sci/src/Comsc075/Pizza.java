package Comsc075;/*
 * Duy Nguyen
 * Comsc075.Pizza.java
 * Calculates area of pizza/size/price based off input length/width/toppings
 */

import java.util.Scanner;

public class Pizza {
    public static void main(String[] args) {

        final double SMALLPIZZA = 7.25;
        final double MEDIUMPIZZA = 10.50;
        final double LARGEPIZZA = 14.75;

        Scanner input = new Scanner(System.in);

        // Takes input and repeats prompt if input is invalid
        double area;
        do {
            System.out.print("Enter length of pizza in cm: ");
            double length = input.nextDouble();

            System.out.print("Enter width of pizza in cm: ");
            double width = input.nextDouble();

            area = length * width;

            if (area <= 0) {
                System.out.printf("The area (%.2f) " +
                        "must be greater than zero.\n", area);
            }
            else if (area > 1000) {
                System.out.printf("The area (%.2f) " +
                        "must be less than or equal to 1000.\n", area);
            }
        } while ((area <= 0) || (area > 1000));

        int toppings;
        do {
            System.out.print("Enter number of toppings: ");
            toppings = input.nextInt();
            if (toppings < 0) {
                System.out.println("Number of toppings " +
                        "must be greater or equal to zero.\n");
            }
        } while (toppings < 0);

        // Calculates size and price based off area
        String size;
        double price;

        System.out.printf("\nThe area of your pizza " +
                "is %.2f square cm.\n", area);
        if (area <= 225) {
            size = "small";
            price = SMALLPIZZA;
        }
        else if (area <= 650) {
            size = "medium";
            price = MEDIUMPIZZA;
        }
        else {
            size = "large";
            price = LARGEPIZZA;
        }

        price += toppings * 0.85;

        System.out.printf("This is a %s pizza.\n", size);
        System.out.printf("Your pizza costs %.2f euros.\n", price);

    }
}
