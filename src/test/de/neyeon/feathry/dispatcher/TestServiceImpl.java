package test.de.neyeon.feathry.dispatcher;

import java.util.Arrays;

import de.neyeon.feathry.dispatcher.Interceptable;

public class TestServiceImpl implements TestService, Interceptable
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
	public Object invoke(String methodName, Object... args)
	{
		return methodName + "(" + Arrays.toString(args) + ")";
	}

	@Override
	public TestBean getTestBean(String name, Integer age)
	{
		return new TestBean(name, age);
	}
}
