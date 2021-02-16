package com.ct.api.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ct.api.errors.BusinessException;
import com.ct.api.services.AmazonS3Service;

@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucketName}")
	private String bucketName;

	@Override
	public String fileUpload(String pasta, String nome, MultipartFile multipartFile) {
		String originalName = multipartFile.getOriginalFilename();
		String extensao = originalName.substring(originalName.lastIndexOf('.'), originalName.length());
		String finalName = generateFileName(pasta, nome, extensao);

		try {
			File file = multipartToFile(originalName, multipartFile);
			uploadFileTos3bucket(finalName, file);
			file.delete();
		} catch (Exception e) {
			throw new BusinessException("Falha ao carregar a imagem");
		}
		return getURIFile(finalName);
	}

	private File multipartToFile(String fileName, MultipartFile multipartFile) throws Exception {

		File file = new File(fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(multipartFile.getBytes());
		fos.close();
		return file;
	}

	private void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(
				new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	@Override
	public String getURIFile(String caminho) {
		URL url = s3client.getUrl(bucketName, caminho);

		if (url == null) {
			return "";
		}
		return url.toString();

	}

	@Override
	public Boolean deleteFile(String fileUrl, String pasta) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf('/'), fileUrl.length());
		s3client.deleteObject(new DeleteObjectRequest(bucketName, pasta.concat(fileName)));
		return true;
	}

	private String generateFileName(String pasta, String usuario, String sufix) {
		return pasta.concat("/") + new Date().getTime() + "_".concat(usuario).concat(sufix);
	}

}
