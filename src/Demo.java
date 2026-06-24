import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String URL = "jdbc:mysql://localhost:3306/nov";
        String user = "root";
        String password = "joker";
        String dpath = "com.mysql.cj.jdbc.Driver";

        String SQL = "SELECT * FROM STUDENT WHERE ID = ?";

        Scanner scanner = new Scanner(System.in);

        try {

            Class.forName(dpath);

            con = DriverManager.getConnection(URL, user, password);

            ps = con.prepareStatement(SQL);

            System.out.println("Enter Your ID");
            int id = scanner.nextInt();

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println(rs.getInt(1) + " "+ rs.getString(2) + " "+ rs.getInt(3) + " "+ rs.getInt(4) + " "+ rs.getInt(5));

            } else {
                System.out.println("WRONG ID...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                if (con != null) {
                    con.close();
                }

                if (scanner != null) {
                    scanner.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}