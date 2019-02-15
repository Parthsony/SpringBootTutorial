package tutorial.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import tutorial.springboot.model.Survey;
import tutorial.springboot.repository.SurveyRepository;

@Component
public class SurveyService {

	@Autowired
	private SurveyRepository surveyRepository;

	/**
	 * Populates list of survey objects when application gets loaded
	 * 
	 * @return List of all the surveys
	 */
	@ApplicationScope
	public List<Survey> getSurveyRepository() {
		return surveyRepository.findAll();
	}

	public List<Survey> retrieveAllSurveys() {
		return getSurveyRepository();
	}

	public Survey retrieveSurvey(Long surveyId) {
		return getSurveyRepository().stream().filter(survey -> surveyId.equals(survey.getId())).findAny().orElse(null);
	}

	public Survey addSurvey(Survey survey) {
		Survey newSurvey = surveyRepository.save(survey);
		newSurvey.getQuestions().forEach(question -> question.setSurvey(newSurvey));
		return surveyRepository.save(newSurvey);
	}

	public Survey updatedSurveyTitle(Long surveyId, String dtoSurveyTitle) {

		if (surveyRepository.existsById(surveyId)) {
			surveyRepository.updateTitle(surveyId, dtoSurveyTitle);
			return retrieveSurvey(surveyId);
		}

		return null;
	}

	public String deleteSurvey(Long surveyId) {

		if (surveyRepository.existsById(surveyId)) {
			surveyRepository.deleteById(surveyId);
		}
		return null;
	}

}
