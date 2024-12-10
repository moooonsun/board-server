package com.example.board.api;

import com.example.board.dto.BoardDTO;
import com.example.board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardService boardService;

    /**
     * 게시글 등록
     */
    @PostMapping("/regist")
    public ResponseEntity<String> regist(@RequestBody BoardDTO boardDTO) {
        // 게시글 등록 로직
        boardService.saveBoard(boardDTO);
        logger.info("게시글 등록: {}", boardDTO);
        return ResponseEntity.ok("게시글이 등록되었습니다.");
    }

    /**
     * 게시글 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<List<BoardDTO>> list(@RequestParam(required = false) String title,
                                               @RequestParam(required = false) String author) {
        List<BoardDTO> boardList = boardService.findAll(title, author);
        logger.info("게시글 목록 조회");
        return ResponseEntity.ok(boardList);
    }


    /**
     * 특정 게시글 조회
     */
    @GetMapping("/find")
    public ResponseEntity<BoardDTO> find(@RequestParam int id) {
        BoardDTO boardDTO = boardService.findById(id);
        return ResponseEntity.ok(boardDTO);
    }

    /**
     * 게시글 수정
     */
    @PostMapping("/modify")
    public ResponseEntity<String> modify(@RequestBody BoardDTO boardDTO) {

        boolean updated = boardService.updateBoard(boardDTO.getId(), boardDTO);
        if (updated) {
            logger.info("게시글 수정: {}", boardDTO);
            return ResponseEntity.ok("게시글이 수정되었습니다.");
        } else {
            logger.warn("게시글 ID {}를 수정할 수 없습니다.", boardDTO.getId());
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * 게시글 삭제
     */
    @PostMapping("/remove")
    public ResponseEntity<String> remove(@RequestBody BoardDTO boardDTO) {
        boolean deleted = boardService.deleteBoard(boardDTO.getId());
        if (deleted) {
            logger.info("게시글 삭제: ID {}", boardDTO.getId());
            return ResponseEntity.ok("게시글이 삭제되었습니다.");
        } else {
            logger.warn("게시글 ID {}를 삭제할 수 없습니다.", boardDTO.getId());
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * 게시글 좋아요 추가
     */
    @PostMapping("/good")
    public ResponseEntity<String> good(@RequestBody BoardDTO boardDTO) {
        logger.info("받은 데이터: {}", boardDTO);
        boolean liked = boardService.addLike(boardDTO.getId());
        if (liked) {
            logger.info("게시글 좋아요 추가: ID {}", boardDTO.getId());
            return ResponseEntity.ok("좋아요가 추가되었습니다.");
        } else {
            logger.warn("게시글 ID {}에 좋아요를 추가할 수 없습니다.", boardDTO.getId());
            return ResponseEntity.notFound().build();
        }
    }
}
