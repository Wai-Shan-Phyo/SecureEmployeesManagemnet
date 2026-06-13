package com.sem.project.common.exception;


import com.sem.project.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex){
        ApiResponse<Void>  response =  ApiResponse.<Void>builder()
                .success(false)
                .message(ex.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>>
    handleException(
            Exception ex) {

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(false)
                        .message("Internal Server Error")
                        .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }



}
