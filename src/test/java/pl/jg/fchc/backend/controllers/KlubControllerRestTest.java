package pl.jg.fchc.backend.controllers;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pl.jg.fchc.backend.domain.dto.KlubViewDto;
import pl.jg.fchc.backend.domain.model.entity.Klub;
import pl.jg.fchc.backend.domain.service.KlubService;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class KlubControllerRestTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    KlubService klubService;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://localhost";
        RestAssured.port = 8181;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    public void test_get_Kluby_Endpoint() {
        Mockito.when(klubService.getAll())
                .thenReturn(List.of(KlubViewDto.builder().id(1L).nazwa("Motor").build()));

        given().
                //pathParam("id",1)
        when().
                get("/api/slowniki/kluby").
        then().
                statusCode(200).
                body("$.size()",is(1),"[0].nazwa",is("Motor"));
        Mockito.verify(klubService).getAll();
        Mockito.verifyNoMoreInteractions(klubService);
    }

    @Test
    public void list() throws Exception {
        List<KlubViewDto> klubViewDtos = List.of(
                KlubViewDto.builder().id(1L).nazwa("Klub1").temp("Test1").build(),
                KlubViewDto.builder().id(2L).nazwa("Klub2").temp("Test2").build(),
                KlubViewDto.builder().id(3L).nazwa("Klub3").temp("Test3").build()
        );
        Mockito.when(klubService.getAll())
                .thenReturn(klubViewDtos);
        mockMvc.perform(get("/api/slowniki/kluby"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("[0].nazwa").value("Klub1"));

        Mockito.verify(klubService).getAll();
        Mockito.verifyNoMoreInteractions(klubService);
        log.info(String.format("klubViewDtosList: size(%d) klubViewDtos.get(0): %s",klubViewDtos.size(),klubViewDtos.get(0)));
    }

}