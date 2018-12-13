/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.das.persistence;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author diego
 */
public class Persistence {
    	public static String nombreClase = "Persistencia";	
        private String host;
        private String bd;
        private String name;
        private String pw;
        private String port;
	public Persistence() throws Exception{
            Properties properties = new Properties();
            InputStream in = null;
            in = new FileInputStream("resources/configuracion.properties");
            properties.load(in);    
            host = properties.getProperty("server.name");
            bd= properties.getProperty("server.bd");
            name= properties.getProperty("server.user");
            pw= properties.getProperty("server.pw");
            port = properties.getProperty("server.port");
	}
	private Connection conexion;
        
        	/**
	 * Constructor de la clase de persistencia.
     * @return 
	 * @throws Exception 
	 * Si la excepcion es de tipo 1: es con conexion nula  
	 * si la excepcion es de tipo 2: no se pudo generar ninguna conexion.
	 */	
	public Persistence conectar() throws Exception {
	try{
	        Class.forName("com.mysql.jdbc.Driver");
	        String BaseDeDatos = "jdbc:mysql://" + host + ":" + port + "/" + bd + "?useSSL=false"; 
	        setConexion(DriverManager.getConnection(BaseDeDatos, name,pw));            
	        getConexion().setAutoCommit(false);
	        if (getConexion() == null) {
	           Exception errorConexion1 = new Exception("conexion con base de datos 1");
	           throw errorConexion1;
	        }
	    }catch(Exception e){
	    	e.printStackTrace();

	    	throw new Exception("conexion con base de datos 2 "+e);
	    }
		
	    return this;
	}

        
        	/**
	 * Ejecuta una sentencia SQL para insertar, actualizar o eliminar.
	 * @param sql Cadena de sentencia para insertar, actualizar o eliminar.
	 * @return true si se logró ejecutar la sentencia D.L.C. false.
	 * @throws SQLException si existe un error en la sintaxis de la sentencia.
	 */
	public boolean escribir(String sql) throws SQLException{
		try {
            Statement sentencia;
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sentencia.executeUpdate(sql);
            getConexion().commit();
            sentencia.close();          
        } catch (SQLException e) {
        	e.printStackTrace();
        	getConexion().rollback();
            return false;
        }        
        return true;
    }
        
        
        
        
        	/**
	 * Ejecuta una sentencia para consultar datos.
	 * @param sql sentencia para consultar.
	 * @return ResultSet conjunto de datos.
	 */
	public ResultSet consultar(String sql) throws Exception{
        ResultSet resultado = null;
        try {
            Statement sentencia;
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql);
            //sentencia.close();
        } catch (Exception e) {
        	throw new SQLException(e);
        }
        return resultado;

    }
        
        
    public void desconectar() {
        try {
            getConexion().close();
            setConexion(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the conexion
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * @param conexion the conexion to set
     */
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
        
        
}


