package org.feathry.config;

import org.feathry.domain.DynamicGormService;

class ServiceInitializationFactory
{
	static def getInterface(String classname)
	{
		return new DynamicGormService().getInterface(classname)
	}
}