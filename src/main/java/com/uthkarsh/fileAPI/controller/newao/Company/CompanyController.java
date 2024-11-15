package com.uthkarsh.fileAPI.controller.newao.Company;

import com.uthkarsh.fileAPI.dto.compnay.CompanyDTO;
import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.enums.OrganizationEnum;
import com.uthkarsh.fileAPI.services.newao.Company.CompanyService;
import com.uthkarsh.fileAPI.services.old.CompanyService.AddCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.internalServerError().body("still need to make changes");
    }

    @PostMapping(value = "/order", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addPurchaseOrder(@RequestPart("id")LongDTO id,
                                              @RequestPart("type") FileUploaderEnum type,
                                              @RequestPart("file")MultipartFile purchaseOrder){

        return ResponseEntity.ok(companyService.uploadPurchaseOrder(id, type, purchaseOrder));
    }
}
