package pl.jg.fchc.backend.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import pl.jg.fchc.backend.domain.model.entity.Klub;
import pl.jg.fchc.backend.domain.repository.KlubRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class KlubServiceTest {

    @Autowired
    private KlubService klubService;

    @MockBean
    private KlubRepository klubRepository;


    @Test
    @DisplayName("Wyszukiwanie klubu po id ")
    //@Disabled
    void findKlubById() {
        //given
        Klub k = new Klub();
        k.setId(1L);
        k.setNazwa("Lada");
        //then
        when(klubService.findKlubById(1L)).thenReturn(Optional.of(k));
        when(klubRepository.findById(1L)).thenReturn(Optional.of(k));
        Optional<Klub> resultService = klubService.findKlubById(1L);
        Optional<Klub> resultRepository = klubRepository.findById(1L);
        log.info(String.format("TEST:klubService.findKlubById(1L) = %s",resultService.get().getNazwa()));
        log.info(String.format("TEST:klubRepository.findById(1L) = %s",resultService.get().getId()));

        // expected
        assertEquals(1L,resultService.get().getId());
        assertEquals("Lada",resultRepository.get().getNazwa());
    }
}