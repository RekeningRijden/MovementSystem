package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Base class for all persistence database actions.
 *
 * @param <T> The database entity class type used for all operations
 * @author Sam
 */
public abstract class AbstractDao<T> {

    @PersistenceContext(unitName = "MovementPU")
    private EntityManager entityManager;

    /**
     * @return Entity Type for all operations.
     */
    protected abstract Class<T> getEntityClass();

    /**
     * @return Returns the entityManager
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Adds the entity to the database
     *
     * @param entity The entity that will be added to the database
     * @return The newly added entity
     */
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    /**
     * Updates an existing entity in the database
     *
     * @param entity The new entity to be persisted
     * @return The updated entity
     */
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    /**
     * Deletes an entity from the database
     *
     * @param entity The entity to remove from the database
     */
    public void remove(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    /**
     * Empty the internal SQL instructions cache, and execute it immediately to
     * the database. FROM:
     * http://stackoverflow.com/questions/4275111/correct-use-of-flush-in-jpa-hibernate
     */
    public void flush() {
        entityManager.flush();
    }

    public int count() {
        return entityManager.createNamedQuery(getEntityClass() + ".count", getEntityClass()).getResultList().size();
    }

    /**
     * Finds an entity by it's unique id
     *
     * @param id The unique id of the entity
     * @return The entity with the corresponding id
     */
    public T findById(Object id) {
        return entityManager.find(getEntityClass(), id);
    }

    /**
     * @return all results from a Entity Type table.
     */
    public List<T> getAll() {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> c = qb.createQuery(getEntityClass());
        c.from(getEntityClass());

        TypedQuery<T> query = entityManager.createQuery(c);
        return query.getResultList();
    }

    /**
     * Remove all characters not allowed in a JPQL query from a String.
     *
     * @param fieldName to remove the characters from.
     * @return String left after character replacement.
     */
    protected String makeFieldNameJqplSafe(String fieldName) {
        return fieldName.replaceAll("[^0-9a-zA-Z_\\.]", "");
    }
}
