package kr.deity.restdocwithoutsecurity;

import com.epages.restdocs.apispec.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get 테스트")
    public void getTest() throws Exception {
        mockMvc.perform(
                        get("/user")
                )
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentationWrapper.document("test-get",
                        ResourceSnippetParameters.builder()
                                .tag("테스트")
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
    public void getPathUri() throws Exception {
        //when
        var actual = mockMvc.perform(get("/user/get/{userId}","path_kim"));

        //then
        actual.andExpect(status().isOk())

        .andDo(MockMvcRestDocumentationWrapper.document("get path",
                ResourceSnippetParameters.builder()
                        .tag("테스트- get path")
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

    @Test
    @DisplayName("get request param")
    void getRequestParam() throws Exception {
        // given
        String message = "dto message";
        mockMvc.perform(
                get("/user/param")
                        .param("userId","kim")

        // when
        ).andExpect(status().isOk())

        //then

        // doc
        .andDo(MockMvcRestDocumentationWrapper.document("get param",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            ResourceDocumentation.resource(
                ResourceSnippetParameters.builder()
                    .tag("테스트- get param")
                    .summary("Get 테스트- param")
                    .description("Get 테스트- param")
                    .requestParameters(
                            ResourceDocumentation.parameterWithName("userId").description("user id")
                    ).build()
            )
        ));
    }

    @Test
    @DisplayName("post로 param 값이 들어오는지 테스트")
    public void postTest() throws Exception {
        String message = "dto message";

        MainResponse mainResponse = new MainResponse("kim");
        var actual = mockMvc.perform(
                post("/user")
                        .param("message","deity")


        ).andExpect(status().isOk());

        actual.andDo(MockMvcRestDocumentationWrapper.document("post param",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                                .tag("테스트- post body")
                                .summary("Post 테스트- body")
                                .description("Post 테스트- body")
                                .requestParameters(
                                        ResourceDocumentation.parameterWithName("message").description("message")
                                ).build()
                )
        ));


        ;
    }

    @Test
    @DisplayName("post로 dto 값이 들어오는지 테스트")
    public void postDtoTest() throws Exception {
        String message = "dto message";
        ObjectMapper mapper = new ObjectMapper();

        var actual = mockMvc.perform(
                post("/user/dto")
                        .param("message","kim")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(mainResponse))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        actual.andDo(MockMvcRestDocumentationWrapper.document("post dto",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                                .tag("테스트- post dto")
                                .summary("Post 테스트- dto")
                                .description("Post 테스트- dto")
                                .requestParameters(
                                        ResourceDocumentation.parameterWithName("message").description("message")
                                ).build()
                )
        ));


        ;
    }

    @Test
    @DisplayName("return list")
    void dtoListTest() throws Exception {
        // given
        var actual = mockMvc.perform(get("/user/list"));


        List<FieldDescriptor> returnResponse = List.of(
                new ConstrainedFields(MainResponse.class).withPath("message").description("message"),
                new ConstrainedFields(MainResponse.class).withPath("url").description("url")
        );
        // when
        actual.andExpect(status().isOk());
        // then
        actual.andDo(MockMvcRestDocumentationWrapper.document("list dto",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                                .tag("테스트- list dto")
                                .summary("list dto 테스트- dto")
                                .description("list 테스트- dto")
                                .responseFields(
                                    fieldWithPath("[].message").type(JsonFieldType.STRING).description("message"),
                                    fieldWithPath("[].url").type(JsonFieldType.STRING).description("url").optional()
                                )
                                .build()
                )


        ));

    }

    private FieldDescriptors responseBase(JsonFieldType returnDataType, String returnDescription){
        return new FieldDescriptors(
                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                fieldWithPath("data").type(returnDataType).description(returnDescription).optional()
                );
    }


}