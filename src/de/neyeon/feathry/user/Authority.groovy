package de.neyeon.feathry.user;

import org.springframework.security.core.GrantedAuthority;

import grails.persistence.Entity;


@Entity
class Authority implements GrantedAuthority
{
	static hasMany = [ users : User ]
	
	String name
	Set<User> users
	Integer level
	
	String getAuthority()
	{
		return name
	}
	
	public int compareTo(GrantedAuthority other)
	{
		if(other instanceof Authority)
			return ((Authority)other).level.compareTo(level)
		return 0; 
	}
}