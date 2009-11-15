package de.neyeon.feathry.domain;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T>
{
	public T get(Serializable id);
	public void save(T arg);
	public void delete(T arg);
	public List<T> getAll();
}
