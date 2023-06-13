package com.cursoceat.instituto.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection con;
    private String url;
    private String user;
    private String password;

    public Connection conectar(){
        //insertar en la url el utf-8 para que reconozco los caracteres especiales
        url="jdbc:mysql://localhost:3306/instituto?useUnicode=true&characterEncoding=utf-8";

        user="root";
        password="";
        try{
            Class.forName( "com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,user,password);
            System.out.println("Éxito");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return con;
    }

}
