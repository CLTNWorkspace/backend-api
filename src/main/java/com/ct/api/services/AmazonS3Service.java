package com.ct.api.services;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3Service {

	public abstract String getURIFile(String camiinho);

	public abstract String fileUpload(String pasta, String usuario, MultipartFile multipartFile);

	public abstract Boolean deleteFile(String fileUrl, String pasta);
}
