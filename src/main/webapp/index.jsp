<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 26 oct. de 2021, 20:09:07
    Author     : Jhonny
--%>
<%@page import="com.emergentes.modelo.Libro" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Libro> lista = (List<Libro>) request.getAttribute("lista");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Libros</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table border="1">
            <tr>
                <td>Id</td>
                <th>ISBN</th>
                <th>Titulo</th><!-- comment -->
                <th>Categoria</th>
            </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                     <td>${item.id}</td><!-- comment -->
                    <td>${item.isbm}</td><!-- comment -->
                    <td>${item.titulo}</td><!-- comment -->
                    <td>${item.categoria}</td><!-- comment -->
                    <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}"
                           onclick="return(confirm('Esta seguro ???'))">Eliminar</a></td>
                </tr>
            </c:forEach>

        </table>   

    </body>
</html>
