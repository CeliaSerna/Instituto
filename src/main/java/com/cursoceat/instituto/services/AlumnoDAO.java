package com.cursoceat.instituto.services;

import com.cursoceat.instituto.controller.*;

import com.cursoceat.instituto.modell.*;
import com.cursoceat.instituto.services.*;


import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.sql.SQLException;
import java.sql.ResultSet;
//la clase AlumnosDAO es una conversion donde se realizan una serie de metodos que van a
//ser utilizados para acceder a la BBDD (listar, buscar, borrar, insertar...etc)
//Como necesita acceder a la BBDD hereda de Conexion
public class AlumnoDAO <T>extends Conexion {
    String sql;

    public void create(Alumno a) {
        Connection con = conectar();
        sql = "INSERT INTO alumnos (nombre, curso,media,fnaci) VALUES (?,?,?,?);";
        try {
            escribir(a, con,sql,"create");
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Alumno read(int id) {
        Alumno a = null;
        sql = "SELECT * FROM alumnos WHERE cod=?;";
        try {
            Connection con = conectar();
            PreparedStatement pt = con.prepareStatement(sql);
            pt.setInt(1,id);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String curso = rs.getString("curso");
                Float media = rs.getFloat("media");
                String fNaci = rs.getString("fnaci");
                a = new Alumno(id,nombre, curso, media, fNaci);
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return a;
    }

    public void update(Alumno a) {
        if (a != null) {
            sql = "UPDATE alumnos SET nombre=?, curso=?, media=?, fnaci=? WHERE cod=?;";
            try {
                Connection con = conectar();
                PreparedStatement pt = con.prepareStatement(sql);

                pt.setString(1, a.getNombre());
                pt.setString(2, a.getCurso());
                pt.setFloat(3, a.getMedia());
                //la fecha la recibimos como tipo java.util.date , la debemos castear

                String fechaT = String.valueOf(a.getfNacimiento());
                pt.setString(4, fechaT);
                pt.setInt(5,a.getId());

                pt.executeUpdate();
                con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void escribir(Alumno a, Connection con, String sql, String opcion) throws SQLException{
        PreparedStatement pt= con.prepareStatement(sql);

        pt.setString(1, a.getNombre());
        pt.setString(2, a.getCurso());
        pt.setFloat(3, a.getMedia());
        //la fecha la recibimos como tipo java.util.date , la debemos castear
        String fechaT = String.valueOf(a.getfNacimiento());
        pt.setString(4, fechaT);
        if ( opcion.equals("update")){
            pt.setInt(5,a.getId());
        }
        pt.executeUpdate();
    }
    public void delete(int id){
        sql="DELETE FROM alumnos WHERE cod=?;";
        try{
            Connection con = conectar();
            PreparedStatement pt= con.prepareStatement(sql);
            pt.setInt(1,id);
            pt.executeUpdate();
            con.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Alumno> readAll() throws ParseException{
        List<Alumno> lista = new ArrayList<>();
        sql="SELECT * FROM alumnos";
        try{
            Connection con = conectar();
            PreparedStatement pt= con.prepareStatement(sql);
            ResultSet rs= pt.executeQuery();
            while (rs.next()){
                String nombre = rs.getString("nombre");
                int id = rs.getInt("cod");
                String curso = rs.getString("curso");
                float media= rs.getFloat("media");
                String fNaci= rs.getString("fnaci");
                Alumno a = new Alumno(id, nombre,curso,media,fNaci);
                lista.add(a);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    public List<Alumno>  read2(String curso) throws ParseException{
        List<Alumno> lista = new ArrayList<>();

        sql = "SELECT * FROM alumnos WHERE curso=?;";
        try {
            Connection con = conectar();
            PreparedStatement pt = con.prepareStatement(sql);
            pt.setString(1,curso);
            ResultSet rs = pt.executeQuery();
           while (rs.next()) {
                String nombre = rs.getString("nombre");

                Float media = rs.getFloat("media");
                String fNaci = rs.getString("fnaci");
                int id=rs.getInt("cod");

               Alumno a = new Alumno(id,nombre, curso, media, fNaci);
               lista.add(a);

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return lista;
    }
    public  ArrayList<Alumno> buscar(T valor, String opcionB)throws SQLException{
        ArrayList<Alumno> lista=new ArrayList<>();
        Alumno a;
        String valorC = (String) valor;

        PreparedStatement pt;
        ResultSet rs;
            try (Connection con= conectar()){
                sql="SELECT * FROM alumnos WHERE "+ opcionB +" = ?;";
                pt= con.prepareStatement(sql);
                if(opcionB.equals("cod")){
                    int valorId = Integer.parseInt(valorC);
                    pt.setInt(1, valorId);
                    System.out.println(valorC+""+ opcionB + " "+ sql);
                    rs=pt.executeQuery();
                    if(rs.next()){
                        String nombre = rs.getString("nombre");
                        float media = rs.getFloat("media");
                        String curso = rs.getString("curso");
                        String fNaci = rs.getString("fnaci");
                        a= new Alumno(valorId , nombre, curso, media, fNaci);
                        lista.add(a);
                    }
                }else{
                   if (opcionB.equals("nombre") || opcionB.equals("curso")){

                       sql="SELECT * FROM alumnos WHERE "+opcionB + " LIKE ?;";
                       pt= con.prepareStatement(sql);
                        pt.setString(1,"%"+valorC+"%");
                       System.out.println(valorC+""+ opcionB + " "+ sql);
                    }else if (opcionB.equals("media")){
                        float valorFloat = Float.parseFloat(valorC);
                        pt.setFloat(1,valorFloat);
                    }
                    rs= pt.executeQuery();
                    while (rs.next()){
                        int id = rs.getInt("cod");
                        String nombre = rs.getString("nombre");
                        float media = rs.getFloat("media");
                        String curso = rs.getString("curso");
                        String fNaci = rs.getString("fnaci");
                        a= new Alumno(id , nombre, curso, media, fNaci);
                        lista.add(a);

                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }catch (ParseException e){
                throw  new RuntimeException(e);
            }
        return lista;
    }
}