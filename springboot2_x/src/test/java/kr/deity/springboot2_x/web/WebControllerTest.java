package kr.deity.springboot2_x.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebController.class)
class WebControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("성공")
    @WithMockUser(username = "USER", password = "1234", roles = "USER")
    public void success() throws Exception {
        var actual = mockMvc.perform(get("/web/list"));
        actual.andDo(print());
        actual.andExpect(status().isOk());

    }

    @Test
    @DisplayName("오류")
    @WithMockUser(username = "USER", password = "1234", roles = "USER")
    public void error() throws Exception {
        var actual = mockMvc.perform(get("/web/list/500"));
        actual.andDo(print());
        actual.andExpect(status().is5xxServerError());

    }

    @Test
    @DisplayName("api 없음")
    public void notAPI() throws Exception {
        var actual = mockMvc.perform(get("/web/list/404"));
        actual.andDo(print());
        actual.andExpect(status().is4xxClientError());

    }
}