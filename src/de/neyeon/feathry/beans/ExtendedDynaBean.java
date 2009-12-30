package de.neyeon.feathry.beans;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertingWrapDynaBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaBeanMapDecorator;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaMap;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.WrapDynaClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that decorates any of the {@link DynaBean} implementations of the <a
 * href="http://commons.apache.org/beanutils/" target="_blank">Apach Commons
 * Beanutil</a> package.<br />
 * Provides additional functionality for populating the current DynaBean into to
 * a specific bean class (supporting instatiation and propagation of nested
 * beans as well if the nested properties are the bean itself, a {@link Map} or
 * another DynaBean instance). <br />
 * Additionally this class provides functionality for choosing the most
 * appropriate bean implementation class from a list of classes, looking for the
 * best similarity between this DynaBean properties and any of the given
 * classes.
 * @author David Luecke (daff@neyeon.de)
 */
public class ExtendedDynaBean implements DynaBean
{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	protected DynaBean bean;

	/**
	 * Create a new ExtendedDynaBean that wraps a given {@link DynaBean}
	 * instance.
	 * @param bean The {@link DynaBean} instance to wrap
	 */
	public ExtendedDynaBean(DynaBean bean)
	{
		this.bean = bean;
	}

	/**
	 * Create a new ExtendedDynaBean from a given bean instance. The instance
	 * will be wrapped into the {@link ConvertingWrapDynaBean}.
	 * @param obj The bean instance to use
	 */
	public ExtendedDynaBean(Object obj)
	{
		bean = new ConvertingWrapDynaBean(obj);
	}

	/**
	 * Create a new ExtendedDynaBean from a given class. An instance of this
	 * class will be created invoking its default constructor.
	 * @param cls
	 * @throws InstantiationException If no default constructor is provided in
	 *         the given class
	 * @throws IllegalAccessException If the default constructor of the given
	 *         class is not visible
	 */
	public ExtendedDynaBean(Class<?> cls) throws InstantiationException, IllegalAccessException
	{
		this(cls.newInstance());
	}

	/**
	 * Create a new ExtendedDynaBean from a given map. It will be wrapped into a
	 * {@link LazyDynaMap}.
	 * @param map The bean map to use
	 */
	public ExtendedDynaBean(Map<?, ?> map)
	{
		bean = new LazyDynaMap(map);
	}

	/**
	 * Returns a populated bean instance of the given class. All nested bean
	 * properties will be instantiated as well if the nested property is
	 * <ul>
	 * <li>the nested bean itself</li>
	 * <li>a map that can convert to the subbean</li>
	 * <li>another DynaBean instance</li>
	 * </ul>
	 * Otherwise a {@link ClassCastException} will be thrown. Additional
	 * properties in this DynaBean will be ignored.
	 * @param <T> The type of the bean to be instantiated
	 * @param cls The class of the bean to be instantiated
	 * @return The created bean instance
	 * @throws InstantiationException If the given class doesn't follow the Java
	 *         Bean convention of providing a default constructor
	 * @throws IllegalAccessException On access errors on the setter method of
	 *         the bean object
	 * @throws InvocationTargetException If the beans setter method cannot be
	 *         called
	 * @throws NoSuchMethodException If the setter for the bean cannot be found
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> cls) throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException
	{
		if (Map.class.isAssignableFrom(cls))
		{
			log.debug("Returning map decorator for {}", cls.getName());
			return (T) this.getMapDecorator();
		}

		T result = cls.newInstance();
		for (DynaProperty property : this.getDynaClass().getDynaProperties())
		{
			String name = property.getName();
			if (PropertyUtils.isWriteable(result, name))
			{
				Object value = this.get(name);
				if (Map.class.isAssignableFrom(property.getType()))
				{
					// If current property is a map we need to convert it to the
					// expected bean type
					Class<?> subBeanType = PropertyUtils.getPropertyType(result, name);
					Map<?, ?> beanMap = (Map<?, ?>) this.get(name);
					Object subBeanInstance = new ExtendedDynaBean(beanMap).getBean(subBeanType);
					log.debug("Found Map for property {}. Bean type is {}.", name, subBeanType
							.getName());
					value = subBeanInstance;
				} else if (DynaBean.class.isAssignableFrom(property.getType()))
				{
					// If we have a DynaBean we need to convert it as well
					Class<?> subBeanType = PropertyUtils.getPropertyType(result, name);
					DynaBean subBean = (DynaBean) this.get(name);
					Object subBeanInstance = new ExtendedDynaBean(subBean).getBean(subBeanType);
					log.debug("Found DynaBean for property {}. Bean type is {}.", name, subBeanType
							.getName());
					value = subBeanInstance;
				}
				PropertyUtils.setProperty(result, name, value);
			}
		}
		return result;
	}

	/**
	 * Choose the class that matches most of the properties of this DynaBean
	 * from an array of classes. If two classes share the exact same or no
	 * properties a ChoiceException is thrown.
	 * @param choices The array of classes that need to be inspected
	 * @return The class matching most of the properties of this bean
	 * @throws ChoiceException If two classes share the exact same properties or
	 *         no properties at all with this bean
	 */
	public Class<?> choose(Class<?>[] choices) throws ChoiceException
	{
		return this.choose(Arrays.asList(choices));
	}

