package com.digital.crud.saladereuniao.saladereuniao.exceptions;



import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;


    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }



}
