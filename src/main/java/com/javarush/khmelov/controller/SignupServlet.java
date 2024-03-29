package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.QuestRepository;
import com.javarush.khmelov.repository.UserRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;


@WebServlet(name = "SignupServlet", value = "/signup")
public class SignupServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        initializeRepositories();
    }

    private void initializeRepositories() {
        ServletContext context = getServletContext();
        if (context.getAttribute("userRepository") == null) {
            userRepository = new UserRepository();
            context.setAttribute("userRepository", userRepository);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);

        userRepository = new UserRepository();

        Collection<User> user = userRepository.getAll();

        currentSession.setAttribute("user", user);

        getServletContext().getRequestDispatcher("/WEB-INF/list-user.jsp").forward(req, resp);
    }
}
