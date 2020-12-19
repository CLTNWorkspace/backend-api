package com.ct.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

	private static final long JWT_TOKEN_VALIDITY = 3600;
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	@Value("${jwt.secret}")
	private String secret;

}
