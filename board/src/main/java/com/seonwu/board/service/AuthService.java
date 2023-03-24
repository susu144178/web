package com.seonwu.board.service;

import com.seonwu.board.dto.request.auth.SignInDto;
import com.seonwu.board.dto.request.auth.SignUpDto;
import com.seonwu.board.dto.response.ResponseDto;
import com.seonwu.board.dto.response.auth.SignInResponseDto;
import com.seonwu.board.dto.response.auth.SignUpResponseDto;

public interface AuthService {

    public ResponseDto<SignUpResponseDto> signUp(SignUpDto dto);

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto);
    
}
