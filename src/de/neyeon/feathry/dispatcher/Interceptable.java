package de.neyeon.feathry.dispatcher;

import javax.imageio.spi.ServiceRegistry;

/**
 * This interface should be implemented by services that want to intercept all methodcalls.
 * The {@link ServiceRegistry} will look if there is any method implemented, first and only
 * call invoke if there was no method found. 
 * @author David Luecke (daff@neyeon.de)
 */
public interface Interceptable
{
	/**
	 * Handle a method invocation with given name and argument list.
	 * @param methodName The name of the method being invoked
	 * @param args The list of arguments
	 * @return The result of the method invocation
	 */
	public Object invoke(String methodName, Object... args);
}
