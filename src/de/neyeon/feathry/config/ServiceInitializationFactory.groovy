package de.neyeon.feathry.config;

import de.neyeon.feathry.domain.DynamicGormService;

class ServiceInitializationFactory
{
	static def getInterface(String classname)
	{
		return new DynamicGormService().getInterface(classname)
	}
}