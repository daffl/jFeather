package org.feathry.dispatcher.rpc;

public interface Session
{
	public String getId();
	public void put(String name, Object value);
	public Object get(String name);
	public void remove(String name);
}
