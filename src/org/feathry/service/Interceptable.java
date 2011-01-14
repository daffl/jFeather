package org.feathry.service;


/**
 * This interface should be implemented by services that want to intercept all other methodcalls.
 * The ServiceDispatcher will first search for any matching methods implemented on the class
 * implementing this interface and only call invoke if no method has been found. 
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
