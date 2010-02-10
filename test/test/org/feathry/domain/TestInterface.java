package test.org.feathry.domain;

import java.util.List;

import org.feathry.user.User;


public interface TestInterface
{
	public void doBla();

	public String hello(String world);

	public List<MockDomainObject> getAll();

	public List<MockDomainObject> findByNameAndSaved(String name, boolean saved);

	public void save(MockDomainObject toSave);

	public void save(User usr);

	public void saveAndChangeName(MockDomainObject obj, String newName);
}
