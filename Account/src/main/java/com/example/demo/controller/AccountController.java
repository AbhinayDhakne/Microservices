package com.example.demo.controller;

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
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/acc")
public class AccountController {
	
	@Autowired
	private AccountService service;

	@GetMapping("/name")
	public String getName()
	{
		return "Abhinay Dhakne";
	}
	
	@PostMapping("/new")
	public ResponseEntity<Account> createUser( @RequestBody Account acc )
	{
		Account saveuser=service.save(acc);
		return new ResponseEntity<Account>(saveuser,HttpStatus.CREATED);
	}
	
	@GetMapping("/show")
	public List<Account> getAccounts() {
		List<Account> listuser = service.listAll();
		return listuser;
	}
	
	@GetMapping("/get/{id}")
	public Account getById(@PathVariable long id)
	{
		return service.get(id);
	}
	
	@DeleteMapping("/del/{id}")
	public boolean deleteAccount(@PathVariable long id)
	{
		return service.delete(id);
	}
}
