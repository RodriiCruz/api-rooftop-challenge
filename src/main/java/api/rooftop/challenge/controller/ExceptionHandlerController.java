package api.rooftop.challenge.controller;

import api.rooftop.challenge.dto.ErrorDTO;
import api.rooftop.challenge.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> notFoundException(RuntimeException ex, WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .error(true)
                .message("Text not found")
                .code(404)
                .build();
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> generalException(RuntimeException ex, WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .error(true)
                .message("An error occurred when processing the text")
                .code(422)
                .build();
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

}
