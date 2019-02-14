package tutorial.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tutorial.springboot.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
