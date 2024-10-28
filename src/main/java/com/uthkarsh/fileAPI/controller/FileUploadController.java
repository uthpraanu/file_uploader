package com.uthkarsh.fileAPI.controller;

import com.uthkarsh.fileAPI.dto.FileInfoDto;
import com.uthkarsh.fileAPI.services.SaveFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {
    @Autowired
    private SaveFileService saveFileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("file")MultipartFile multipartFile){
        return saveFileService.upload(multipartFile);
    }
}
