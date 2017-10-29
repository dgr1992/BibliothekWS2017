package at.fhv.team05.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class Repository<TModel> {
    private SessionFactory _sessionFactory;

    protected abstract Class<TModel> getModelClass();

    public TModel getById(Serializable id) {
        Session session = _sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TModel model = (TModel) session.get(getModelClass(), id);
        transaction.commit();
        session.close();
        return model;
    }

    public List<TModel> list() {
        Session session = _sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TModel> query = builder.createQuery(getModelClass());
        Root<TModel> root = query.from(getModelClass());
        query.select(root);
        List<TModel> models = session.createQuery(query).list();
        transaction.commit();
        session.close();
        return models;
    }

    public <TModel> void update(TModel model) {
        Session session = _sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(model);
        transaction.commit();
        session.close();
    }
}
