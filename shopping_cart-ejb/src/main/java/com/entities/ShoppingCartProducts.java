/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abdallah Saboukh
 */
@Entity
@Table(name = "shopping_cart_products")
@XmlRootElement

public class ShoppingCartProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator(name = "shopping_cart_products_id", allocationSize = 1)
    @GeneratedValue(generator = "shopping_cart_products_id", strategy = GenerationType.TABLE)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "BUY")
    private String buy;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Products productId;
    @JoinColumn(name = "SHOPPING_CART_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ShoppingCarts shoppingCartId;

    public ShoppingCartProducts() {
    }

    public ShoppingCartProducts(Integer id) {
        this.id = id;
    }

    public ShoppingCartProducts(Integer id, String buy) {
        this.id = id;
        this.buy = buy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public ShoppingCarts getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(ShoppingCarts shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShoppingCartProducts)) {
            return false;
        }
        ShoppingCartProducts other = (ShoppingCartProducts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.ShoppingCartProducts[ id=" + id + " ]";
    }

}
