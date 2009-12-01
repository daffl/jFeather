package de.neyeon.feathry.user;

import java.beans.PropertyEditor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;

class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	UserService userService
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException
	{
		User usr = userService.findByUsername(login)
		def detailsmap =
		[
		 	getAuthorities : { usr.authorities },
		 	getPassword : { usr.password },
		 	getUsername : { usr.username },
		 	isAccountNonExpired : { true },
		 	isAccountNonLocked : { true },
			isCredentialsNonExpired : { true },
			isEnabled : { usr.enabled }
		]
		
		return detailsmap as UserDetails
	}
}