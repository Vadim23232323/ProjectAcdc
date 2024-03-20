package com.javarush.khmelov.controller;


import com.javarush.khmelov.repository.QuestRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Создание новой сессии
        HttpSession currentSession = req.getSession(true);

        QuestRepository questRepository = new QuestRepository();


        // Добавление в сессию параметров поля (нужно будет для хранения состояния между запросами)
        currentSession.setAttribute("field", questRepository);
        // и значений поля, отсортированных по индексу (нужно для отрисовки крестиков и ноликов)


        // Перенаправление запроса на страницу index.jsp через сервер
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}