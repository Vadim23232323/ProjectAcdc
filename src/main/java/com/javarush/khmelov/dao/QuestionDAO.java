package com.javarush.khmelov.dao;

import com.javarush.khmelov.entity.Question;
import org.hibernate.Session;

import org.hibernate.Transaction;


import java.util.Collection;
import java.util.Optional;

import com.javarush.khmelov.util.HibernateUtil;



public class QuestionDAO implements DAO<Question> {

    @Override
    public Collection<Question> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Question", Question.class).list();
        }
    }

    @Override
    public Optional<Question> get(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Question.class, id));
        }
    }

    @Override
    public void create(Question entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
    }

    @Override
    public void update(Question entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }
    }

    @Override
    public void delete(Question entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }
    }
}