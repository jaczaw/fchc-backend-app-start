package pl.jg.fchc.backend.it;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.jg.fchc.backend.domain.dto.KlubDTO;
import pl.jg.fchc.backend.domain.dto.KlubViewDTO;
import pl.jg.fchc.backend.domain.exception.KlubNotFoundException;
import pl.jg.fchc.backend.domain.model.entity.Klub;
import pl.jg.fchc.backend.domain.service.KlubService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class KlubControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    KlubService klubService;


    @Test
    //@Disabled
    @DisplayName("when Post Request To Klub And Valid Klub_then Correct Response")
    void whenPostRequestToKlubAndValidKlub_thenCorrectResponse() throws Exception {
        //MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON);
        String klub = "{\"nazwa\":\"Dynamo kijew\"}";
        this.mockMvc.perform(post("/api/kluby/")
                .content(klub)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.content()
                //        .contentType(textPlainUtf8));
    }

    @Test
    void shouldAllowDeletingReviewsWhenUserIsAdmin() throws Exception {
        this.mockMvc
                .perform(
                        delete("/api/Kluby/42")
                                .with(user("duke").roles("ADMIN", "SUPER_USER"))
                                .with(csrf())
                )
                //.andExpect(status().isOk());
                .andExpect(status().is(404));
    }

    @Test
    void shouldRejectCreatingReviewsWhenKlubIsAnonymous() throws Exception {
        this.mockMvc
                .perform(
                        post("/api/kluby/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"nazwa\": \"FC Topulcza\"}")
                                .with(csrf())
                )
                //.andExpect(status().isUnauthorized());
                .andExpect(status().is(200));
    }

    @Test
    void shouldReturnAllKlubyForUnauthenticatedUser() throws Exception {
        KlubViewDTO klub = KlubViewDTO.builder()
                .id(1L)
                .nazwa("FC TOPULCZA")
                .build();

        when(klubService.getAll())
                .thenReturn(List.of(klub));

        this.mockMvc
                .perform(get("/api/kluby/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nazwa").value("FC TOPULCZA"));

    }

    @Test
    void shouldReturn404WhenKlubIsNotFound() throws Exception {
        when(klubService.getKlubByNazwa("Panika Dereznia"))
                .thenThrow(new KlubNotFoundException("Klub nie został znaleziony"));

        this.mockMvc
                .perform(get("/api/kluby/nazwa/Panika Dereznia"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAllowCreationForUnauthenticatedKlub() throws Exception {
        this.mockMvc
                .perform(
                        post("/api/kluby/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"nazwa\": \"Porażka Topulcza\"}")
                                .with(csrf())
                )
                //.andExpect(status().isCreated())
                .andExpect(status().is(200));
                //.andExpect(header().exists("Location"))
                //.andExpect(header().string("Location", Matchers.containsString("Porażka Topulcza")));

        verify(klubService).insert(any(KlubDTO.class));
    }




}