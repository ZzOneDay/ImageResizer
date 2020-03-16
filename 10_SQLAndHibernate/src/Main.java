import java.sql.*;

public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String pass = "test1234";



    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


    public static void main(String[] args) {
        String query = "SELECT course_name, " +
                "DateDIFF(max(subscription_date), min(subscription_date))/(count(student_name)*30) " +
                "FROM skillbox.purchaselist " +
                "GROUP BY course_name";

        try {
            connection = DriverManager.getConnection(url, user, pass);

            statement = connection.createStatement();

            resultSet = statement.executeQuery(query);
            System.out.println("Connect opened...");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
                System.out.println("Connect closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
