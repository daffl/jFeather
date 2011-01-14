package org.feathry.service.config;

import org.feathry.service.domain.DynamicGormService;

class ServiceInitializationFactory
{
	static def getInterface(String classname)
	{
		return new DynamicGormService().getInterface(classname)
	}
}