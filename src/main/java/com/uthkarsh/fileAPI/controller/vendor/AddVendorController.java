package com.uthkarsh.fileAPI.controller.vendor;

import com.uthkarsh.fileAPI.dto.vendor.VendorDTO;
import com.uthkarsh.fileAPI.services.VendorService.AddVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1")
public class AddVendorController {
    @Autowired
    private AddVendorService addVendorService;

    @PostMapping(value = "/addVendor")
    public ResponseEntity<?> addVendor(@RequestBody VendorDTO vendorDTO){
        return addVendorService.addVendor(vendorDTO);
    }
}
