package test.de.neyeon.feathry.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import de.neyeon.feathry.user.User;
import de.neyeon.feathry.user.UserService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/de/neyeon/feathry/config/default.xml"])
class UserDetailsServiceImplTest
{
	@Autowired	
	UserDetailsService userDetailsService
	@Autowired
	UserService userService
	
	@Test
	public void testLoadUserByUsernameString()
	{
		assert userDetailsService != null
		assert userService != null
		User testuser = new User(username : "Testuser", password : "Test123")
		userService.save(testuser)
		assert testuser.id != null
		UserDetails details = userDetailsService.loadUserByUsername(testuser.username)
		assert details.getUsername() == testuser.username
		assert details.getPassword() == testuser.password
		assert details.isEnabled()
	}
}