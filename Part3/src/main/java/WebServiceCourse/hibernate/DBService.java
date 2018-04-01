package WebServiceCourse.hibernate;


//import dbService.dao.UsersDAO;
//import dbService.dataSets.UsersDataSet;
import WebServiceCourse.dbDervice.DBException;
import WebServiceCourse.hibernate.dao.UsersDao;
import WebServiceCourse.hibernate.dao.UsersGroupDao;
import WebServiceCourse.hibernate.dataset.UserDataSet;
import WebServiceCourse.hibernate.dataset.UsersGroup;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;


import java.sql.Connection;

public class DBService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "create";

    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }

    @SuppressWarnings("UnusedDeclaration")
    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(UsersGroup.class);


        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
        configuration.setProperty("hibernate.connection.username", "tully");
        configuration.setProperty("hibernate.connection.password", "tully");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(UsersGroup.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "hash");
        configuration.setProperty("hibernate.connection.password", "hash");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }


    public UserDataSet getUser(long id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UsersDao dao = new UsersDao(session);
            UserDataSet dataSet = dao.get(id);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public long addUser(String name) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDao dao = new UsersDao(session);
            long id = dao.insertUser(name);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void delUser(String name){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsersDao dao = new UsersDao(session);
        dao.deleteUser(name);
        transaction.commit();
        session.close();
    }

    public void printConnectInfo() {
        try {
            SessionFactoryImplementor sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            System.out.println("DB name: " + sessionFactoryImpl.getProperties().get("hibernate.connection.url"));
            //System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            //System.out.println("Driver: " + connection.getMetaData().getDriverName());
            //System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public long saveGroup(String name){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsersGroupDao ugd = new UsersGroupDao(session);
        long id = ugd.insertGroup(name);
        transaction.commit();
        session.close();
        return id;
    }
}
