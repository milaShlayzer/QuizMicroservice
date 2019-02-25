package com.mila.quiz.services;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mila.quiz.dao.Question;

@Repository
public interface QuestionServiceRepo extends MongoRepository<Question,Integer> {

	@Query(fields="{_id : 1}")
	public Question findFirstByOrderByIdDesc();



}
