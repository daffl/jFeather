package de.neyeon.feathry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.userdetails.UserCache;

import de.neyeon.feathry.cache.CacheService;
import de.neyeon.feathry.cache.UserCacheService;

public class ServiceFactory
{
	public static final String DEFAULT_CONTEXT = "de/neyeon/feathry/config/default.xml";
	protected static ServiceFactory instance;
	
	public static ServiceFactory getInstance()
	{
		if(instance == null)
			instance = new ServiceFactory(DEFAULT_CONTEXT);
		return instance;
	}
	
	private ApplicationContext context;

	protected ServiceFactory(String contextName)
	{
		// InputStream ctxStream = getClass().getClassLoader().getResourceAsStream(contextName);
		// TODO load context from resource
		context = new ClassPathXmlApplicationContext(contextName);
	}
	
	public CacheService getCache()
	{
		return (CacheService) context.getBean("cache");
	}
	
	public UserCache getUserCache()
	{
		return (UserCache)context.getBean("userCache");
	}
}
