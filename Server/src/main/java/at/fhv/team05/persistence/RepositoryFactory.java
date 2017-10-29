package at.fhv.team05.persistence;

public class RepositoryFactory extends RepositoryCreator {

    @Override
    protected Repository createRepository(Class classtype) {

        Repository repo = null;

        if (classtype.getName().contains("Book")) {
            return repo = new BookRepository();
        }
        if (classtype.getName().contains("Category")) {
            return repo = new CategoryRepository();
        }


        else {
            return null;
        }

    }
}
