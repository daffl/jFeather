package test.org.feathry.dispatcher.rest;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.feathry.service.NotSupportedException;
import org.feathry.service.Resource;
import org.junit.Before;
import org.junit.Test;

import test.org.feathry.dispatcher.beans.TestBean;

public class ResourceLocatorTest
{
	public class TestBeanService<T> implements Resource<T>
	{

		@Override
		public void delete(T arg) throws NotSupportedException
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public T get(Serializable id)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<T> getAll() throws NotSupportedException
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void save(T arg) throws NotSupportedException
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class Blubb extends TestBeanService<TestBean>
	{
		
	}
	
	@Test
	public void doTest()
	{
		Class<?> serviceClass = Blubb.class;
		if(serviceClass != null && Resource.class.isAssignableFrom(serviceClass))
		{
			System.out.println(Arrays.toString(serviceClass.getGenericInterfaces()));
			System.out.println(serviceClass.getSuperclass());
			System.out.println(Object.class.getSuperclass());
			for(Type cur : serviceClass.getGenericInterfaces())
			{
				System.out.println(cur);
				if(cur instanceof ParameterizedType)
				{
					ParameterizedType pt = (ParameterizedType) cur;
					Type t = pt.getActualTypeArguments()[0];
					if(t instanceof Class)
					{
						Class<?> x = (Class)t;
						System.out.println(x);
					}
				}
			}
		}
	}
}
