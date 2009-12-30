package de.neyeon.feathry.dispatcher.rpc;

import java.lang.reflect.Method;
import java.util.Arrays;

public final class RemoteProcedureCall
{
	private final String serviceName;
	private final String methodName;
	private final Object[] args;

	public RemoteProcedureCall(String serviceName, String methodName,
			Object[] args)
	{
		this.serviceName = serviceName;
		this.methodName = methodName;
		if (args == null)
			this.args = new Object[0];
		else
			this.args = args;
	}

	public Class<?>[] getParameterTypes()
	{
		Class<?>[] result = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++)
		{
			result[i] = args[i].getClass();
		}
		return result;
	}

	public int getParameterCount()
	{
		return args.length;
	}
	
	public Method getDispatchableMethod(Object serviceInstance) throws NoSuchMethodException
	{
		return this.getDispatchableMethod(serviceInstance.getClass());
	}

	public Method getDispatchableMethod(Class<?> cls) throws NoSuchMethodException
	{
		try
		{
			return cls.getMethod(methodName, this.getParameterTypes());
		} catch (SecurityException e)
		{
			throw new NoSuchMethodException(
					"Security exception while trying to access method "
							+ methodName + " on instance of type "
							+ cls.getName());
		}
	}

	@Override
	public String toString()
	{
		return serviceName + "." + methodName + "(" + Arrays.toString(args)
				+ ")";
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public String getMethodName()
	{
		return methodName;
	}

	public Object[] getArguments()
	{
		return args;
	}
}
