package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository implements Repository<User> {

    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserRepository() {
         sessionFactory = new Configuration()
                 .addAnnotatedClass(com.javarush.khmelov.entity.User.class)
                 .addAnnotatedClass(com.javarush.khmelov.entity.Role.class)
                 .buildSessionFactory();
    }


    @Override
    public Collection<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public Optional<User> get(long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(User.class, id));
        }
    }

    @Override
    public void create(User entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
    }

    @Override
    public void update(User entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }
    }

    @Override
    public void delete(User entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }
    }

    public Optional<User> findByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User where login = :login", User.class)
                    .setParameter("login", login)
                    .uniqueResultOptional();
        }
    }

    public Role findRoleById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Role.class, id);
        }
    }
}
