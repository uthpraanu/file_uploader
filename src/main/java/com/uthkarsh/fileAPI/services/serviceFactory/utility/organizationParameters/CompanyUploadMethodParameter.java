package com.uthkarsh.fileAPI.services.serviceFactory.utility.organizationParameters;

import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.services.serviceFactory.utility.organizationParameters.OrganizationMethoParameter.OrganizationUploadMethodParameter;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyUploadMethodParameter implements OrganizationUploadMethodParameter {
    private long companyID;
    private FileUploaderEnum type;
    private MultipartFile file;
}
