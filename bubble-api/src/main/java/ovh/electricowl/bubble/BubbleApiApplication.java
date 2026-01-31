package ovh.electricowl.bubble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ovh.electricowl")
public class BubbleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BubbleApiApplication.class, args);
	}

}
