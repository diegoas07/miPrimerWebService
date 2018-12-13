/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.das.controller;

import com.das.model.User;
import com.das.persistenceImp.UserImp;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author diego
 */
@WebService(serviceName = "service")
public class service {

    private UserImp userimp = new UserImp();
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "insert")
    public boolean  insert(@WebParam(name = "nombre") String nombre ,@WebParam(name = "apellido") String apellido, @WebParam(name = "cedula") String cedula) throws Exception {
        User user = new User();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setCedula(cedula);
        return userimp.insertUser(user);
        
    }
    
}
