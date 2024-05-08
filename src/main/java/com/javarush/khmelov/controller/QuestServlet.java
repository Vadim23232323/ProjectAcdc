package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.dao.QuestDAO;
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
import java.util.Collection;

@Slf4j
@WebServlet(name = "QuestServlet", value = "/list-quest")
public class QuestServlet extends HttpServlet {

    private QuestDAO questRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();

        questRepository = (QuestDAO) context.getAttribute("questRepository");

        if (context.getAttribute("questRepository") == null) {
            questRepository = new QuestDAO();
            context.setAttribute("questRepository", questRepository);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession currentSession = req.getSession(true);

        Collection<Quest> quest = questRepository.getAll();

        currentSession.setAttribute("quest", quest);

        getServletContext().getRequestDispatcher(WebPaths.WP_LIST_QUEST).forward(req, resp);

        log.info("The quests page is open.");
    }
}
