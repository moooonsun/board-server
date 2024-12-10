package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
    private int id;                // 게시글 ID
    private String title;          // 제목
    private String content;        // 내용
    private int good;              // 좋아요 수
    private String author;         // 글쓴이
    private String created;        // 작성 날짜
}
