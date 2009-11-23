package de.neyeon.feathry.logging;

import org.apache.log4j.Logger;

class Log
{	
	public Log()
	{
		Object.metaClass.getLog << { -> Logger.getLogger(delegate.getClass()) }		
	}
}