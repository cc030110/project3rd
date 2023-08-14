package com.spring.project3rd.domain.boardCommunityImg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommunityImgRequestDto {
    private int fileNo;
    private int boardNo;
    private MultipartFile img;
}
