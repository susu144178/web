package com.seonwu.board.service;

import com.seonwu.board.dto.request.user.PatchProfileDto;
import com.seonwu.board.dto.response.ResponseDto;
import com.seonwu.board.dto.response.user.PatchProfileResponseDto;

public interface UserService {

    public ResponseDto<PatchProfileResponseDto> patchProfile(String email, PatchProfileDto dto);
    
}
