package com.example.board.mapper;

import com.example.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// BoardMapper.java (애노테이션 대신 XML을 사용하는 방식)
@Mapper
public interface BoardMapper {
    int save(BoardDTO board);
    List<BoardDTO> findAll();
    BoardDTO findById(int id);
    int update(BoardDTO board);
    int delete(int id);
    List<BoardDTO> findByTitleContaining(String title);
    List<BoardDTO> findByAuthorContaining(String author);
    int incrementGood(int id);
}
