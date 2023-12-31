package com.spring.project3rd.domain.boardFreeImg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardFreeImgRequestDto {
    private int fileNo;
    private int boardNo;
    private MultipartFile img;
}
