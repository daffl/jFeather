package test.org.feathry.dispatcher.services;

import test.org.feathry.dispatcher.beans.OtherTestBean;
import test.org.feathry.dispatcher.beans.TestBean;


public class TestServiceImpl implements TestService
{
	@Override
	public String hello(String name)
	{
		return "Hello " + name;
	}

	@Override
	public String hello(String hello, String name)
	{
		return hello + " " + name;
	}
	
	@Override
	public TestBean getTestBean(String name, Integer age)
	{
		return new TestBean(name, age);
	}

	@Override
	public String save(TestBean test)
	{
		assert(test != null);
		return test.toString();
	}

	@Override
	public String save(OtherTestBean test)
	{
		assert(test != null);
		return test.toString();
	}

	@Override
	public OtherTestBean updateDate(OtherTestBean toUpdate)
	{
		assert(toUpdate != null);
		toUpdate.setDate(1000000);
		return toUpdate;
	}
}
