package test.de.neyeon.feathry.beans;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Before;
import org.junit.Test;

import de.neyeon.feathry.beans.ChoiceException;
import de.neyeon.feathry.beans.ExtendedDynaBean;

public class ExtendedDynaBeanTest
{
	private TestDummyBean tdb;
	private Map<Object, Object> map;

	@Before
	public void setUp() throws Exception
	{
		tdb = new TestDummyBean();
		tdb.setName("Test");
		tdb.setAge(23);
		InnerDummyBean idb = new InnerDummyBean();
		idb.setId("InnerTest");
		tdb.setInnerDummy(idb);

		map = new HashMap<Object, Object>();
		map.put("name", "Test");
		map.put("age", 23);
		Map<Object, Object> innerMap = new HashMap<Object, Object>();
		innerMap.put("id", "InnerTest");
		innerMap.put("unneccesaryProperty", 10);
		map.put("innerDummy", innerMap);
	}

	@Test
	public void testExtendedBeanObject() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		ExtendedDynaBean edb = new ExtendedDynaBean(tdb);
		assertEquals("Test", edb.get("name"));
		assertEquals(23, edb.get("age"));		
	}

	@Test
	public void testExtendedBeanClassOfQ() throws InstantiationException, IllegalAccessException
	{
		ExtendedDynaBean edb = new ExtendedDynaBean(TestDummyBean.class);
		assertEquals("Default", edb.get("name"));
	}

	@Test
	public void testExtendedBeanMapOfQQ() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		ExtendedDynaBean edb = new ExtendedDynaBean(map);
		assertEquals("Test", edb.get("name"));
		assertEquals(23, edb.get("age"));
		assertEquals("InnerTest", BeanUtils.getProperty(edb, "innerDummy.id"));
	}

	@Test
	public void testGetBean() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		ExtendedDynaBean edb = new ExtendedDynaBean(map);
		TestDummyBean result = edb.getBean(TestDummyBean.class);
		assertEquals("Test", result.getName());
		assertEquals(23, result.getAge());
		assertEquals("InnerTest", BeanUtils.getProperty(result, "innerDummy.id"));
		assertTrue(result.getInnerDummy() instanceof InnerDummyBean);
	}

	@Test
	public void testGetMapDecorator() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		ExtendedDynaBean edb = new ExtendedDynaBean(tdb);
		Map<?, ?> result = new HashMap<Object, Object>(edb.getMapDecorator());
		assertEquals("Test", result.get("name"));
		InnerDummyBean idb = (InnerDummyBean) result.get("innerDummy");
		assertEquals("InnerTest", idb.getId());
		
		// getBean(Map.class) should give the same result
		assertEquals("Test", result.get("name"));
		idb = (InnerDummyBean) result.get("innerDummy");
		assertEquals("InnerTest", idb.getId());		
	}

	@Test
	public void testGetSimilarityDynaBean()
	{
		ExtendedDynaBean edb = new ExtendedDynaBean(tdb);
		assertEquals(1.0, edb.getSimilarity(tdb.getClass()), 0.0);
		// InnerDummyBean properties: class, age, id = 2 total
		// TestDummyBean properties: class, age, name, innerDummy = 4 total
		// Equal: class, age = 2 / 4 = 0.5
		assertEquals(0.5, edb.getSimilarity(InnerDummyBean.class), 0.0);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testChooseArrayOfClassQ() throws ChoiceException
	{
		ExtendedDynaBean edb = new ExtendedDynaBean(map);
		Class<?>[] classes = { InnerDummyBean.class, TestDummyBean.class };
		assertEquals(TestDummyBean.class, edb.choose(classes));
		
		ExtendedDynaBean edb2 = new ExtendedDynaBean((Map) map.get("innerDummy"));
		assertEquals(InnerDummyBean.class, edb2.choose(classes));
	}
	
}
