package org.jbestie.sqlexp.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SqlExpSimpleUrlLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    public static final String USERNAME = "username";

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        request.getSession().removeAttribute(USERNAME);
        super.onLogoutSuccess(request, response, authentication);
    }
}
