package com.ct.api.security;

import static com.ct.api.util.Constantes.EXPIRATION_TIME;
import static com.ct.api.util.Constantes.TOKEN_PREFIX;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.ct.api.domain.Usuario;
import com.ct.api.dto.UsuarioAutenticadoDTO;
import com.ct.api.errors.BusinessException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenService implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	@Autowired
	private UserDetailsService userDetailsService;

	@Value("${jwt.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {
		return Jwts.builder().setSubject(usuario.getId().toString()).claim("email", usuario.getEmail())
				.claim("nome", usuario.getNomeUsuario()).claim("celular", usuario.getCelular())
				.claim("plano", usuario.getPlano()).claim("foto", usuario.getFoto())
				.claim("localidade", usuario.getCidade() + ", " + usuario.getUf())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, secret).compact();

	}

	public UsuarioAutenticadoDTO obterDadosUsuario(String token) {
		try {
			if (token.startsWith(TOKEN_PREFIX)) {
				token = token.replace(TOKEN_PREFIX, "");
			}
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			UsuarioAutenticadoDTO usuario = new UsuarioAutenticadoDTO();
			usuario.setId(Long.parseLong((String) body.getSubject()));
			usuario.setNome((String) body.get("nome"));
			usuario.setEmail((String) body.get("email"));
			usuario.setCelular((String) body.get("celular"));
			usuario.setFoto((String) body.get("foto"));
			usuario.setPlano(Long.valueOf(body.get("plano").toString()));
			usuario.setLocalidade((String) body.get("localidade"));

			return usuario;
		} catch (ExpiredJwtException e) {
			throw new BusinessException("Erro na autenticação");
		}
	}

	public String getUsername(String token) {
		UsuarioAutenticadoDTO info = obterDadosUsuario(token);
		return info.getEmail();
	}

	public UsernamePasswordAuthenticationToken getAuthentication(String token) {

		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));

		if (userDetails != null) {

			return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		}

		return null;
	}

}
