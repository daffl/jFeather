package tmp.groovytests;

import grails.persistence.Entity;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import org.feathry.service.user.User;

@Entity
class Authority // implements GrantedAuthority
{
//	static hasMany = [ users : User ]
//	static transients = [ "authority" ]
//	
	String name
//	Set<User> users
//	Integer level
//	
//	String getAuthority()
//	{
//		return name
//	}
//	
//	public int compareTo(GrantedAuthority other)
//	{
//		if(other instanceof Authority)
//			return ((Authority)other).level.compareTo(level)
//		return 0; 
//	}
}