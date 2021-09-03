/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;


import com.entities.Users;
import com.facades.UsersFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Abdallah Saboukh
 */
@Stateless
public class AddEditUserService implements AddEditUserServiceLocal {

    @EJB
    private UsersFacadeLocal usersFacade;

    @Override
    public Users addEditUser(Users user) {
        usersFacade.create(user);
        return user;
    }
}
