package com.uthkarsh.fileAPI.services.serviceFactory.FileDownloadPackage.TypeFileDownloadBean.FileDownloadInterface;

import org.springframework.core.io.Resource;

public interface FileDownload {
    public Resource download(String name);
}
