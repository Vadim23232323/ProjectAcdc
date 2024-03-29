package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.QuestRepository;
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

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService;

    public LoginServlet() {
        this.userService = new UserService(new UserRepository()); // Создаем новый экземпляр сервиса
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
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

                // Редирект на защищенную страницу
                resp.sendRedirect(GO.LIST_QUEST);
            } else {
                // Неправильный пароль
                resp.sendRedirect(GO.LOGIN);
            }
        } else {
            // Пользователь не найден
            resp.sendRedirect(GO.LOGIN);
        }
    }
}