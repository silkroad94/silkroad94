package com.example.board.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/member/join", "/member/login", "/member/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try {
            log.info("로그인 인증 체크 필터 시작 {}", requestURI);

            if (isLoginCheckPath(requestURI)) {
                log.info("로그인 인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpServletRequest.getSession(false);
                if (session == null || session.getAttribute("loginMember") == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    // 로그인 페이지로 리다이렉트
                    httpServletResponse.sendRedirect("/member/login");
                    // 리턴을 해주지 않으면 계속 진행한다.
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            // 톰캣까지 예외를 보내주어야 한다 그렇지 않으면 정상 요청으로 처리된다.
            throw e;
        } finally {
            log.info("로그인 인증 체크 필터 종료 {}", requestURI);
        }
    }

    // 화이트 리스트의 경우 인증 체크 안함
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
