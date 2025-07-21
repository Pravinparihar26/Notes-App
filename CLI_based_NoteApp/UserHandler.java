import java.util.HashMap;
import java.util.Scanner;

class UserHandler {

    private HashMap<String, HashPassword> uNamePass = new HashMap<>();
    private Scanner sc = new Scanner(System.in);
    private User obj = new User();

    void login() {
        System.out.print("Enter Username : ");
        String userName = sc.nextLine();
        if (uNamePass.containsKey(userName)) {
            try {
                System.out.print("Enter Password : ");
                String password = sc.nextLine();
                String salt = uNamePass.get(userName).getSalt();
                HashPassword HP = new HashPassword();
                String curHasedPassword = HP.hashPassword(password, salt);
                String storedPassword = uNamePass.get(userName).getHash();
                if ((curHasedPassword).equals(storedPassword)) {
                    System.out.println("Successfully Loged in");
                    System.out.println();
                    obj.logedIn(userName);
                } else {
                    System.out.println("error, Enter correct Password");
                    System.out.println();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Username doesn't exist");
            System.out.println();
        }
    }

    void register() {
        try {
            System.out.print("Enter Username : ");
            String userName = sc.nextLine();
            if (uNamePass.containsKey(userName)) {
                System.out.println("Username already exist");
            } else {
                System.out.print("Enter Password : ");
                String password = sc.nextLine();
                HashPassword HP = new HashPassword();
                String salt = HP.generateSalt();
                String hash = HP.hashPassword(password, salt);
                HP.setSalt(salt);
                HP.setHash(hash);
                uNamePass.put(userName, HP);
                System.out.println("Your Account Created Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}