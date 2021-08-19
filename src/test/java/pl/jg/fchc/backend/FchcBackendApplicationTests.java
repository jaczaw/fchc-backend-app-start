package pl.jg.fchc.backend;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import pl.jg.fchc.backend.domain.dto.KlubDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class FchcBackendApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate; // available with Spring Web MVC

	@LocalServerPort
	private Integer port;

	@Test
	void contextLoads() {
		ResponseEntity<KlubDTO> actuatorResult =
				this.testRestTemplate.getForEntity("/api/kluby/2", KlubDTO.class);
		log.info(String.format("status: %d wartosc: %s",
				actuatorResult.getStatusCodeValue(),
				actuatorResult.getBody().toString()));

		assertEquals(200, actuatorResult.getStatusCodeValue());
		assertEquals("Aalborg BK (DEN)",actuatorResult.getBody().getNazwa());
	}


}
