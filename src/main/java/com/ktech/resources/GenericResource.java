package com.ktech.resources;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ktech.repositories.GenericRepository;

import dev.morphia.query.UpdateOperations;

/**
 * Class with generic CRUD methods
 * 
 * @author aleal
 *
 * @param <T> Entity type
 */
public abstract class GenericResource<T, R extends GenericRepository<T>> {

	protected R repository;

	public GenericResource(R repository) {
		this.repository = repository;
	}
	
	/**
	 * Generic method that returns all entities in DB
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<T> entities = this.repository.findAll();
		return Response.ok(entities).build();
	}

	/**
	 * Generic method that returns an entity by its ID
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getById(@PathParam("id") String id) {
		T entity = this.repository.findById(id);
		if (Objects.isNull(entity)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	/**
	 * Generic method that creates an entity in DB
	 * 
	 * @param entity
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Valid T entity) {
		T newEntity = this.repository.create(entity);
		return Response.ok(newEntity).build();
	}

	/**
	 * Generic method that updates an entity in DB
	 * 
	 * @param entity
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") String id, T entity) {
		T updatedEntity = this.repository.update(id, this.buildUpdateOperations(entity));
		return Response.ok(updatedEntity).build();
	}

	/**
	 * Generic method that deletes an entity in DB by its ID
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response delete(@PathParam("id") String id) {
		T entity = this.repository.delete(id);
		if (Objects.isNull(entity)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	/**
	 * Method that must be overridden in the child class if update is present
	 * @param entity
	 * @return
	 */
	protected UpdateOperations<T> buildUpdateOperations(T entity) {
		throw new NotSupportedException("This method should be overridden if update method is available");
	}
	
}
