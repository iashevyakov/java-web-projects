package ru.itis.inform.filters;
import ru.itis.inform.models.User;
import ru.itis.inform.services.TokenService;
import ru.itis.inform.services.TokenServiceImpl;
import ru.itis.inform.services.UserService;
import ru.itis.inform.services.UserServiceImpl;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("current_user")) {

                    TokenService tokenService = new TokenServiceImpl();

                    String id = tokenService.findToken(cookie.getValue());

                    UserService userService = new UserServiceImpl();

                    User user = userService.findId(id);

                    if (user != null) {


                        ((HttpServletRequest) servletRequest).getSession().setAttribute("current_user", user);


                        ((HttpServletResponse) servletResponse).sendRedirect("/home");


                        return;
                    }
                }
            }
        }
        if (((HttpServletRequest) servletRequest).getSession().getAttribute("current_user") != null) {


                  ((HttpServletResponse) servletResponse).sendRedirect("/home");


            return;
        }

         else {

            filterChain.doFilter(servletRequest, servletResponse);

            return;
        }
    }

    public void destroy() {

    }
}