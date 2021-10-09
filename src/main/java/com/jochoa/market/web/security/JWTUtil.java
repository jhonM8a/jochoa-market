package com.jochoa.market.web.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JWTUtil {
    private static final String KEY = "Jochoa_p";
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date()) //Cuando fue creaado
                .setExpiration(new Date(System.currentTimeMillis()+1000 *60*60*10)) //Dura 10 horas
                .signWith(SignatureAlgorithm.HS256, KEY) //Firma
                .compact();
    }
}
