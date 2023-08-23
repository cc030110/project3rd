package com.spring.project3rd.security.jwt.filter;

import com.spring.project3rd.domain.user.User;
import com.spring.project3rd.security.jwt.exception.JwtExceptionCode;
import com.spring.project3rd.security.jwt.token.JwtAuthenticationToken;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
//        User found;
//
//        // Access Token 유효성 검증
//        try {
//            JwtTokenizer.TokenExpired(token, secretKey);
//
//            found = userRepository.findByEmail(JwtTokenizer.getId(token, secretKey))
//                    .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
//
//            //Access Token이 유효하지 않는다면 아래 로직을 지나갈 것
//        } catch (ExpiredJwtException e) {
//            log.error("💡 Access Token 이 만료되었습니다.");
//
//            // redis에 저장되어있는 토큰 정보를 만료된 access token으로 찾아온다.
//            RefreshToken foundTokenInfo = refreshTokenRepository.findByAccessToken(token)
//                    .orElseThrow(() -> new AppException(ErrorCode.TOKEN_NOT_FOUND));
//
//            String refreshToken = foundTokenInfo.getRefreshToken();
//
//            // 만약 refresh 토큰도 만료되었다면, ExceptionHandlerFilter에서 처리된다.
//            JwtTokenUtil.isExpired(refreshToken, secretKey);
//
//            // refresh 토큰이 아직 유효하다면, redis에 함께 저장해둔, employeeId를 가져온다.
//            Long employeeId = Long.valueOf(foundTokenInfo.getId());
//
//            found = employeeRepository.findById(employeeId)
//                    .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
//
//            //위 사용자 정보로 다시 Access Token을 만들어 발급한다.
//            token = JwtTokenUtil.createToken(found.getAccount(), found.getEmail(), secretKey);
//
//            //새로 발급한 Access Token으로 Redis도 업데이트를 해준다.
//            refreshTokenRepository.save(new RefreshToken(String.valueOf(employeeId), refreshToken, token));
//            //클라이언트 측 쿠키의 Access Token도 업데이트를 해준다.
//            CookieGenerator cookieGenerator = new CookieGenerator();
//            cookieGenerator.setCookieName("token");
//            cookieGenerator.setCookieHttpOnly(true);
//            cookieGenerator.addCookie(response, token);
//            cookieGenerator.setCookieMaxAge(60 * 60);//1시간
//        }

        String token="";
        try {
            token = getToken(request);
            if (StringUtils.hasText(token)) {
                getAuthentication(token);
            }
            filterChain.doFilter(request, response);
        }
        catch (NullPointerException | IllegalStateException e) {
            request.setAttribute("exception", JwtExceptionCode.NOT_FOUND_TOKEN.getCode());
            log.error("Not found Token // token : {}", token);
            log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
            throw new BadCredentialsException("throw new not found token exception");
        } catch (SecurityException | MalformedJwtException e) {
            request.setAttribute("exception", JwtExceptionCode.INVALID_TOKEN.getCode());
            log.error("Invalid Token // token : {}", token);
            log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
            throw new BadCredentialsException("throw new invalid token exception");
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", JwtExceptionCode.EXPIRED_TOKEN.getCode());
            log.error("EXPIRED Token // token : {}", token);
            log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
            throw new BadCredentialsException("throw new expired token exception");
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", JwtExceptionCode.UNSUPPORTED_TOKEN.getCode());
            log.error("Unsupported Token // token : {}", token);
            log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
            throw new BadCredentialsException("throw new unsupported token exception");
        } catch (Exception e) {
            log.error("====================================================");
            log.error("JwtFilter - doFilterInternal() 오류 발생");
            log.error("token : {}", token);
            log.error("Exception Message : {}", e.getMessage());
            log.error("Exception StackTrace : {");
            e.printStackTrace();
            log.error("}");
            log.error("====================================================");
            throw new BadCredentialsException("throw new exception");
        }
    }


    private void getAuthentication(String token) {
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(token);
        authenticationManager.authenticate(authenticationToken);
        // Holder에 담으면 언제든지 정보를 가져올수 있음
        SecurityContextHolder.getContext()
                .setAuthentication(authenticationToken);
    }

    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer")){
            String[] arr = authorization.split(" ");
            return arr[1];
        }
        return null;
    }
}