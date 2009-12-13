package de.neyeon.feathry.dispatcher.rpc;

public interface ServiceDispatcher
{
	public abstract Object invoke(RemoteProcedureCall rpc) throws Throwable;
}