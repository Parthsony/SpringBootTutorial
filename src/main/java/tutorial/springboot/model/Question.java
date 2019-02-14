package tutorial.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Modal class of Question
 * 
 * @author Parth Soni
 *
 */
@Getter
@Setter
@Entity
@Table(name = "question")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	private Long id;

	private String description;

	private String correctAnswer;

	@JsonIgnoreProperties("questions")
	@ManyToOne()
	@JoinColumn(name = "survey_id")
	private Survey survey;

	@ElementCollection
	private List<String> options;
}
