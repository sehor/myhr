package myhr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myhr.data.domain.user.Role;
import myhr.data.domain.user.User;
import myhr.data.domain.user.UserRole;
import myhr.data.repository.RoleRepository;
import myhr.data.repository.UserRepository;
import myhr.data.repository.UserRoleRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRoleRepository ur_Repository;
	

	@PostMapping("/adduser")
	public User addUser(@RequestBody User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	
	@PostMapping("/addrole")
	public Role addRole(@RequestBody Role role) {
		
		return roleRepository.save(role);
	}
	
	@PostMapping("/adduser-role")
	public UserRole addUserAndRole(@RequestBody UserRole userRole) {
		return ur_Repository.save(userRole);
	}
}
