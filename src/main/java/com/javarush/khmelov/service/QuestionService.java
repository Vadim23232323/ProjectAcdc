package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Answer;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.dao.AnswerDAO;
import com.javarush.khmelov.dao.QuestDAO;
import com.javarush.khmelov.dao.QuestionDAO;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Slf4j
public class QuestionService {

    private final QuestDAO questRepository;
    private final QuestionDAO questionRepository;
    private final AnswerDAO answerRepository;

    public QuestionService(QuestDAO questRepository, QuestionDAO questionRepository, AnswerDAO answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questRepository = questRepository;
    }

    public Optional<Question> getNextQuestion(Long answerId) {
        if (answerId == null) {
            return questionRepository.get(1L);
        }

        Optional<Answer> answerOptional = answerRepository.get(answerId);
        if (answerOptional.isPresent()) {
            Long nextQuestionId = answerOptional.get().getNextQuestionId();
            if (nextQuestionId != null) {
                return questionRepository.get(nextQuestionId);
            }
        }
        return Optional.empty();
    }

    public Optional<Question> getStartingQuestionForQuest(Long questId) {
        Optional<Quest> questOptional = questRepository.get(questId);

        if (questOptional.isPresent()) {
            Long startQuestionId = questOptional.get().getStartQuestionId();
            return questionRepository.get(startQuestionId);
        }
        return Optional.empty();
    }

    public List<Answer> getFilteredAnswers(Question question) {
        return answerRepository.getByQuestionId(question.getId());
    }

    public boolean shouldShowResults(Question question) {
        return question.isWin() || question.isWasted();
    }
}