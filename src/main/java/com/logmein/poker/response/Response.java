package com.logmein.poker.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response {
    private String message;
    private Object payload;
    private String timestamp = new Timestamp(new Date().getTime()).toString();

    public <T> Response responseObject(String message, T payload) {
        this.message = message;
        this.payload = payload;
        return this;
    }
}
