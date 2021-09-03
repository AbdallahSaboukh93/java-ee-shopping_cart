/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facades;

import com.entities.ShoppingCarts;
import com.entities.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Abdallah Saboukh
 */
@Local
public interface ShoppingCartsFacadeLocal {

    void create(ShoppingCarts shoppingCarts);

    void edit(ShoppingCarts shoppingCarts);

    void remove(ShoppingCarts shoppingCarts);

    ShoppingCarts find(Object id);

    List<ShoppingCarts> findAll();

    List<ShoppingCarts> findRange(int[] range);

    int count();
    
public ShoppingCarts findShoppingCartByUser(Users user) throws Exception;    
}
