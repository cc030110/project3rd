package com.spring.project3rd.domain.boardImg;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="board_free_img")
@Getter
@NoArgsConstructor
public class BoardFreeImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileNo;
    private int boardNo;
    private String img;

}
