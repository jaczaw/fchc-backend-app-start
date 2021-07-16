package pl.jg.fchc.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import pl.jg.fchc.backend.domain.dto.KlubDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class FchcBackendApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate; // available with Spring Web MVC

	@LocalServerPort
	private Integer port;

	@Test
	void printPortsInUse() {
		System.out.println(port);

	}

	@Test
	void httpClientExample() {

		}

	@Test
	void contextLoads() {

		ResponseEntity<KlubDTO> actuatorResult =
				this.testRestTemplate.getForEntity("/api/kluby/2", KlubDTO.class);
		System.out.println(actuatorResult.getStatusCodeValue());

		assertEquals(200, actuatorResult.getStatusCodeValue());

		System.out.println(actuatorResult.getBody().getNazwa());
		assertEquals("FC Köln (GER)",actuatorResult.getBody().getNazwa());
	}


}
