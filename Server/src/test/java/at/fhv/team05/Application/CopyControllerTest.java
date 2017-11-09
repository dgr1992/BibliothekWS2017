package at.fhv.team05.Application;

import at.fhv.team05.Application.medium.BookController;
import at.fhv.team05.domain.medium.Book;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.persistence.DatabaseConnection;
import at.fhv.team05.persistence.Repository;
import at.fhv.team05.persistence.RepositoryFactory;
import org.junit.Test;

public class CopyControllerTest {
    @Test
    public void searchCopyByCopyNumber() throws Exception {
    }

    //TODO JUNIT test ist DB abhängig --> ändern
    @Test
    public void getCopiesByMediumID() throws Exception {
        DatabaseConnection.init();

        Repository<Book> rep = RepositoryFactory.getRepositoryInstance(Book.class);
        BookController bookController = BookController.getInstance();
        CopyController copyController = CopyController.getInstance();
        Book book = rep.getById(1);

        IMediumDTO medium = bookController.getDTO(book);
        System.out.println(copyController.getCopiesByMediumID(medium));


    }

}