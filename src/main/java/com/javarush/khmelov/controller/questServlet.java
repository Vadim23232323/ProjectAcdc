package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.Answer;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.AnswerRepository;
import com.javarush.khmelov.repository.QuestRepository;
import com.javarush.khmelov.repository.QuestionRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "QuestServlet", value = "/quest")
public class questServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);

        if (currentSession.getAttribute("questionRepository") == null) {
            QuestionRepository questionRepository = new QuestionRepository();
            AnswerRepository answerRepository = new AnswerRepository();
            currentSession.setAttribute("questionRepository", questionRepository);
            currentSession.setAttribute("answerRepository", answerRepository);
        }

        QuestionRepository questionRepository = (QuestionRepository) currentSession.getAttribute("questionRepository");
        AnswerRepository answerRepository = (AnswerRepository) currentSession.getAttribute("answerRepository");

        Long currentQuestionId;

        if (req.getParameter("answerId") != null) {
            // Get nextQuestionId from the submitted answer
            Long answerId = Long.parseLong(req.getParameter("answerId"));
            Optional<Answer> answerOptional = answerRepository.get(answerId);
            if (answerOptional.isPresent()) {
                currentQuestionId = answerOptional.get().getNextQuestionId();
            } else {
                // Handle invalid answerId
                currentQuestionId = null; // Or some default question
            }
        } else {
            // Get the starting question ID from the selected quest (implement quest selection logic)
            currentQuestionId = 1L; // Replace with actual quest startQuestionId
        }

        if (currentQuestionId != null) {
            Optional<Question> questionOptional = questionRepository.get(currentQuestionId);
            if (questionOptional.isPresent()) {
                Question currentQuestion = questionOptional.get();
                req.setAttribute("currentQuestion", currentQuestion);
            } else {
                // Handle invalid questionId
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/quest.jsp").forward(req, resp);
    }
}