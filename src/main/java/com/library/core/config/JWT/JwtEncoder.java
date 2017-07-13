package com.library.core.config.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by user on 13.07.2017.
 */
public final class JwtEncoder {

    private static String signingKey = "secret";

    public static String createJwt(JwtPayload payload) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;

        JwtBuilder builder = Jwts.builder().setId(payload.getId().toString())
                .claim("admin", payload.getAdmin().toString())
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public static JwtPayload parseJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(jwt)
                .getBody();
        JwtPayload payload = new JwtPayload();
        payload.setId(Long.parseLong(claims.getId()));
        payload.setAdmin(claims.get("admin").equals("true"));
        return payload;
    }

}
