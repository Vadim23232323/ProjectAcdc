package com.javarush.khmelov.repository;
import com.javarush.khmelov.entity.Answer;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class QuestionRepository implements Repository<Question>{

    private final Map<Long,Question> map = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public QuestionRepository() {
        map.put(1L, new Question(1L, 1L, "Потеря памяти", "ты потерял память. принять вызов НЛО",1));
        map.put(2L, new Question(2L, 1L, "Мостик", "Ты принял вызов, поднимаешься на мостик к капитану",2));
        map.put(3L, new Question(3L, 1L, "Ты кто", "поднялся на мостик. Ты кто",3));
        map.put(4L, new Question(4L, 1L, "Победа", "тебя вернули домой",3));
    }

    @Override
    public Collection<Question> getAll() {
        return map.values();
    }


    @Override
    public Optional<Question> get(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void create(Question entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(Question entity) {
        map.put(entity.getId(), entity);
    }

    @Override
    public void delete(Question entity) {
        map.remove(entity.getId());
    }
}
