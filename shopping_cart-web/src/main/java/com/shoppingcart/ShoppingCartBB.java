/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shoppingcart;

import com.entities.Products;
import com.entities.ShoppingCartProducts;
import com.entities.ShoppingCarts;
import com.entities.Users;
import com.facades.ShoppingCartProductsFacadeLocal;
import com.services.ProductsServiceLocal;
import com.services.ShippingCartServiceLocal;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Abdallah Saboukh
 */
@Named(value = "shoppingCartBB")
@ViewScoped
public class ShoppingCartBB implements Serializable {

    @EJB
    private ProductsServiceLocal productsService;
    @EJB
    private ShippingCartServiceLocal shippingCartService;
    @EJB
    private ShoppingCartProductsFacadeLocal shoppingCartProductsFacade;

    private ShoppingCartProducts selectedShoppingCartProducts = new ShoppingCartProducts();
    private ShoppingCarts shoppingCart = new ShoppingCarts();
    private Users currnetUser;
    private ShoppingCartProducts shoppingCartProducts = new ShoppingCartProducts();

    private List<ShoppingCartProducts> shoppingCartProductsList = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            currnetUser = (Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            shoppingCart = shippingCartService.findShoppingCartByUser(currnetUser);
            shoppingCartProductsList = shoppingCartProductsFacade.findShoppingCartProducts(shoppingCart);

        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
        }
    }

    public void deleteProductFromShoppingCart() {
        try {
         
            deleteShoppingCartProducts(selectedShoppingCartProducts);
            editProductCount(selectedShoppingCartProducts);
        } catch (Exception e) {
        }
    }

    public String back() {
        return "mainPage";
    }

    public void confirmBuying() {
        try {
            if (shoppingCartProductsList != null && !shoppingCartProductsList.isEmpty()) {
                for (ShoppingCartProducts shoppingCartProducts : shoppingCartProductsList) {
                    shoppingCartProducts.setBuy("1");
                    shippingCartService.editShoppingCartProducts(shoppingCartProducts);
                }
            }
        } catch (Exception e) {
        }
    }

    public StreamedContent getImage(Products products) {
        InputStream is = new ByteArrayInputStream((byte[]) products.getImage());
        StreamedContent myImage = new DefaultStreamedContent(is, "image/png");
        return myImage;
    }

    private void deleteShoppingCartProducts(ShoppingCartProducts selectedShoppingCartProducts) {
        try {
            shippingCartService.deleteShoppingCartProducts(selectedShoppingCartProducts);
            shoppingCartProductsList.remove(selectedShoppingCartProducts);
        } catch (Exception e) {
        }
    }

    private void editProductCount(ShoppingCartProducts selectedShoppingCartProducts) {
        try {
            String stringMessage = "";
            selectedShoppingCartProducts.getProductId().setPCount(selectedShoppingCartProducts.getProductId().getPCount() + 1);
            productsService.editProduct(selectedShoppingCartProducts.getProductId());
            stringMessage = "Product Removed From Shopping Cart";

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, stringMessage, stringMessage);
            FacesContext.getCurrentInstance().addMessage("status", message);
        } catch (Exception e) {
        }
    }

    public ShoppingCartProducts getSelectedShoppingCartProducts() {
        return selectedShoppingCartProducts;
    }

    public void setSelectedShoppingCartProducts(ShoppingCartProducts selectedShoppingCartProducts) {
        this.selectedShoppingCartProducts = selectedShoppingCartProducts;
    }

    public ShoppingCarts getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCarts shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Users getCurrnetUser() {
        return currnetUser;
    }

    public void setCurrnetUser(Users currnetUser) {
        this.currnetUser = currnetUser;
    }

    public List<ShoppingCartProducts> getShoppingCartProductsList() {
        return shoppingCartProductsList;
    }

    public void setShoppingCartProductsList(List<ShoppingCartProducts> shoppingCartProductsList) {
        this.shoppingCartProductsList = shoppingCartProductsList;
    }

}
