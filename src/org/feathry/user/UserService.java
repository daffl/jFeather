package org.feathry.user;

public interface UserService
{
	public void save(User usr);
	public User get(Long id);
	public User findByUsername(String username);
	public void save(Authority auth);
	public void delete(User usr);
}
