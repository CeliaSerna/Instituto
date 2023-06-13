<%--
  Created by IntelliJ IDEA.
  User: PROGRAMACION
  Date: 09/06/2023
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Cabecera</title>
<style>


</style>
</head>
<body>


<div class="container">

    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">Instituto Tomelloso</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="Controller?opcion=listar">Listar</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="alta.jsp">Alta de un alumno</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="modificacion.jsp">Modificar alumno</a>
            </li>
            <li class="nav-item">
              <form class="d-flex" role="search" action="Controller" method="post">
                <input type="hidden" name="opcion" value="buscar">
                <input class="form-control me-2" type="search"  aria-label="Search"
                name="nombreB">

                <select name="opcionB"  class="form-control me-2">
                  <option selected value="noOpcion">Buscar por</option>
                  <option value="cod">ID</option>
                  <option value="nombre">Nombre</option>
                  <option value="curso">Curso</option>
                  <option value="media">Media</option>
                </select>
                <button class="btn btn-outline-success" type="submit" name="search">Buscar</button>
              </form>

            </li>
          </ul>
        </div>
      </div>
    </nav>

</div>
</body>
</html>
