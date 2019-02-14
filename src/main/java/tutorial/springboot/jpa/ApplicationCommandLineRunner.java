package tutorial.springboot.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tutorial.springboot.constant.AppContants;
import tutorial.springboot.model.Question;
import tutorial.springboot.model.Survey;
import tutorial.springboot.repository.SurveyRepository;

/**
 * The purpose of this class is to populate sample data when application get
 * starts. i.e Add one survey and questions into database as soon as spring boot
 * app launches command line runners run method will be Invoked
 * 
 * @author Parth Soni
 */

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {

	@Autowired
	private SurveyRepository surveyRepository;

	@Override
	public void run(String... args) throws Exception {

		List<String> options = Arrays.asList(AppContants.INDIA, AppContants.RUSSIA, AppContants.UNITED_STATES,
				AppContants.CHINA);

		Survey survey1 = new Survey();

		Question question1 = new Question();
		question1.setDescription("Largest Country in the World By land");
		question1.setCorrectAnswer(AppContants.RUSSIA);
		question1.setOptions(options);
		question1.setSurvey(survey1);

		Question question2 = new Question();
		question2.setDescription("Largest Country in the World By land");
		question2.setCorrectAnswer(AppContants.CHINA);
		question2.setOptions(options);
		question2.setSurvey(survey1);

		Question question3 = new Question();
		question3.setDescription("Highest GDP in the World");
		question3.setCorrectAnswer(AppContants.UNITED_STATES);
		question3.setOptions(options);
		question3.setSurvey(survey1);

		Question question4 = new Question();
		question4.setDescription("Second largest english speaking country");
		question4.setCorrectAnswer(AppContants.INDIA);
		question4.setOptions(options);
		question4.setSurvey(survey1);

		List<Question> questions = new ArrayList<>();
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		questions.add(question4);

		survey1.setTitle("General Questions");
		survey1.setDescription("Survey about general knowledge questions asked to people");
		survey1.setQuestions(questions);

		surveyRepository.save(survey1);
	}

}
