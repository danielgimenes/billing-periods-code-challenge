package com.ppro.interview.billing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void sayHiToKitten() throws Exception {
        mockMvc.perform(get("/v1/hello/kitten"))
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"message\": \"hello\", \"recipient\": \"kitten\" }", true));
    }

    @Test
    void returnsNotFoundIfNoNameProvided() throws Exception {
        mockMvc.perform(get("/v1/hello/")).andExpect(status().isNotFound());
    }
}