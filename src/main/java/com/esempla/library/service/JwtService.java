package com.esempla.library.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private Logger log= LoggerFactory.getLogger(JwtService.class);
    private static final String SECRET_KEY = "3F4428472B4B6250655368566D5971337436773979244226452948404D635166";

    public String extractUserName(String token) {
        log.debug("Extracting userName from token");
        return extractClaim(token, Claims::getSubject);
    }
    private Key getSigningKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        log.debug("Cheia a fost decodata");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        log.debug("Extracting claims from token");
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        log.debug("Extracting claim from token");
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        log.debug("Creating token");
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        log.debug("Creating token");
        return generateToken(new HashMap<>(), userDetails);
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        log.debug("Username extracted is:{}",username);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        log.debug("Verify if token is valid");
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        log.debug("Verify if token isn't expired");
        return extractClaim(token, Claims::getExpiration);
    }

}
