package com.uthkarsh.fileAPI.controller;

import com.uthkarsh.fileAPI.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {
    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("file")MultipartFile multipartFile){
        return fileService.upload(multipartFile);
    }

    @GetMapping(value = "/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName){
        System.out.println("reached");
        return fileService.download(fileName);
    }
}
