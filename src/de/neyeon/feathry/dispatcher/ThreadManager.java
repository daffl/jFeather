package de.neyeon.feathry.dispatcher;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager
{
	protected ExecutorService executor;

	public ThreadManager()
	{
		executor = Executors.newCachedThreadPool();
	}

	public void offer(Runnable thread)
	{
		executor.execute(thread);
	}

	public void offerAll(List<Runnable> threads)
	{
		for (Runnable cur : threads)
		{
			this.offer(cur);
		}
	}
}
