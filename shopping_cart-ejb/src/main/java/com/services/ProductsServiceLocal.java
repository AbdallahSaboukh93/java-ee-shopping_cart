/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.Products;
import com.entities.ShoppingCartProducts;
import com.entities.ShoppingCarts;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Abdallah Saboukh
 */
@Local
public interface ProductsServiceLocal {
     public  List<Products> findAllProducts();
     
     public void editProduct(Products product)throws Exception ;
     
     public List<ShoppingCartProducts> findShoppingCartProducts(ShoppingCarts shoppingCart)throws Exception ;
}
