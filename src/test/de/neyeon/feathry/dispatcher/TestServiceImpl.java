package test.de.neyeon.feathry.dispatcher;

public class TestServiceImpl
{
	public void voidTest()
	{
		System.out.println("Test");
	}
	
	public void argsTest(String t, int x)
	{
		System.out.println(t + " : " + x);
	}
}
