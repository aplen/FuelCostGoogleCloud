package io.github.plindzek.lang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(value = "Adam")
public class LangServiceTest {

    @Autowired
    private LangService langService;


    @Test
    public void shouldReturnCorrectText() {
        assertThat(langService.prepareLogin(1)).isEqualTo("Siema<br />Adam!");
    }
}
