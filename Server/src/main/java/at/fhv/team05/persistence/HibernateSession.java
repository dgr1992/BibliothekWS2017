package at.fhv.team05.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    private static SessionFactory _sessionFactory;

    private HibernateSession() {
    }

    public static Session getSession() {
        if (_sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure();
                _sessionFactory = configuration.buildSessionFactory();
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }
        if (!_sessionFactory.getCurrentSession().isOpen()) {
            return _sessionFactory.openSession();
        }
        return _sessionFactory.getCurrentSession();
    }

    public static void closeFactory() {
        if (_sessionFactory != null) {
            _sessionFactory.close();
            _sessionFactory = null;
        }
    }
}
