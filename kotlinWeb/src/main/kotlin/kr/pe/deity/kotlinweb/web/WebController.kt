package kr.pe.deity.kotlinweb.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerErrorException


/**\
 * 일괄적으로 ResponseEntity가 덥어 씌워졌으면 좋겠다.
 */
@RestController

class WebController {

    @GetMapping("/get")
    fun get() = "Hello api"

    @GetMapping("/param")
    fun get(userId:String) = println(userId)

    @GetMapping("/get/{userId}")
    fun getPathVariable(@PathVariable userId:String) = println(userId)

    @PostMapping("/post/string")
    fun post(@RequestBody userId:String) = println(userId)

    @PostMapping("/post/dto")
    fun postDto(@RequestBody testRequest:TestRequest) = testRequest


    @GetMapping("/list")
    fun list(): ResponseEntity<DataResponse<List<WebResponse>>> {
        val webResponse = WebResponse("deity", "name")
        val webResponse2 = WebResponse("deity2", "name2")

        val list = listOf<WebResponse>(webResponse, webResponse2)


        return ResponseEntity.ok(DataResponse(data=list))
    }

    @GetMapping("/500")
    fun list500(): Nothing = throw ServerErrorException("서버팀에 문의하세요")


}

data class TestRequest (val id:String, val name:String)

data class WebResponse(val id:String, val name:String)

