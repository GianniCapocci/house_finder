package gr.hua.dit.house_finder;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class HouseFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseFinderApplication.class, args);
	}

}
