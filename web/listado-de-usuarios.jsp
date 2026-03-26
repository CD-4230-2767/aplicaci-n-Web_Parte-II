<%-- 
    Document   : listado-de-usuarios
    Created on : 8 nov. 2023, 9:09:48
    Author     : alopezorozco
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Murach's Java Servlets and JSP - Listado de Usuarios</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col">
                    <h1>Elementos de la lista</h1>
                    <hr>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Email</th>
                                <th scope="col">FirstName</th>
                                <th scope="col">LastName</th>
                                <th scope="col" colspan="2">Operaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.email}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td><a href="emailList?action=modificar&email=${user.email}" class="btn btn-info" role="button">Modificar</a></td>
                                <td><a href="emailList?action=eliminar&email=${user.email}" class="btn btn-danger" role="button">Eliminar</a></td>
                            </tr>    
                        </c:forEach>
                        </tbody>    
                    </table>
                </div>
            </div>
        </div>

    </body>
</html>