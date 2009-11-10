package tests;

import grails.spring.BeanBuilder;

import de.neyeon.feathry.domain.Person;
import org.codehaus.groovy.grails.orm.hibernate.ConfigurableLocalSessionFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;


class SimpleTest
{
	ApplicationContext context;
	
	public SimpleTest()
	{
		context = new ClassPathXmlApplicationContext("context.xml")		
	}
	
	def doTest()
	{
		def persons = [ 
				[ firstName : "Foo", lastName : "Fasel Fasel" ],
				[ firstName : "Jack", lastName : "The Ripper" ]
				]
		persons.each {
			new Person(it).save(flush : true)
		}
		
		Person.findByFirstName("David").each { println it }		
	}
	
	public static void main(String[] args)
	{
		def t = new SimpleTest()
		t.doTest()
	}
}