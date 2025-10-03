package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class GenericDAO<T> {
    protected final Class<T> entityClass;
    protected final EntityManager entityManager;

    public GenericDAO(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }

    public void save(T entity) {
        EntityTransaction tx = entityManager.getTransaction();
        entityManager.persist(entity);
    }

    public T findById(Long id) { return entityManager.find(entityClass, id); }

    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    public void update(T entity) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(entity);
        tx.commit();
    }

    public void delete(T entity) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(entity);
        tx.commit();
    }
}
