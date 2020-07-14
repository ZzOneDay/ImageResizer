import CompositeKey.KeyLinkedPurchaseList;
import org.hibernate.*;
import table.*;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ConnectorToMySQL connectorToMySQL = ConnectorToMySQL.makeConnect();
        SessionFactory sessionFactory = connectorToMySQL.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        //Запрос данных из PurchaseList
        Query query = session.createSQLQuery("SELECT * FROM purchaselist");
        List<Student> students = session.createQuery("FROM Student").getResultList();
        List<Course> courses = session.createQuery("FROM Course").getResultList();

        List<Object[]> resultList = query.getResultList();

        for (Object[] purchase: resultList) {
            Student student = getStudentByName(students, purchase[0].toString());
            Course course = getCourseByName(courses, purchase[1].toString());
            if (student == null || course == null) {
                System.out.println("Ошибка");
                break;
            }
            Date purchaseDate = (Date) purchase[3];
            int priceCourse = course.getPrice();

            //Создание ключа
            KeyLinkedPurchaseList keyLinkedPurchaseList = new KeyLinkedPurchaseList(student,course);

            //Создаем объект для новой таблицы
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(keyLinkedPurchaseList);
            linkedPurchaseList.setStudentName(student.getName());
            linkedPurchaseList.setCourseName(course.getName());
            linkedPurchaseList.setPurchaseDate(purchaseDate);
            linkedPurchaseList.setCoursePrice(priceCourse);

            //Добавляем объект в сессии
            session.save(linkedPurchaseList);
        }

        //Сохраняем все изменения. Добавляем новую таблицу с новыми объектами
        session.getTransaction().commit();

        ConnectorToMySQL.closeConnect();
    }

    private static Student getStudentByName(List<Student> list, String name) {
        for (Student student: list) {
            if (student.getName().equals(name)) {
                return student;
            }
        } return null;
    }

    private static Course getCourseByName(List<Course> list, String name) {
        for (Course course: list) {
            if (course.getName().equals(name)) {
                return course;
            }
        } return null;
    }
}

