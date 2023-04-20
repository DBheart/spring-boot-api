package kr.deity.restdocwithoutsecurity.lecture;


import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
class LectureControllerTest {
    @Autowired
    private MockMvc mockMvc;



    @Test
    @DisplayName("데이터 조회")
    void testList() throws Exception {
        // given
        var actual = mockMvc.perform(get("/lecture"));

        // when
//        actual.andDo(print());
        actual.andExpect(status().isOk());
        // then

        actual.andDo(MockMvcRestDocumentationWrapper.document("lecture-list",
                ResourceSnippetParameters.builder()
                            .tag("수강신청")
                            .summary("수강신청")
                            .description("수강신청 테스트"),
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("[]").description("수강신청 리스트")
                )
        ));
    }

    @Test
    @DisplayName("데이터 저장")
    void testSaveLecture() throws Exception {
        // given
        var actual = mockMvc.perform((post("/lecture")
                        .param("name","수학")
//                .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(lecture))
//                .accept(MediaType.APPLICATION_JSON)
        ));

        // when
        actual.andExpect(status().isOk());
        // then

        // document
        actual.andDo(MockMvcRestDocumentationWrapper.document("lecture save",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                                .tag("수강신청")
                                .summary("수강신청")
                                .description("수강신청 저장")
                                .requestParameters(
                                        ResourceDocumentation.parameterWithName("name").description("수강과목")
                                )
//                                .requestFields(
//                                        fieldWithPath("name").description("수강신청 명").optional()
//                                )
                                .responseFields(
                                        fieldWithPath("[]").type(JsonFieldType.ARRAY).description("수강신청 과목 리스트").optional(),
                                        fieldWithPath("[].seq").description("수강신청 seq").optional(),
                                        fieldWithPath("[].name").description("수강신청 명").optional()
                                )
                                .build()
                )
        ));
    }

}