package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.repository.QuestRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@WebServlet(name = "QuestServlet", value = "/list-quest")
public class QuestServlet extends HttpServlet {

    private QuestRepository questRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();

        questRepository = (QuestRepository) context.getAttribute("questRepository");

        if (context.getAttribute("questRepository") == null) {
            questRepository = new QuestRepository();
            context.setAttribute("questRepository", questRepository);
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession currentSession = req.getSession(true);

        Collection<Quest> quest = questRepository.getAll();

        currentSession.setAttribute("quest", quest);

        getServletContext().getRequestDispatcher("/WEB-INF/list-quest.jsp").forward(req, resp);

        log.info("Открыта страница квестов.");
    }
}
