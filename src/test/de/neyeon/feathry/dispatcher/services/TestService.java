package test.de.neyeon.feathry.dispatcher.services;

import test.de.neyeon.feathry.dispatcher.beans.OtherTestBean;
import test.de.neyeon.feathry.dispatcher.beans.TestBean;

public interface TestService
{
	public String hello(String name);
	public String hello(String hello, String name);
	public TestBean getTestBean(String name, Integer age);
	public String save(TestBean test);
	public String save(OtherTestBean test);
	public OtherTestBean updateDate(OtherTestBean toUpdate);
}
