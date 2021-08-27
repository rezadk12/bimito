package question1;

import utils.CheckNumber;
import utils.WriteFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Safe safe = new Safe();
        if (args.length == 3) {  // // test mode for testcase in test.question1 package
            safe.setPassword(args[0]);
            safe.setSafeGears(Arrays.asList(args[1].split(",")));
            System.out.println(String.format("total number of movement is: %d", safe.calculateMinimumOfChange()));
            WriteFile.write(args[2], safe.calculateMinimumOfChange() + "");
        } else { // not test mode
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("enter k:");
                int k = in.nextInt();
                in.nextLine();
                String password;
                do {
                    System.out.println("enter password:");
                    password = in.nextLine();
                } while (password.length() != k || !CheckNumber.isNumeric(password));
                safe.setPassword(password);
                List<String> safeGears = new ArrayList<>();
                String gear = "";
                for (int i = 0; i < k; i++) {
                    System.out.println(String.format("enter gear number %d:", i + 1));
                    gear = in.nextLine();
                    if (!CheckNumber.isNumeric(gear) || !CheckNumber.IsAllNumber(gear) || gear.length() != 9) {
                        System.err.println("please enter all 1-9 number and just 9 character!!!");
                        i--;
                    } else
                        safeGears.add(gear);
                }
                safe.setSafeGears(safeGears);
                System.out.println(String.format("total number of movement is: %d", safe.calculateMinimumOfChange()));
                in.close();
            } catch (Exception e) {
                System.err.println("an error occurred;");
                Main.main(new String[0]);
            }
        }
    }
}
