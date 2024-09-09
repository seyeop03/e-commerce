package common;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

    public static int inputInt(String message, Scanner sc) {
        try {
            System.out.print(message);
            int input = sc.nextInt();
            sc.nextLine();
            return input;
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    public static String inputString(String message, Scanner sc) {
        System.out.print(message);
        return sc.next();
    }

    public static Long inputLong(String message, Scanner sc) {
        try {
            System.out.print(message);
            return sc.nextLong();
        } catch (InputMismatchException e) {
            return -1L;
        }
    }
}
