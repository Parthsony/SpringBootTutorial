package tutorial.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Model class of Survey
 * 
 * @author Parth Soni
 *
 */
@Getter
@Setter
@Entity
@Table(name = "survey")
public class Survey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_id")
	private Long id;

	private String title;

	private String description;

	@JsonIgnoreProperties("survey")
	@OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Question> questions;

	public void addQuestion(Question question) {
		questions.add(question);
		question.setSurvey(this);
	}

	public void removeQuestion(Question question) {
		questions.remove(question);
		question.setSurvey(null);
	}
}
