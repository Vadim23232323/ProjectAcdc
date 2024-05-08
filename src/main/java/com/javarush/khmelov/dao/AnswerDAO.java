package com.javarush.khmelov.dao;

import com.javarush.khmelov.entity.Answer;
import com.javarush.khmelov.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.*;


public class AnswerDAO implements DAO<Answer> {

    @Override
    public Collection<Answer> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Answer", Answer.class).list();
        }
    }

    @Override
    public Optional<Answer> get(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Answer.class, id));
        }
    }

    @Override
    public void create(Answer entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
    }

    @Override
    public void update(Answer entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }
    }

    @Override
    public void delete(Answer entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }
    }

    public List<Answer> getByQuestionId(Long questionId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Answer WHERE question.id = :questionId";
            Query<Answer> query = session.createQuery(hql, Answer.class);
            query.setParameter("questionId", questionId);
            return query.list();
        }
    }
}