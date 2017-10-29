package at.fhv.team05.persistence;

public abstract class RepositoryCreator {

    public Repository getRepository(Class classtype) {
        return createRepository(classtype);
    }

    protected abstract Repository createRepository(Class classtype);

}
