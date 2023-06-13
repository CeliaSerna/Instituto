<%--
  Created by IntelliJ IDEA.
  User: PROGRAMACION
  Date: 09/06/2023
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Modificar Alumno</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

</head>
<body>
<header>
    <jsp:include page="header.jsp"></jsp:include>
</header>

<div class="container">

    <h2>Modificar alumnos</h2>
    <form class="row g-3 needs-validation" novalidate method="get" action="Controller">
        <input type="hidden" name="opcion" value="ejecutarModificacion">

        <div class="col-md-1">
            <label for="validationCustom01" class="form-label">Código</label>
            <input type="text" class="form-control" id="validationCustom011" value="<c:out value="${id}"></c:out>" name="idAlumno" required maxlength="50">
            <div class="invalid-feedback">
                ¡El id es necesario!
            </div>
        </div>
        <div class="col-md-4">
            <label for="validationCustom01" class="form-label">Nombre y Apellidos</label>
            <input type="text" class="form-control" id="validationCustom01" value="<c:out value="${nombre}"></c:out>" name="nombreAlumno" required maxlength="50">
            <div class="invalid-feedback">
                ¡El nombre es necesario!
            </div>
        </div>
        <div class="col-md-2">
            <label for="validationCustom02" class="form-label">Curso</label>
            <input type="text" class="form-control" id="validationCustom02" value="<c:out value="${curso}"></c:out>" name="cursoAlumno" maxlength="2" required>
            <div class="invalid-feedback">
                ¡El curso es necesario!
            </div>
        </div>
        <div class="col-md-2">
            <label for="validationCustomUsername" class="form-label">Media</label>
            <div class="input-group has-validation">
                <input type="text" class="form-control" id="validationCustomUsername" name="mediaAlumno" value="<c:out value="${media}"></c:out>"
                       aria-describedby="inputGroupPrepend" required >
                <div class="invalid-feedback">
                    ¡La media es necesaria!
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <label for="validationCustom03" class="form-label">Fecha de nacimiento</label>
            <input type="date" class="form-control" id="validationCustom03" name="fNaciAlumno" required value="<c:out value="${fNaci}"></c:out>">
            <div class="invalid-feedback">
                ¡La fecha de nacimiento es necesaria!
            </div>
        </div>


        <div class="col-12">
            <button class="btn btn-primary" type="submit">Actualizar</button>
        </div>
    </form>
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (() => {
            'use strict'

            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            const forms = document.querySelectorAll('.needs-validation')

            // Loop over them and prevent submission
            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
        })()
    </script>
</div>
</body>
</html>