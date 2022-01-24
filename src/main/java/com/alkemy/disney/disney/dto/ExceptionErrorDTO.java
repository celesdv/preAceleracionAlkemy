package com.alkemy.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Data
public class ExceptionErrorDTO {

    private HttpStatus httpStatus;
    private String msg;
    private List<String> errors;

}
