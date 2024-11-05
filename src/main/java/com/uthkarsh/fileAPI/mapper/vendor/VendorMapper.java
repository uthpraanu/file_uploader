package com.uthkarsh.fileAPI.mapper.vendor;

import com.uthkarsh.fileAPI.dto.vendor.VendorDTO;
import com.uthkarsh.fileAPI.entity.general.Address;
import com.uthkarsh.fileAPI.entity.organization.Vendor;

public class VendorMapper {
    public static Vendor mapIt(VendorDTO vendorDTO){
        Address address = new Address();

        address.setFlatName(vendorDTO.getAddress().getFlatName());
        address.setStreetName(vendorDTO.getAddress().getStreetName());
        address.setCity(vendorDTO.getAddress().getCity());
        address.setState(vendorDTO.getAddress().getState());
        address.setCountry(vendorDTO.getAddress().getCountry());
        address.setPinCode(vendorDTO.getAddress().getPinCode());

        Vendor vendor = new Vendor();

        vendor.setName(vendorDTO.getName());
        vendor.setEmail(vendorDTO.getEmail());
        vendor.setPhoneCode(vendorDTO.getPhoneCode());
        vendor.setPhoneNumber(vendorDTO.getPhoneNumber());
        vendor.setActive(vendorDTO.getActive());
        vendor.setAddress(address);

        return vendor;
    }
}
