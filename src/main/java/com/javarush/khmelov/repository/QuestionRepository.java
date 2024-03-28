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

        // Question NLO
        map.put(1L, new Question(1L, 1L,"ты потерял память. принять вызов НЛО?",1,false,false));
        map.put(2L, new Question(2L, 1L,"Ты принял вызов, поднимаешься на мостик к капитану?",2,false,false));
        map.put(3L, new Question(3L, 1L,"поднялся на мостик. Ты кто?",3,false,false));
        map.put(4L, new Question(4L, 1L, "Победа. Тебя вернули домой.",4,true,false));
        map.put(5L, new Question(5L, 1L, "Ты отклонил вызов.Поражение.",1,false,true));
        map.put(6L, new Question(6L, 1L, "Ты не пошел на переговоры.Поражение.",2,false,true));
        map.put(7L, new Question(7L, 1L, "Твою ложь разоблачили.Поражение.",3,false,true));
        // Question land travel
        map.put(8L, new Question(8L, 2L,"Ты стоишь на вершине горы",1,false,false));
        map.put(9L, new Question(9L, 2L,"Ты на поляне с цветами",1,false,false));
        map.put(10L, new Question(10L, 2L,"Ты плаваешь в океане",1,false,false));
        map.put(11L, new Question(11L, 2L,"Ты на берегу моря",1,false,false));
        map.put(12L, new Question(12L, 2L,"Ты на пляже с ракушками",1,false,false));
        map.put(13L, new Question(13L, 2L,"Ты в густом лесу",1,false,false));
        map.put(14L, new Question(14L, 2L,"Поздравляю! Ты завершил свое путешествие по Земле!",1,true,false));
        // Quest car mystery
        map.put(15L, new Question(15L, 3L,"Какой автомобиль считается самым продаваемым в мире?",1,false,false));
        map.put(16L, new Question(16L, 3L,"Какая страна производит больше всего автомобилей?",2,false,false));
        map.put(17L, new Question(17L, 3L,"Какой автомобиль является символом итальянского автомобилестроения?",3,false,false));
        map.put(18L, new Question(18L, 3L,"Как называется самая дорогая марка автомобилей в мире?",4,false,false));
        map.put(19L, new Question(19L, 3L,"Поздравляю! ты знаешь все об автомобилях.",5,true,false));
        map.put(20L, new Question(20L, 3L,"Вы проиграли! попробуйте еще раз.",1,false,true));
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
