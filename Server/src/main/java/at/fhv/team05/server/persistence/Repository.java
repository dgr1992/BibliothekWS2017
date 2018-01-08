package at.fhv.team05.server.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Repository<TModel> implements AutoCloseable {
    protected abstract Class<TModel> getModelClass();

    public void save(List<TModel> models) {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = session.beginTransaction();
        for (TModel model : models) {
            session.saveOrUpdate(model);
        }
        transaction.commit();
    }

    public void save(TModel model) {
        List<TModel> models = new ArrayList<>(1);
        models.add(model);
        save(models);
    }

    public void delete(List<TModel> models) {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = session.beginTransaction();
        for (TModel model : models) {
            session.delete(model);
        }
        transaction.commit();
    }

    public void deleteById(Serializable id) {
        List<TModel> models = new ArrayList<>(1);
        models.add(getById(id));
        delete(models);
    }

    public TModel getById(Serializable id) {
        Session session = DatabaseConnection.getSession();
        return session.get(getModelClass(), id);
    }

    public List<TModel> list() {
        return list(null);
    }

    public List<TModel> list(Predicate<TModel> predicate) {
        Session session = DatabaseConnection.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TModel> query = builder.createQuery(getModelClass());
        Root<TModel> root = query.from(getModelClass());
        query.select(root);
        if (predicate == null) {
            return session.createQuery(query).list();
        }
        return session.createQuery(query).stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void close() {
        DatabaseConnection.getSession().close();
    }
}
