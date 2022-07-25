package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.restresponse.AccountRest;
import com.example.demo.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import com.example.demo.model.Account;
import com.example.demo.model.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	public static final String USER_SERVICE="userService";

	@Autowired
	private AccountRest ac;
	
	@Autowired
	private UserService service;

	@GetMapping("/name")
	public String getUserName()
	{
		return ac.getName();
	}
	
	@GetMapping("/show")
	public List<User> getAllUser() {
		List<User> listuser = service.listAll();
		return listuser;
	}
	
	@PostMapping("/new")
	public ResponseEntity<User> createUser( @RequestBody User user )
	{
		User saveuser=service.save(user);
		return new ResponseEntity<User>(saveuser,HttpStatus.CREATED);
	}
	
	@GetMapping("get/{id}")
	public User getById(@PathVariable int id)
	{
		return service.get(id);
	}
	
	@DeleteMapping("del/{id}")
	public boolean deleteUser(@PathVariable int id)
	{
		return service.delete(id);
	}
	
	@GetMapping("/display")
	@CircuitBreaker(name=USER_SERVICE, fallbackMethod = "getAllAvailableAccounts")
	public List<Account> getAccounts()
	{
		return ac.getAccounts();
	}
	
	public List<Account> getAllAvailableAccounts(Exception e){
        List<Account> list;
        list = new ArrayList<Account>();
        Account u1 = new Account(101010, 65890, "Hadapsar", "S", 12000);
        list.add(u1);
        return list;
    }
	
	@PostMapping("/newacc")
	public ResponseEntity<Account> createUser( @RequestBody Account acc )
	{
		return ac.createUser(acc);
	}
	
	@GetMapping("/getacc/{id}")
	public Account getById(@PathVariable long id)
	{
		return ac.getById(id);
	}
	
	@DeleteMapping("/delacc/{id}")
	boolean deleteAccount(@PathVariable long id)
	{
		return ac.deleteAccount(id);
	}
}
