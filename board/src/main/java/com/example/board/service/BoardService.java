package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;  // BoardMapper를 주입받습니다.

    // 게시글 저장
    public void saveBoard(BoardDTO board) {
        boardMapper.save(board);  // MyBatis의 save 메서드 호출
    }

    // 모든 게시글 조회
    public List<BoardDTO> findAll(String title, String author) {
        if (title != null && !title.isEmpty()) {
            return boardMapper.findByTitleContaining(title);  // 제목으로 검색
        } else if (author != null && !author.isEmpty()) {
            return boardMapper.findByAuthorContaining(author);  // 작성자로 검색
        }
        return boardMapper.findAll();  // 기본적으로 모든 게시글 반환
    }

    // 특정 게시글 조회
    public BoardDTO findById(int id) {
        return boardMapper.findById(id);  // MyBatis의 findById 메서드 호출
    }

    // 게시글 수정
    public boolean updateBoard(int id, BoardDTO board) {
        int result = boardMapper.update(board);  // MyBatis의 update 메서드 호출
        return result > 0;
    }

    // 게시글 삭제
    public boolean deleteBoard(int id) {
        int result = boardMapper.delete(id);  // MyBatis의 delete 메서드 호출
        return result > 0;
    }

    // 좋아요 수 증가
    public boolean addLike(int id) {
        int result = boardMapper.incrementGood(id);  // MyBatis의 incrementGood 메서드 호출
        return result > 0;
    }
}
