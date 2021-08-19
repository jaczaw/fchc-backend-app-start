package pl.jg.fchc.backend.it;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import pl.jg.fchc.backend.domain.model.entity.Klub;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class KlubControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    void sprawdzCzyEndpointDostepny(){
        ResponseEntity<Klub> actuatorResult =
                this.testRestTemplate.getForEntity("/api/kluby/1", Klub.class);
        log.info(String.format(" Kod statusu: %s",actuatorResult.getStatusCodeValue()));

        assertEquals(200, actuatorResult.getStatusCodeValue());
    }
}