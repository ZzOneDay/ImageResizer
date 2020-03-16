import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String pass = "test1234";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT course_name AS 'Название курса', datediff(max(subscription_date), min(subscription_date))/(count(student_name)*30) AS 'Среднее количество покупок в месяц' FROM skillbox.purchaselist group by course_name;");

            while(resultSet.next()) {
                System.out.println(resultSet.getArray("Название курса") + " | " + resultSet.getString("Среднее количество покупок в месяц"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
