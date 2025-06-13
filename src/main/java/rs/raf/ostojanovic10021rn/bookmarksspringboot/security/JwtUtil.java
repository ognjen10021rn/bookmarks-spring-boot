package rs.raf.ostojanovic10021rn.bookmarksspringboot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import rs.raf.ostojanovic10021rn.bookmarksspringboot.models.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {

    static final String SECRET = "BATMAAAN";

    protected String generateJWT(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("userId", user.getUserId());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    protected String getUserFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    protected boolean isExpired(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }
    public boolean validateToken(String token, UserDetails user) {
        return (user.getUsername().equals(getUserFromJwt(token)) && !isExpired(token));
    }
}
