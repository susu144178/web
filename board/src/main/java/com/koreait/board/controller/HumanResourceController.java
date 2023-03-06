package com.koreait.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.board.dto.request.humanResource.PostHumanResourceRequestDto;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.response.humanResource.PostHumanResourceResponseDto;
import com.koreait.board.service.HumanResourceService;

@RestController
@RequestMapping("/apis/hr")
public class HumanResourceController {
    
    @Autowired
    private HumanResourceService humanResourceService;

    @PostMapping("/")
    public ResponseDto<PostHumanResourceResponseDto> postHumanResource(@Valid @RequestBody PostHumanResourceRequestDto requestBody) {
        ResponseDto<PostHumanResourceResponseDto> response = humanResourceService.postHumanResource(requestBody);

        return response;
    }
}
