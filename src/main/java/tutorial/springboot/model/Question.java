package tutorial.springboot.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Question {

	private String id;
	private String description;
	private String correctAnswer;
	private List<String> options;
}
