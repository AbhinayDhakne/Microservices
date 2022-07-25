package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.Controller.UserController;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMockitoApplication {
	
	@Autowired
	private UserController service;
	
	@MockBean
	private UserRepository repository;
	
	@Test
	public void getUsersTest()
	{
		when(repository.findAll()).thenReturn(Stream.of(new User(10005,"Abhinay","Abhinay.Dhakne@amdocs.com","7788990066", "4455667788", "2000-09-07", "M"),
				new User(10005,"Abhishek","Abhishek.Dhakne@amdocs.com","9876543210", "8899776655", "2000-09-06", "M")).collect(Collectors.toList()));
		assertEquals(2,service.getAllUser().size());
	}
	
	@Test
	public void saveUserTest()
	{
		User acc=new User(10005,"Abhishek","Abhishek.Dhakne@amdocs.com","9876543210", "8899776655", "2000-09-06", "M");
		when(repository.save(acc)).thenReturn(acc);
		assertEquals(new ResponseEntity<User>(acc,HttpStatus.CREATED), service.createUser(acc));
	}

}
