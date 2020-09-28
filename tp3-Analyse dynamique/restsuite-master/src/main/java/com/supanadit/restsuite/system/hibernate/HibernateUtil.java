package com.supanadit.restsuite.system.hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class HibernateUtil {
    private static StandardServiceRegistry registry;

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                com.supanadit.restsuite.logger.LogWriter.out("getSessionFactory", "getSessionFactoryBuilder");
                com.supanadit.restsuite.logger.LogWriter.out("getSessionFactory", "getSessionFactoryBuilder");
                com.supanadit.restsuite.logger.LogWriter.out("getSessionFactory", "getMetadataBuilder");
                com.supanadit.restsuite.logger.LogWriter.out("getSessionFactory", "getMetadataBuilder");
                com.supanadit.restsuite.logger.LogWriter.out("getSessionFactory", "StandardServiceRegistryBuilder");
                com.supanadit.restsuite.logger.LogWriter.out("getSessionFactory", "StandardServiceRegistryBuilder");
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();
                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);
                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();
                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                com.supanadit.restsuite.logger.LogWriter.out("getSessionFactory", "printStackTrace");
                e.printStackTrace();
                if (registry != null) {
                    com.supanadit.restsuite.logger.LogWriter.out("getSessionFactory", "destroy");
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            com.supanadit.restsuite.logger.LogWriter.out("shutdown", "destroy");
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}