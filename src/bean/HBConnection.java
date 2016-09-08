package bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bill on 16/9/8.
 */
public class HBConnection {
    public static Connection defaultConnection;

    public static Connection getDefaultConnection() {
        if (defaultConnection != null) {
            return defaultConnection;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                System.out.println("连接JDBC Driver出错");
                e.printStackTrace();
                return null;
            }
            String userName = "user";
            String password = "password";
            String url = "jdbc:mysql://localhost:3306/webbase?useUnicode=true&characterEncoding=UTF-8";
            try {
                defaultConnection = DriverManager.getConnection(url, userName, password);
            } catch (SQLException e) {
                System.out.println("连接数据库出错");
                e.printStackTrace();
                return null;
            }
            return defaultConnection;
        }
    }
    public void closeDefaultConnection() {
        try {
            defaultConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
