package common;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

    public static int inputInt(String message, Scanner sc) {
        int input = -1;

        while (true) {
            try {
                System.out.print(message);
                input = sc.nextInt();
                break;  // 입력 성공 시 while 루프 탈출
            } catch (InputMismatchException e) {
                sc.nextLine();  //잘못된 입력으로 남아있는 데이터를 버퍼에서 비움
                System.out.println("잘못된 입력입니다. 숫자를 입력해 주세요.");
            }
        }
        return input;
    }

    public static String inputString(String message, Scanner sc) {
        System.out.print(message);
        return sc.next();
    }

    public static Long inputLong(String message, Scanner sc) {
        Long input = -1L;

        while (true) {
            try {
                System.out.print(message);
                input = sc.nextLong();
                break;
            } catch (InputMismatchException e) {
                sc.nextLine(); //잘못된 입력으로 남아있는 데이터를 버퍼에서 비움
                System.out.println("잘못된 입력입니다. 숫자를 입력해 주세요.");
            }
        }
        return input;
    }
}
