package tutorial.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tutorial.springboot.model.Survey;
import tutorial.springboot.service.SurveyService;

/**
 * Controller that expose Survey Apis.
 * 
 * <p>
 * In addition, this controller have end points which allows to do CRUD operation
 * of Survey. i.e. get all surveys, add survey and update survey.
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

}
