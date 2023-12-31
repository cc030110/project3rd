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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadFileService {

    private final Uploadcare uploadcare;

    // 파일 업로드 여러장
    public List<String> uploadImgFiles(List<MultipartFile> files){
        List<String> response = new ArrayList<>();
        for(MultipartFile imgs : files){
            try {
                String fileName = imgs.getOriginalFilename();
                System.out.println("인코딩 전 : "+fileName);
                // 파일명을 UTF-8로 인코딩
                String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
                System.out.println("인코딩 후 : "+encodedFileName);
                byte[] image = imgs.getBytes();
                File file = new File(encodedFileName);
                OutputStream os = new FileOutputStream(file);
                os.write(image);
                String imgUrl = uploadcare.getUploadFileUrl(encodedFileName);
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

    // 파일 업로드 1장
    public String uploadImgFile(MultipartFile img){
        String response="";
        try {
            String fileName = img.getOriginalFilename();
            System.out.println("인코딩 전 : "+fileName);
            // 파일명을 UTF-8로 인코딩
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
            System.out.println("인코딩 후 : "+encodedFileName);
            byte[] image = img.getBytes();
            File file = new File(encodedFileName);
            OutputStream os = new FileOutputStream(file);
            os.write(image);
            String imgUrl = uploadcare.getUploadFileUrl(encodedFileName);
            os.close();
            // 추가된 파일 삭제
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("파일 삭제 성공");
                } else {
                    System.out.println("파일 삭제 실패");
                }
            }
            response=imgUrl;
        } catch (IOException | UploadFailureException e) {
            response="fail";
            System.out.println("fail to upload : "+img.getOriginalFilename());
            throw new RuntimeException(e);
        }

        return response;
    }


}
