package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.User;
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

@Slf4j
@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(WebPaths.WP_PROFILE).forward(req, resp);

        log.info("Profile page open");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User currentUser = (User) session.getAttribute("user");

        String login = req.getParameter("userLogin");
        String newSurname = req.getParameter("userSurname");
        String newName = req.getParameter("userName");
        String newPassword = req.getParameter("userPassword");

        if (newSurname.isEmpty() || newName.isEmpty()) {
            req.setAttribute("errorMessage", "Please fill out all required fields.");
            getServletContext().getRequestDispatcher(WebPaths.WP_PROFILE).forward(req, resp);
            return;
        }

        currentUser.setSurname(newSurname);
        currentUser.setName(newName);

        PasswordEncoder passwordEncoder = new BasicPasswordEncoder();

        if (!newPassword.isEmpty()) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            log.info("User: " + login + " changed the password.");
        }

        userService.update(currentUser);

        log.info("User: " + login + " updated my details.");

        session.setAttribute("user", currentUser);

        resp.sendRedirect(WebPaths.PROFILE);
    }
}
