package com.spring.project3rd.domain.boardFreeImg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="board_free_img")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardFreeImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileNo;
    private int boardNo;
    private String img;

    public BoardFreeImg(int boardNo,String url){
        this.boardNo=boardNo;
        this.img=url;
    }
}
