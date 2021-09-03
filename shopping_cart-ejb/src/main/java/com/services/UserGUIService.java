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
public class UserGUIService implements UserGUIServiceLocal {

    @EJB
    private UsersFacadeLocal usersFacade;
 
    @Override
    public Users findUserByNameAndPassword(Users user) {
        return usersFacade.findUserByNameAndPassword(user);
    }

    @Override
    public Users findUserByName(Users user) {
        return usersFacade.findUserByName(user);
    }

}
