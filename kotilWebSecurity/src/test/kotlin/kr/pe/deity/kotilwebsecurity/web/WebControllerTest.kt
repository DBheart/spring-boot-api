package kr.pe.deity.kotilwebsecurity.web

import com.epages.restdocs.apispec.FieldDescriptors
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper
import com.epages.restdocs.apispec.ResourceDocumentation
import com.epages.restdocs.apispec.ResourceSnippetParameters
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(WebController::class)
@AutoConfigureRestDocs
class WebControllerTest{

    @Autowired
    lateinit var mockMvc : MockMvc

    @Test
    @DisplayName("리스트성공")
    @WithMockUser(username="User", password = "admin")
    fun successList(){
        val actual = mockMvc.perform(RestDocumentationRequestBuilders.get("/web/list"))
        actual.andExpect(MockMvcResultMatchers.status().isOk)

        actual.andDo(MockMvcRestDocumentationWrapper.document(
            "web-list-success",
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
            ResourceDocumentation.resource(
                ResourceSnippetParameters.builder().tag("웹테스트").description("웹 성공테스트")
                    .responseFields(responseBase(JsonFieldType.ARRAY,"웹데이터")).build())
            )
        )

    }

    @Test
    @DisplayName("500 오류")
    @WithMockUser(username = "User", password = "admin")
    fun serverError(){
        val actual = mockMvc.perform(RestDocumentationRequestBuilders.get("/web/500"))
        actual.andExpect(MockMvcResultMatchers.status().is5xxServerError)

        actual.andDo(MockMvcRestDocumentationWrapper.document("web-error",
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
            ResourceDocumentation.resource(
                ResourceSnippetParameters.builder().tag("웹테스트").description("웹 서버오류")
                    .responseFields(
                        fieldWithPath("error").description("오류코드").optional(),
                        fieldWithPath("status").description("status"),
                        fieldWithPath("message").description("오류 메세지")
                    ).build())

            )
        )
    }

    @Test
    @DisplayName("400 오류")
    @WithMockUser(username = "User", password = "admin")
    fun notFoundPage(){
        val actual = mockMvc.perform(RestDocumentationRequestBuilders.get("/web/400"))
        actual.andExpect(MockMvcResultMatchers.status().isNotFound)

        actual.andDo(MockMvcRestDocumentationWrapper.document("web-notfound",
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
            ResourceDocumentation.resource(
                ResourceSnippetParameters.builder().tag("웹테스트").description("없는 API").build()
            )


            ))

    }




    private fun responseBase(returnDataType: JsonFieldType, returnDescription:String):FieldDescriptors{
        return FieldDescriptors(
            fieldWithPath("status").description("http method"),
            fieldWithPath("error").description("http method").optional(),
            fieldWithPath("message").description("http method").optional(),
//            fieldWithPath("data").type(returnDataType).description(returnDescription),
            fieldWithPath("data[]").type(returnDataType).description(returnDescription),
            fieldWithPath("data[].id").description("아이디"),
            fieldWithPath("data[].name").description("이름")

        );
    }

}

