package com.example.restdocwithswagger.lecture;

import com.epages.restdocs.apispec.FieldDescriptors;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.example.restdocwithswagger.lecture.entity.Lecture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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


        actual.andDo(document("lecture-list",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                                .tag("Lecture")
                                .summary("Lecture test")
                                .description("Lecture test")
                                .responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
//                                        fieldWithPath("data").type(JsonFieldType.).description("강의목록").optional()

                                        subsectionWithPath("data").type(JsonFieldType.ARRAY).description("강의목록"),
                                        fieldWithPath("data.[].seq").type(JsonFieldType.NUMBER).description("강의코드").optional(),
                                        fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("강의명")
                                )
                                .build()
                )
        ));

    }

    @Test
    @WithMockUser(username = "USER", password = "1234", roles = "ADMIN")
    public void save() throws Exception {
        Lecture lecture = new Lecture();
        lecture.setName("math");
        mockMvc.perform(post("/lecture/save").requestAttr("lecture",lecture)).andExpect(status().isOk());

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