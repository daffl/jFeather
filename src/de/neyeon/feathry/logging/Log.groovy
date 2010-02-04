package de.neyeon.feathry.logging;

import org.slf4j.LoggerFactory;

class Log
{	
	public Log()
	{
		Object.metaClass.getLog << { -> LoggerFactory.getLogger(delegate.getClass()) }		
	}
}