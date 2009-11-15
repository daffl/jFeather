package de.neyeon.feathry.user;

import org.hibernate.annotations.Cache;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import grails.persistence.Entity;


@Entity
class User
{
	String username;
	String password;
	Date passwordExpiry;
	Set<Group> groups;
	
	public void setPassword(String pw)
	{
		password = new ShaPasswordEncoder().encodePassword(pw, null)
	}
}