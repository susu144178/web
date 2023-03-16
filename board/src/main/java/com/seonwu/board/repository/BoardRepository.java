package com.seonwu.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seonwu.board.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    public List<BoardEntity> findByOrderByBoardWriteDatetimeDesc();
    public List<BoardEntity> findByWriterEmailOrderByBoardWriteDatetimeDesc(String writerEmail);

    public BoardEntity findByBoardNumber(int boardNumber);
    
}
