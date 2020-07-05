import org.hibernate.*;


public class Main {


    public static void main(String[] args) {
        ConnectorToMySQL connectorToMySQL = ConnectorToMySQL.makeConnect();

        SessionFactory sessionFactory = connectorToMySQL.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

//        Query query = session.createQuery("from Subscriptions");
//        List list = query.getResultList();
//        for (Object o : list) {
//            Subscriptions subscriptions = (Subscriptions)  o;
//        }

        TestQueryHQL.printSubscriptions(session);
        TestQueryHQL.printCoursesName(session);
        TestQueryHQL.printTeachersName(session);
        TestQueryHQL.printStudentsName(session);

        transaction.commit();

        ConnectorToMySQL.closeConnect();
    }


}

