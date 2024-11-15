package com.uthkarsh.fileAPI.services.newao.Vendor;

import com.uthkarsh.fileAPI.services.newao.factory.DocumentService.DocumentServiceFactory.DocumentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    @Autowired
    private DocumentFactory documentFactory;

    public String uploadQuotationOrder(){
        return "working";
    }
}
