package de.neyeon.feathry.service.domain;

import java.util.List;

/**
 * Service to implement the basic CRUD (Create, Read, Update, Delete)
 * operation on a domain model object.
 * @author David Luecke (daff@neyeon.de)
 * @param <T> The ressource to be addressed by this service
 */
public interface CrudService<T>
{	
	public T get(Object id);	// GET id
	public void save(T arg); 	// PUT
	public void delete(T arg);	// DELETE
	public List<T> getAll();	// GET
}
