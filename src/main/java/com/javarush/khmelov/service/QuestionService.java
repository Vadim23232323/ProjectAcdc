package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Answer;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.AnswerRepository;
import com.javarush.khmelov.repository.QuestionRepository;

import java.util.Optional;

public class QuestionService {

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public Optional<Question> getNextQuestion(Long answerId) {
        if (answerId == null) {
            // Логика выбора стартового вопроса
            return questionRepository.get(1L); // Заменить на реальный идентификатор стартового вопроса
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
}
