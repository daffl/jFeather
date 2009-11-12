package tmp.groovytests;

import org.hibernate.SessionFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

class MyGormTest extends AbstractTransactionalDataSourceSpringContextTests {
	SessionFactory sessionFactory
	
	/**
	 * Turn off dependency injection.
	 */ 
	def MyGormTest() {
		super.setDependencyCheck(false)
	}
	
	/**
	 * Load the spring configuration files.
	 */
	@Override
	protected String[] getConfigLocations() {
		return ["context.xml"]
	}
	
	/**
	 * Manually set the sessionFactory.
	 */
	@Override
	protected void onSetUp() throws Exception {
		this.sessionFactory = (SessionFactory) super.getApplicationContext().getBean("sessionFactory")    
	}
	
	/**
	 * Write your test.
	 */
	void testSave() {
		def persons = [ 
		               [ firstName : "David", lastName: "Luecke 2"],
		               [ firstName : "John", lastName : "Doe 2" ]
					]
		persons.each {
			new Person(it).save(flush : true)
		}
		
		Person.findByFirstName("David").each { println it }
	}
}

