package com.uthkarsh.fileAPI.controller.utility;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1")
public class ListAllQuotationController {

    @GetMapping(value = "/getAllQuotation")
    public ResponseEntity<?> getAllQuotation(){

    }
}
