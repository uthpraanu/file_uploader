package com.uthkarsh.fileAPI.services.newao.factory.utility.organizationParameters;

import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.services.newao.factory.utility.organizationParameters.OrganizationMethoParameter.OrganizationUploadMethodParameter;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorUploadMethodParameter implements OrganizationUploadMethodParameter{
    private long vendorID;
    private long purchaseOrderID;
    private FileUploaderEnum type;
    private MultipartFile file;
}