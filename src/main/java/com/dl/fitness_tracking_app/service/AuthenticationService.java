package com.dl.fitness_tracking_app.service;

import com.dl.fitness_tracking_app.dto.AuthenticationRequest;
import com.dl.fitness_tracking_app.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.email(),
                        authenticationRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        ResponseCookie responseCookie = jwtService.generateJwtCookie(userDetails);
        System.out.println(responseCookie.getValue());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body("User Authenticated");
    }
}
