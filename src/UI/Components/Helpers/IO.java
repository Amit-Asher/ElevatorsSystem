package UI.Components.Helpers;

import java.util.Scanner;

public class IO {

    private final static Scanner sc = new Scanner(System.in);

    public static int readInt(String msg) {
        System.out.print(msg);
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    public static String readLine() {
        return sc.nextLine();
    }

    public static int readChoice(String msg)
    {
        int choice;

        try {
            System.out.print(msg);
            choice = Integer.parseInt(readLine());
        }
        catch (Exception e) {
            choice = -1;
        }
        return choice;
    }
}
