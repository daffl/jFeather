package de.neyeon.feathry.domain;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import antlr.collections.List;

import java.lang.reflect.Type;


class DynamicGormDispatcher
{
	private def declaredMethods;
	
	def getInterface(Class<?> cls)
	{
		log.debug("Getting interface for ${cls.name}")
		def map = [:]
		cls.methods.each() { method ->
			map."${method.name}" = { Object[] args-> 
				this.dispatch(method, args)
			}
		}		
		return map.asType(cls)
	}
	
	def dispatch(Method method, Object[] args)
	{
		log.debug("Dispatching ${method.name} with $args")

		// TODO: check if the method called is defined in this class already
		
		if(method.returnType == void.class)
		{
			// A void method is being called lets try to call the method given on the object
			args.each { it.invokeMethod method.name, null }
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
			// Invoke the generic method
			return cls.invokeMethod(method.name, args)
		}
	}
	
	def getInterface(String classname)
	{
		return getInterface(Class.forName(classname))
	}	
}