package com.spring.project3rd.domain.boardCommunity;


import com.spring.project3rd.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="board_community")
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class BoardCommunity extends Timestamp {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int boardNo;
    @Column(nullable=false)
    private String id;
    @Column(length=100,nullable=false)
    private String title;
    @Column(length=4000)
    private String contents;
    @Column(nullable = false)
    int participants_num;
    @Column(nullable=false)
    private int deadline;

}
