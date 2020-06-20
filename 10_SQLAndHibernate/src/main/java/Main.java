import org.hibernate.Transaction;
import table.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        ConnectorToMySQL connectorToMySQL = ConnectorToMySQL.makeConnect();

        SessionFactory sessionFactory = connectorToMySQL.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();


        Teacher teacher = session.get(Teacher.class, 1);
        System.out.println(teacher.getName());
        for (Course course : teacher.getCoursesList()) {
            System.out.println(course.getName());
        }

//        System.out.println("GET COURSE -> LIST STUDENT");
//        Course anyCourse = session.get(Course.class,1);
//        System.out.println("COURSE NAME IS " + anyCourse.getName());
//        List<Student> students = anyCourse.getStudentList();
//        for (Student student : students) {
//            System.out.println(student.getName() + " " + student.getRegistrationDate());
//        }
//
//        System.out.println("GET STUDENT -> LIST COURSE");
//        Student anyStudent = session.get(Student.class, 1);
//        System.out.println("STUDENT NAME IS " + anyStudent.getName());
//        List<Course> courses = anyStudent.getCoursesList();
//        for (Course course : courses) {
//            System.out.println(course.getName());
//        }


//        Создаем новый курс.
//        Course course = getNewCourse("Новый новый курс", CourseType.DESIGN,1);
//        session.save(course);

//        Редактируем курс.
//        Course course = session.get(Course.class, 50);
//        course.setName("Актуальный курс");
//        session.save(course);

//        Удаляем курс по ID
//        Course course = session.get(Course.class, 48); //id не по порядку создаются
//        System.out.println(course.getName());
//        session.delete(course);

        transaction.commit();

        ConnectorToMySQL.closeConnect();
    }

//    private static Course getNewCourse(String name, CourseType courseType, int teacherId) {
//        Course course = new Course();
//        course.setName(name);
//        course.setType(courseType);
//        course.setTeacherId(teacherId); //Теперь работает через Teacher teacher;
//        return course;
//    }
}

