package uz.pdp.SpringSecurity.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_HS256_KEY = "34e7f550e2715c958372db43db8f68c0ea686807bf7eab4f22f2ee673df0f929";
    private final Long EXPIRED_DATE_ACCESS =  60 * 1000L;
    private final Long EXPIRED_DATE_REFRESH = 24 * 60 * 60 * 1000L;

    public SecretKey getKeysHs256(){
        return Keys.hmacShaKeyFor(SECRET_HS256_KEY.getBytes());
    }

    public String generateTokenAccess(String username) {
        return createToken(username, new HashMap<>(), EXPIRED_DATE_ACCESS);
    }

    public String generateTokenRefresh(String username) {
        return createToken(username, new HashMap<>(), EXPIRED_DATE_REFRESH);
    }

    public String createToken(String subject, Map<String, Object> claims, Long expirationTime) {
        return Jwts.builder()
                .signWith(getKeysHs256(), SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .compact();
    }

    public String getSubject(String token) {
        Claims jwtBody = getJWTBody(token);
        return jwtBody.getSubject();
    }

    public Claims getJWTBody(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getKeysHs256())
                .build();
        return (Claims) parser.parse(token).getBody();
    }

}

