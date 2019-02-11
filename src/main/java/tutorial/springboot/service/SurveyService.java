package tutorial.springboot.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import tutorial.springboot.model.Question;
import tutorial.springboot.model.Survey;

@Component
public class SurveyService {

	private static final String CHINA = "China";
	private static final String UNITED_STATES = "United States";
	private static final String RUSSIA = "Russia";
	private static final String INDIA = "India";

	private static List<Survey> surveys = new ArrayList<>();

	public List<Survey> retrieveAllSurveys() {
		return surveys;
	}

	public Survey retrieveSurvey(String surveyId) {
		return surveys.stream().filter(survey -> surveyId.equals(survey.getId())).findAny().orElse(null);
	}

	public List<Question> retrieveQuestions(String surveyId) {
		Survey retrieveSurvey = retrieveSurvey(surveyId);
		return null != retrieveSurvey ? retrieveSurvey.getQuestions() : new ArrayList<>();
	}

	public Question retrieveQuestion(String surveyId, String questionId) {

		return retrieveSurvey(surveyId) == null ? null
				: retrieveSurvey(surveyId).getQuestions().stream()
						.filter(question -> questionId.equals(question.getId())).findAny().orElse(null);
	}

	private SecureRandom random = new SecureRandom();

	public Question addQuestion(String surveyId, Question question) {

		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		question.setId(new BigInteger(130, random).toString(32));
		survey.getQuestions().add(question);

		return question;
	}

	static {

		Question question1 = new Question("Question1", "Largest Country in the World", RUSSIA,
				Arrays.asList(INDIA, RUSSIA, UNITED_STATES, CHINA));
		Question question2 = new Question("Question2", "Most Populus Country in the World", CHINA,
				Arrays.asList(INDIA, RUSSIA, UNITED_STATES, CHINA));
		Question question3 = new Question("Question3", "Highest GDP in the World", UNITED_STATES,
				Arrays.asList(INDIA, RUSSIA, UNITED_STATES, CHINA));
		Question question4 = new Question("Question4", "Second largest english speaking country", INDIA,
				Arrays.asList(INDIA, RUSSIA, UNITED_STATES, CHINA));

		List<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3, question4));

		Survey survey = new Survey("Survey1", "My Favorite Survey", "Description of the Survey", questions);

		surveys.add(survey);
	}

}
