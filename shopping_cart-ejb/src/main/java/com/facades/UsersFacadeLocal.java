/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facades;

import com.entities.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Abdallah Saboukh
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users);

    void edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    List<Users> findRange(int[] range);

    int count();

    public Users findUserByNameAndPassword(Users user);

    public Users findUserByName(Users user);

}
