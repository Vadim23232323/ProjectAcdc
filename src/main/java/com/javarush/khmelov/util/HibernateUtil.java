package com.javarush.khmelov.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .addAnnotatedClass(com.javarush.khmelov.entity.Question.class)
                    .addAnnotatedClass(com.javarush.khmelov.entity.Answer.class)
                    .addAnnotatedClass(com.javarush.khmelov.entity.Quest.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Инициализация SessionFactory не удалась: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}