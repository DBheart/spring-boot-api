package kr.pe.deity.kotilwebsecurity.web

abstract class BaseResponse(
    open val status:Int?,
    open val error:String?,
    open val message:String?,
)

data class DataResponse<T>(
    val data: T?
) : BaseResponse(status = 200, error="SUCCESS", message = "")

data class ErrorResponse(
    override val message: String?,
): BaseResponse(status=500, error="FAIL", message = message)