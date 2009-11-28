package de.neyeon.feathry.user;

import de.neyeon.feathry.domain.CrudService;

public interface UserService extends CrudService<User>
{
	public User findByUsername(String username);	
}
