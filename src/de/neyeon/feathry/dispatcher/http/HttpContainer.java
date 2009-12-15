package de.neyeon.feathry.dispatcher.http;

import java.util.HashMap;
import java.util.Map;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

import de.neyeon.feathry.dispatcher.ThreadManager;

/**
 * Class implementing the Simpleframework HTTP container interface for handling requests.
 * The container takes a map of {@link WorkerFactory}s that will be used to instantiate
 * the appropriate worker thread. The map key is the HTTP base URL on the server that maps
 * to the correct worker factory (e.g. http://localhost:port/factoryName maps to the WorkerFactory
 * called factoryName).  
 * @author David Luecke (daff@neyeon.de)
 */
public class HttpContainer implements Container
{	
	private Map<String, WorkerFactory> workers;
	private ThreadManager threadManager;

	/**
	 * Creates a new http container with an empty map of worker factories.
	 */
	public HttpContainer()
	{
		this.setWorkers(new HashMap<String, WorkerFactory>());
	}

	/* (non-Javadoc)
	 * @see org.simpleframework.http.core.Container#handle(org.simpleframework.http.Request, org.simpleframework.http.Response)
	 */
	@Override
	public void handle(Request req, Response resp)
	{
		String url = req.getPath().getPath(0, 1).substring(1);
		Worker worker = this.getWorkerFactory(url).create(req, resp);
		// TODO get a default worker to handle this if no appropriate handler
		// has been found
		threadManager.offer(worker);
	}

	/**
	 * Get the worker factory for a given base url. Returns null if none has been found.
	 * @param url The base URL to get the worker factory for
	 * @return The worker factory for this base url or null if not found
	 */
	public WorkerFactory getWorkerFactory(String url)
	{
		return workers.get(url);
	}

	/**
	 * Add a new worker factory for a given base url. Existing factories will be overwritten.
	 * @param url The base url to use
	 * @param factory The worker factory to use for the given url
	 */
	public void addWorkerFactory(String url, WorkerFactory factory)
	{
		workers.put(url, factory);
	}

	/**
	 * Set the thread manager used for handling the creation of e.g. new worker threads.
	 * @param threadManager The thread manager to set.
	 */
	public void setThreadManager(ThreadManager threadManager)
	{
		this.threadManager = threadManager;
	}

	/**
	 * Returns the current thread manager used.
	 * @return The current thread manager
	 */
	public ThreadManager getThreadManager()
	{
		return threadManager;
	}

	/**
	 * Set an entire map of url, worker factory entries.
	 * @param workers The map to set
	 */
	public void setWorkers(Map<String, WorkerFactory> workers)
	{
		this.workers = workers;
	}

	/**
	 * Get the current url, worker factory map.
	 * @return URL / WorkerFactory map
	 */
	public Map<String, WorkerFactory> getWorkers()
	{
		return workers;
	}
}
