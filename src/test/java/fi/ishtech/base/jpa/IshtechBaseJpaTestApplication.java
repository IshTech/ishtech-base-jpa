package fi.ishtech.base.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "fi.ishtech.base" })
public class IshtechBaseJpaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IshtechBaseJpaTestApplication.class, args);
	}

}