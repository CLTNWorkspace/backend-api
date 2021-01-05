package com.ct.api.configuration;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.ct.api.domain.Usuario;
import com.ct.api.dto.UsuarioAutenticadoDTO;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenService implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;
	private static final long JWT_TOKEN_VALIDITY = 3600;
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	@Autowired
	private UserDetailsService userDetailsService;

	@Value("${jwt.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {

		UsuarioAutenticadoDTO dados = new UsuarioAutenticadoDTO(usuario.getId(), usuario.getNomeUsuario(),
				usuario.getEmail(), usuario.getCelular());

		String subjet = new Gson().toJson(dados);

		return Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis())).setSubject(subjet)
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS256, secret).compact();

	}

	public UsuarioAutenticadoDTO pegarDados(String token) {
		return new Gson().fromJson(Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject(),
				UsuarioAutenticadoDTO.class);
	}

	public String getUsername(String token) {

		UsuarioAutenticadoDTO info = pegarDados(token);
		return info.getEmail();
	}

	public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));

			if (userDetails != null) {

				return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			}

			return null;
		}

		return null;
	}

}
