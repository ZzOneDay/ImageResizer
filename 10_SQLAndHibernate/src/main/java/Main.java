import table.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import table.PurchaseList;


public class Main {


    public static void main(String[] args) {
        ConnectorToMySQL connectorToMySQL = ConnectorToMySQL.makeConnect();

        SessionFactory sessionFactory = connectorToMySQL.getSessionFactory();
        Session session = sessionFactory.openSession();

        Course course = session.get(Course.class, 1);
        System.out.println(course.getName());
        PurchaseList purchaseList = session.get(PurchaseList.class, 1);
        System.out.println(purchaseList.getSubscriptionDate());
    }
}

