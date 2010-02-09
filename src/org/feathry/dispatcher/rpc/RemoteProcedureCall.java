package org.feathry.dispatcher.rpc;

import java.util.Arrays;

public class RemoteProcedureCall
{
	private final String serviceName;
	private final String methodName;
	private final Object[] args;
	private Class<?>[] types;

	public RemoteProcedureCall(String serviceName, String methodName,
			Object[] args, Class<?>[] types)
	{
		this(serviceName, methodName, args);
		this.types = types;
	}
	
	public RemoteProcedureCall(String serviceName, String methodName,
			Object[] args)
	{
		this.serviceName = serviceName;
		this.methodName = methodName;
		if (args == null)
			this.args = new Object[0];
		else
			this.args = args;
		
		types = new Class<?>[this.args.length];
		for (int i = 0; i < this.args.length; i++)
		{
			types[i] = this.args[i].getClass();
		}
	}
	
	/**
	 * Returns if the prameter types of this RPC are
	 * assignable to the parameter type of a list of classes.
	 * Used to match methods with polymorphism.
	 * @param classes
	 * @return
	 */
	public boolean isAssignableTo(Class<?>[] classes)
	{
		if(classes.length != types.length) {
			return false;
		}
		for(int i = 0; i < types.length; i++)
		{
			if(!classes[i].isAssignableFrom(types[i])) {
				return false;
			}
		}
		return true;
	}

	public Class<?>[] getParameterTypes()
	{
		return types;
	}

	public int getParameterCount()
	{
		return args.length;
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
