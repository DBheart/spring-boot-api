package com.example.restdocwithswagger.sample;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Get 테스트")
    @WithMockUser(username = "USER", password = "1234", roles = "USER")
    public void getTest() throws Exception {
        mockMvc.perform(get("/sample"))
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentationWrapper.document("test-get",
                        ResourceSnippetParameters.builder()
                                .tag("테스트 param")
                                .summary("Get 테스트")
                                .description("Get 테스트")
                                .responseSchema(Schema.schema("MainResponse"))
                        ,
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메세지")
                        )
                ));

    }

    @Test
    @DisplayName("get path")
    @WithMockUser(username = "USER", password = "1234", roles = "USER")
    public void getPathUri() throws Exception {
        //when
        var actual = mockMvc.perform(
                get("/sample/get/{userId}","kim")
                );

        //then
        actual.andExpect(status().isOk())

                .andDo(MockMvcRestDocumentationWrapper.document("get path",
                        ResourceSnippetParameters.builder()
                                .tag("테스트 param")
                                .summary("Get 테스트- path")
                                .description("Get 테스트- path")
                                .requestParameters(
                                        ResourceDocumentation.parameterWithName("userId").description("user id")
                                )

                        ,
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));

        ;
    }

    //리퀘스트를... 날리려면 위와 같이 해야하나? 리스폰스... ㅇㅇ?
    
    @Test
    @DisplayName("Post 테스트")
    @WithMockUser(username = "USER", password = "1234", roles = "USER")
    void postTest() throws Exception {
        // given

        // when
        var actual = mockMvc.perform(
                post("/sample/dto")
                        .param("message","kim")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(mainResponse))
                        .accept(MediaType.APPLICATION_JSON)

        );

        //then
        actual.andExpect(status().isOk());

        actual.andDo(print());

        actual.andDo(MockMvcRestDocumentationWrapper.document("post param",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                                .tag("테스트 param")
                                .summary("Post 테스트- body")
                                .requestParameters(
                                        ResourceDocumentation.parameterWithName("message").description("message")
                                ).build()
                )
        ));
        // when
        // then
    }

}