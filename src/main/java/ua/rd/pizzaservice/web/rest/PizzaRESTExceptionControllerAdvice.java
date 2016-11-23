package ua.rd.pizzaservice.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Serhii_Mykhliuk
 */
@RestControllerAdvice
public class PizzaRESTExceptionControllerAdvice {

    // возвращаемый объект будет конвертиться в JSON
    // не нужно будет писать @ResponseBody
    // c rest должна быть более жесткая валидация
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErrorInfo
            handleBadRequest(HttpServletRequest req, Exception ex){
        return new ErrorInfo(req.getRequestURI(), ex, "Smth wrong");
    }
}
