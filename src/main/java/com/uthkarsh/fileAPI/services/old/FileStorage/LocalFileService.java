package com.uthkarsh.fileAPI.services.old.FileStorage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LocalFileService {

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

    public ResponseEntity<?> download(String name){
        try{

            if (name.contains("..") || name.contains("/") || name.contains("\\")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Reject invalid paths
            }

            Path filePath = Paths.get(path).resolve(name).normalize();

            Resource resource = new UrlResource(filePath.toUri());
            System.out.println("resource = "+resource.toString());

            if(resource.exists() && resource.isReadable()){
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
                        .body(resource);

            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        catch (IOException e){
            return ResponseEntity.internalServerError().body("Something wrong in the code");
        }
    }
}
