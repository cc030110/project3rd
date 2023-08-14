package com.spring.project3rd.service;

import com.spring.project3rd.util.Uploadcare;
import com.uploadcare.upload.UploadFailureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadFileService {

    private final Uploadcare uploadcare;

    public List<String> uploadImgFile(List<MultipartFile> files){
        List<String> response = new ArrayList<>();
        for(MultipartFile imgs : files){
            try {
                String path = imgs.getOriginalFilename();
                System.out.println("file path : "+path);
                byte[] image = imgs.getBytes();
                File file = new File(path);
                OutputStream os = new FileOutputStream(file);
                os.write(image);
                String imgUrl = uploadcare.getUploadFileUrl(path);
                os.close();
                // 추가된 파일 삭제
                if (file.exists()) {
                    if (file.delete()) {
                        System.out.println("파일 삭제 성공");
                    } else {
                        System.out.println("파일 삭제 실패");
                    }
                }
                response.add(imgUrl);
            } catch (IOException | UploadFailureException e) {
                response.add("fail");
                System.out.println("fail to upload : "+imgs.getOriginalFilename());
                throw new RuntimeException(e);
            }
        }

        return response;
    }


}
