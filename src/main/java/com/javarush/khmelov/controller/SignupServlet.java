package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.Role;
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
import java.io.IOException;



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
        getServletContext().getRequestDispatcher("/WEB-INF/create-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("userLogin");
        String password = req.getParameter("userPassword");
        String name = req.getParameter("userName");
        String surname = req.getParameter("userSurname");

        if (userService.findByLogin(login).isPresent()) {

            req.setAttribute("errorMessage", "Пользователь с таким именем уже существует!");
            getServletContext().getRequestDispatcher("/WEB-INF/create-user.jsp").forward(req, resp);
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

        resp.sendRedirect(GO.LOGIN);
    }

}
