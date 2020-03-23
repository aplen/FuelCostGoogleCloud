package io.github.plindzek.lang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LangControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LangService langService;

    @Test
    public void shouldReturnExpectedStatus() throws Exception {

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
}
