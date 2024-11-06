package com.uthkarsh.fileAPI.services.utility;

import com.uthkarsh.fileAPI.entity.orders.OrderQuotation;
import com.uthkarsh.fileAPI.repository.orders.OrderQuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllQuotationService {
    @Autowired
    private OrderQuotationRepository orderQuotationRepository;

    public ResponseEntity<?> getAllQuotation(){
        List<OrderQuotation> orders = orderQuotationRepository.findAll();

        List<String> urlList = new ArrayList<>();

        for(OrderQuotation o : orders){
            urlList.add(o.getName());
        }

        return ResponseEntity.ok(urlList);
    }
}
