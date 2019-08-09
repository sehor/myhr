package myhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import myhr.data.domain.user.Role;
import myhr.data.domain.user.User;
import myhr.data.repository.RoleRepository;
import myhr.data.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user=userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("该用户不存在！请检查用户名是否正确");
		}
		
		user.setRoles(roleRepository.findRolesByUserId(user.getId()));
		return user;
	}

}
