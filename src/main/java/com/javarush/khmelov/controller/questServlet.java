package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.AnswerRepository;
import com.javarush.khmelov.repository.QuestRepository;
import com.javarush.khmelov.repository.QuestionRepository;
import com.javarush.khmelov.service.QuestionService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@WebServlet(name = "QuestServlet", value = "/list-quest")
public class questServlet extends HttpServlet {

    private QuestRepository questRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        initializeRepositories();
    }

    private void initializeRepositories() {
        ServletContext context = getServletContext();
        if (context.getAttribute("questRepository") == null) {
            QuestRepository questRepository = new QuestRepository();
            context.setAttribute("questRepository", questRepository);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession currentSession = req.getSession(true);

        questRepository = new QuestRepository();

        Collection<Quest> quest = questRepository.getAll();

        currentSession.setAttribute("quest", quest);

        getServletContext().getRequestDispatcher("/WEB-INF/list-quest.jsp").forward(req, resp);
    }
}
