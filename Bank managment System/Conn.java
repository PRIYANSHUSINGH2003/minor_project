import java.sql.*;

public class Conn {

    Connection c;
    Statement a;
    public Conn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "110044");
            a = c.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
