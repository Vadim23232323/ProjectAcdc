package com.javarush.khmelov.controller;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.repository.QuestRepository;
import com.javarush.khmelov.service.ImageService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.Optional;

@WebServlet(name = "ImageServlet", value = "/image")
public class ImageServlet extends HttpServlet {
    private QuestRepository questRepository;
    private ImageService imageService;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        questRepository = (QuestRepository) context.getAttribute("questRepository");

        if (imageService == null) {
            imageService = new ImageService(context);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String questIdParam = req.getParameter("questId");
        if (questIdParam != null) {
            serveImage(questIdParam, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Отсутствует параметр questId");
        }
    }

    private void serveImage(String questIdParam, HttpServletResponse resp) throws IOException {
        try {
            long questId = Long.parseLong(questIdParam);
            Optional<Quest> questOptional = questRepository.get(questId);
            if (questOptional.isPresent()) {
                Quest quest = questOptional.get();
                imageService.serveImage(quest.getImage(), resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Квест с идентификатором: " + questId + " не найден");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат questId: " + questIdParam);
        }
    }
}
