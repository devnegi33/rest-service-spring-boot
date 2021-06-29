package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UserDao {
	static List<User> asList= new ArrayList<User>();
	static {
		asList.add(new User(123, "Devender Negi", new Date()));
		asList.add(new User(124, "Ramesh", new Date()));
		asList.add(new User(123, "Asish", new Date()));
	}
	
	public List<User>findAll(){
		return asList;
	}
	
	public User save(User user) {
		asList.add(user);
		return user;
	}
	
	public User findOne(Integer id) {
		 Optional<User> usrOptional = asList.stream().filter(usr->usr.getId().equals(id)).findFirst();
		 return usrOptional.orElseThrow(()->new UserNotFoundException("id-> " +id));
	}
	
	public User deleteUser(Integer id) {
		Iterator<User> iterator = asList.iterator();
		while(iterator.hasNext()) {
			User next = iterator.next();
			if(id.equals(next.getId())) {
				iterator.remove();
			return next;
			}
		}
		return null;
	}

}
