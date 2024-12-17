package com.bnt.UserManagementSystem.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.Base64.Decoder;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.function.Function;


@Service
public class JwtService {

    private String secretKey="";

    public JwtService(){
      try {
        KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
        SecretKey sk=keyGen.generateKey();
        secretKey=Base64.getEncoder().encodeToString(sk.getEncoded());
        
      } catch (Exception e) {
         throw new RuntimeException(e);
      }

    }

    public String genrateToken(String username) {

        Map<String,Object> claims=new HashMap<>();

         
      return Jwts.builder()
              .claims()
              .add(claims)
              .subject(username)
              .issuedAt(new Date(System.currentTimeMillis()))
              .expiration(new Date(System.currentTimeMillis()+60*60*80))
              .and()
              .signWith(getKey())
              .compact();
      
    }

    private SecretKey getKey() {
      byte[] keyBytes = Base64.getDecoder().decode(secretKey); // Correct Base64 decoding
      return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
           return extractClaim(token, Claims::getSubject);    
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
      final Claims claims = extractAllClaims(token);
      return claimResolver.apply(claims);
  }
   
     private Claims extractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
        }

    

    public boolean validateToken(String token, UserDetails userDetails) {
      final String userName = extractUserName(token);
      return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
       }
     
       private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
      }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }   
}
