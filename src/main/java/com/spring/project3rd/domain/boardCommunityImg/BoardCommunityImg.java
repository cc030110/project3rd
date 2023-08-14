package com.spring.project3rd.domain.boardCommunityImg;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="board_community_img")
@Getter
@NoArgsConstructor
public class BoardCommunityImg {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int fileNo;
        private int boardNo;
        private String img;

        public BoardCommunityImg(int boardNo, String url){
            this.boardNo=boardNo;
            this.img=url;
        }
}