	/**
	 * Choose the class that matches most of the properties of this DynaBean
	 * from a list of classes. If two classes share the exact same or no
	 * properties a ChoiceException is thrown.
	 * @param choices The list of classes that need to be inspected
	 * @return The class matching most of the properties of this bean
	 * @throws ChoiceException If two classes share the exact same properties or
	 *         no properties at all with this bean
	 */
	public Class<?> choose(List<Class<?>> choices) throws ChoiceException
	{
		log.debug("Choosing from classes {}", choices);
		double similarity = 0.0;
		Class<?> bestChoice = null;
		for (Class<?> cur : choices)
		{
			double currentSimilarity = this.getSimilarity(cur);
			if (log.isDebugEnabled())
			{
				// Doesn't seem to autobox otherwise
				Object[] args = { cur.getName(), currentSimilarity, similarity };
				log.debug("Inspecting {} class similarity is {}, current best similarity is {}.",
						args);
			}
			if ((currentSimilarity == similarity) && (similarity != 0.0))
			{
				throw new ChoiceException("Cannot make distinct choice for class " + cur.getName()
						+ ". Similarity is " + currentSimilarity);
			}
			if (currentSimilarity > similarity)
			{
				similarity = currentSimilarity;
				bestChoice = cur;
			}
		}
		if (bestChoice == null)
		{
			throw new ChoiceException(
					"Could not make an appropriate choice. There are no similarities to this bean.");
		}
		return bestChoice;
	}

	/**
	 * Calculates the similarity of the property names between the DynaClass of
	 * this DynaBean and another class in percent.
	 * @param cls The class to inspect
	 * @return The similarity of the property names in percent
	 */
	public double getSimilarity(Class<?> cls)
	{
		return this.getSimilarity(WrapDynaClass.createDynaClass(cls));
	}

	/**
	 * Calculates the similarity of the property names between the DynaClass of
	 * this DynaBean and another DynaClass in percent. If the other DynaClass
	 * shares all properties that this DynaBean supports it will return 1.0,
	 * otherwise less.
	 * @param other The other DynaClass to inspect
	 * @return The similarity of the properties in percent
	 */
	public double getSimilarity(DynaClass other)
	{
		DynaClass myDynaClass = this.getDynaClass();
		int sum = 0;
		for (DynaProperty prop : myDynaClass.getDynaProperties())
		{
			if (other.getDynaProperty(prop.getName()) != null)
			{
				sum++;
			}
		}
		return ((double) sum) / ((double) myDynaClass.getDynaProperties().length);
	}

	/**
	 * Calculates the similarity of the property names between the DynaClass of
	 * this DynaBean and the one of another DynaBean.
	 * @param other
	 * @return
	 */
	public double getSimilarity(DynaBean other)
	{
		return this.getSimilarity(other.getDynaClass());
	}

	public Map<?, ?> getMapDecorator()
	{
		return new DynaBeanMapDecorator(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.DynaBean#contains(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean contains(String name, String key)
	{
		return bean.contains(name, key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.DynaBean#get(java.lang.String, int)
	 */
	@Override
	public Object get(String name, int index)
	{
		return bean.get(name, index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.DynaBean#get(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Object get(String name, String key)
	{
		return bean.get(name, key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.DynaBean#get(java.lang.String)
	 */
	@Override
	public Object get(String name)
	{
		return bean.get(name);
	}

	@Override
	public DynaClass getDynaClass()
	{
		return bean.getDynaClass();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.DynaBean#remove(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void remove(String name, String key)
	{
		bean.remove(name, key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.DynaBean#set(java.lang.String, int,
	 * java.lang.Object)
	 */
	@Override
	public void set(String name, int index, Object value)
	{
		bean.set(name, index, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.DynaBean#set(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public void set(String name, Object value)
	{
		bean.set(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.DynaBean#set(java.lang.String,
	 * java.lang.String, java.lang.Object)
	 */
	@Override
	public void set(String key, String name, Object value)
	{
		bean.set(key, name, value);
	}

}
