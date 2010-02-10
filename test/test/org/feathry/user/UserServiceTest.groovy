
package test.org.feathry.user;

import static org.junit.Assert.*;


import org.feathry.user.Authority;


import org.feathry.user.User;
import org.feathry.user.UserService;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/org/feathry/config/default.xml"])
class UserServiceTest
{
	@Autowired
	UserService userService
	
	User exampleUser()
	{		
		return new User(username : "Testuser", password : "Test123")
	}
	
	@Test
	public void testSave()
	{
		Authority auth = new Authority(name : "User")
		userService.save(auth)
		assert auth.id != null
		User usr = exampleUser()
		usr.authorities = [auth]
		userService.save(usr)
		assert usr.id != null
		assert usr.authorities.size() == 1
	}
	
	@Test
	public void testFindByUsername()
	{
		User usr = userService.findByUsername(exampleUser().username)
		assert usr != null
		assert usr.password == exampleUser().password
		assert usr.authorities.size() == 1
	}
	
	@Test
	public void testGet()
	{
		User eu = exampleUser()
		User other = userService.get(1L)
		assert other.username == eu.username
		assert other.password == eu.password
	}
	
	@Test
	public void testDelete()
	{
		User usr = userService.get(1L)
		userService.delete(usr)
		assert userService.get(1L) == null
	}
	
}