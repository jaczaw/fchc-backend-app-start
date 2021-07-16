package pl.jg.fchc.backend.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.jg.fchc.backend.domain.model.entity.Klub;
import pl.jg.fchc.backend.domain.repository.KlubRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class KlubServiceTest {

    @Autowired
    private KlubService klubService;

    @MockBean
    private KlubRepository klubRepository;

    @Test
    void getAllKlub() {
    }

    @Test
    @DisplayName("Wyszukiwanie klubu po id ")
    void findKlubById() {
        //given
        Klub k = new Klub();
        k.setId(1L);
        k.setNazwa("Lada");
        //then
        when(klubRepository.findById(1L)).thenReturn(Optional.of(k));
        Optional<Klub> result = klubService.findKlubById(2L);

        // expected
        assertEquals(1L,result.get().getId());
    }
}