package com.lynas;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author imssbora
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        Properties properties = loadProperty();
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder =
                        new StandardServiceRegistryBuilder();

                Map<String, Object> settings = new HashMap<>();
                settings.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.Oracle10gDialect");
                settings.put(Environment.URL, properties.getProperty("hibernate.connection.url"));
                settings.put(Environment.USER, properties.getProperty("hibernate.connection.username"));
                settings.put(Environment.PASS, properties.getProperty("hibernate.connection.password"));
                settings.put(Environment.SHOW_SQL, true);
                settings.put("hibernate.current_session_context_class", "thread");

                // HikariCP settings

                // Maximum waiting time for a connection from the pool
                settings.put("hibernate.hikari.connectionTimeout", "20000");
                // Minimum number of ideal connections in the pool
                settings.put("hibernate.hikari.minimumIdle", "10");
                // Maximum number of actual connection in the pool
                settings.put("hibernate.hikari.maximumPoolSize", "20");
                // Maximum time that a connection is allowed to sit ideal in the pool
                settings.put("hibernate.hikari.idleTimeout", "300000");
                settings.put("hibernate.hikari.connectionTestQuery", "SELECT 1 FROM DUAL");

                registryBuilder.applySettings(settings);

                StandardServiceRegistry registry = registryBuilder.build();
                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(User.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private static Properties loadProperty()  {
        Properties prop = new Properties();
        try {
            InputStream input = HibernateUtil.class.getClassLoader().getResourceAsStream("application.properties");
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }
}