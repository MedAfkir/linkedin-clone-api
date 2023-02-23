package com.linkedinclone.api.handlers;

import com.linkedinclone.api.exceptions.alreadyused.AlreadyUsedException;
import com.linkedinclone.api.exceptions.alreadyused.EmailAlreadyUsedException;
import com.linkedinclone.api.exceptions.alreadyused.UsernameAlreadyUsedException;
import com.linkedinclone.api.exceptions.invalidbodyrequest.InvalidBodyRequestException;
import com.linkedinclone.api.exceptions.notfound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(value = InvalidBodyRequestException.class)
    public ResponseEntity<?> invalidBodyRequest(InvalidBodyRequestException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

    @ExceptionHandler(value = AlreadyUsedException.class)
    public ResponseEntity<?> invalidBodyRequest(UsernameAlreadyUsedException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }
}
