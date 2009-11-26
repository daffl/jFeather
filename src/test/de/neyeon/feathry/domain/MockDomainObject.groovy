package test.de.neyeon.feathry.domain;

import java.util.List;

class MockDomainObject
{
	static List<MockDomainObject> findByNameAndSaved(String name, boolean saved)
	{
		return [new MockDomainObject(name : name, saved : saved)];
	}
	
	static def getAll()
	{
		return [new MockDomainObject(name : "Test1"), new MockDomainObject(name : "Test2")]
	}
	
	String name
	boolean saved = false
	
	void save()
	{
		saved = true
	}
	
	void saveAndChangeName(String newName)
	{
		name = newName
		saved = true
	}
	
	public String toString()
	{
		return name
	}
	
	public boolean equals(Object obj)
	{
		return this.class == obj.class && ((MockDomainObject)obj).name == name && ((MockDomainObject)obj).saved == saved
	}
}