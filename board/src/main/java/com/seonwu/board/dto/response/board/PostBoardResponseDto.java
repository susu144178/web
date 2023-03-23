package com.seonwu.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.seonwu.board.entity.BoardEntity;
import com.seonwu.board.entity.CommentEntity;
import com.seonwu.board.entity.LikyEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "게시물 작성 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardResponseDto {

    @ApiModelProperty(value = "게시물 Entity", required = true)
    private BoardEntity board;

    @ApiModelProperty(value = "댓글 Entity 리스트", required = true)
    private List<CommentEntity> commentList;

    @ApiModelProperty(value = "좋아요 Entity 리스트", required = true)
    private List<LikyEntity> likeList;

    public PostBoardResponseDto(BoardEntity board) {
        this.board = board;
        this.commentList = new ArrayList<>();
        this.likeList = new ArrayList<>();
    }
    
}
