package com.mila.quiz.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mila.quiz.dao.Question;
import com.mila.quiz.services.QuestionServiceRepo;

@Controller
public class QuestionRestController {

	@Autowired
	QuestionServiceRepo questionService;
	private final static  String QUESTION_PREFIX = "/quesions";
		
	@RequestMapping(method = RequestMethod.GET, value = QUESTION_PREFIX+"/get-question")
	public @ResponseBody Question getQuestion(@RequestParam("id") int id) {
	
		return questionService.findById(id).get();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = QUESTION_PREFIX+"/max-id")
	public @ResponseBody Question getMaxId() {
	
		return questionService.findFirstByOrderByIdDesc();
	}


	@RequestMapping(method = RequestMethod.PUT, value = QUESTION_PREFIX+"/add-question")
	public @ResponseBody void putQuestion(@RequestBody @Valid Question qestion) throws Exception {
		 questionService.save(qestion);
	}

}
