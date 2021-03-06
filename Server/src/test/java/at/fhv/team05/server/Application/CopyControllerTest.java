package at.fhv.team05.server.Application;

import at.fhv.team05.server.Application.medium.BookController;
import at.fhv.team05.server.domain.medium.Book;
import at.fhv.team05.library.dtos.CopyDTO;
import at.fhv.team05.library.dtos.IMediumDTO;
import at.fhv.team05.server.persistence.DatabaseConnection;
import at.fhv.team05.server.persistence.Repository;
import at.fhv.team05.server.persistence.RepositoryFactory;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

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
        List<CopyDTO> list = copyController.getCopiesByMediumID(medium).getListDTO();

        assertThat(list, hasSize(5));
    }

}