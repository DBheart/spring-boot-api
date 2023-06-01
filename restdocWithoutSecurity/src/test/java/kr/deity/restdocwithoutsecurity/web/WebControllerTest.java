package kr.deity.restdocwithoutsecurity.web;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class WebControllerTest {
    @Autowired
    MockMvc mockMvc;

//    @Test
//    public void success() throws Exception {
//        var actual = mockMvc.perform(RestDocumentationRequestBuilders.get("/web/list"));
//        actual.andDo(MockMvcResultHandlers.print());
//        actual.andExpect(MockMvcResultMatchers.status().isOk());
//
//        actual.andDo(MockMvcRestDocumentationWrapper.document("web-test",
//                ResourceSnippetParameters.builder()
//                        .tag("웹 Response 테스트"),
//                preprocessRequest(prettyPrint()),
//                preprocessResponse(prettyPrint()),
//                responseFields(
//                        fieldWithPath("status").description("Http status"),
//                        fieldWithPath("error").description("성공여부"),
//                        fieldWithPath("message").optional().description("성공여부"),
//                        fieldWithPath("data").description("데이터"),
//                        fieldWithPath("data[]").description("데이터"),
//                        fieldWithPath("data[].1").description("첫번쨰데이터"),
//                        fieldWithPath("data[].2").description("두번쨰데이터")
////                        fieldWithPath("[]").description("데이터"),
////                        fieldWithPath("[].1").description("첫번쨰데이터"),
////                        fieldWithPath("[].2").description("두번쨰데이터")
//                )
//        ));
//    }

    @Test
    public void successRecode() throws Exception {
        var actual = mockMvc.perform(RestDocumentationRequestBuilders.get("/web/list/recode"));
        actual.andDo(MockMvcResultHandlers.print());
        actual.andExpect(MockMvcResultMatchers.status().isOk());

        actual.andDo(MockMvcRestDocumentationWrapper.document("web-test",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                        .description("데이터 조회")
                        .tag("웹 프로그래밍 테스트")
                        .responseFields(
                                fieldWithPath("[]").description("데이터"),
                                fieldWithPath("[].id").description("아이디"),
                                fieldWithPath("[].name").description("이름")
                        )
                        .build()
                )
        ));
    }

    @Test
    public void fail() throws Exception {
        var actual = mockMvc.perform(RestDocumentationRequestBuilders.get("/web/list/500"));
        actual.andDo(MockMvcResultHandlers.print());
        actual.andExpect(MockMvcResultMatchers.status().is5xxServerError());

        actual.andDo(MockMvcRestDocumentationWrapper.document("web-test2",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                                .description("실패 확인")
                                .tag("웹 프로그래밍 테스트")
                                .responseFields(
                                        fieldWithPath("status").description("HTTP STATUS"),
                                        fieldWithPath("error").description("실패 유무"),
                                        fieldWithPath("message").description("오류 메세지")
                                )
                                .build()
                )
        ));
    }

    @Test
    public void notApiCall() throws Exception {
        var actual = mockMvc.perform(RestDocumentationRequestBuilders.get("/web/list/404"));
        actual.andDo(MockMvcResultHandlers.print());
        actual.andExpect(MockMvcResultMatchers.status().is4xxClientError());

        actual.andDo(MockMvcRestDocumentationWrapper.document("web-test3",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder().description("데이터 없음").tag("웹 프로그래밍 테스트").build()
                )

        ));

    }

}