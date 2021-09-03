/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facades;

import com.entities.ShoppingCarts;
import com.entities.ShoppingCarts_;
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
public class ShoppingCartsFacade extends AbstractFacade<ShoppingCarts> implements ShoppingCartsFacadeLocal {

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

    public ShoppingCartsFacade() {
        super(ShoppingCarts.class);
    }

    @Override
    public ShoppingCarts findShoppingCartByUser(Users user) throws Exception {
        CriteriaQuery criteriaQuery = cb.createQuery();
        Root<ShoppingCarts> shoppingCartsRoot = criteriaQuery.from(ShoppingCarts.class);
        criteriaQuery.select(shoppingCartsRoot);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(shoppingCartsRoot.get(ShoppingCarts_.userId).get(Users_.id), user.getId()));
        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery typedQuery = em.createQuery(criteriaQuery);
        if (typedQuery.getResultList() != null && !typedQuery.getResultList().isEmpty()) {
            return (ShoppingCarts) typedQuery.getResultList().stream().findFirst().get();
        }
        return new ShoppingCarts();
    }

}
