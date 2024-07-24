package com.jobhunter.appjobfestservice.exceptions;

import com.jobhunter.appjobfestservice.shit.payload.ErrorData;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<Response<ErrorData>> handleException(RestException ex) {
        ex.printStackTrace();
        //AKS HOLDA DOIMIY EXCEPTIONLAR ISHLAYVERADI
        if (ex.getFieldName() == null && ex.getErrors() != null) {
            return new ResponseEntity<>(Response.errorResponse(ex.getErrors()), ex.getStatus());
        }
        //AGAR RESOURSE TOPILMAGANLIGI XATOSI BO'LSA CLIENTGA QAYSI TABLEDA NIMA TOPILMAGANLIGI HAQIDA XABAR QAYTADI
        return new ResponseEntity<>(Response.errorResponse(ex.getUserMsg(), ex.getStatus().value()), ex.getStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(
                Response.errorResponse("SOMETHING_WENT_WRONG", 500),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
