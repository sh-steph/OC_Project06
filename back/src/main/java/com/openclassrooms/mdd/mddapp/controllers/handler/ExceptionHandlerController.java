package com.openclassrooms.mdd.mddapp.controllers.handler;

import com.openclassrooms.mdd.mddapp.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ErrorDto> handleUsernameNotFoundException(
            UsernameNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(HttpStatus.BAD_REQUEST,
                        "Error: Email is already taken!"));
    }
}
