package com.zashykhina.springBoot.dao;

import com.zashykhina.springBoot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{
    private final EntityManagerFactory entityManager;
    private EntityManager em = null;
    private EntityTransaction transaction = null;

    @Autowired
    public UserDaoImp(EntityManagerFactory entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> showAllUsers() {
        em = entityManager.createEntityManager();
        transaction = em.getTransaction();
        transaction.begin();
        List <User> userList = em.createQuery("select u from User u").getResultList();
        return userList;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        em = entityManager.createEntityManager();
        transaction = em.getTransaction();
        transaction.begin();;
        User userById = em.getReference(User.class, id);
        return userById;
    }

    @Override
    @Transactional
    public void delete(int id) {
        em = entityManager.createEntityManager();
        transaction = em.getTransaction();
        transaction.begin();
        User deletedUser = em.find(User.class, id);
        em.remove(deletedUser);
        transaction.commit();
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        em = entityManager.createEntityManager();
        transaction = em.getTransaction();
        transaction.begin();
        User userUpdate = em.find(User.class, id);
        userUpdate.setName(user.getName());
        userUpdate.setSurname(user.getSurname());
        userUpdate.setEmail(user.getEmail());
        em.merge(userUpdate);
        transaction.commit();
    }

    @Override
    @Transactional
    public void save(User user) {
        em = entityManager.createEntityManager();
        transaction = em.getTransaction();
        transaction.begin();
        em.persist(user);
        transaction.commit();
    }
}
