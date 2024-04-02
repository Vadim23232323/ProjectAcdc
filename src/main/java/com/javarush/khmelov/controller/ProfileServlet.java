package com.javarush.khmelov.controller;
import com.javarush.khmelov.entity.User;
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
        getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User currentUser = (User) session.getAttribute("user");

        String newSurname = req.getParameter("userSurname");
        String newName = req.getParameter("userName");
        String newPassword = req.getParameter("userPassword");

        if (newSurname.isEmpty() || newName.isEmpty()) {
            req.setAttribute("errorMessage", "Пожалуйста, заполните все обязательные поля.");
            getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
            return;
        }

        currentUser.setSurname(newSurname);
        currentUser.setName(newName);

        PasswordEncoder passwordEncoder = new BasicPasswordEncoder();

        if (!newPassword.isEmpty()) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
        }

        userService.update(currentUser);

        session.setAttribute("user", currentUser);

        resp.sendRedirect(GO.PROFILE);
    }
}
