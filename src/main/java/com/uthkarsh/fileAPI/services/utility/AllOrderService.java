package com.uthkarsh.fileAPI.services.utility;

import com.uthkarsh.fileAPI.entity.orders.PurchaseOrder;
import com.uthkarsh.fileAPI.repository.orders.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public ResponseEntity<?> getAllOrders(){
        List<PurchaseOrder> list = purchaseOrderRepository.findAll();

        List<String> urlList = new ArrayList<>();

        for(PurchaseOrder p : list){
            urlList.add(p.getOrderName());
        }

        return ResponseEntity.ok(urlList);
    }
}
