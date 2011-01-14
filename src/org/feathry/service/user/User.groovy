package org.feathry.service.user;

import javax.persistence.UniqueConstraint;

import grails.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;

@Entity
class User
{
	// static transients = [ "passwordEncoder" ]
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