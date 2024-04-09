package com.javarush.khmelov.controller;

import com.javarush.khmelov.entity.Answer;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.AnswerRepository;
import com.javarush.khmelov.repository.QuestRepository;
import com.javarush.khmelov.repository.QuestionRepository;
import com.javarush.khmelov.service.QuestionService;
import com.javarush.khmelov.util.WebPaths;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Slf4j
@WebServlet(name = "QuestionServlet", value = "/question")
public class QuestionServlet extends HttpServlet {

    private QuestionService questionService;
    private QuestRepository questRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        initializeRepositories();
        initializeQuestionService();
    }

    private void initializeRepositories() {
        ServletContext context = getServletContext();
        if (context.getAttribute("questionRepository") == null) {
            questRepository = new QuestRepository();
            questionRepository = new QuestionRepository();
            answerRepository = new AnswerRepository();
            context.setAttribute("questRepository", questRepository);
            context.setAttribute("questionRepository", questionRepository);
            context.setAttribute("answerRepository", answerRepository);
        }
    }

    private void initializeQuestionService() {
        ServletContext context = getServletContext();
        questionRepository = (QuestionRepository) context.getAttribute("questionRepository");
        answerRepository = (AnswerRepository) context.getAttribute("answerRepository");
        questRepository = (QuestRepository) context.getAttribute("questRepository");
        questionService = new QuestionService(questRepository,questionRepository, answerRepository);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long answerId = req.getParameter("answerId") != null ? Long.parseLong(req.getParameter("answerId")) : null;
        Long questId = req.getParameter("questId") != null ? Long.parseLong(req.getParameter("questId")) : null;

        Optional<Question> questionOptional;

        if (questId != null) {
            questionOptional = questionService.getStartingQuestionForQuest(questId);
        } else {
            questionOptional = questionService.getNextQuestion(answerId);
        }

        if (questionOptional.isPresent()) {
            req.setAttribute("currentQuestion", questionOptional.get());

            List<Answer> filteredAnswers = questionService.getFilteredAnswers(questionOptional.get());
            req.setAttribute("filteredAnswers", filteredAnswers);

        } else {
            req.setAttribute("error", "Question not found");
            log.info("Question not found");
        }

        boolean showResults = false;
        if (questionOptional.isPresent()) {
            Question currentQuestion = questionOptional.get();
            showResults = questionService.shouldShowResults(currentQuestion);
        }
        req.setAttribute("showResults", showResults);

        RequestDispatcher dispatcher = req.getRequestDispatcher(WebPaths.WP_QUESTION);
        dispatcher.forward(req, resp);
    }

}