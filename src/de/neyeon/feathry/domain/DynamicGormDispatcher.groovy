package de.neyeon.feathry.domain;

import test.de.neyeon.feathry.domain.TestInterface;

class DynamicGormDispatcher
{
	def outstr = "(From this reference) Called method" 
	
	def getInterface(Class<?> cls)
	{
		log.debug("Getting interface for ${cls.name}")
		def map = [:]
		cls.methods.each() { method ->
			map."${method.name}" = { Object[] args-> 
				this.dispatch(method.name, args)
			}
		}		
		return map.asType(cls)
	}
	
	def dispatch(String methodname, Object[] args)
	{
		log.debug("Dispatching $methodname with $args")		
	}
	
	def getInterface(String classname)
	{
		return getInterface(Class.forName(classname))
	}	
}