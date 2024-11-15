package com.uthkarsh.fileAPI.controller.old.utility;

import com.uthkarsh.fileAPI.services.old.utility.AllOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1")
public class ListAllOrdersController {
    @Autowired
    private AllOrderService allOrderService;

    @GetMapping(value = "/getAllOrders")
    public ResponseEntity<?> getAllOrders(){
        return allOrderService.getAllOrders();
    }
}
