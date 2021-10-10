package com.jochoa.market.web.security;

import com.jochoa.market.domain.service.MarketUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    MarketUserDetailsService marketUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            String jwt = authorizationHeader.substring(7);
            String userName = jwtUtil.extractUserName(jwt);

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){ //Que no haya una autenticación para el usuario
                UserDetails userDetails = marketUserDetailsService.loadUserByUsername(userName);

                if(jwtUtil.validateToken(jwt, userDetails)){
                    //
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    //Detalles de la conexión que esta recibiendo: Evaluar navegador, horarios, s.o etc
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //Para que no tenga que pasar nuevamente por el if
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
