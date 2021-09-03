/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mainpages;

import com.entities.Users;
import com.services.AddEditUserServiceLocal;
import com.services.UserGUIServiceLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Abdallah Saboukh
 */
@Named(value = "loginBB")
@ViewScoped
public class LoginBB implements Serializable {

    @EJB
    private AddEditUserServiceLocal addEditUserService;
    @EJB
    private UserGUIServiceLocal userGUIService;

    private Users user = new Users();
    private Users newUser;

    String stringMessage = "";

    @PostConstruct
    public void init() {
        newUser = new Users();
    }

    public String getPageTitle() {
        return "";
    }

    public void reset() {
        try {
        } catch (Exception exception) {
        }
    }

    public String login() {
        user = userGUIService.findUserByNameAndPassword(user);
        if (user != null && user.getId() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
            return "mainPage";
        } else {
            stringMessage = "User Name Or Password Is Wrong ";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, stringMessage, stringMessage);
            FacesContext.getCurrentInstance().addMessage("status", message);

        }
        return "";
    }

    public void save() {
        Users tempUser = userGUIService.findUserByName(newUser);
        if (tempUser != null && tempUser.getId() != null) {
            stringMessage = "This User Name Is Used Before Please Change User Name";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, stringMessage, stringMessage);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, stringMessage, stringMessage);
            addEditUserService.addEditUser(newUser);
            stringMessage = "User Added  Successfully";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, stringMessage, stringMessage);
        FacesContext.getCurrentInstance().addMessage("status", message);
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getNewUser() {
        return newUser;
    }

    public void setNewUser(Users newUser) {
        this.newUser = newUser;
    }

    public String getStringMessage() {
        return stringMessage;
    }

    public void setStringMessage(String stringMessage) {
        this.stringMessage = stringMessage;
    }

}
