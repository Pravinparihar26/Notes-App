import java.util.Scanner;
import java.sql.*;

class UserHandler {

    private Scanner sc = new Scanner(System.in);
    private User obj = new User();

    public void login() {
        System.out.print("Enter Username : ");
        String userName = sc.nextLine();

        try {
            Connection con = DBConnection.connectMySQL();
            PreparedStatement username = con.prepareStatement("SELECT 1 FROM userdb WHERE username = ? LIMIT 1");
            username.setString(1, userName);
            ResultSet unResult = username.executeQuery();

            if (unResult.next()) {
                System.out.print("Enter Password : ");
                String password = sc.nextLine();

                PreparedStatement salt = con.prepareStatement("SELECT salt FROM userdb WHERE username = ? LIMIT 1");
                salt.setString(1, userName);
                ResultSet saltResult = salt.executeQuery();
                saltResult.next();
                String stringSalt = saltResult.getString(1);

                String curHasedPassword = HashPassword.hashPassword(password, stringSalt);

                PreparedStatement storedPassword = con.prepareStatement("SELECT password FROM userdb WHERE username = ? LIMIT 1");
                storedPassword.setString(1,userName);
                ResultSet passwordResult = storedPassword.executeQuery();
                passwordResult.next();
                String stringPassword = passwordResult.getString(1);

                if ((curHasedPassword).equals(stringPassword)) {
                    System.out.println("Successfully Loged in");
                    System.out.println();
                    obj.logedIn(userName);
                } else {
                    System.out.println("error, Enter correct Password");
                    System.out.println();
                }
            }
            else {
                System.out.println("Username doesn't exist");
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    void register() {
        try {
            System.out.print("Enter Username : ");
            String userName = sc.nextLine();

            Connection con = DBConnection.connectMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT 1 FROM userdb WHERE username = ? LIMIT 1");
            ps.setString(1, userName);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                System.out.println("Username already exist");
            } else {
                System.out.print("Enter Password : ");
                String password = sc.nextLine();
                String salt = HashPassword.generateSalt();
                String hash = HashPassword.hashPassword(password, salt);
                PreparedStatement insert = con.prepareStatement("INSERT INTO userdb VALUES (?,?,?)");
                insert.setString(1, userName);
                insert.setString(2, hash);
                insert.setString(3, salt);
                insert.executeUpdate();
                System.out.println("Your Account Created Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}