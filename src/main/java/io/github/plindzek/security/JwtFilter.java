package io.github.plindzek.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = //request.getHeader("Authorization");
                /*hardcoded header - we should receive this from facebook or other app to login
                header contains username, role eg. ROLE_ADMIN or ADMIN and others parameters (session length)
                encrypted with our password
                */
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6ImFkbWluIiwiaWF0IjoxNTE2MjM5MDIyLCJyb2xlIjoiUk9MRV9BRE1JTiJ9.TUVNSdER2DutYHcLjVR9CUhHMNqzD3f64LDfPxM1oH4";

        UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(header);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header) {

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey("1234".getBytes()).parseClaimsJws(header.replace("Bearer ", ""));
        String role = claimsJws.getBody().get("role").toString();
        String userName = claimsJws.getBody().get("name").toString();
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(role));
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, null, simpleGrantedAuthorities);
        return usernamePasswordAuthenticationToken;
    }
}


