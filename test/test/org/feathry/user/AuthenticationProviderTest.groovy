package test.org.feathry.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import groovy.util.GroovyTestCase;
import org.feathry.user.User;
import org.feathry.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/org/feathry/config/default.xml"])
class AuthenticationProviderTest extends GroovyTestCase
{
	@Autowired
	AuthenticationProvider authenticationProvider
	@Autowired
	PasswordEncoder passwordEncoder
	@Autowired
	UserService userService
	
	@Test
	public void testAuthentication()
	{
		User testuser = new User(username : "Testuser", password : passwordEncoder.encodePassword("Test123", null))
		userService.save(testuser)
		assert testuser.id != null
		assert testuser.password == passwordEncoder.encodePassword("Test123", null)
		Authentication request = new UsernamePasswordAuthenticationToken("Testuser", "Test123")
		Authentication result = authenticationProvider.authenticate(request)
		SecurityContextHolder.getContext().setAuthentication(result)
		
		try
		{
			request = new UsernamePasswordAuthenticationToken("Testuser", "blabla")
			result = authenticationProvider.authenticate(request)
			SecurityContextHolder.getContext().setAuthentication(result)
			fail("No exception thrown")
		}
		catch(Exception e)
		{
			
		}
	}
}