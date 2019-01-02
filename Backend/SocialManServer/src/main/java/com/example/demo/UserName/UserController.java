package com.example.demo.UserName;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserRepository UserRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/users/{userId}/posts")
    public List<Post> getPosts(@PathVariable("userId") int userId ) {  //need to add user attribute posts
        return UserRepository.findById(userId).get().getPosts();
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "/users/{userId}/friends")
    public List<User> getFriends(@PathVariable("userId") int userId ) {  //need to add user attribute posts
        return UserRepository.findById(userId).get().getFriends();
    }
	@RequestMapping(method = RequestMethod.POST, path = "/users/{userId}/newpost") //need to add this to the posts attribute
    public Post makePost(@RequestBody Post post, @PathVariable("userId") int userId) {
		Optional<User> user = UserRepository.findById(userId);
		if(user.isPresent()) {
			List<Post> posts = user.get().getPosts();
			posts.add(post);
			UserRepository.save(user.get());
			return post;
		}
		else {
			return post;
		}
    }
	
	@RequestMapping(method = RequestMethod.POST, path = "/users/{userId}/addbyid/{friend}") //need to add this to the posts attribute
    public User addFriend(@PathVariable("userId") int userId, @PathVariable("friend") int friend) {
		Optional<User> user = UserRepository.findById(userId);
		Optional<User> toAdd = UserRepository.findById(friend);
		if(user.isPresent() & toAdd.isPresent()) {
			user.get().getFriends().add(toAdd.get());
			toAdd.get().getFriends().add(user.get());
			UserRepository.save(user.get());
			UserRepository.save(toAdd.get());
			return toAdd.get();
		}
		else {
			return null;
		}
    }
	@PostMapping("/users/new")
	User newUser(@RequestBody User newUser) {
		return UserRepository.save(newUser);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/users")
	public List<User> getAllUsers() {
		List<User> results = (List<User>) UserRepository.findAll();

		return results;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/users/{userId}")
	public Optional<User> findUserById(@PathVariable("userId") Integer id) {

		Optional<User> results = UserRepository.findById(id);
		return results;
	}
	//look into getting the email and password by request body?
	@RequestMapping(method = RequestMethod.POST, path = "/checkUser/{email}/{password}")
	public Optional<User> checkUser(@PathVariable("email") String email, @PathVariable("password") String password) {  //@RequestBody String[] credentials
		Optional<User> results = UserRepository.findUserByEmailAndPassword(email, password);
		return results;
		
	}


//	@RequestMapping(method = RequestMethod.POST, path ="setname/{userid, n}")
////	@PostMapping("users/setname/{userid, n}")
//	public String setName(@PathVariable("userid") Integer id, @PathVariable("n")String n) {		
//		Optional<User> u = findUserById(id);
//		User user = u.get();
//		
//		user.setName(n);
//		return user.getName();
//		
//	}
//
//	@PostMapping("/setemail")
//	public String[] setEmail() {
//		List<User> results = (List<User>) UserRepository.findAll(); //<--change this function.
//		String[] emails = new String[results.size()];
//
//		for(int i = 0; i < results.size(); i++) {
//			emails[i] = results.get(i).getEmail();
//		}
//		return emails;
//	}
//
//	@PostMapping("/setid")
//	public List<User> setId() {
//		List<User> results = UserRepository.findAll(); //<--change this function.
//		return results;
//	}
//
//	@PostMapping("/setPassword")
//	public boolean setPassword(String name, String password) {
//		List<User> results = (List<User>) UserRepository.findAll(); //<--change this function.
//
//		for(int i = 0; i < results.size(); i++) {
//			if(results.get(i).getName() == name)
//			{
//				results.get(i).setPassword(password);
//				return true;
//			}
//		}
//		return false;
//	}

	//	
	//	@GetMapping("/getname")
	//    public String[] getName() {
	//        List<User> results = (List<User>) UserRepository.findAll(); //<--change this function.
	//        String[] names = new String[results.size()];
	//        for(int i = 0; i < results.size(); i++) {
	//       names[i] = results.get(i).getName();
	//        }
	//        return names;
	//    }
	//	
	//	@GetMapping("/getid")
	//    public List<User> getAllId() {
	//        List<User> results = UserRepository.findAll(); //<--change this function.
	//        return results;
	//    }
	//	
	//	@GetMapping("/getemail")
	//    public String[] getEmail() {
	//        List<User> results = (List<User>) UserRepository.findAll(); //<--change this function.
	//        String[] emails = new String[results.size()];
	//        
	//        for(int i = 0; i < results.size(); i++) {
	//        	emails[i] = results.get(i).getEmail();
	//        }
	//        return emails;
	//    }
	//	
	//	@GetMapping("/getPassword")
	//    public String[] getPassword() {
	//        List<User> results = (List<User>) UserRepository.findAll(); //<--change this function.
	//        String[] passwords = new String[results.size()];
	//        
	//        for(int i = 0; i < results.size(); i++) {
	//        	passwords[i] = results.get(i).getPassword();
	//        }
	//        return passwords;
	//    }

}
