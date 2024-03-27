package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.AnswerRepository;
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
import java.util.Optional;

@WebServlet(name = "QuestServlet", value = "/question")
public class questionServlet extends HttpServlet {
    private QuestionService questionService;

    @Override
    public void init() throws ServletException {
        super.init();
        initializeRepositories();
        initializeQuestionService();
    }

    private void initializeRepositories() {
        ServletContext context = getServletContext();
        if (context.getAttribute("questionRepository") == null) {
            QuestionRepository questionRepository = new QuestionRepository();
            AnswerRepository answerRepository = new AnswerRepository();
            context.setAttribute("questionRepository", questionRepository);
            context.setAttribute("answerRepository", answerRepository);
        }
    }

    private void initializeQuestionService() {
        ServletContext context = getServletContext();
        QuestionRepository questionRepository = (QuestionRepository) context.getAttribute("questionRepository");
        AnswerRepository answerRepository = (AnswerRepository) context.getAttribute("answerRepository");
        questionService = new QuestionService(questionRepository, answerRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long answerId = req.getParameter("answerId") != null ? Long.parseLong(req.getParameter("answerId")) : null;

        Optional<Question> questionOptional = questionService.getNextQuestion(answerId);

        if (questionOptional.isPresent()) {
            req.setAttribute("currentQuestion", questionOptional.get());
        } else {
            req.setAttribute("error", "Вопрос не найден");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/question.jsp").forward(req, resp);
    }
}