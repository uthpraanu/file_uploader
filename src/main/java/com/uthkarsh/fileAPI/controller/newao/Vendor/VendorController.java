package com.uthkarsh.fileAPI.controller.newao.Vendor;

import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.dto.vendor.VendorDTO;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.services.newao.Vendor.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.internalServerError().body("works need to be done");
    }

    @PostMapping(value = "/quotation", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadOrderQuotation(@RequestPart("vendorID")LongDTO vendorID,
                                                  @RequestPart("orderID") LongDTO orderID,
                                                  @RequestPart("type") FileUploaderEnum type,
                                                  @RequestPart("file")MultipartFile file){
        return ResponseEntity.ok(vendorService.uploadOrderQuotation(vendorID, orderID, type, file));
    }
}
