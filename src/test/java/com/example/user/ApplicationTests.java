package com.example.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCheckHealthController() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/checkhealth/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"code\":200,\"message\":\"ok\",\"data\":{\"id\":1,\"status\":0,\"message\":\"ok\"}}")));
    }
}
