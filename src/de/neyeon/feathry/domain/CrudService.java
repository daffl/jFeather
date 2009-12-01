package de.neyeon.feathry.domain;

import java.util.List;

public interface CrudService<T>
{
	public T get(Object id);
	public void save(T arg);
	public void delete(T arg);
	public List<T> getAll();
}
