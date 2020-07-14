import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class LogControl {
    public static void checkLogFile () {
        long maxLogSize = 2097152;
        try {
            File file = new File("log/Hibernate.log");
            if (file.length() > maxLogSize) {
                System.out.println("Внимание: log/Hibernate.log превыешает допустимый размер файла 2MB");
                System.out.println("Нажмите Enter чтобы удалить файл: log/Hibernate.log");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String string = bufferedReader.readLine().trim();
                if (string.equals("")) {
                    file.delete();
                    System.out.print("LogControl" + file.getName() + " is deleted.");
                }
            }
        } catch (Exception e) {
            System.out.println("Hibernate.log - не найден");
        }
    }
}
