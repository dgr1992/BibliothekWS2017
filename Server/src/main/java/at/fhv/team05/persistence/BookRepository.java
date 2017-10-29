package at.fhv.team05.persistence;

import at.fhv.team05.domain.Book;

public class BookRepository extends Repository<Book> {

    @Override
    protected Class<Book> getModelClass() {
        return Book.class;
    }
}
