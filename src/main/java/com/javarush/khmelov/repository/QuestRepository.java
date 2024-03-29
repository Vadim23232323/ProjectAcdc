package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Quest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class QuestRepository implements Repository<Quest> {

    private final Map<Long, Quest> map = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public QuestRepository() {

        // Quest NLO
        map.put(1l, new Quest(1L, "НЛО", 1L, "Quest_1.jpg"));
        // Quest land travel
        map.put(2l, new Quest(2L, "Путешествие по Земле", 8L, "Quest_2.jpg"));
        // Quest car mystery
        map.put(3l, new Quest(3L, "Загадки автомобилей", 15L, "Quest_3.jpg"));

    }

    @Override
    public Collection<Quest> getAll() {
        return map.values();
    }


    @Override
    public Optional<Quest> get(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void create(Quest entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(Quest entity) {
        map.put(entity.getId(), entity);
    }

    @Override
    public void delete(Quest entity) {
        map.remove(entity.getId());
    }


}
