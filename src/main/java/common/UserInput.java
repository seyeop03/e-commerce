package common;

import java.util.IllegalFormatException;
import java.util.Scanner;

public class UserInput {
    public static int inputInt(String message, Scanner sc) {
        try {
            System.out.print(message);
            return sc.nextInt();
        } catch (IllegalFormatException e) {
            return -1;
        }
    }

    public static Long inputLong(String message, Scanner sc) {
        try {
            System.out.print(message);
            return sc.nextLong();
        } catch (IllegalFormatException e) {
            return -1L;
        }
    }

    public static String inputString(String message, Scanner sc) {
            System.out.print(message);
            return sc.next();
    }
}
