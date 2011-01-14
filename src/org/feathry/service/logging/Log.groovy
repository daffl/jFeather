package org.feathry.service.logging;

import org.slf4j.LoggerFactory;

class Log
{	
	public Log()
	{
		Object.metaClass.getLog << { -> LoggerFactory.getLogger(delegate.getClass()) }		
	}
}