package tutorial.springboot.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tutorial.springboot.model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

	@Transactional
	@Modifying
	@Query("update Survey set title = :surveyTitle where survey_id = :surveyId")
	public void updateTitle(Long surveyId, String surveyTitle);
}
