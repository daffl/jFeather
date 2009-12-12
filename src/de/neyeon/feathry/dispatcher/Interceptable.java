package de.neyeon.feathry.dispatcher;

/**
 * This interface should be implemented by services that do not implement
 * a specific interface but want to intercept all methodcalls instead.
 * @author daff
 * 
 */
public interface Interceptable
{
	public Object invoke(String methodName, Object... args);
}
