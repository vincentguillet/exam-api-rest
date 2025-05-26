package com.humanbooster.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Generic DAO implementation for basic CRUD operations using Hibernate.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the entity's identifier
 */
public abstract class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {

    protected final Class<T> entityClass;
    protected SessionFactory sessionFactory;

    /**
     * Constructor for GenericDaoImpl.
     *
     * @param sessionFactory the Hibernate SessionFactory
     * @param entityClass    the class of the entity this DAO manages
     */
    public GenericDaoImpl(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    /**
     * Creates a new entity in the database.
     * @param entity the entity to be created
     */
    @Override
    public void create(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    /**
     * Reads an entity from the database by its ID.
     *
     * @param id the identifier of the entity to be read
     * @return the entity if found, or null if not found
     */
    @Override
    public T read(ID id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            T entity = session.get(entityClass, id);
            session.getTransaction().commit();
            return entity;
        }
    }

    /**
     * Updates an existing entity in the database.
     *
     * @param id the ID of the entity to be updated
     */
    @Override
    public void update(ID id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            T entity = session.get(entityClass, id);
            session.merge(entity);
            session.getTransaction().commit();
        }
    }

    /**
     * Deletes an entity from the database by its ID.
     *
     * @param id the ID of the entity to be deleted
     */
    @Override
    public void delete(ID id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            T entity = session.find(entityClass, id);
            if (entity != null) session.remove(entity);
            session.getTransaction().commit();
        }
    }

    /**
     * Retrieves all entities of the specified type from the database.
     *
     * @return a list of all entities
     */
    @Override
    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<T> entities = session.createQuery("from " + entityClass.getName(), entityClass).list();
            session.getTransaction().commit();
            return entities;
        }
    }
}