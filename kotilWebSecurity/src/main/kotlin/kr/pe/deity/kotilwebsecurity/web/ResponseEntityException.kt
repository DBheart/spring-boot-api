package kr.pe.deity.kotilwebsecurity.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ServerErrorException

@ControllerAdvice
class ResponseEntityException {

    @ExceptionHandler(value =[ServerErrorException::class])
    fun serverException(e:ServerErrorException): ResponseEntity<BaseResponse>{

        val errorResponse = ErrorResponse(e.message);

        return ResponseEntity.internalServerError().body<BaseResponse>(errorResponse)
    }
}