package com.uthkarsh.fileAPI.mapper.company;

import com.uthkarsh.fileAPI.dto.CompanyDTO;
import com.uthkarsh.fileAPI.entity.general.Address;
import com.uthkarsh.fileAPI.entity.organization.Company;

public class CompanyMapper {
    public static Company mapIt(CompanyDTO companyDTO){
        Address address = new Address();

        address.setFlatName(companyDTO.getAddress().getFlatName());
        address.setStreetName(companyDTO.getAddress().getStreetName());
        address.setCity(companyDTO.getAddress().getCity());
        address.setState(companyDTO.getAddress().getState());
        address.setCountry(companyDTO.getAddress().getCountry());
        address.setPinCode(companyDTO.getAddress().getPinCode());

        Company company = new Company();

        company.setCompanyName(companyDTO.getCompanyName());
        company.setCompanyEmail(companyDTO.getCompanyEmail());
        company.setCompanyPhoneCode(companyDTO.getCompanyPhoneCode());
        company.setCompanyPhoneNumber(companyDTO.getCompanyPhoneCode());
        company.setActive(companyDTO.getActive());
        company.setAddress(address);

        return company;
    }
}
