package pl.jg.fchc.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Profile;


@SpringBootApplication
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@Profile("default")
public class FchcBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FchcBackendApplication.class, args);
	}

}
