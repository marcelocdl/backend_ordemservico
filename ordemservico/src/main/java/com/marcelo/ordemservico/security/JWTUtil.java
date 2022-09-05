package com.marcelo.ordemservico.security;

import com.marcelo.ordemservico.model.Usuario;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {

    public static final long TEMPO_VIDA = Duration.ofSeconds(3600).toMillis();



    public String gerarToken(Usuario usuario) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("sub", usuario.getUsername());
        claims.put("permissions", usuario.getPermission());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + TEMPO_VIDA))
                .signWith(SignatureAlgorithm.HS256, "OrdemEServico")
                .compact();
    }

    public String getUsernameToken(String token) {
        if (token != null) {
            return this.parseToken(token).getSubject();
        }
        return null;
    }

    public boolean isExpiredToken(String token) {
        if (token != null) {
            return this.parseToken(token).getExpiration().before(new Date());
        }
        return false;
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey("OrdemEServico")
                .parseClaimsJws(token.replace("Bearer", ""))
                .getBody();
    }

}
