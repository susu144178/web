package com.seonwu.board.service;

import com.seonwu.board.dto.request.user.PatchProfileDto;
import com.seonwu.board.dto.request.user.ValidateEmailDto;
import com.seonwu.board.dto.request.user.ValidateNicknameDto;
import com.seonwu.board.dto.request.user.ValidateTelNumberDto;
import com.seonwu.board.dto.response.ResponseDto;
import com.seonwu.board.dto.response.user.GetUserResponseDto;
import com.seonwu.board.dto.response.user.PatchProfileResponseDto;
import com.seonwu.board.dto.response.user.ValidateEmailResponseDto;
import com.seonwu.board.dto.response.user.ValidateNicknameResponseDto;
import com.seonwu.board.dto.response.user.ValidateTelNumberResponseDto;

public interface UserService {

    public ResponseDto<PatchProfileResponseDto> patchProfile(String email, PatchProfileDto dto);

    public ResponseDto<GetUserResponseDto> getUser(String email);

    public ResponseDto<ValidateEmailResponseDto> validateEmail(ValidateEmailDto dto);

    public ResponseDto<ValidateNicknameResponseDto> validateNickname(ValidateNicknameDto dto);

    public ResponseDto<ValidateTelNumberResponseDto> validateTelNumber(ValidateTelNumberDto dto);
    
}
