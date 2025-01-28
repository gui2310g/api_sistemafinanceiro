package com.example.api_sistemafinanceiro.gui.handler;

import com.example.api_sistemafinanceiro.gui.common.ConversorData;
import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceBadRequestException;
import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceNotFoundException;
import com.example.api_sistemafinanceiro.gui.domain.model.ErrorResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorResposta> handleResourceBadRequestException(ResourceBadRequestException ex) {
        String dataHora = ConversorData.convertDatetoDateHour(new Date());

        ErrorResposta message = new ErrorResposta(
                dataHora,
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage()
        );

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResposta> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String dataHora = ConversorData.convertDatetoDateHour(new Date());

        ErrorResposta message = new ErrorResposta(
                dataHora,
                HttpStatus.NOT_FOUND.value(),
                "Not found",
                ex.getMessage()
        );

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResposta> handleRequestException(Exception ex) {
        String dataHora = ConversorData.convertDatetoDateHour(new Date());

        ErrorResposta message = new ErrorResposta(
                dataHora,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage()
        );

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
