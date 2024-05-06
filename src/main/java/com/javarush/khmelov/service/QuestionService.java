package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Answer;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.AnswerRepository;
import com.javarush.khmelov.repository.QuestRepository;
import com.javarush.khmelov.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class QuestionService {

    private final QuestRepository questRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionService(QuestRepository questRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questRepository = questRepository;
    }

    public Optional<Question> getNextQuestion(Long answerId) {
        if (answerId == null) {
            return questionRepository.get(1);
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

        Optional<Quest> questOptional;
        questOptional = questRepository.get(questId);

        log.info("User started quest: " + questOptional.get().getName());

        if (questOptional.isPresent()) {
            Long startQuestionId = questOptional.get().getStartQuestionId();
            return questionRepository.get(startQuestionId);
        }
        return Optional.empty();
    }

    public List<Answer> getFilteredAnswers(Question question) {
        Collection<Answer> answers = answerRepository.getAll();
        List<Answer> filteredAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            if (Objects.equals(answer.getQuestionId(), question.getId())) {
                filteredAnswers.add(answer);
            }
        }
        return filteredAnswers;
    }

    public boolean shouldShowResults(Question question) {
        return question.isWin() || question.isWasted();
    }
}

