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

        // Answer NLO
        map.put(1L, new Answer(1L, 1L, "Принять вызов", 2L));
        map.put(2L, new Answer(2L, 1L, "Отклонить вызов", 5L));
        map.put(3L, new Answer(3L, 2L, "Подняться на мостик", 3L));
        map.put(4L, new Answer(4L, 2L, "Отказаться подниматься на мостик", 6L));
        map.put(5L, new Answer(5L, 3L, "Расказать правду о себе", 4L));
        map.put(6L, new Answer(6L, 3L, "Солгать о себе", 7L));
        // QAnswer land travel
        map.put(7L, new Answer(7L, 8L, "Видишь бескрайние леса.", 10L));
        map.put(8L, new Answer(8L, 8L, "Видишь величественные ледники.", 9L));
        map.put(9L, new Answer(9L, 9L, "На горизонте виднеются горы.", 8L));
        map.put(10L, new Answer(10L, 9L, "На горизонте виднеется озеро.", 10L));
        map.put(11L, new Answer(11L, 10L, "Вокруг тебя дельфины.", 12L));
        map.put(12L, new Answer(12L, 10L, "Вокруг тебя медузы.", 11L));
        map.put(13L, new Answer(13L, 11L, "Слышишь шум волн.", 9L));
        map.put(14L, new Answer(14L, 11L, "Слышишь пение птиц.", 13L));
        map.put(15L, new Answer(15L, 12L, "Находишь звезду морскую.", 14L));
        map.put(16L, new Answer(16L, 12L, "Находишь жемчужину.", 11L));
        map.put(16L, new Answer(16L, 13L, "Ориентируешься по солнцу.", 8L));
        map.put(16L, new Answer(16L, 13L, "Ориентируешься по звездам.", 12L));
        // Quest car mystery
        map.put(17L, new Answer(17L, 15L, "Toyota Corolla.", 16L));
        map.put(18L, new Answer(18L, 15L, "Volkswagen Golf.", 20L));
        map.put(19L, new Answer(19L, 16L, "США.", 20L));
        map.put(20L, new Answer(20L, 16L, "Китай.", 17L));
        map.put(21L, new Answer(21L, 17L, "Ferrari.", 18L));
        map.put(22L, new Answer(22L, 17L, "Lamborghini.", 20L));
        map.put(23L, new Answer(23L, 18L, "Rolls-Royce.", 10L));
        map.put(24L, new Answer(24L, 18L, "Bugatti.", 19L));
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
