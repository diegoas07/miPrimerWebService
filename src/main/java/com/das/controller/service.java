/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.das.controller;

import com.das.model.User;
import com.das.persistenceImp.UserImp;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author diego
 */
@WebService(serviceName = "service")
public class service {

    private final UserImp userimp = new UserImp();
    
    /**
     * This is a sample web service operation
     * @param cc
     * @return 
     */
    @WebMethod(operationName = "find")
    public ArrayList<String> find(@WebParam(name = "name") String cc) {
        try {
            ArrayList<String> list = new ArrayList<>();
            User user = new User();
            user.setCedula(cc);
            user = userimp.findUser(user);
            list.add(user.getNombre());
            list.add(user.getApellido());
            list.add(user.getCedula());
            list.add(user.getId());
            return list;
            } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @WebMethod(operationName = "insert")
    public boolean  insert(@WebParam(name = "nombre") String nombre ,@WebParam(name = "apellido") String apellido, @WebParam(name = "cedula") String cedula)  {
        try {
            User user = new User();
            user.setNombre(nombre);
            user.setApellido(apellido);
            user.setCedula(cedula);
            return userimp.insertUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "UpdateUser")
    public boolean UpdateUser(@WebParam(name = "nombre") String nombre ,@WebParam(name = "apellido") String apellido, @WebParam(name = "cedula") String cedula) {
        try {
            User user = new User();
            user.setNombre(nombre);
            user.setApellido(apellido);
            user.setCedula(cedula);
            return userimp.updateUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @WebMethod(operationName = "delete")
    public boolean delete ( @WebParam(name ="cedula") String cc ){
        try {
            User user = new User();
            user.setCedula(cc);
            return userimp.deleteUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
