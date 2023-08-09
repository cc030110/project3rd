package com.spring.project3rd.domain.boardFree;

import com.spring.project3rd.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="board_free")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardFree extends Timestamp {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 컬럼인 것을 명시
    @Column(name="board_no")
    private int boardNo;
    @Column(nullable = false)
    private String id;
    @Column(length = 100,nullable = false)
    private String title;
    @Column(length = 4000)
    private String contents;
    @Column(name="is_modified")
    private short isModified;

}
