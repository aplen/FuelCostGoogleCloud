package io.github.plindzek.hello;

import io.github.plindzek.lang.Lang;
import io.github.plindzek.lang.LangRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService service;


    @Test
    public void shouldReturnExpectedText() throws Exception {
        when(service.prepareGreeting(null, null)).thenReturn("Siema" + "<br />" + "kitty" + "!");

        mockMvc
                .perform(get("/api"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Siema" + "<br />" + "kitty" + "!")));
    }

    @Test
    public void shouldReturnExpectedTextWithParameters() throws Exception {
        when(service.prepareGreeting("Adam", 1)).thenReturn("Siema<br />Adam!");

        mockMvc
                .perform(get("/api")
                        .param("langId", "1")
                        .param("name", "Adam"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Siema<br />Adam!")));

//        verify(service, times(1)).prepareGreeting(anyString(), anyInt());

    }


}
