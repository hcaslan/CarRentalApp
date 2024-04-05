package org.bilgeadam.utility;

public class OutputHelper {
    static final String ANSI_RED = "\u001B[31m";
    // ANSI escape code for green color
    static final String ANSI_GREEN = "\u001B[32m";
    // ANSI escape code to reset the color
    static final String ANSI_RESET = "\u001B[0m";

    public static void errorMessage(String Message) {
        System.out.println(ANSI_RED + Message + ANSI_RESET);
    }
    public static void successMessage(String Message) {
        System.out.println(ANSI_GREEN + Message + ANSI_RESET);
    }
}
