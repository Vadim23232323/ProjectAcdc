package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.repository.QuestRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@WebServlet(name = "ImageServlet", value = "/image")
public class ImageServlet extends HttpServlet {
    private final QuestRepository questRepository = new QuestRepository(); // Assuming you have an instance of QuestRepository

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questIdParam = req.getParameter("questId");
        if (questIdParam != null) {
            try {
                long questId = Long.parseLong(questIdParam);
                Optional<Quest> questOptional = questRepository.get(questId);
                if (questOptional.isPresent()) {
                    Quest quest = questOptional.get();
                    String imagePath = "/WEB-INF/img/" + quest.getImage(); // Assuming images are in /WEB-INF/img
                    File imageFile = new File(getServletContext().getRealPath(imagePath));
                    if (imageFile.exists()) {
                        resp.setContentType("image/jpeg"); // Adjust content type as needed
                        try (OutputStream os = resp.getOutputStream();
                             FileInputStream fis = new FileInputStream(imageFile)) {
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = fis.read(buffer)) != -1) {
                                os.write(buffer, 0, bytesRead);
                            }
                        }
                    } else {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
