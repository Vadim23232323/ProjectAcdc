package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class QuestRepository implements Repository<Quest>{

    private final Map<Long, Quest> map = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public QuestRepository() {
        map.put(1l, new Quest(1L,"Потеря памяти", "ты потерял память. принять вызов НЛО",1));
        map.put(1l, new Quest(2L,"Мостик", "Принял вызов, поднимаешься на мостик к капитану",2));
        map.put(1l, new Quest(3L,"Ты кто", "поднялся на мостик. Ты кто",3));
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
