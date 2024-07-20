package GeminiConsult.Security;

import GeminiConsult.Entities.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Users user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("geminiConsult").withSubject(user.getLogin()).withExpiresAt(generateExpirationDate()).sign(algorithm);
            return token;
        } catch (Exception e) {
            throw new RuntimeException("Error While authenticating");
        }
    }

    private Instant generateExpirationDate() {
        return Instant.now().atOffset(ZoneOffset.UTC).plusSeconds(3600).toInstant();
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("geminiConsult").build().verify(token).getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Error While validating");
        }
    }
}
