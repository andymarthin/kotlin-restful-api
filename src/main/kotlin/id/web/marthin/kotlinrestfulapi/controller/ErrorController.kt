package id.web.marthin.kotlinrestfulapi.controller

import id.web.marthin.kotlinrestfulapi.error.NotFoundException
import id.web.marthin.kotlinrestfulapi.error.UnauthorizedException
import id.web.marthin.kotlinrestfulapi.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String>{
        return WebResponse(
                code = 400,
                status = "Bad Request",
                data = constraintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(notFoundException: NotFoundException): WebResponse<String>{
        return WebResponse(
                code = 404,
                status = "NOT FOUND",
                data = "Not Found"
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unautherized(unauthorizedException: UnauthorizedException): WebResponse<String>{
        return WebResponse(
                code = 401,
                status = "UNAUTHERIZED",
                data = "Please put you X-Api-Key"
        )
    }
}