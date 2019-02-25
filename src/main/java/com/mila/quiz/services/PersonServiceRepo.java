package com.mila.quiz.services;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mila.quiz.dao.Person;

public interface PersonServiceRepo extends MongoRepository<Person,String>{
public Person findByFirstNameAndLastName(String firstName,String lastName);

}
