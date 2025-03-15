package com.example.api_sistemafinanceiro.gui.security;

import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import com.example.api_sistemafinanceiro.gui.domain.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        var token = recoverToken(request);

        if(token != null) {
            var login = jwtUtil.validateToken(token);
            Optional<Usuario> userOpt = usuarioRepository.findByEmail(login);
            userOpt.ifPresent(user -> SecurityContextHolder.getContext().setAuthentication(getAuthentication(user)));
        }

        chain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        return header != null ? header.replace("Bearer ", "") : null;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(Usuario usuario) {
        return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
   }
}
