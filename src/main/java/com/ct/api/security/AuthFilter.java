package com.ct.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ct.api.domain.Usuario;
import com.ct.api.dto.LoginDTO;
import com.ct.api.util.Constantes;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenService tokenService;

	public AuthFilter(AuthenticationManager authenticationManager, JwtTokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			LoginDTO loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
					loginRequest.getSenha());
			return authenticationManager.authenticate(authentication);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		Usuario usuario = new Usuario();

		String token = tokenService.gerarToken(usuario);
		response.addHeader(Constantes.HEADER_STRING, Constantes.TOKEN_PREFIX + token);
	}

}
