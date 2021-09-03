/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.ShoppingCartProducts;
import com.entities.ShoppingCarts;
import com.entities.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Abdallah Saboukh
 */
@Local
public interface ShippingCartServiceLocal {

    public ShoppingCarts findShoppingCartByUser(Users user) throws Exception;

    public void addShippingCart(ShoppingCarts shoppingCart) throws Exception;

    public void createShoppingCartProducts(ShoppingCartProducts shoppingCartProducts) throws Exception;

    public void deleteShoppingCartProducts(ShoppingCartProducts shoppingCartProducts) throws Exception;

    public void editShoppingCartProducts(ShoppingCartProducts shoppingCartProducts) throws Exception;

    public List<ShoppingCartProducts> findShoppingCartProducts(ShoppingCarts shoppingCart) throws Exception;
    
    public List<ShoppingCartProducts> findShoppingCartProductsByShoppingCart(ShoppingCarts shoppingCart ) throws Exception ;
}
