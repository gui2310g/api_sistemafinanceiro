package com.example.api_sistemafinanceiro.gui.security;

import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import com.example.api_sistemafinanceiro.gui.domain.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtUtil {

    private final Map<String, String> activeTokens = new ConcurrentHashMap<>();

    @Value("${auth.jwt.secret}")
    private String jwtSecret;

    @Value("${auth.jwt.expiration}")
    private Long jwtExpires;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generateToken(Authentication authentication) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpires);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        String token = Jwts.builder()
                .subject(usuario.getUsername())
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(getSigningKey())
                .compact();

        activeTokens.put(usuario.getUsername(), token);

        return token;
    }

    private Claims getClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);
        String email = claims.getSubject();

        return activeTokens.containsKey(email) && activeTokens.get(email).equals(token) &&
                claims.getExpiration().after(new Date());
    }

    public Long getAuthenticatedUserId(Authentication authentication) {
        String email = authentication.getName();
        Usuario user = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }
}
