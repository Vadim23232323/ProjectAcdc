package com.javarush.khmelov.controller;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.UserRepository;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.util.BasicPasswordEncoder;
import com.javarush.khmelov.util.GO;
import com.javarush.khmelov.util.PasswordEncoder;
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
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);

        log.info("Открыта страница авторизации пользователя");
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

                resp.sendRedirect(GO.LIST_QUEST);

                log.info("Пользователь: " + login + " успешно выполнил вход");

            } else {
                req.setAttribute("errorMessage", "Неверно указан пароль!");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);

                log.info("Пользователь: " + login + " указал не верный пароль при попытке авторизации.");
            }
        } else {
            req.setAttribute("errorMessage", "Имя пользователя заданно неверно или не существует!");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }
}