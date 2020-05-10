package com.ktech.repositories;

import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;

import com.ktech.MongoConnection;
import com.ktech.utils.GenericUtils;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public abstract class GenericRepository<E> {

	private Datastore datastore;
	private final Class<E> entityClass;

	public GenericRepository() {
		this.entityClass = GenericUtils.getSuperClassParameterizedType(this);
	}

	protected Datastore getDatastore() {
		if(Objects.isNull(this.datastore)) {
		  this.datastore = MongoConnection.getDatastore();
		}
		return this.datastore;
	}
	
	/**
	 * Builds getByField Query<Entity>  
	 * @param field
	 * @param value
	 * @return
	 */
	protected Query<E> buildGetByFieldQuery(String field, Object value) {
		return this.getDatastore().createQuery(entityClass).field(field).equal(value);
	}

	/**
	 * Gets all entities
	 * @return
	 */
	public List<E> getAll() {
		return this.getDatastore().createQuery(entityClass).find().toList();
	}
	
    /**
     * Gets entity by its id
     * @param id
     * @return
     */
	public E getById(String id) {
		return this.buildGetByFieldQuery("_id", new ObjectId(id)).first();
	}

	/** 
	 * Gets an entity by field
	 * @param field
	 * @param value
	 * @return
	 */
	public E getOneByField(String field, Object value) {
		return this.buildGetByFieldQuery(field, value).first();
	}

	/**
	 * Creates an new Entity
	 * @param entity
	 * @return
	 */
	public E create(E entity) {
		this.getDatastore().save(entity);
		return entity;
	}

	/**
	 * Updates by id according to the informed operations 
	 * @param id
	 * @param operations
	 * @return
	 */
	public E update(String id, UpdateOperations<E> operations) {
		return this.getDatastore().findAndModify(this.buildGetByFieldQuery("_id", new ObjectId(id)), operations);
	}

	/**
	 * Deletes and entity
	 * @param id
	 * @return
	 */
	public E delete(String id) {
		return this.getDatastore().findAndDelete(this.buildGetByFieldQuery("_id", new ObjectId(id)));
	}

	/**
	 * Initiate operations object in order to allowing an update 
	 * @return
	 */
	public UpdateOperations<E> createUpdateOperations() {
		return this.getDatastore().createUpdateOperations(entityClass);
	}
}
