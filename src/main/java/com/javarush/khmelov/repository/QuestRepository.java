package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.User;

import java.util.Collection;
import java.util.Optional;

public class QuestRepository implements Repository<Quest>{
    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public Optional<Quest> get(long id) {
        return Optional.empty();
    }

    @Override
    public void create(Quest entity) {

    }

    @Override
    public void update(Quest entity) {

    }

    @Override
    public void delete(Quest entity) {

    }
}
