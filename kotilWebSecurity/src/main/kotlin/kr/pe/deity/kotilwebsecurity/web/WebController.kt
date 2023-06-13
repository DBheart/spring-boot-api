package kr.pe.deity.kotilwebsecurity.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerErrorException

@RestController
@RequestMapping("/web")
class WebController {

    //Request 확인

    @GetMapping("/get")
    fun get(): String = "Hello api"

    @GetMapping("/param")
    fun get(userId: String) = userId

    @GetMapping("/get/{userId}")
    fun getPathVariable(@PathVariable userId: String) = userId

    @PostMapping("/post/string")
    fun post(@RequestBody(required = false) userId: String) = userId

    @PostMapping("/post/dto")
    fun postDto(@RequestBody testRequest: TestRequest) = testRequest

    //Response 확인
    @GetMapping("/list")
    fun list(): ResponseEntity<DataResponse<List<WebResponse>>>{
        val webResponse = WebResponse("deity", "name")
        val webResponse2 = WebResponse("deity2", "name2")

        val list = listOf<WebResponse>(webResponse, webResponse2)

        return ResponseEntity.ok(DataResponse(data = list))

    }

    @GetMapping("/500")
    fun list500():Nothing = throw ServerErrorException("서버팀에 문의하세요")


}

data class WebResponse(val id:String, val name:String)

data class TestRequest(val id:String, val name:String)
