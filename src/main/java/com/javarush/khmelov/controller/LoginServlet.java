package com.javarush.khmelov.controller;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.UserRepository;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.util.BasicPasswordEncoder;
import com.javarush.khmelov.util.PasswordEncoder;
import com.javarush.khmelov.util.WebPaths;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    // I plan to do dependency management
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        userService = (UserService) context.getAttribute("userService");

        if (context.getAttribute("userService") == null) {
            userService = new UserService(new UserRepository());
            context.setAttribute("userService", userService);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(WebPaths.WP_LOGIN).forward(req, resp);

        log.info("User login page is open");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> optionalUser = userService.findByLogin(login);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            PasswordEncoder passwordEncoder = new BasicPasswordEncoder();
            if (passwordEncoder.matches(password, user.getPassword())) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", user);

                resp.sendRedirect(WebPaths.LIST_QUEST);

                log.info("User: " + login + " successfully logged in");

            } else {
                req.setAttribute("errorMessage", "The password is incorrect.");
                getServletContext().getRequestDispatcher(WebPaths.WP_LOGIN).forward(req, resp);

                log.info("User: " + login + " you specified the wrong password when trying to log in.");
            }
        } else {
            req.setAttribute("errorMessage", "The username specified is incorrect or does not exist.");
            getServletContext().getRequestDispatcher(WebPaths.WP_LOGIN).forward(req, resp);
        }
    }
}