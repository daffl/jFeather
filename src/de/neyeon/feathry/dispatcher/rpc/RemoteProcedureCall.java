package de.neyeon.feathry.dispatcher.rpc;

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
		if(args == null)
			this.args = new Object[0];
		else
			this.args = args;
	}
	
	public Class<?>[] getArgumentTypes()
	{
		Class<?>[] result = new Class<?>[args.length];
		for(int i = 0; i < args.length; i++)
		{
			result[i] = args[i].getClass();
		}
		return result;
	}
	
	public boolean canDispatchOn(Object serviceInstance)
	{
		if(serviceInstance != null) {
			return this.canDispatchOn(serviceInstance.getClass());
		} else {
			return false;
		}
	}
	
	public boolean canDispatchOn(Class<?> serviceClass)
	{
		try
		{
			serviceClass.getMethod(methodName, this.getArgumentTypes());
			return true;
		} catch (SecurityException e)
		{
			return false;
		} catch (NoSuchMethodException e)
		{
			return false;
		}
	}

	@Override
	public String toString()
	{
		return serviceName + "." + methodName + "(" + Arrays.toString(args) + ")";
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
