package com.cursoceat.instituto.controller;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;



import com.cursoceat.instituto.modell.Alumno;
import com.cursoceat.instituto.services.AlumnoDAO;


@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    String mensajeExito, mensajeError;
    AlumnoDAO alumnoDAO = new AlumnoDAO();
    String opcion = "";
    int id = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        opcion = request.getParameter("opcion");
        System.out.println(opcion);
        if (!opcion.isEmpty()) {
            try {
                switch (opcion) {
                    case "listar": {
                        listar(request, response);
                        break;
                    }
                    case "alta":{
                        alta(request,response);
                        break;
                    }
                    case "modificar":
                        case "eliminar":{
                            id= Integer.parseInt(request.getParameter("cod"));
                            if(opcion.equals("eliminar")){
                                eliminar(request,response);
                            }else{
                                modificar(request,response);
                            }
                            break;
                        }
                     case"ejecutarModificacion":{
                        ejecutarModificacion(request,response);
                        break;
                    }

                    default: {
                        System.out.println("Opción no válida");
                        break;
                    }
                }
            } catch (ParseException e) {
                throw new RuntimeException();
            }
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    protected void listar(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ParseException, ServletException {
     List<Alumno> alumnos = alumnoDAO.readAll();

        request.getSession().setAttribute("listaA", alumnos);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void alta(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, ServletException {
        String nombre = request.getParameter("nombreAlumno");
        String curso = request.getParameter("cursoAlumno");
        String fNaci = request.getParameter("fNaciAlumno");
        float media = Float.parseFloat(request.getParameter("mediaAlumno"));
        try {
            Alumno a = new Alumno(nombre, curso, media, fNaci);
            alumnoDAO.create(a);
            mensajeExito = "Alumno creado correctamente";
            request.setAttribute("mensajeExito", mensajeExito);
        } catch (Exception e) {
            mensajeError = "Error en la nueva alta";
            request.setAttribute("mensajeError", mensajeError);
        } finally {
            listar(request, response);
        }

    }

    protected void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, ServletException {
        Alumno a = alumnoDAO.read(id);
        System.out.println(a);
        request.setAttribute("id", a.getId());
        request.setAttribute("nombre", a.getNombre());
        request.setAttribute("curso", a.getCurso());
        request.setAttribute("media", a.getMedia());

        String fechaT = String.valueOf(a.getfNacimiento());

        //SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        //String fecha = date.format(a.getfNacimiento());
        request.setAttribute("fNaci", fechaT);
        request.getRequestDispatcher("modificacion.jsp").forward(request, response);

    }

    protected void ejecutarModificacion(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, ServletException {

        int id = Integer.parseInt(request.getParameter("idAlumno"));
        String nombre = request.getParameter("nombreAlumno");
        String curso = request.getParameter("cursoAlumno");
        String fNaci = request.getParameter("fNaciAlumno");
        float media = Float.parseFloat(request.getParameter("mediaAlumno"));
        try {
            Alumno a = new Alumno(id,nombre, curso, media, fNaci);
            alumnoDAO.update(a);
            mensajeExito = "Alumno modificado correctamente";
            request.setAttribute("mensajeExito", mensajeExito);
        } catch (Exception e) {
            mensajeError = "Error en la modificación";
            request.setAttribute("mensajeError", mensajeError);
        } finally {
            listar(request, response);
        }

    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, ServletException {

        try{
        alumnoDAO.delete(id);
        mensajeExito = "Alumno eliminado correctamente";
        request.setAttribute("mensajeExito", mensajeExito);

    }catch (Exception e){
        mensajeError = "Error al borrar el alumno "+ id;
        request.setAttribute("mensajeError", mensajeError);
    }finally {
        listar(request, response);
    }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    String nombreB= request.getParameter("nombreB");
    String opcionB = request.getParameter("opcionB");
        System.out.println(nombreB+" "+ opcionB);
    boolean buscar = true;
    ArrayList<Alumno> listaEncontrada=null;
    if(opcionB.equals("cod") || opcionB.equals("media")){
        try{
            int comprobacion= Integer.parseInt(nombreB);
            buscar = true;

        }catch (Exception e){
            buscar=false;
            mensajeError= "Existe un error en los parametros de la búsqueda, solo númeors";
            request.setAttribute("mensajeError", mensajeError);
        }
    }else if(opcionB.equals("noOpcion")){
        mensajeError="Elija una opción";
        request.setAttribute("mensajeError", mensajeError);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
    if (buscar){
        try{
            listaEncontrada= alumnoDAO.buscar(nombreB,opcionB);
            System.out.println(listaEncontrada.get(0));
        }catch (SQLException e){
            mensajeError ="Existe un error en los parametros de la búsqueda";
            request.setAttribute("mensajeError", mensajeError);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        if(listaEncontrada.isEmpty()){
            mensajeError="No existen resultados que coincidan con la búsqueda seleccionada";
            request.setAttribute("mensajeError", mensajeError);

        }else{
            request.setAttribute("listaA", listaEncontrada);
        }
    }
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
    }