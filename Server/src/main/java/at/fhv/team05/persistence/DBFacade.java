package at.fhv.team05.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

import at.fhv.team05.domain.Book;
import at.fhv.team05.domain.Category;
import at.fhv.team05.domain.Copy;
import at.fhv.team05.domain.Rental;

public class DBFacade {
    private static DBFacade _instance;
    private SessionFactory _sessionfactory;


    private DBFacade(){
        //Config file needs to be in saved in the folder "resources"
        _sessionfactory = new Configuration().configure().buildSessionFactory();
    }

    public static DBFacade getInstance(){
        if(_instance == null){
           _instance = new DBFacade();
        }
        return _instance;
    }

    public Book getBook(String title) {
        Session session = _sessionfactory.openSession();
        Book book = null;

        try {
            book = (Book)session.createQuery("SELECT b FROM Book b WHERE b._title = :title")
                    .setParameter("title", title)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return book;
    }

    public List<Book> getAllBooks(){
        Session session = _sessionfactory.openSession();
        List<Book> books= null;

        try {
            books = (List<Book>)session.createQuery("FROM Book")
                    .list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return books;
    }

    public List<Category> getAllCategories(){
        Session session = _sessionfactory.openSession();
        List<Category> books= null;

        try {
            books = (List<Category>)session.createQuery("FROM Category ")
                    .list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return books;
    }

    public List<Copy> getAllCopies(){
        Session session = _sessionfactory.openSession();
        List<Copy> books= null;

        try {
            books = (List<Copy>)session.createQuery("FROM Copy")
                    .list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return books;
    }

    public List<Rental> getAllRental(){
        Session session = _sessionfactory.openSession();
        List<Rental> books= null;

        try {
            books = (List<Rental>)session.createQuery("FROM Rental")
                    .list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return books;
    }
}
