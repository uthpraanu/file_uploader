package com.uthkarsh.fileAPI.controller.old.awsTesting;

import com.uthkarsh.fileAPI.services.old.aws.AwsMyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "api/v1/aws")
public class fileController {
    @Autowired
    private AwsMyFileService awsMyFileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        System.out.println("entered");
        return awsMyFileService.upload(multipartFile);
    }

    @GetMapping(value = "/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName){
        System.out.println("reached");
        return awsMyFileService.download(fileName);
    }
}
