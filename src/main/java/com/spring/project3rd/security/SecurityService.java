//package com.spring.project3rd.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;
//import java.security.Key;
//import java.util.Date;
//
//@Service
//public class SecurityService {
//    private static final String SECRET_KEY = "gkjsbkgjdfkgjhdkjfhckncghgerighmcigsdfgsdfgo";
//
//    // 로그인 서비스 던질때 같이
//    public String createToken(String subject, long expTime) {
//        if (expTime <= 0) {
//            throw new RuntimeException("만료시간이 0보다 작음");
//        }
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
//        Key signingkey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
//
//        return Jwts.builder()
//                .setSubject(subject)
//                .signWith(signingkey, signatureAlgorithm)
//                .setExpiration(new Date(System.currentTimeMillis() * expTime))
//                .compact();
//    }
//
//    // 토큰 검증하는 메서드를 만들어서 boolean
//    public String getSubject(String token){
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//}
