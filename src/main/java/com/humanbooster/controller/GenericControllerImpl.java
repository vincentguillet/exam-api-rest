package com.humanbooster.controller;

import com.humanbooster.dao.GenericDao;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * GenericControllerImpl is a generic implementation of a RESTful controller
 * that provides basic CRUD operations for any entity type.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the entity's identifier
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenericControllerImpl<T, ID> implements GenericController<T, ID>, GenericDao<T, ID> {

    protected final SessionFactory sessionFactory;
    private final GenericDao<T, ID> dao;

    /**
     * Constructor for GenericControllerImpl.
     *
     * @param sessionFactory the Hibernate SessionFactory used to create sessions
     * @param dao            the GenericDao instance for performing CRUD operations
     */
    public GenericControllerImpl(SessionFactory sessionFactory, GenericDao<T, ID> dao) {
        this.sessionFactory = sessionFactory;
        this.dao = dao;
    }

    /**
     * POST method to handle creation requests.
     * Creates a new entity in the database.
     * @param entity the entity to be created using the DAO
     */
    @POST
    @Override
    public void create(T entity) {
        dao.create(entity);
    }

    /**
     * GET method to handle read requests.
     * Reads an entity from the database by its ID.
     * @param id the ID of the entity to be read using the DAO
     * @return the entity if found, or null if not found
     */
    @GET
    @Path("/{id}")
    @Override
    public T read(@PathParam("id") ID id) {
        return dao.read(id);
    }

    /**
     * PUT method to handle update requests.
     * Updates an existing entity in the database.
     * @param id the ID of the entity to be updated using the DAO
     */
    @PUT
    @Path("/{id}")
    @Override
    public void update(@PathParam("id") ID id) {
        dao.update(id);
    }

    /**
     * DELETE method to handle delete requests.
     * Deletes an entity from the database by its ID.
     * @param id the ID of the entity to be deleted using the DAO
     */
    @DELETE
    @Path("/{id}")
    @Override
    public void delete(@PathParam("id") ID id) {
        dao.delete(id);
    }

    /**
     * GET method to retrieve all entities.
     * @return a list of all entities in the database
     */
    @GET
    @Override
    public List<T> getAll() {
        return dao.getAll();
    }
}
