package tmp.groovytests;

import grails.spring.BeanBuilder;

import org.codehaus.groovy.grails.orm.hibernate.ConfigurableLocalSessionFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;



class SimpleTest
{
	ApplicationContext context;
	
	public SimpleTest()
	{
		context = new ClassPathXmlApplicationContext("tmp/context.xml")		
	}
	
	def doTest()
	{
//		def persons = [ 
//				[ firstName : "Foo", lastName : "Fasel Fasel" ],
//				[ firstName : "Jack", lastName : "The Ripper" ]
//				]
//		persons.each {
//			assert new Person(it).save(flush : true).id != null
//		}
//		
//		Person.findByFirstName("Jack").each { println it }
		// authorities : [new Authority(name : "User")]
		// assert new Person(firstName : "Foo", lastName : "Fasel Fasel").save(flush:true).id != null
		ServicesUser usr = new ServicesUser(username : "Foo", password : "Fasel Fasel")
		assert usr.save(flush:true).id != null
		println usr.id
		// println ServicesUser.load(1)
	}
	
	public static void main(String[] args)
	{
		def t = new SimpleTest()
		t.doTest()
	}
}