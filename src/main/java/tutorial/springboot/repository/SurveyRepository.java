package tutorial.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tutorial.springboot.model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

	
}
