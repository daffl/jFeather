package org.feathry.service.user;

import org.springframework.security.core.GrantedAuthority;

import grails.persistence.Entity;


@Entity
class Authority implements GrantedAuthority
{
	static transients = [ "authority" ]
	
	String name
	Integer level = 0
	
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