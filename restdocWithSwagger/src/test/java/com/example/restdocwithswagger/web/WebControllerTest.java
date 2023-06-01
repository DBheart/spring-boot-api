package com.example.restdocwithswagger.web;

import com.epages.restdocs.apispec.FieldDescriptors;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(WebController.class)
@AutoConfigureRestDocs
class WebControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("웹 성공사례 확인")
    @WithMockUser(username = "USER",password = "1234")
    public void successList() throws Exception {
        var actual = mockMvc.perform(RestDocumentationRequestBuilders.get("/web/list"));
        actual.andExpect(MockMvcResultMatchers.status().isOk());

        actual.andDo(MockMvcRestDocumentationWrapper.document("web-list-success",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder().tag("웹테스트").description("웹 성공테스트")
                                .responseFields(responseBase(JsonFieldType.ARRAY,"웹데이터"))
                                .build()
                )
        ));
    }

    //TODO 리턴값이 없는 경우를 상정한다.

    private FieldDescriptors responseBase(JsonFieldType returnDataType, String returnDescription){
        return new FieldDescriptors(
                fieldWithPath("[]").type(returnDataType).description(returnDescription),
                fieldWithPath("[].id").description("아이디"),
                fieldWithPath("[].name").description("이름")

        );
    }

    @Test
    @DisplayName("500오류 발생")
    @WithMockUser(username = "USER", password = "1234")
    public void serverException() throws Exception {
        var actual = mockMvc.perform(RestDocumentationRequestBuilders.get("/web/500"));

        actual.andDo(print());
        actual.andExpect(MockMvcResultMatchers.status().is5xxServerError());


        actual.andDo(MockMvcRestDocumentationWrapper.document("web-server-error",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder().tag("웹테스트").description("웹 서버오류")
                                .responseFields(
                                        fieldWithPath("error").description("오류코드").optional(),
                                        fieldWithPath("status").description("status"),
                                        fieldWithPath("message").description("오류 메세지")
                                )
                                .build()
                )
        ));
    }

    @Test
    @DisplayName("400 찾을수 없다")
    public void notFoundPage() throws Exception {
        var actual = mockMvc.perform(RestDocumentationRequestBuilders.get("/web/400"));

        actual.andExpect(MockMvcResultMatchers.status().is4xxClientError());
        actual.andDo(print());

        actual.andDo(MockMvcRestDocumentationWrapper.document("web-not-page",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder().description("데이터 없음").tag("웹테스트").build()
                )

        ));
    }

}