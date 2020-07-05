import org.hibernate.Session;
import table.Course;
import table.Student;
import table.Subscriptions;
import table.Teacher;

import javax.persistence.Query;
import java.util.List;

public class TestQueryHQL {

    public static void printStudentsName(Session session) {
        Query query = session.createQuery("from Student");
        List list = query.getResultList();
        for (Object object : list) {
            Student student = (Student) object;
            System.out.println(student.getName());
        }
    }

    public static void printTeachersName(Session session) {
        Query query = session.createQuery("from Teacher");
        List list = query.getResultList();
        for (Object object : list) {
            Teacher teacher = (Teacher) object;
            System.out.println(teacher.getName());
        }
    }

    public static void printCoursesName(Session session) {
        Query query = session.createQuery("from Course");
        List list = query.getResultList();
        for (Object object : list) {
            Course course = (Course) object;
            System.out.println(course.getName());
        }
    }

    public static void printSubscriptions(Session session) {
        Query query = session.createQuery("from Subscriptions");
        List list = query.getResultList();
        for (Object o : list) {
            Subscriptions subscriptions = (Subscriptions) o;
            int courseId = subscriptions.getKeyStudentIdCourseId().getCourseId();
            int studentId = subscriptions.getKeyStudentIdCourseId().getStudentId();
            Course course = session.get(Course.class,courseId);
            Student student = session.get(Student.class,studentId);
            System.out.println(student.getName() + " bought " + course.getName()
                    + ", " +subscriptions.getSubscriptionDate());
        }
    }


}
