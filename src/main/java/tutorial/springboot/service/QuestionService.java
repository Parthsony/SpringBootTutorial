package tutorial.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import tutorial.springboot.model.Question;
import tutorial.springboot.model.Survey;
import tutorial.springboot.repository.QuestionRepository;
import tutorial.springboot.repository.SurveyRepository;

@Component
public class QuestionService {

	@Autowired
	private SurveyRepository surveyRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@ApplicationScope
	public List<Question> getQuestionRepository() {
		return questionRepository.findAll();
	}

	public List<Question> retrieveQuestions(Long surveyId) {

		Survey retrieveSurvey = surveyRepository.findById(surveyId).orElse(null);

		return null != retrieveSurvey ? retrieveSurvey.getQuestions() : new ArrayList<>();
	}

	public Question retrieveQuestion(Long surveyId, Long questionId) {

		Survey retrieveSurvey = surveyRepository.findById(surveyId).orElse(null);

		return retrieveSurvey == null ? null
				: retrieveSurvey.getQuestions().stream().filter(question -> questionId.equals(question.getId()))
						.findAny().orElse(null);
	}

	public Question addQuestion(Long surveyId, Question question) {

		Survey survey = surveyRepository.findById(surveyId).orElse(null);

		if (survey == null) {
			return null;
		}

		survey.getQuestions().add(question);

		return questionRepository.save(question);
	}

	public Question updateQuestion(Long surveyId, Long questionIdDto, Question questionDto) {

		Question question = questionRepository.findById(questionIdDto).orElse(null);

		if (null == question) {
			return null;
		}

		question.setId(questionIdDto);
		question.setDescription(questionDto.getDescription());
		question.setOptions(questionDto.getOptions());
		question.setSurvey(surveyRepository.findById(surveyId).orElse(null));

		return questionRepository.save(question);
	}

	public void deletedQuestion(Long questionId) {
		Question question = questionRepository.findById(questionId).orElse(null);
		if (null != question) {
			questionRepository.delete(question);
		}
	}

}
