package com.mila.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mila.quiz.dao.Game;
import com.mila.quiz.dao.Question;
import com.mila.quiz.dao.SimpleResponseObject;
import com.mila.quiz.gameservice.GameService;
import com.mila.quiz.services.GameServiceRepo;
import com.mila.quiz.services.QuestionServiceRepo;

@Controller
public class GameRestController  {
	
	@Autowired
	GameService gameService;
	@Autowired
	QuestionServiceRepo questionServiceRepo;
	@Autowired
	GameServiceRepo gameServiceRepo;

	private final String GAME_PREFIX = "/game";

	@RequestMapping(method = RequestMethod.GET, value = GAME_PREFIX + "/start")
	public @ResponseBody SimpleResponseObject startGame(@RequestParam("pid") String pid) {

		return new SimpleResponseObject(gameService.initilizeGame(pid));

	}

	@RequestMapping(method = RequestMethod.GET, value = GAME_PREFIX + "/get-question")
	public @ResponseBody Question getQuestion(@RequestParam("gid") String gid) {
		return gameService.getQuestionFromGame(gid);

	}

	@RequestMapping(method = RequestMethod.GET, value = GAME_PREFIX + "/answer-question")
	public @ResponseBody SimpleResponseObject answerQuestion(@RequestParam("gid") String gid,
			@RequestParam("answer") Integer answer) throws Exception {
		return new SimpleResponseObject(gameService.answerQuestion(answer, gid));

	}

	@RequestMapping(method = RequestMethod.GET, value = GAME_PREFIX + "/get-status")
	public @ResponseBody Game getGameStatus(@RequestParam("gid") String gid) {
		Game game = gameServiceRepo.findByIdAndExcludeQuestions(gid);
		return game;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = GAME_PREFIX + "/end")
	public @ResponseBody void endGame(@RequestParam("gid") String gid) {
		gameServiceRepo.delete(gameServiceRepo.findById(gid).get());
	}

}
