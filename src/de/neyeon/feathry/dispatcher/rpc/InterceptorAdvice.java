package de.neyeon.feathry.dispatcher.rpc;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements a {@link MethodInterceptor} advice that converts method
 * calls into a {@link RemoteProcedureCall} and dispatches them on
 * the {@link ServiceRegistry} set.
 * @author David Luecke (daff@neyeon.de)
 */
public class InterceptorAdvice implements MethodInterceptor
{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String serviceName;
	private final ServiceRegistry registry;
	
	public InterceptorAdvice(String serviceName, ServiceRegistry registry)
	{
		this.serviceName = serviceName;
		this.registry = registry;
	}
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable
	{
		Object[] args = invocation.getArguments();
		String methodName = invocation.getMethod().getName();
		RemoteProcedureCall rpc = new RemoteProcedureCall(getServiceName(), methodName, args);
		log.debug("Proxy is dispatching RPC {}", rpc);
		return getRegistry().invoke(rpc);
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public ServiceRegistry getRegistry()
	{
		return registry;
	}

}
