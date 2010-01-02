package test.de.neyeon.feathry.dispatcher;

import java.util.Arrays;

import de.neyeon.feathry.dispatcher.Interceptable;

public class InterceptableService implements Interceptable
{

	@Override
	public Object invoke(String methodName, Object... args)
	{
		return methodName + "(" + Arrays.toString(args) + ")";
	}

}
