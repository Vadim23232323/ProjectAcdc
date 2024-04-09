package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.Role;
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
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
@WebServlet(name = "SignupServlet", value = "/signup")
public class SignupServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();

        userService = (UserService) context.getAttribute("userService");

        if (userService == null) {
            userService = new UserService(new UserRepository());
            context.setAttribute("userService", userService);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(WebPaths.WP_CREATE_USER).forward(req, resp);

        log.info("The registration page is open.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("userLogin");
        String password = req.getParameter("userPassword");
        String name = req.getParameter("userName");
        String surname = req.getParameter("userSurname");

        if (userService.findByLogin(login).isPresent()) {
            req.setAttribute("errorMessage", "A user with the same name already exists.");
            getServletContext().getRequestDispatcher(WebPaths.WP_CREATE_USER).forward(req, resp);
            log.info("A user named " + login + " already exists.");
            return;
        }

        PasswordEncoder passwordEncoder = new BasicPasswordEncoder();
        User user = User.builder()
                .login(login)
                .password(passwordEncoder.encode(password))
                .name(name)
                .surname(surname)
                .role(Role.USER)
                .build();

        userService.create(user);

        log.info("User " + login + " has been successfully registered.");

        resp.sendRedirect(WebPaths.LOGIN);
    }

}
