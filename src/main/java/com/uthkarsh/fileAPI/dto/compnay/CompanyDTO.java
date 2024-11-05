package com.uthkarsh.fileAPI.dto.compnay;

import com.uthkarsh.fileAPI.dto.general.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
