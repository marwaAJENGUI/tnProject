package tn.esprit.spring.service.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

//import org.springframework.security.core.userdetails.UserDetails;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import tn.esprit.spring.entity.Location;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.UserDTO;


public interface IUserService {
	
	
	
	// CRUD USER
	
	public List<User> mylist();
	public User addUser(User user);
	public User findbyid(long id);
	public void deleteUser(long id) ;
	
	
	public User findUserByUsername(String username);
	
	public UserDetails loadUserByUsername(String username);
	
	public User addUser(UserDTO user);
	
	public User authenticate(String username, String password);
	
	public Location givenIP__whenFetchingCity__thenReturnsCityData() throws IOException, GeoIp2Exception  ;
		
}
