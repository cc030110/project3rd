package com.spring.project3rd.security.jwt.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@Getter
@RedisHash(value = "jwtToken", timeToLive = 60*60*24*3)
public class RefreshToken {

    @Id
    private String id;

    private String refreshToken;

    @Indexed // 이게 있어야 데이터 찾아올숭 ㅣㅆ음
    private String accessToken;
}