/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.Products;
import com.entities.ShoppingCartProducts;
import com.entities.ShoppingCarts;
import com.facades.ProductsFacadeLocal;
import com.facades.ShoppingCartProductsFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Abdallah Saboukh
 */
@Stateless
public class ProductsService implements ProductsServiceLocal {

    @EJB
    private ProductsFacadeLocal productsFacade;
    @EJB
    private ShoppingCartProductsFacadeLocal shoppingCartProductsFacade;

    @Override
    public List<Products> findAllProducts() {
        return productsFacade.findAll();
    }

    @Override
    public void editProduct(Products product) throws Exception {
        productsFacade.edit(product);
    }

    @Override
    public List<ShoppingCartProducts> findShoppingCartProducts(ShoppingCarts shoppingCart) throws Exception {
      return shoppingCartProductsFacade.findShoppingCartProducts(shoppingCart);
    }
}
