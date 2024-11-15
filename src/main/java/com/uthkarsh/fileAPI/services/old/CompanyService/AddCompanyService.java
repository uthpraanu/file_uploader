package com.uthkarsh.fileAPI.services.old.CompanyService;

import com.uthkarsh.fileAPI.dto.compnay.CompanyDTO;
import com.uthkarsh.fileAPI.entity.organization.Company;
import com.uthkarsh.fileAPI.exception.RepositoryException;
import com.uthkarsh.fileAPI.exception.ServiceFailedException;
import com.uthkarsh.fileAPI.mapper.company.CompanyMapper;
import com.uthkarsh.fileAPI.repository.organization.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddCompanyService {
    @Autowired
    private CompanyRepository companyRepository;


    public ResponseEntity<?> addCompany(CompanyDTO companyDTO){

        try{
            Company c = CompanyMapper.mapIt(companyDTO);

            companyRepository.save(c);

            return ResponseEntity.ok("Saved successfully to the database");
        }
        catch (DataIntegrityViolationException ex){
            throw new RepositoryException("Duplicate entity");
        }
        catch (Exception e){
            throw new ServiceFailedException("Internal error occured");
        }

    }
}
