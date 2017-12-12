package at.fhv.team05.server.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.TimeZone;

public class DatabaseConnection {
    private static DatabaseConnection _databaseConnection;

    private SessionFactory _sessionFactory;
    private Session _session;

    private DatabaseConnection() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            _sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        if (_databaseConnection == null) {
            init();
        } else if (_databaseConnection._session == null
                || (!_databaseConnection._session.isConnected() && !_databaseConnection._session.isOpen())) {
            _databaseConnection._session = _databaseConnection._sessionFactory.openSession();
        }
        return _databaseConnection._session;
    }

    public static void close() throws Exception {
        if (_databaseConnection != null) {
            if (_databaseConnection._sessionFactory != null) {
                _databaseConnection._sessionFactory.close();
            }
            _databaseConnection._sessionFactory = null;
        }
    }

    public static void init() {
        if (_databaseConnection == null) {
            _databaseConnection = new DatabaseConnection();
            _databaseConnection._session = _databaseConnection._sessionFactory.openSession();
        }
    }
}