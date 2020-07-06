import org.hibernate.*;


public class Main {

    public static void main(String[] args) {
        ConnectorToMySQL connectorToMySQL = ConnectorToMySQL.makeConnect();
        SessionFactory sessionFactory = connectorToMySQL.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        TestQueryHQL.printSubscriptions(session);
        TestQueryHQL.printCoursesName(session);
        TestQueryHQL.printTeachersName(session);
        TestQueryHQL.printStudentsName(session);

        transaction.commit();

        ConnectorToMySQL.closeConnect();
    }
}

