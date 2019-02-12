package tutorial.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tutorial.springboot.repository.UserRepository;

/**
 * As soon as spring boot app launches command line method will be Invoked
 * 
 * @author parth.soni
 *
 */

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("Parth", "Admin"));
		userRepository.save(new User("Kevin", "Admin"));
		userRepository.save(new User("Vatsal", "User"));
		userRepository.save(new User("Nehal", "User"));

		userRepository.findDistinctRoles().forEach(user -> {
			if (userRepository.existsById(user.getId())) {
				log.info(user.toString());
			}
		});
	}

}
