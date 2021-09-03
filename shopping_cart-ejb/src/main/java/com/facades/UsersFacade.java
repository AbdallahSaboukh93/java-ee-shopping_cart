/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facades;

import com.entities.Users;
import com.entities.Users_;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Abdallah Saboukh
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "com_shopping_cart-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    CriteriaBuilder cb;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @PostConstruct
    protected void initCriteriaBuilder() {
        cb = getEntityManager().getCriteriaBuilder();
    }

    public UsersFacade() {
        super(Users.class);
    }

    @Override
    public Users findUserByNameAndPassword(Users user) {
        CriteriaQuery criteriaQuery = cb.createQuery();
        Root<Users> userRoot = criteriaQuery.from(Users.class);
        criteriaQuery.select(userRoot);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(userRoot.get(Users_.userName), user.getUserName()));
        predicates.add(cb.equal(userRoot.get(Users_.password), user.getPassword()));

        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery typedQuery = em.createQuery(criteriaQuery);
        if (typedQuery.getResultList() != null && !typedQuery.getResultList().isEmpty()) {
            return (Users) typedQuery.getResultList().stream().findFirst().get();
        }
        return new Users();
    }

    @Override
    public Users findUserByName(Users user) {
        CriteriaQuery criteriaQuery = cb.createQuery();
        Root<Users> userRoot = criteriaQuery.from(Users.class);
        criteriaQuery.select(userRoot);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(userRoot.get(Users_.userName), user.getUserName()));
        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery typedQuery = em.createQuery(criteriaQuery);
        if (typedQuery.getResultList() != null && !typedQuery.getResultList().isEmpty()) {
            return (Users) typedQuery.getResultList().stream().findFirst().get();
        }
        return new Users();
    }

}
