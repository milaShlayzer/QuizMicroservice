package com.mila.quiz.gameservice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mila.quiz.dao.Game;
import com.mila.quiz.dao.Question;
import com.mila.quiz.services.GameServiceRepo;
import com.mila.quiz.services.QuestionServiceRepo;

@Service
public class GameService {
	@Value("${quize.game.numberOfQuesions}")
	protected int NUMBER_OF_QUESTIONS;

	@Autowired
	QuestionServiceRepo questionServiceRepo;
	@Autowired
	GameServiceRepo gameServiceRepo;

	// function for the start rest
	public String initilizeGame(String pid) {
		Set<Integer> questionIdsSet = createRandomQuesionIds();
		Game game = createGameObject(pid, questionIdsSet);
		game = gameServiceRepo.save(game);

		return game.getId();
	}

	private Set<Integer> createRandomQuesionIds() {
		Random rand = new Random();
		Question lastQuestion = questionServiceRepo.findFirstByOrderByIdDesc();// get the last question
		int numberOfQustions = lastQuestion.getId();// get the number of the last question for the random limit

		Set<Integer> questionIdsSet = new HashSet<Integer>();
		while (questionIdsSet.size() <= NUMBER_OF_QUESTIONS) {
			questionIdsSet.add(rand.nextInt(numberOfQustions));
		}

		return questionIdsSet;
	}

	private Game createGameObject(String pid, Set<Integer> questionIdsSet) {
		Game game = new Game();
		Iterator<Question> iterator = questionServiceRepo.findAllById(questionIdsSet).iterator();
		ArrayList<Question> arrQest = new ArrayList<Question>();

		while (iterator.hasNext()) {
			arrQest.add(iterator.next());
		}
		game.setQuestions(arrQest);
		game.setPid(pid);

		return game;
	}

	// function for get-questing rest
	public Question getQuestionFromGame(String gid){
		Game game = gameServiceRepo.findByIdQuestionsAndIndex(gid);
		Question question = game.getQuestions().get(game.getCurrentQuestion());
		question.setCorrectAnswer(null);
		return question;

	}

	// function for answer-question rest
	public Boolean answerQuestion(Integer answer, String gid) throws Exception  {
		boolean isCorrect =false;
		Game game = gameServiceRepo.findById(gid).get();
		
		if(game.getCurrentQuestion()>=NUMBER_OF_QUESTIONS)
		{
			throw new Exception("No more questions, you can end the game.");
		}
		
		Question question = game.getQuestions().get(game.getCurrentQuestion());
		
			game.setCurrentQuestion(game.getCurrentQuestion() + 1);
			if (answer == question.getCorrectAnswer()) {
				game.setScore(game.getScore() + 100);
				isCorrect =true;
			}
			gameServiceRepo.save(game);
		

		return isCorrect;
	}

}
