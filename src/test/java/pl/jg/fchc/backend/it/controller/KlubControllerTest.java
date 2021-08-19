package pl.jg.fchc.backend.it.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import pl.jg.fchc.backend.domain.model.entity.Klub;
import pl.jg.fchc.backend.domain.repository.KlubRepository;
import pl.jg.fchc.backend.it.AbstractIntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
public class KlubControllerTest extends AbstractIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    KlubRepository klubRepository;


    @Test
    public void simpleJPATest() {
        Klub klub = new Klub();
        klub.setNazwa("Moj Klub");
        klub.setTemp("test");
        klubRepository.save(klub);

        Klub result = restTemplate.getForObject("/api/kluby/" + klub.getId(), Klub.class);
        log.info(String.format("Dodano klub: %s o identyfikatorze: %d",result.getNazwa(),result.getId()));

        assertEquals("Moj Klub", result.getNazwa());
    }

}
