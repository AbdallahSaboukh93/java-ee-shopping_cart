/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facades;

import com.entities.ShoppingCarts;
import com.entities.ShoppingCartProducts;
import com.entities.ShoppingCartProducts_;
import com.entities.ShoppingCarts_;
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
public class ShoppingCartProductsFacade extends AbstractFacade<ShoppingCartProducts> implements ShoppingCartProductsFacadeLocal {

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

    public ShoppingCartProductsFacade() {
        super(ShoppingCartProducts.class);
    }

    @Override
    public List<ShoppingCartProducts> findShoppingCartProducts(ShoppingCarts shoppingCart) throws Exception {

        CriteriaQuery criteriaQuery = cb.createQuery();
        Root<ShoppingCartProducts> shoppingCartProductsRoot = criteriaQuery.from(ShoppingCartProducts.class);
        criteriaQuery.select(shoppingCartProductsRoot);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(shoppingCartProductsRoot.get(ShoppingCartProducts_.shoppingCartId).get(ShoppingCarts_.id), shoppingCart.getId()));
        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }

}
