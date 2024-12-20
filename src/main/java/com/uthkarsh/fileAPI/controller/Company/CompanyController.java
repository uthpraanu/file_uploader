package com.uthkarsh.fileAPI.controller.Company;

import com.uthkarsh.fileAPI.dto.compnay.CompanyDTO;
import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.services.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/v2/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCompany(@RequestBody CompanyDTO companyDTO){
        return ResponseEntity.ok(companyService.addNewCompany(companyDTO));
    }

    @PostMapping(value = "/order", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPurchaseOrder(@RequestPart("id")LongDTO companyID,
                                              @RequestPart("type") FileUploaderEnum type,
                                              @RequestPart("file")MultipartFile purchaseOrder){

        return ResponseEntity.ok(companyService.uploadPurchaseOrder(companyID, type, purchaseOrder));
    }

    @GetMapping(value = "/order/{storageType}/{fileName}")
    public ResponseEntity<?> downloadPurchaseOrder(@PathVariable String fileName,
                                                   @PathVariable FileUploaderEnum storageType){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+fileName+"\"")
                .body(
                        companyService.downloadPurchaseOrder(fileName, storageType)
                );
    }
}
