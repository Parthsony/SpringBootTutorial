package tutorial.springboot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tutorial.springboot.model.Question;
import tutorial.springboot.service.QuestionService;

/**
 * Controller that expose questions Apis.
 * 
 * <p>
 * In addition, this controller have endpoints which allows to do CRUD operation
 * of question. i.e. get all questions of particular survey, get particular
 * question, add and update question.
 * 
 * @author Parth Soni
 */
@RestController
@RequestMapping("surveys/{surveyId}/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping
	public List<Question> getQuestionsListOfSurvey(@PathVariable final Long surveyId) {
		return questionService.retrieveQuestions(surveyId);
	}

	@GetMapping("/{questionId}")
	public ResponseEntity<Question> getQuestionFromSurvey(@PathVariable final Long surveyId,
			@PathVariable final Long questionId) {

		final Question question = questionService.retrieveQuestion(surveyId, questionId);

		if (null != question) {
			return new ResponseEntity<>(question, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(question, HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Void> addQuestionToSurvey(@PathVariable final Long surveyId,
			@RequestBody final Question question) {

		Question newQuestion = questionService.addQuestion(surveyId, question);

		if (null == newQuestion)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(newQuestion.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{questionId}")
	public ResponseEntity<String> updateQuestion(@PathVariable final Long surveyId, @PathVariable final Long questionId,
			@RequestBody final Question question) {

		Question updatedQuestion = questionService.updateQuestion(surveyId, questionId, question);

		if (null == updatedQuestion) {
			return new ResponseEntity<>("Question not found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("Question has been updated succesfully", HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{questionId}")
	public ResponseEntity<String> deletedQuestion(@PathVariable final Long questionId) {

		questionService.deletedQuestion(questionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
