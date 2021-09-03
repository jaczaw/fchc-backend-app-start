package pl.jg.fchc.backend;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import pl.jg.fchc.backend.domain.dto.KlubDto;


import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
class FchcBackendApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate; // available with Spring Web MVC

	@LocalServerPort
	private Integer port;

	@Test
	void contextLoads() {
		ResponseEntity<KlubDto> actuatorResult =
				this.testRestTemplate.getForEntity("/api/kluby/2", KlubDto.class);
		if(Objects.nonNull(actuatorResult.getBody())){
			log.info(String.format("status: %d wartosc: %s",
					actuatorResult.getStatusCodeValue(),
					actuatorResult.getBody().toString()));
		}

		assertThat(actuatorResult.getBody()).isNotNull();
		assertThat(actuatorResult.getStatusCodeValue()).isEqualTo(200);
		assertThat(actuatorResult.getBody().getNazwa()).isEqualTo("Aalborg BK (DEN)");
	}


}
