package com.uthkarsh.fileAPI.services.old.utility;

import com.uthkarsh.fileAPI.entity.orders.PurchaseOrder;
import com.uthkarsh.fileAPI.repository.orders.PurchaseOrderRepository;
import com.uthkarsh.fileAPI.repository.organization.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AllOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public ResponseEntity<?> getAllOrders(){
        List<PurchaseOrder> list = purchaseOrderRepository.findAll();

//        List<String> urlList = new ArrayList<>();
//
//        for(PurchaseOrder p : list){
//            urlList.add(p.getOrderName());
//        }

        HashMap<String, ArrayList<String>> urlList = new HashMap<>();

        for(PurchaseOrder p : list){
            String companyName = p.getCompany().getCompanyName();

            if(urlList.containsKey(companyName)){
                urlList.get(companyName).add(p.getOrderName());
            }
            else{
                ArrayList<String> a = new ArrayList<>();

                a.add((p.getOrderName()));

                urlList.put(companyName,a);
            }
        }

        return ResponseEntity.ok(urlList);
    }
}
