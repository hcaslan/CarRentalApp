package org.bilgeadam.repository;

import jakarta.persistence.TypedQuery;
import org.bilgeadam.entity.Car;
import org.bilgeadam.entity.User;

import java.util.List;

public class UserRepository extends RepositoryManager<User,Long> {
    public UserRepository() {
        super(User.class);
    }
    public List<User> searchUserByKeyword(String keyword) {
        String jpql = "SELECT u FROM User u WHERE u.email LIKE :keyword OR u.lastname LIKE :keyword OR u.name LIKE :keyword";
        TypedQuery<User> query = getEntityManager().createQuery(jpql, User.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }
}
