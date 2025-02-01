package com.dl.fitness_tracking_app.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.cookie.name}")
    private String jwtCookie;
    @Value("${jwt.expiration.date}")
    private long expirationDate;
    @Value("${jwt.secret.key}")
    private String secretKey;

    public ResponseCookie generateJwtCookie(UserDetails userDetails) {
        String token = generateToken(userDetails);
        return ResponseCookie.from(jwtCookie,token)
                .path("/api/v1")
                .maxAge(24*60*60)
                .httpOnly(true)
                .build();
    }

    private String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(HashMap<String,Object> kvHashMap, UserDetails userDetails) {
        long expirationTime = System.currentTimeMillis() + expirationDate;
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(expirationTime))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = secretKey.getBytes();  // Convert the string to bytes

        // Ensure the key length is exactly 32 bytes (256 bits)
        if (keyBytes.length < 32) {
            // If the key is shorter than 32 bytes, pad it (using zero padding here)
            byte[] paddedKey = new byte[32];
            System.arraycopy(keyBytes, 0, paddedKey, 0, keyBytes.length); // Copy the key to the new array
            keyBytes = paddedKey;  // Use the padded key
        } else if (keyBytes.length > 32) {
            // If the key is longer than 32 bytes, truncate it
            byte[] truncatedKey = new byte[32];
            System.arraycopy(keyBytes, 0, truncatedKey, 0, 32);  // Copy only the first 32 bytes
            keyBytes = truncatedKey;  // Use the truncated key
        }

        // Return a SecretKeySpec initialized with the key bytes for HmacSHA256
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    private Key getSignKey(SignatureAlgorithm signatureAlgorithm) {
        return Keys.secretKeyFor(signatureAlgorithm);
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null){
            return cookie.getValue();
        } else{
            return null;
        }
    }

    public String extractUserEmail(String token) {
       return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
