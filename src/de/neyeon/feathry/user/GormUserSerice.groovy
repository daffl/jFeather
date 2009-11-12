package de.neyeon.feathry.user;

import java.beans.PropertyEditor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;

class GormUserService implements UserDetailsService
{
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException
	{
		User.findByUsername(login)
	}
}