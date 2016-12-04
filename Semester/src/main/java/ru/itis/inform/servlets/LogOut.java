package ru.itis.inform.servlets;
import ru.itis.inform.services.TokenService;
import ru.itis.inform.services.TokenServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LogOut extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("current_user")) {

                String token = cookie.getValue();

                cookie.setValue("");

                cookie.setPath("/");

                cookie.setMaxAge(0);

                TokenService tokenService = new TokenServiceImpl();

                if (tokenService.findToken(token) != null) {

                    tokenService.deleteToken(token);

                }

                resp.addCookie(cookie);

            }
        }

        req.getSession().invalidate();

        resp.sendRedirect("/login");

    }
}