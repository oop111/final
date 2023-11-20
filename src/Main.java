import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static String username;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        Database d = new Database();
        User user = null;

        System.out.println("Do you have an account? (1-no, 2-yes)");
        int acc = scanner.nextInt();
        if (acc == 1) {
            user = d.saveUser();
            username = user.getUsername();
        }else if(acc == 2){
            user = d.authUser();
            username = user.getUsername();
        }else{
            System.out.println("Input 1 or 2 numbers!");
        }

        while (true) {

            d.notifyInMain(username);

            System.out.println("-----------------------------------");
            System.out.println("|Chose need action:               |");
            System.out.println("|1. Show shop devices             |");
            System.out.println("|2. Buy device                    |");
            System.out.println("|3. Notify messages               |");
            System.out.println("|4. Exit from application         |");
            System.out.println("-----------------------------------");

            int choice = scanner.nextInt();
            

            switch (choice){
                case 1:
                    d.viewDevices();
                    break;
                case 2:
                    d.buyDevices(username);
                    break;
                case 3:
                    user.checkObserverStatus(username);
                    break;
                case 4:
                    exit(0);
                    break;
                default:
                    System.out.println("Chose correct number!");
            }
        }
    }

}
