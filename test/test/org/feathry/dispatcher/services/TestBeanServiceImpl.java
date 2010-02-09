package test.org.feathry.dispatcher.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.feathry.service.NotSupportedException;

import test.org.feathry.dispatcher.beans.TestBean;

public class TestBeanServiceImpl implements TestBeanService
{
	@Override
	public TestBean get(Serializable id)
	{
		TestBean got = new TestBean();
		got.setAge(10);
		got.setName("TestWithId" + id.toString());
		return got;
	}

	@Override
	public void delete(TestBean arg) throws NotSupportedException
	{
		// TODO Auto-generated method stub

	}


	@Override
	public List<TestBean> getAll() throws NotSupportedException
	{
		List<TestBean> result = new ArrayList<TestBean>();
		TestBean got = new TestBean();
		got.setAge(10);
		got.setName("Test1");
		result.add(got);
		got = new TestBean();
		got.setAge(22);
		got.setName("Test2");
		result.add(got);
		return result;
	}

	@Override
	public void save(TestBean arg) throws NotSupportedException
	{
		// TODO Auto-generated method stub

	}

}
