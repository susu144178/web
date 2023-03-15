package com.seonwu.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seonwu.board.common.constant.ApiPattern;
import com.seonwu.board.dto.request.board.PostBoardDto;
import com.seonwu.board.dto.response.ResponseDto;
import com.seonwu.board.dto.response.board.PostBoardResponseDto;
import com.seonwu.board.service.BoardService;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

    @Autowired
    private BoardService boardService;

    private final String POST_BOARD = "";

    @PostMapping(POST_BOARD)
    public ResponseDto<PostBoardResponseDto> postBoard(@AuthenticationPrincipal String email, @Valid @RequestBody PostBoardDto requestBody) {
        ResponseDto<PostBoardResponseDto> response = boardService.postBoard(email, requestBody);

        return response;
    }
    
}
