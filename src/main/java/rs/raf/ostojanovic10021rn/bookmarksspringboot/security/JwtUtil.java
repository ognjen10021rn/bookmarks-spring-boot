package rs.raf.ostojanovic10021rn.bookmarksspringboot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import rs.raf.ostojanovic10021rn.bookmarksspringboot.models.AuthenticationDetails;
import rs.raf.ostojanovic10021rn.bookmarksspringboot.models.UserDto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "BATMAAAN";

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String generateToken(AuthenticationDetails authenticationDetails) {
        Map<String, Object> claims = new HashMap<>();

        if (authenticationDetails instanceof UserDto) {
            claims.put("id", authenticationDetails.getUserId());
            claims.put("username", authenticationDetails.getUsername());
        }

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(authenticationDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public boolean validateToken(String token, UserDetails user) {
        return (user.getUsername().equals(extractUsername(token)) && !isTokenExpired(token));
    }
}