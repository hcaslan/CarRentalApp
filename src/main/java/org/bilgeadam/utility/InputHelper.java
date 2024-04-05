package org.bilgeadam.utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {
    static Scanner sc = new Scanner(System.in);

    public static String getStringInput(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    public static int getIntegerInput(String message) {
        System.out.println(message);
        int input;
        while (true) {
            try {
                input = sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                OutputHelper.errorMessage("Incorrect entry! Enter an integer:");
                sc.nextLine();
            }
        }
        return input;
    }

    public static Double getDoubleInput(String message) {
        System.out.println(message);
        double input;
        while (true) {
            try {
                input = sc.nextDouble();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                OutputHelper.errorMessage("Incorrect entry! Enter again: ");
                sc.nextLine();
            }
        }
        return input;
    }

    public static Long getLongInput(String message) {
        System.out.println(message);
        long input;
        while (true) {
            try {
                input = sc.nextLong();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                OutputHelper.errorMessage("Incorrect entry! Enter again: ");
                sc.nextLine();
            }
        }
        return input;
    }

    public static Boolean getBooleanInput(String message) {

        boolean input;
        while (true) {
            System.out.println(message);
            try {
                String inputS = sc.nextLine();
                if(inputS.equals("T")){
                    input = true;
                    break;
                } else if(inputS.equals("F")){
                    input = false;
                    break;
                }
            } catch (InputMismatchException e) {
                OutputHelper.errorMessage("Incorrect entry! Enter again: ");
                sc.nextLine();
            }
        }
        return input;
    }
}
