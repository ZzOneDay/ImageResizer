import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConnectorToMySQL {
    private static ConnectorToMySQL connectorToMySQL;
    private static StandardServiceRegistry registry;
    private static Metadata metadata;
    private static SessionFactory sessionFactory;

    public static ConnectorToMySQL makeConnect() {
        LogControl.checkLogFile();
        if (connectorToMySQL == null) {
            connectorToMySQL = new ConnectorToMySQL();
        }
        if (registry == null) {
            registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
        }
        if (metadata == null) {
            metadata = new MetadataSources(registry).getMetadataBuilder().build();
        }

        System.out.println("ConnectToMySQL is opened");
        return connectorToMySQL;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
    }

    public static void closeConnect() {
        sessionFactory.close();
        registry.close();
        System.out.println("ConnectToMySQL is closed");
    }


}
