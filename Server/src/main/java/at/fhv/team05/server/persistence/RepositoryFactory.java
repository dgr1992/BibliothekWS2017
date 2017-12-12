package at.fhv.team05.server.persistence;


import java.util.HashMap;
import java.util.Map;

public class RepositoryFactory {

    private static Map<Class, Repository> _repositoryMap = new HashMap<>(11);

    private RepositoryFactory() {
    }

    private static <T> void createRepositoryInstance(Class<T> c) {
        _repositoryMap.put(c, new Repository<T>() {
            @Override
            protected Class<T> getModelClass() {
                return c;
            }
        });
    }

    public static <T> Repository<T> getRepositoryInstance(Class<T> c) {
        if (!_repositoryMap.containsKey(c)) {
            createRepositoryInstance(c);
        }
        return _repositoryMap.get(c);
    }

    public static void close() {
        _repositoryMap.forEach((Class aClass, Repository repository) -> {
            try {
                repository.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        _repositoryMap.clear();
    }
}
