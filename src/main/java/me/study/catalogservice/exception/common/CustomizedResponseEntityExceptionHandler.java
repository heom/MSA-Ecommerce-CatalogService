package me.study.catalogservice.exception.common;

import me.study.catalogservice.exception.CatalogNotFoundException;
import me.study.catalogservice.exception.StockException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CatalogNotFoundException.class)
    public final ResponseEntity<Object> handleCatalogNotFoundException(CatalogNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse = getDefaultExceptionResponse(ex, request, HttpStatus.NOT_FOUND, "Catalog Not Found");
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockException.class)
    public final ResponseEntity<Object> handleStockException(StockException ex, WebRequest request){
        ExceptionResponse exceptionResponse = getDefaultExceptionResponse(ex, request, HttpStatus.BAD_REQUEST, "Stock Error");
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = getDefaultExceptionResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
                                                                , HttpHeaders headers
                                                                , HttpStatus status
                                                                , WebRequest request) {
        ExceptionResponse exceptionResponse
                = new ExceptionResponse(new Date()
                , HttpStatus.BAD_REQUEST.value()
                , "Validation Failed"
                , ex.getBindingResult().toString()
                , ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse getDefaultExceptionResponse(Exception ex, WebRequest request
                                                        , HttpStatus httpStatus , String error) {
        return new ExceptionResponse(new Date()
                                    , httpStatus.value()
                                    , error
                                    , ex.getMessage()
                                    , ((ServletWebRequest)request).getRequest().getRequestURI());
    }
}
