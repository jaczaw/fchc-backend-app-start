package pl.jg.fchc.backend.domain.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.jg.fchc.backend.domain.model.entity.Klub;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Jacek Zawislak on 01.01.2021
 */

@Slf4j
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
class KlubRepositoryTest {

    @Autowired
    KlubRepository klubRepository;

    @AfterEach
    void setUp() {
        Klub klub1 = new Klub();
        klub1.setId(1L);
        klub1.setNazwa("Porazka Topulcza");
        klub1.setTemp("temp1");
        Klub klub2 = new Klub();
        klub1.setId(2L);
        klub1.setNazwa("Porazka Topulcza");
        klub1.setTemp("temp1");

        log.info(String.format("KONFIGURACJA: %s",Replace.values()));

    }

    @Test
    void testKlubNativQuery() {

        int expected = klubRepository.findAll().size();

        log.info(String.format("Liczba wierszy repozytorium %d",expected));

        assertEquals(expected, klubRepository.findAll().size());
    }

    @Test
    void testKlubRepository(){
        Klub klub = new Klub();
        klub.setNazwa("Klub Testowy 1");
        klubRepository.save(klub);
        log.info(String.format("testKlubRepository() id = %d",klub.getId()));

        assertNotNull(klub.getId());
    }


    @BeforeEach
    void tearDown() {
    }
}