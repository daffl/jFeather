package de.neyeon.feathry.user;

import grails.persistence.Entity;


@Entity
class User
{
	String username;
	String password;
	Date passwordExpiry;
	Set<Group> groups;
	
	public void setPassword(String password)
	{
		this.password = password;
	}
}