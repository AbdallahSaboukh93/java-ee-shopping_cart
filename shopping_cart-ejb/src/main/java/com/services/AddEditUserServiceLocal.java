/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.Users;
import javax.ejb.Local;

/**
 *
 * @author Abdallah Saboukh
 */
@Local
public interface AddEditUserServiceLocal {
    public Users addEditUser(Users user);
}
