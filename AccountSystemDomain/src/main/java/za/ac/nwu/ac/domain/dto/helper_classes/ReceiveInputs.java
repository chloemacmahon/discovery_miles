package za.ac.nwu.ac.domain.dto.helper_classes;

import java.util.Scanner;

public class ReceiveInputs {

    public static int getIntInput(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public static String getStringInput(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static double getDoubleInput(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
    }
}
