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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
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

		Claims claims = Jwts.claims();
		claims.put("id", usuario.getId());
		claims.put("email", usuario.getEmail());
		claims.put("nome", usuario.getNomeUsuario());
		claims.put("celular", usuario.getCelular());

		return Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis())).setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS256, secret).compact();

	}

	public UsuarioAutenticadoDTO obterDadosUsuario(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();
			UsuarioAutenticadoDTO usuario = new UsuarioAutenticadoDTO();
			usuario.setId(Long.parseLong((String) body.get("id")));
			usuario.setNome((String) body.get("nome"));
			usuario.setEmail((String) body.get("email"));
			usuario.setCelular((String) body.get("celular"));

			return usuario;
		} catch (JwtException | ClassCastException e) {
			return null;
		}
	}

	public String getUsername(String token) {
		UsuarioAutenticadoDTO info = obterDadosUsuario(token);
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
