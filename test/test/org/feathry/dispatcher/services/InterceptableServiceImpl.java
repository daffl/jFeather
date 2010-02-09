package test.org.feathry.dispatcher.services;

import java.util.Arrays;

import org.feathry.service.Interceptable;


public class InterceptableServiceImpl implements Interceptable
{

	@Override
	public Object invoke(String methodName, Object... args)
	{
		return methodName + "(" + Arrays.toString(args) + ")";
	}

}
