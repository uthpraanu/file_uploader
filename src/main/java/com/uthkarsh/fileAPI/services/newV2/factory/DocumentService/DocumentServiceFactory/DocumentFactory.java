package com.uthkarsh.fileAPI.services.newV2.factory.DocumentService.DocumentServiceFactory;

import com.uthkarsh.fileAPI.enums.OrganizationEnum;
import com.uthkarsh.fileAPI.exception.PersonalizedFactoryException;
import com.uthkarsh.fileAPI.services.newV2.factory.DocumentService.DocumentServiceBean.CompanyPurchaseOrderService;
import com.uthkarsh.fileAPI.services.newV2.factory.DocumentService.DocumentServiceBean.DocumentServiceInterface.OrganizationDocumentService;
import com.uthkarsh.fileAPI.services.newV2.factory.DocumentService.DocumentServiceBean.VendorOrderQuotationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DocumentFactory {

    private static final Logger logger = LoggerFactory.getLogger(DocumentFactory.class);

    private final ApplicationContext applicationContext;

    @Autowired
    public DocumentFactory(ApplicationContext context){
        this.applicationContext = context;
    }

    public OrganizationDocumentService getOrganizationService(OrganizationEnum organization){
        logger.info("Retrieving OrganizationDocumentService for organization type: {}", organization);

        try{
            if(organization == OrganizationEnum.COMPANY){
                logger.debug("Returning CompanyPurchaseOrderService bean");

                return applicationContext.getBean(CompanyPurchaseOrderService.class);
            }
            else if(organization == OrganizationEnum.VENDOR){
                logger.debug("Returning VendorOrderQuotationService bean");

                return applicationContext.getBean(VendorOrderQuotationService.class);
            }
            else{
                logger.error("Unsupported organization type: {}",organization);

                throw new IllegalArgumentException("Unsupported name provided for organization");
            }
        }
        catch (Exception e){
            logger.error("Failed to retrieve OrganizationDocumentService for organization type: {}", organization, e);

            throw new PersonalizedFactoryException("Failed to create the Document Uploader for: "+organization);
        }
    }
}
