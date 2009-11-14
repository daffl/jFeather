package tmp.groovytests;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import grails.persistence.Entity;

@Entity
class Person {
	
	String firstName
	String lastName
	
	static constraints = {
		firstName blank:false
		lastName blank:false
	}
	
	public String toString() { return id + ": " + firstName + " " + lastName };
}