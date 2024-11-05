package com.uthkarsh.fileAPI.dto.vendor;

import com.uthkarsh.fileAPI.dto.general.AddressDTO;
import com.uthkarsh.fileAPI.entity.general.Address;
import com.uthkarsh.fileAPI.entity.orders.OrderQuotation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorDTO {

    private String name;

    private String email;

    private int phoneCode;

    private Long phoneNumber;

    private Boolean active;

    // Foreign key and relations

    private AddressDTO address;

}
