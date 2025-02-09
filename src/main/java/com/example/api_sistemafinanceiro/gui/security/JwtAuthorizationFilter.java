package com.example.api_sistemafinanceiro.gui.security;

import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtUtil jwtUtil;

    private final UserDetailsSecurity userDetailsSecurity;

    public JwtAuthorizationFilter(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            UserDetailsSecurity userDetailsSecurity
    ) {
        super(authenticationManager);
        this.userDetailsSecurity = userDetailsSecurity;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if(request.getHeader("Authorization") != null && header.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));

            if(auth.isAuthenticated()) SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

   private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String email = jwtUtil.getUsername(token);
        Usuario usuario = (Usuario) userDetailsSecurity.loadUserByUsername(email);

        if(usuario == null || !jwtUtil.isValidToken(token)) throw new RuntimeException("Acesso negado");

        return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
   }
}
