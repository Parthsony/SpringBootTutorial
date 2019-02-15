package tutorial.springboot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tutorial.springboot.model.Survey;
import tutorial.springboot.service.SurveyService;

/**
 * Controller that expose Survey Apis.
 * 
 * <p>
 * In addition, this controller have end points which allows to do CRUD
 * operation of Survey. i.e. get all surveys, add survey and update survey.
 * 
 * @author Parth Soni
 */
@RestController
@RequestMapping("surveys")
public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	@GetMapping
	public ResponseEntity<List<Survey>> getAllSurveys() {
		return new ResponseEntity<>(surveyService.retrieveAllSurveys(), HttpStatus.OK);
	}

	@GetMapping("/{surveyId}")
	public ResponseEntity<Survey> getSurvey(@PathVariable final Long surveyId) {

		Survey retrieveSurvey = surveyService.retrieveSurvey(surveyId);

		if (null != retrieveSurvey) {
			return new ResponseEntity<>(retrieveSurvey, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(retrieveSurvey, HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Void> addSurvey(@RequestBody final Survey dtoSurvey) {

		Survey newSurvey = surveyService.addSurvey(dtoSurvey);

		if (null == newSurvey)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(newSurvey.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PatchMapping("/{surveyId}")
	public ResponseEntity<Survey> updateTitleOfTheSurvey(@PathVariable Long surveyId, @RequestBody Survey dtoSurvey) {

		Survey survey = surveyService.updatedSurveyTitle(surveyId, dtoSurvey.getTitle());

		if (null != survey) {
			return new ResponseEntity<>(survey, HttpStatus.OK);
		}
		return null;
	}

	@DeleteMapping("/{surveyId}")
	public ResponseEntity<String> deleteSurvey(@PathVariable Long surveyId) {

		String deleteSurvey = surveyService.deleteSurvey(surveyId);
		if (null != deleteSurvey) {
			return new ResponseEntity<>(deleteSurvey, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
