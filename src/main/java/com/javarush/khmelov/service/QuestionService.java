package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Answer;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.AnswerRepository;
import com.javarush.khmelov.repository.QuestRepository;
import com.javarush.khmelov.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
@Slf4j
public class QuestionService {

    private QuestRepository questRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public QuestionService(QuestRepository questRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questRepository = questRepository;
    }

    public Optional<Question> getNextQuestion(Long answerId) {
        if (answerId == null) {
            return questionRepository.get(1); // Заменить на реальный идентификатор стартового вопроса
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

        log.info("Пользователь запустил квест: " + questOptional.get().getName());

        if (questOptional.isPresent()) {
            Long startQuestionId = questOptional.get().getStartQuestionId();
            return questionRepository.get(startQuestionId);
        }
        return Optional.empty();
    }
}
