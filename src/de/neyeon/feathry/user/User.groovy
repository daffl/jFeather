package de.neyeon.feathry.user;

import grails.persistence.Entity;



@Entity
class User
{
	static hasMany = [ authorities : Authority ]
	static mapping = {
		authorities lazy:false
	}
	static constraints = {
		username(unique:true)
	}
	
	String username
	String password
	Boolean enabled = true
}