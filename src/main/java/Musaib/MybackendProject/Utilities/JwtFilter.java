package Musaib.MybackendProject.Utilities;

import Musaib.MybackendProject.Services.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
public class JwtFilter extends OncePerRequestFilter {

        @Autowired
        private MyUserDetailService userDetailsService;

        @Autowired
        private JwtHelper jwtUtil;


        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws ServletException, IOException {

            final String authorizationHeader = request.getHeader("Authorization");

            String username = null;
            String jwt = null;


            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

                jwt = authorizationHeader.substring(7);
                System.out.println(jwt);
                username = jwtUtil.extractUsername(jwt);
            }


            if (username != null && SecurityContextHolder.getContext().getAuthentication()== null) {
                UserDetails user  = this.userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(jwt, user)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            chain.doFilter(request, response);
        }

    }

