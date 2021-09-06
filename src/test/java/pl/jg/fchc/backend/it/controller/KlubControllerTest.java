package pl.jg.fchc.backend.it.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import pl.jg.fchc.backend.domain.dto.KlubDto;
import pl.jg.fchc.backend.domain.service.KlubService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Slf4j
//@SpringBootTest
//@AllArgsConstructor
class KlubControllerTest {//extends AbstractIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    KlubService klubService;


    @Test
    @Disabled
    void simpleJPATest() {
        KlubDto klubDto= KlubDto.builder().nazwa("Moj Klub").temp("test").build();
        klubService.insert(klubDto);

        KlubDto result = restTemplate.getForObject("/api/slowniki/kluby/" + klubDto.getId(), KlubDto.class);
        log.info(String.format("Dodano klub: %s o identyfikatorze: %d",result.getNazwa(),result.getId()));

        assertThat(result.getId()).isNotNull();
        assertThat(result.getNazwa()).isEqualTo("Moj Klub");
    }

}
