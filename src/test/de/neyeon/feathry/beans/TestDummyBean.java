package test.de.neyeon.feathry.beans;

public class TestDummyBean
{
	private String name = "Default";
	private int age;
	private InnerDummyBean innerDummy;

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge()
	{
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age)
	{
		this.age = age;
	}

	/**
	 * @return the innerDummy
	 */
	public InnerDummyBean getInnerDummy()
	{
		return innerDummy;
	}

	/**
	 * @param innerDummy the innerDummy to set
	 */
	public void setInnerDummy(InnerDummyBean innerDummy)
	{
		this.innerDummy = innerDummy;
	}

}
