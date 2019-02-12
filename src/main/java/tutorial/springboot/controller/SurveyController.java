package tutorial.springboot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tutorial.springboot.model.Question;
import tutorial.springboot.model.Survey;
import tutorial.springboot.service.SurveyService;

@RestController
public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	@GetMapping("surveys")
	public List<Survey> getAllSurveys() {
		return surveyService.retrieveAllSurveys();
	}

	@GetMapping("surveys/{surveyId}")
	public Survey getSurvey(@PathVariable final String surveyId) {
		return surveyService.retrieveSurvey(surveyId);
	}

	@GetMapping("surveys/{surveyId}/questions")
	public List<Question> getQuestionsListOfSurvey(@PathVariable final String surveyId) {
		return surveyService.retrieveQuestions(surveyId);
	}

	@GetMapping("surveys/{surveyId}/questions/{questionId}")
	public Question getQuestionFromSurvey(@PathVariable final String surveyId, @PathVariable final String questionId) {
		return surveyService.retrieveQuestion(surveyId, questionId);
	}

	@PostMapping("surveys/{surveyId}/add")
	public ResponseEntity<Void> addQuestionToSurvey(@PathVariable final String surveyId,
			@RequestBody final Question question) {

		Question newQuestion = surveyService.addQuestion(surveyId, question);

		if (null == newQuestion)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(newQuestion.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
