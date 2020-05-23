package io.github.plindzek.lang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(value = "user")
public class LangControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LangService langService;

    @Test
    public void shouldReturnStatus200AndCorrectJson() throws Exception {

        Lang lang = new Lang();
        lang.setLangCode("1");
        lang.setLangId(12);
        LangDTO langDto = new LangDTO(lang);
        List<LangDTO> langs = new ArrayList<>();
        langs.add(langDto);

        when(langService.findAll()).thenReturn(langs);

        mockMvc
                .perform(get("/api/langs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[0].langCode", containsString("1")))
                .andExpect(jsonPath("[0].langId", is(12)));
    }
    @Test
    public void shouldReturnExpectedText() throws Exception {
        //service mocking
        when(langService.prepareLogin(null)).thenReturn("Siema" + "<br />" + "kitty" + "!");

        mockMvc
                .perform(get("/api"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Siema" + "<br />" + "kitty" + "!")));
    }
    @Test
    public void shouldReturnExpectedTextWithParameters() throws Exception {

        when(langService.prepareLogin(1)).thenReturn("Siema<br />Adam!");

        mockMvc
                .perform(get("/api")
                        .param("lang", "1")
                        .param("name", "Adam"))
                .andDo(print())
                .andExpect(status().isOk())

                .andExpect(content().string(containsString("Siema<br />Adam!")));
//checks whether the mock has been called the specified number of times
        verify(langService, times(1)).prepareLogin(anyInt());

    }
}
