package com.congsole.SimpleSNS.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User Name is duplicated"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Name Was Not Found"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Password is Invalid")
    ;


    private HttpStatus status;
    private String message;

}
