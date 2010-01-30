package de.neyeon.feathry.service;

import java.io.Serializable;
import java.util.List;

/**
 * Service to implement the basic CRUD (Create, Read, Update, Delete)
 * operation on a domain model object.
 * @author David Luecke (daff@neyeon.de)
 * @param <T> The ressource to be addressed by this service
 */
public interface Resource<T>
{
	public T get(Serializable id);
	public void save(T arg) throws NotSupportedException;
	public void delete(T arg) throws NotSupportedException;
	public List<T> getAll() throws NotSupportedException;
}
