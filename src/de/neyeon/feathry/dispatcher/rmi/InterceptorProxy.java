package de.neyeon.feathry.dispatcher.rmi;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;

import de.neyeon.feathry.dispatcher.rpc.RemoteProcedureCall;
import de.neyeon.feathry.dispatcher.rpc.ServiceRegistry;

/**
 * The InterceptorProxy is an extended {@link ProxyFactoryBean} using itself as
 * the method interceptor AOP advice. Every InterceptorProxy is attached to a given
 * service name and uses a given {@link ServiceRegistry} to invoke all methodcalls
 * on any interface attached to this proxy.
 * @author David Luecke (daff@neyeon.de)
 */
public class InterceptorProxy extends ProxyFactoryBean implements MethodInterceptor
{
	private static final long serialVersionUID = -3652699102866297079L;

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String serviceName;
	private final ServiceRegistry registry;
	
	public InterceptorProxy(String serviceName, ServiceRegistry registry)
	{
		super();
		this.addAdvice(this);
		Class<?> ifcls = registry.getServiceInterface(serviceName);
		this.addInterface(ifcls);
		this.registry = registry;
		this.serviceName = serviceName;
		log.debug("Initialized proxy for service {} exporting {}", serviceName, ifcls);
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

	/**
	 * @return the serviceName
	 */
	public String getServiceName()
	{
		return serviceName;
	}

	/**
	 * @return the registry
	 */
	public ServiceRegistry getRegistry()
	{
		return registry;
	}
}
