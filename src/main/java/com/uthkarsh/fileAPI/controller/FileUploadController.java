package com.uthkarsh.fileAPI.controller;

import com.uthkarsh.fileAPI.dto.FileInfoDto;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {

    @PostMapping(value = "/upload", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("id") Long id, @RequestPart("info") FileInfoDto details,
                                             @RequestPart("file")MultipartFile multipartFile){
        return ResponseEntity.ok("Testing ....");
    }
}
