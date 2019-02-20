package tutorial.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tutorial.springboot.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
