package com.sillas.sillas.config.security;

import com.sillas.sillas.config.jwt.JWTUserData;
import com.sillas.sillas.config.jwt.TokenConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    /*
    OncePerRequest é usado para autenticar requisições uma vez por requisição, ou seja,
    a cada chamada podemos verificar o conteúdo e a procedência, exemplo mais prático é o
    JWT, com o usuário logado e adquirido o seu token, todas as requisições que ele fizer podemos autenticar
    e verificar se ele está autorizado e quais os níveis de autorização que ele possui no sistema

     */
    private final TokenConfig tokenConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authorize = request.getHeader("Authorization");

        if(Strings.isNotEmpty(authorize) && authorize.startsWith("Bearer ")) {
            String token = authorize.substring("Bearer ".length());
            Optional<JWTUserData> optUser = tokenConfig.validadeToken(token);

            if(optUser.isPresent()){
                JWTUserData userData = optUser.get();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userData, null, null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
