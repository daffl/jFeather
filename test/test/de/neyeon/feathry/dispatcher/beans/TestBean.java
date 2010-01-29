package test.de.neyeon.feathry.dispatcher.beans;

public class TestBean
{
	private String name;
	private Integer age;

	public TestBean(String name, Integer age)
	{
		this.name = name;
		this.age = age;
	}

	public TestBean() { }

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestBean other = (TestBean) obj;
		if (age == null)
		{
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return name + " : " + age;
	}
}
