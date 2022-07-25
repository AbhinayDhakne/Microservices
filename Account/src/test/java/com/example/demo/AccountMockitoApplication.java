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

import com.example.demo.controller.AccountController;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMockitoApplication {
	
	@Autowired
	private AccountController service;
	
	@MockBean
	private AccountRepository repository;
	
	@Test
	public void getUsersTest()
	{
		when(repository.findAll()).thenReturn(Stream.of(new Account(1000000005, 10004, "Alandi","S",(float) 100.23),
			new Account(1000000006, 10004, "Pune","C", (float)101.23)).collect(Collectors.toList()));
		assertEquals(2,service.getAccounts().size());
	}
	
	@Test
	public void saveUserTest()
	{
		Account acc=new Account(1000000005, 10004, "Alandi","S",(float) 100.23);
		when(repository.save(acc)).thenReturn(acc);
		assertEquals(new ResponseEntity<Account>(acc,HttpStatus.CREATED), service.createUser(acc));
	}

}
