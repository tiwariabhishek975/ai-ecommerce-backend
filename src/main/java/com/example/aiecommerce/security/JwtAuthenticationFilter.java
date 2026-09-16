package com.example.aiecommerce.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends org.springframework.web.filter.OncePerRequestFilter {
    private final JwtService jwtService; private final CustomUserDetailsService uds;
    public JwtAuthenticationFilter(JwtService jwtService,CustomUserDetailsService uds){this.jwtService=jwtService;this.uds=uds;}
    @Override protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws ServletException,IOException {
        String header=request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer ")){
            String token=header.substring(7);
            if(jwtService.isValid(token)){
                try {
                    String email=jwtService.extractEmail(token);
                    UserDetails user=uds.loadUserByUsername(email);
                    var auth=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } catch(Exception ignored) {}
            }
        }
        chain.doFilter(request,response);
    }
}
