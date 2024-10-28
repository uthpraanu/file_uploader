package com.uthkarsh.fileAPI.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SaveFileService {
    @Value("${file.upload.path}")
    private String path;
    public ResponseEntity<?> upload(MultipartFile file){


        if(file.isEmpty()){
            return ResponseEntity.badRequest().body("The file sent is empty");
        }

        Path filePath = Paths.get(path+"\\"+file.getOriginalFilename());
        System.out.println(filePath);

        try(BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile())){

            byte[] buffer = new byte[8192];
            int bytesRead = 0;

            while((bytesRead = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            return ResponseEntity.ok("PDF uploaded successfully");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Some error occured");
        }
    }
}
