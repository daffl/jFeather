package org.feathry.dispatcher.rpc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassWrapper
{
	public static final ClassWrapper OBJECT = new ClassWrapper(Object.class);
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final Class<?> cls;
	
	public ClassWrapper(Class<?> cls)
	{
		this.cls = cls;
	}
	
	public List<Method> getMethods(String name)
	{
		List<Method> result = new ArrayList<Method>();
		for (Method cur : getWrappedClass().getMethods())
		{
			if (cur.getName().equals(name))
			{
				result.add(cur);
			}
		}
		return result;		
	}
	
	public List<Method> getMethods(String name, int parameterCount)
	{
		List<Method> result = new ArrayList<Method>();
		for (Method cur : getWrappedClass().getMethods())
		{
			if (cur.getName().equals(name) && cur.getParameterTypes().length == parameterCount)
			{
				result.add(cur);
			}
		}
		return result;
	}
	
	public List<Method> getMethods()
	{
		return Arrays.asList(cls.getMethods());
	}
	
	public Method findMethod(RemoteProcedureCall rpc, List<Method> choices) throws SecurityException, NoSuchMethodException
	{
		for (Method m : choices)
		{
			Class<?>[] types = m.getParameterTypes();
			if (rpc.isAssignableTo(types))
			{
				return getWrappedClass().getMethod(rpc.getMethodName(), types);
			}
		}
		return getWrappedClass().getMethod(rpc.getMethodName(), rpc.getParameterTypes());		
	}
	
	public Method findMethod(RemoteProcedureCall rpc) throws SecurityException, NoSuchMethodException
	{
		return findMethod(rpc, getMethods());
	}
	
	public boolean hasMethod(String name)
	{
		for(Method m : getMethods())
		{
			if(m.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public Method getMethod(String name, Class<?>... classes) throws SecurityException, NoSuchMethodException
	{
		return getWrappedClass().getMethod(name, classes);
	}
	
	public Class<?> getWrappedClass()
	{
		return cls;
	}
}
