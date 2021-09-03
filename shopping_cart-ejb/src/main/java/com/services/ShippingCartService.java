/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.ShoppingCartProducts;
import com.entities.ShoppingCarts;
import com.entities.Users;
import com.facades.ShoppingCartProductsFacadeLocal;
import com.facades.ShoppingCartsFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Abdallah Saboukh
 */
@Stateless
public class ShippingCartService implements ShippingCartServiceLocal {

    @EJB
    private ShoppingCartsFacadeLocal shoppingCartFacade;
    @EJB
    private ShoppingCartProductsFacadeLocal shoppingCartProductsFacade;

    @Override
    public ShoppingCarts findShoppingCartByUser(Users user) throws Exception {
        return shoppingCartFacade.findShoppingCartByUser(user);
    }

    @Override
    public void addShippingCart(ShoppingCarts shoppingCart) throws Exception {
        shoppingCartFacade.create(shoppingCart);
    }

    @Override
    public void createShoppingCartProducts(ShoppingCartProducts shoppingCartProducts) throws Exception {
        shoppingCartProductsFacade.create(shoppingCartProducts);
    }

    @Override
    public void deleteShoppingCartProducts(ShoppingCartProducts shoppingCartProducts) throws Exception {
        shoppingCartProductsFacade.remove(shoppingCartProducts);
    }

    @Override
    public void editShoppingCartProducts(ShoppingCartProducts shoppingCartProducts) throws Exception {
        shoppingCartProductsFacade.edit(shoppingCartProducts);
    }

    @Override
    public List<ShoppingCartProducts> findShoppingCartProducts(ShoppingCarts shoppingCart) throws Exception {
      return shoppingCartProductsFacade.findShoppingCartProducts(shoppingCart);
    }

    
    @Override
    public List<ShoppingCartProducts> findShoppingCartProductsByShoppingCart(ShoppingCarts shoppingCart ) throws Exception{
    return new ArrayList<>();
    }
}
