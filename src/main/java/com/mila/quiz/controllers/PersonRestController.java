package com.mila.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mila.quiz.dao.Person;
import com.mila.quiz.services.PersonServiceRepo;

@Controller
public class PersonRestController {
	@Autowired
	PersonServiceRepo personService;
	private final String PERSON_PREFIX="/person";
	
	@RequestMapping(method = RequestMethod.PUT,value=PERSON_PREFIX+"/add-person")
	public @ResponseBody void putPerson(@RequestBody Person person ) {
		
		personService.save(person);
	}
	@RequestMapping(method= RequestMethod.GET,value=PERSON_PREFIX+"/get-person")
	public @ResponseBody Person getperson(@RequestParam("first-name")  String firstName, @RequestParam("last-name") String lastName) {
		return personService.findByFirstNameAndLastName(firstName, lastName);
		
	}
}
