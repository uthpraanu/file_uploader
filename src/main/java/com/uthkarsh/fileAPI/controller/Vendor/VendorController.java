package com.uthkarsh.fileAPI.controller.Vendor;

import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.dto.vendor.VendorDTO;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.services.Vendor.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v2/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addVendor(@RequestBody VendorDTO vendorDTO){
        return ResponseEntity.ok(vendorService.addNewVendor(vendorDTO));
    }

    @PostMapping(value = "/quotation", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadOrderQuotation(@RequestPart("vendorID")LongDTO vendorID,
                                                  @RequestPart("orderID") LongDTO orderID,
                                                  @RequestPart("type") FileUploaderEnum type,
                                                  @RequestPart("file")MultipartFile file){
        return ResponseEntity.ok(vendorService.uploadOrderQuotation(vendorID, orderID, type, file));
    }

    @GetMapping(value = "/quotation/{storageType}/{fileName}")
    public ResponseEntity<?> downloadOrderQuotation(@PathVariable String fileName,
                                                   @PathVariable FileUploaderEnum storageType){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+fileName+"\"")
                .body(
                        vendorService.downloadOrderQuotation(fileName, storageType)
                );
    }
}
