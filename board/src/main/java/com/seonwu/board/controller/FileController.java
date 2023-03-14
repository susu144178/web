package com.seonwu.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seonwu.board.common.constant.ApiPattern;
import com.seonwu.board.service.FileService;

@RestController
@RequestMapping(ApiPattern.FILE)
public class FileController {
    
    @Autowired
    private FileService fileService;
}
