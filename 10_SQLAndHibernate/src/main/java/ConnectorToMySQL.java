import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConnectorToMySQL {
    private static ConnectorToMySQL connectorToMySQL = new ConnectorToMySQL();
    private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml").build();
    private static Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    private static SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

    public static ConnectorToMySQL makeConnect() {
        return connectorToMySQL;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeConnect() {
        sessionFactory.close();
        registry.close();
        System.out.println("ConnectToMySQL is closed");
    }
}
