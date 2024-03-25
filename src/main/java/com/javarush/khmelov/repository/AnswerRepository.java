package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Answer;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class AnswerRepository implements Repository<Answer>{


    private final Map<Long,Answer> map = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public AnswerRepository() {
        map.put(1L, new Answer(1L, 1L, "Принять вызов", 2L));
        map.put(2L, new Answer(2L, 1L, "Отклонить вызов", 1L));
        map.put(3L, new Answer(3L, 2L, "Подняться на мостик", 3L));
        map.put(4L, new Answer(4L, 2L, "Отказаться подниматься на мостик", 1L));
        map.put(5L, new Answer(5L, 3L, "Расказать правду о себе", 4L));
        map.put(6L, new Answer(6L, 3L, "Солгать о себе", 1L));

    }

    @Override
    public Collection<Answer> getAll() {
        return map.values();
    }


    @Override
    public Optional<Answer> get(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void create(Answer entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(Answer entity) {
        map.put(entity.getId(), entity);
    }

    @Override
    public void delete(Answer entity) {
        map.remove(entity.getId());
    }
}
