/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facades;

import com.entities.ShoppingCartProducts;
import com.entities.ShoppingCarts;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Abdallah Saboukh
 */
@Local
public interface ShoppingCartProductsFacadeLocal {

    void create(ShoppingCartProducts shoppingCartProducts);

    void edit(ShoppingCartProducts shoppingCartProducts);

    void remove(ShoppingCartProducts shoppingCartProducts);

    ShoppingCartProducts find(Object id);

    List<ShoppingCartProducts> findAll();

    List<ShoppingCartProducts> findRange(int[] range);

    int count();
    
    public List<ShoppingCartProducts> findShoppingCartProducts(ShoppingCarts shoppingCart) throws Exception;
    
}
