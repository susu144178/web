package com.seonwu.board.dto.response.board;

import java.util.List;

import com.seonwu.board.entity.BoardEntity;
import com.seonwu.board.entity.CommentEntity;
import com.seonwu.board.entity.LikyEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchBoardResponseDto {

    private BoardEntity board;

    private List<CommentEntity> commentList;

    private List<LikyEntity> likeList;
    
}
