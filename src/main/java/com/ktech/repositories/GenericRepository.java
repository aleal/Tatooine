package com.ktech.repositories;

import java.util.Collection;
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
	 * Builds a findByField Query<Entity>  
	 * @param field
	 * @param value
	 * @return
	 */
	protected Query<E> buildFindByFieldQuery(String field, Object value) {
		return this.getDatastore().createQuery(entityClass).field(field).equal(value);
	}

	/**
	 * Finds all entities
	 * @return
	 */
	public List<E> findAll() {
		return this.getDatastore().createQuery(entityClass).find().toList();
	}
	
    /**
     * Finds an entity by its id
     * @param id
     * @return
     */
	public E findById(String id) {
		return this.buildFindByFieldQuery("_id", new ObjectId(id)).first();
	}

	/** 
	 * Finds an entity by field
	 * @param field
	 * @param value
	 * @return
	 */
	public E findOneByField(String field, Object value) {
		return this.buildFindByFieldQuery(field, value).first();
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
		return this.getDatastore().findAndModify(this.buildFindByFieldQuery("_id", new ObjectId(id)), operations);
	}

	/**
	 * Deletes and entity
	 * @param id
	 * @return
	 */
	public E delete(String id) {
		return this.getDatastore().findAndDelete(this.buildFindByFieldQuery("_id", new ObjectId(id)));
	}

	/**
	 * Initiate operations object in order to allowing an update 
	 * @return
	 */
	public UpdateOperations<E> createUpdateOperations() {
		return this.getDatastore().createUpdateOperations(entityClass);
	}
	
	
	public Collection<E> createAll(Collection<E> entities) {
		this.getDatastore().save(entities);
		return entities;
	}
}
