package com.uthkarsh.fileAPI.dto;

import com.uthkarsh.fileAPI.entity.general.Address;
import com.uthkarsh.fileAPI.entity.orders.PurchaseOrder;
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
public class CompanyDTO {

    private String companyName;

    private String companyEmail;

    private int companyPhoneCode;

    private long companyPhoneNumber;

    private Boolean active;

    // Foreign keys or RelationShips

    private AddressDTO address;

}
