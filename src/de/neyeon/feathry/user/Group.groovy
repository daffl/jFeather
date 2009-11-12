package de.neyeon.feathry.user;

import grails.persistence.Entity;

@Entity
class Group
{
	String name;
	Set<User> users;
}