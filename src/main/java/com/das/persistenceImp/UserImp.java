/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.das.persistenceImp;

import com.das.model.User;
import com.das.persistence.Persistence;
import com.das.persistenceInt.UserInt;
import java.sql.ResultSet;

/**
 *
 * @author diego
 */
public class UserImp  implements UserInt{
    
    Persistence persistencia;
    
    @Override
    public boolean insertUser(User user) throws Exception {
        String sql = "insert into users (nombre, apellido, cedula) values ('"+user.getNombre()+"','"+user.getApellido()+"','"+user.getCedula()+"')";
        persistencia = new Persistence();
        persistencia.conectar();
        return persistencia.escribir(sql);
    }

    @Override
    public User findUser(User user) throws Exception {
        String sql = "select nombre, apellido, cedula, id from users where cedula="+user.getCedula();
        System.err.println("select: "+sql);
        persistencia = new Persistence();
        persistencia.conectar();
        ResultSet result = persistencia.consultar(sql);
        while(result.next()){
            user.setApellido(result.getString(2));
            user.setNombre(result.getString(1));
            user.setCedula(result.getString(3));
            user.setNombre(result.getString(4));
        }
        return user;
    }

    @Override
    public boolean deleteUser(User user) throws Exception {
        String sql = "delete from users where cedula = "+user.getCedula();
        persistencia = new Persistence();
        persistencia.conectar();
        return persistencia.escribir(sql);

    }

    @Override
    public boolean updateUser(User user) throws Exception {
        String sql = "update users set nombre ='"+user.getNombre()+"' , apellido ='"+user.getApellido()+"'  where cedula = '"+user.getCedula()+"'";
        persistencia = new Persistence();
        persistencia.conectar();
        return persistencia.escribir(sql);
    }
    
}
