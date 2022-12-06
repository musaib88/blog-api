package Musaib.MybackendProject.Utilities;

import Musaib.MybackendProject.Services.MyUserDetailService;
import net.bytebuddy.asm.MemberSubstitution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
public class JwtVerification extends OncePerRequestFilter {
    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    MyUserDetailService myUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String Jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("bearer")) {
            Jwt = authorizationHeader.substring(7);
            username = this.jwtHelper.extractUsername(Jwt);

        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            UserDetails userDetails = this.myUserDetailService.loadUserByUsername(username);

            if (jwtHelper.validateToken(Jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(request, response);
            }
        }
    }
}
