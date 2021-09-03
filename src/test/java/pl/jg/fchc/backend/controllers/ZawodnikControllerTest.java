package pl.jg.fchc.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.jg.fchc.backend.domain.model.entity.Zawodnik;

import pl.jg.fchc.backend.domain.repository.ZawodnikRepository;
import pl.jg.fchc.backend.domain.service.ZawodnikService;


import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser // wyłazcenie autoryzacji
//@AutoConfigureMockMvc(addFilters = false) //wyłaczenie filtrów autoryzacja
class ZawodnikControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ZawodnikRepository zawodnikRepository;
    @MockBean
    ZawodnikService zawodnikService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    //@Transactional
    void powinienZwrocicInnegoPilkarzy() throws Exception{

        //given
        Zawodnik nowyZawodnik = Zawodnik.builder()
                .id(1L)
                .nazwaZawodnika("Zawislak Jacek")
                .dataUrodzenia(LocalDate.of(1974,12,14))
                .wzrost(189)
                .build();


        //when
        when(zawodnikService.findZawodnikById(1L)).thenReturn(Optional.of(nowyZawodnik));

        MvcResult mvcResult = mockMvc.perform(get("/api/zawodnicy/1"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        Zawodnik zawodnik = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Zawodnik.class);
        assertThat(zawodnik).isNotNull();
        assertThat(zawodnik.getId()).isEqualTo(nowyZawodnik.getId());
        assertThat(zawodnik.getNazwaZawodnika()).isEqualTo("Zawislak Jacek");
        assertThat(zawodnik.getWzrost()).isEqualTo(189);
        log.info(String.format("zawodnik.getId(): %d   zawodnikEntity.getId(): %d",zawodnik.getId(), nowyZawodnik.getId()));
    }

    @Test
    void powinienZwrocicWybranegoPilkarza() throws Exception{

        //"id":499,"nazwaZawodnika":"BLASZCZYKOWSKI Jakub","wzrost":175,"dataUrodzenia":"1985-12-14"

        when(zawodnikService.findZawodnikById(1L))
                .thenReturn(Optional.of(Zawodnik.builder()
                        .id(1L)
                        .nazwaZawodnika("BLASZCZYKOWSKI Jakub")
                        .wzrost(175)
                        .dataUrodzenia(LocalDate.of(1985, 12, 14))
                        .build()));
        log.info("powinienZwrocicWybranegoPilkarza");
        log.info("\"id\":1,\"nazwaZawodnika\":\"BLASZCZYKOWSKI Jakub\",\"wzrost\":175,\"dataUrodzenia\":\"1985-12-14");


        this.mockMvc.perform(get("/api/zawodnicy/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.nazwaZawodnika", Matchers.is("BLASZCZYKOWSKI Jakub")));

    }
}