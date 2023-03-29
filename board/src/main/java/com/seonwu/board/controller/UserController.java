package com.seonwu.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seonwu.board.common.constant.ApiPattern;
import com.seonwu.board.dto.request.user.PatchProfileDto;
import com.seonwu.board.dto.response.ResponseDto;
import com.seonwu.board.dto.response.user.GetUserResponseDto;
import com.seonwu.board.dto.response.user.PatchProfileResponseDto;
import com.seonwu.board.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "유저 모듈")
@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {
    
    @Autowired
    private UserService userService;

    private final String GET_USER = "/";
    private final String PATCH_PROFILE = "/profile";

    @ApiOperation(value = "유저 프로필 URL 수정", notes = "Request Header Authorization에 Bearer JWT를 포함하고 Request Body에 profile을 포함하여 요청을 하면, 성공시 유저 정보를 반환, 실패시 실패 메세지를 반환")
    @PatchMapping(PATCH_PROFILE)
    public ResponseDto<PatchProfileResponseDto> patchProfile(@ApiParam(hidden = true) @AuthenticationPrincipal String email, @Valid @RequestBody PatchProfileDto requestBody) {
        ResponseDto<PatchProfileResponseDto> response = userService.patchProfile(email, requestBody);

        return response;
    }

    @ApiOperation(value = "유저 정보 불러오기", notes = "Request Header Authorization에 Bearer Token을 포함하여 요청을 하면, 성공시 유저 정보를 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET_USER)
    public ResponseDto<GetUserResponseDto> getUser(@AuthenticationPrincipal String email) {
        ResponseDto<GetUserResponseDto> response = userService.getUser(email);
        
        return response;
    }
}
