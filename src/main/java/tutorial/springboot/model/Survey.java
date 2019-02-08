package tutorial.springboot.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Survey {

	private String id;
	private String title;
	private String description;
	private List<Question> questions;

}
