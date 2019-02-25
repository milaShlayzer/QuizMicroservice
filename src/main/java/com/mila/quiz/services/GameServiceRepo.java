package com.mila.quiz.services;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mila.quiz.dao.Game;

public interface GameServiceRepo extends MongoRepository<Game, String>{

	@Query(value="{_id:?0}", fields="{questions : 0}")
	public Game findByIdAndExcludeQuestions(Object id);
	
	@Query(value="{_id:?0}", fields="{questions : 1, currentQuestion : 1}")
	public Game findByIdQuestionsAndIndex(Object id);
	
}
