package com.koreait.board.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.UNAUTHORIZED,
    reason = "Need Authorization"
)
public class UnautorizationException extends RuntimeException {
    
    public UnautorizationException() {
        super("Authentication is Required");
    }
}
