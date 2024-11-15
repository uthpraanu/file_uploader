package com.uthkarsh.fileAPI.services.old.VendorService;

import com.uthkarsh.fileAPI.dto.vendor.VendorDTO;
import com.uthkarsh.fileAPI.entity.organization.Vendor;
import com.uthkarsh.fileAPI.exception.RepositoryException;
import com.uthkarsh.fileAPI.exception.ServiceFailedException;
import com.uthkarsh.fileAPI.mapper.vendor.VendorMapper;
import com.uthkarsh.fileAPI.repository.organization.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
public class AddVendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public ResponseEntity<?> addVendor(VendorDTO vendorDTO){
        try{
            Vendor vendor = VendorMapper.mapIt(vendorDTO);

            vendorRepository.save(vendor);

            return ResponseEntity.ok("Vendor saved successfully");
        }
        catch (DataIntegrityViolationException ex){
            throw new RepositoryException("Duplicate entity");
        }
        catch (Exception e){
            throw new ServiceFailedException("Internal error occured");
        }
    }
}
