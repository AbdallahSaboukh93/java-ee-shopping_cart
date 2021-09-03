/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mainpages;

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
@Named(value = "mainPageBB")
@ViewScoped
public class MainPageBB implements Serializable {

    @EJB
    private ProductsServiceLocal productsService;
    @EJB
    private ShippingCartServiceLocal shippingCartService;
    @EJB
    private ShoppingCartProductsFacadeLocal shoppingCartProductsFacade;

    private List<Products> productsList = new ArrayList<>();
    private Products selectedProduct = new Products();
    private ShoppingCarts shoppingCart = new ShoppingCarts();
    private Users currnetUser;
    private ShoppingCartProducts shoppingCartProducts = new ShoppingCartProducts();

    private List<ShoppingCartProducts> shoppingCartProductsList = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            currnetUser = (Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            shoppingCart = shippingCartService.findShoppingCartByUser(currnetUser);
            productsList = productsService.findAllProducts();
            if (shoppingCart.getId() != null) {
                shoppingCartProductsList = shoppingCartProductsFacade.findShoppingCartProducts(shoppingCart);
            }
        } catch (Exception e) {
        }
    }

    public void addProductInShoppingCart() {
        try {
            if (shoppingCart.getId() == null) {
                shoppingCart.setUserId(currnetUser);
                shippingCartService.addShippingCart(shoppingCart);
            }
            createShoppingCartProducts(selectedProduct);
            editProductCount(selectedProduct);
        } catch (Exception e) {
        }
    }

    public StreamedContent getImage(Products products) {
        InputStream is = new ByteArrayInputStream((byte[]) products.getImage());
        StreamedContent myImage = new DefaultStreamedContent(is, "image/png");
        return myImage;
    }

    public String shoppingCart() {
        return "shoppingCart";
    }

    private void createShoppingCartProducts(Products product) {
        try {
            shoppingCartProducts=new ShoppingCartProducts();
            shoppingCartProducts.setShoppingCartId(shoppingCart);
            shoppingCartProducts.setProductId(product);
            shoppingCartProducts.setBuy("0");
            shippingCartService.createShoppingCartProducts(shoppingCartProducts);
            shoppingCartProductsList.add(shoppingCartProducts);
        } catch (Exception e) {
        }
    }

    private void editProductCount(Products product) {
        try {
            String stringMessage = "";
            if (product.getPCount() != 0) {
                product.setPCount(product.getPCount() - 1);
                productsService.editProduct(product);
                stringMessage = "Product Added To Shopping Cart";
            } else {
                stringMessage = "No Product Yet";
            }
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, stringMessage, stringMessage);
            FacesContext.getCurrentInstance().addMessage("status", message);
        } catch (Exception e) {
        }
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
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

    public Products getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Products selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<ShoppingCartProducts> getShoppingCartProductsList() {
        return shoppingCartProductsList;
    }

    public void setShoppingCartProductsList(List<ShoppingCartProducts> shoppingCartProductsList) {
        this.shoppingCartProductsList = shoppingCartProductsList;
    }

}
