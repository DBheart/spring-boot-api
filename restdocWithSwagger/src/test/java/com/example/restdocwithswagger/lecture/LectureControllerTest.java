package com.example.restdocwithswagger.lecture;

import com.epages.restdocs.apispec.FieldDescriptors;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.example.restdocwithswagger.lecture.entity.Lecture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
public class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "USER", password = "1234", roles = "USER")
    public void list() throws Exception {

        var actual = mockMvc.perform(get("/lecture/list"));
        actual.andExpect(status().isOk());

        actual.andDo(print());

        actual.andDo(
            MockMvcRestDocumentationWrapper.document("{method-name}",
                    preprocessRequest(Preprocessors.prettyPrint()),
                    preprocessResponse(Preprocessors.prettyPrint()),
                    resource(
                            ResourceSnippetParameters.builder()
                                    .description("결품/지연 조회")
                                    .tag("결품/지연 등록처리(위수탁)")
                                    .responseFields(ResponseNotDto)
                                    .build()
                    )
            )
        );

    }

    public static final FieldDescriptor[] ResponseNotDto = new FieldDescriptor[]{
            fieldWithPath("status").description("상태"),
            fieldWithPath("code").description("상태코드"),
            fieldWithPath("message").description("메시지"),
            fieldWithPath("data").description("결품/지연 주문 목록").optional(),
//                fieldWithPath("data[].seq").description("강의코드").optional(),
//                fieldWithPath("data[].name").description("강의명").optional(),
    };

    public static final FieldDescriptor[] ResponseDto = new FieldDescriptor[]{
//            fieldWithPath("status").description("상태"),
//            fieldWithPath("code").description("상태코드"),
//            fieldWithPath("message").description("메시지"),
            fieldWithPath("[]").description("강의목록"),
                fieldWithPath("[].seq").description("강의코드").optional(),
                fieldWithPath("[].name").description("강의명").optional(),
    };
    @Test
    @WithMockUser(username = "USER", password = "1234", roles = "ADMIN")
    public void save() throws Exception {
        Lecture lecture = new Lecture();
        lecture.setName("math");
        var actual = mockMvc.perform(post("/lecture/save")
                        .param("name","math")
                );
        actual.andExpect(status().isOk());
        actual.andDo(print());

        actual.andDo(document("lecture-save",
                preprocessRequest(Preprocessors.prettyPrint()),
                preprocessResponse(Preprocessors.prettyPrint()),
                resource(
                        ResourceSnippetParameters.builder()
                                .description("결품/지연 조회")
                                .tag("결품/지연 등록처리(위수탁)")
                                .requestParameters(
                                        ResourceDocumentation.parameterWithName("name").description("수강과목")
                                )
//                                .requestFields(
//                                        fieldWithPath("name").description("과목명")
//                                )
                                .responseFields(ResponseDto)
                                .build()
                )
        ));

    }



    private FieldDescriptors responseBase(JsonFieldType returnDataType, String returnDescription){
        return new FieldDescriptors(
                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                subsectionWithPath("data").type(returnDataType).description(returnDescription).optional()
        );
    }


    private FieldDescriptors resInvoiceAddFieldArray(){
        return new FieldDescriptors(
                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지")
        );
    }

}