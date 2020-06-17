package tn.esprit.spring.sevice.impl;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import tn.esprit.spring.entity.Location;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.UserDTO;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.interfaces.IUserService;

@Service
public class UserServiceImpl implements UserDetailsService,IUserService {
//public class UserServiceImpl implements IUserService{

	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	

	@Override
	public User addUser(UserDTO user) {
		User newuser = new User();
		newuser.setUsername(user.getUsername());
		newuser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newuser.setFirstName(user.getFirstName());
		newuser.setLastName(user.getLastName());
		newuser.setEmail(user.getEmail());
		newuser.setRole(user.getRole());
		return userRepo.save(newuser);
	}
	
	@Override
	public User findUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	
	//NEW ONE 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}



	@Override
	public User authenticate(String username, String password) {
		User u = userRepo.findUserByUsernameAndPassword(username, password);
		return u;
	}
	
	
	//A VOIR !
//	@Test
	@Override
	public Location givenIP__whenFetchingCity__thenReturnsCityData() throws IOException, GeoIp2Exception {
		String ip = "102.156.210.233";
		String dbLocation = "C:\\eclipse-pi-workspace\\ConsomiTounsi619_indiv\\src\\main\\resources\\GeoLite2-City.mmdb";

		File database = new File(dbLocation);
		DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
		InetAddress ipAddress = InetAddress.getByName(ip);
		CityResponse response = dbReader.city(ipAddress);
		String countryName = response.getCountry().getName();
		double latitude = response.getLocation().getLatitude();
		double longitude = response.getLocation().getLongitude();
		String cityName = response.getCity().getName();
//	    String postal = response.getPostal().getCode();
		String state = response.getLeastSpecificSubdivision().getName();
//		String latitudeConverted = String.valueOf(latitude);
//		String longitudeConverted = String.valueOf(longitude);
		Location loc = new Location();
		loc.setCountryName(countryName);
		loc.setState(state);
		loc.setCity(cityName);
		loc.setLatitude((float)latitude);
		loc.setLongitude((float)longitude);
		return loc ; 
	}
	
	// CRUD USER
	
	
	
	@Override
	public User addUser(User user) {
		return userRepo.save(user);
	}
	

	@Override
	public User findbyid(long id) {

		return userRepo.findById(id).get();
	}

	@Override
	public List<User> mylist() {

		return (List<User>) userRepo.findAll();
	}
	
	@Override
	public void deleteUser(long id) {

		userRepo.deleteById(id);

	}	
	
}
