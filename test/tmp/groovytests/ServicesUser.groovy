package tmp.groovytests;

import grails.persistence.Entity;

@Entity
class ServicesUser
{
	
	String username
	String password
	Date  bla
	
	static constraints = {
		username blank:false
		password blank:false
	}
	
	public String toString() { return id + ": " + username + " " + password };
}