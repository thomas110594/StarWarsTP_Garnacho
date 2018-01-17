package com.example.thomasgarnacho.tpfinal_garnacho.data.models;

/**
 * Created by thomasgarnacho on 11/01/2018.
 */

public class HttpError {
    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }
}
