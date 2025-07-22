import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/userdata";
    private static final String username = "root";
    private static final String password = "Password";

    public static Connection connectMySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connected to the database!");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
        return null;
    }
}
