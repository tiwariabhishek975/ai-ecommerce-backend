package com.example.aiecommerce.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<?> notFound(ResourceNotFoundException e){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error",e.getMessage()));}
    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<?> badRequest(BadRequestException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> validation(MethodArgumentNotValidException e){
        String msg=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(Map.of("error",msg));
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<?> general(Exception e){return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error","Internal server error")); }
}
