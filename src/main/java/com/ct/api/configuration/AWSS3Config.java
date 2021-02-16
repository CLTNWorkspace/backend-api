package com.ct.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSS3Config {

	@Value("${s3.accessKeyId}")
	private String accessKeyId;

	@Value("${s3.secretKey}")
	private String secretKey;

	@Value("${s3.region}")
	private String region;

	@Bean
	public AmazonS3 initializeAmazon() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretKey);

		return AmazonS3ClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	}
}
