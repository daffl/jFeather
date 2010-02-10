package org.feathry.domain;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import grails.persistence.Entity;

import java.util.Collection;

import java.lang.reflect.Type;

class DynamicGormService
{	
	def getInterface(Class<?> cls)
	{
		def map = [:]
		cls.methods.each()
		{ method ->
			map."${method.name}" =
			{ Object[] args-> 
				this.dispatch(method, args)
			}
		}		
		return map.asType(cls)
	}
	
	def dispatch(Method method, Object[] args)
	{
		// Default invoker
		def invoke = { def obj, String methodname, Object[] params ->
			return obj.invokeMethod(methodname, params)
		}
		
		// TODO: do something with methods that are already implemented

		if(method.returnType == void.class)
		{
			def obj = args[0] // Object where the method will be called on
			// TODO args?.tail() ?
			def argslist = args.length > 1 ? args[1..<args.length] : null
			invoke(obj, method.name, argslist)
		}
		else if(!method.returnType.primitive)
		{
			Class cls = method.returnType
			// If the caller wants a collection
			if(Collection.class.isAssignableFrom(method.returnType))
			{
				// We need the generic type argument of that collection
				// (assuming we only have one generic argument)
				cls = ((ParameterizedType)method.getGenericReturnType()).getActualTypeArguments()[0]				
			}
			// Invoke the static method
			return invoke(cls, method.name, args)
		}
	}
	
	def getInterface(String classname)
	{
		return getInterface(Class.forName(classname))
	}	
}