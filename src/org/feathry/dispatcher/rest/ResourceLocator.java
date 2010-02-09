package org.feathry.dispatcher.rest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.feathry.dispatcher.rpc.ServiceRegistry;
import org.feathry.service.Resource;


public class ResourceLocator
{
	public ResourceLocator(ServiceRegistry registry)
	{
		for(String name : registry.getServiceNames())
		{
			Class<?> serviceClass = registry.getServiceInterface(name);
			if(serviceClass != null && Resource.class.isAssignableFrom(serviceClass))
			{
				for(Type cur : serviceClass.getGenericInterfaces())
				{
					if(cur instanceof ParameterizedType)
					{
						ParameterizedType pt = (ParameterizedType) cur;
						Type t = pt.getActualTypeArguments()[0];
						if(t instanceof Class)
						{
							Class<?> x = (Class)t;
							
						}
					}
				}
			}
		}
	}
	
//		Class<TestThing> cls = TestThing.class;
//		for(Type cur : cls.getGenericInterfaces())
//		{
//			System.out.println(cur);			
//			if(cur instanceof ParameterizedType)
//			{
//				ParameterizedType pt = (ParameterizedType) cur;
//				Type t = pt.getActualTypeArguments()[0];
//				if(t instanceof Class)
//				{
//					Class<?> x = (Class)t;
//					System.out.println(x.getName());
//				}
//			}
//		}
	
}
