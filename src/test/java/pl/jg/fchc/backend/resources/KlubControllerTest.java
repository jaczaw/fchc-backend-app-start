package pl.jg.fchc.backend.resources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import pl.jg.fchc.backend.domain.dto.KlubDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class KlubControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getKluby() {
    }

    @Test
    void getKlubById() {
    }

    @Test
    void addNewKlub() {
    }

    @Test
    void sprawdzCzyEndpointDostepny(){
        ResponseEntity<KlubDTO> actuatorResult =
                this.testRestTemplate.getForEntity("/api/kluby", KlubDTO.class);
        System.out.println(actuatorResult.getStatusCodeValue());

        assertEquals(200, actuatorResult.getStatusCodeValue());
    }
}