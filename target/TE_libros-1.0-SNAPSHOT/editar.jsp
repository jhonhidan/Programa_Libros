<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : editar
    Created on : 26 oct. de 2021, 20:16:26
    Author     : Jhonny
--%>
<%@page import="java.util.List" %>
<%@page import="com.emergentes.modelo.Libro" %>
<%
    Libro  lib = (Libro)request.getAttribute("lib");

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${lib.id==0}">
                Nuevo
            </c:if>
              <c:if test="${lib.id!=0}">
                Editar
            </c:if>  
        </h1>
        <form action="MainController" method="post">

            <table border="1">
                <input type="hidden" name="id" value="${lib.id}"><!-- comment -->
                <tr>
                    <td>ISBN</td>
                    <td>
                        <input type="text" name="isbm" value="${lib.isbm}"><!-- comment --> 
                    </td><!-- comment -->
                    
                </tr>
                 <tr>
                    <td>Titulo</td>
                    <td>
                        <input type="text" name="titulo" value="${lib.titulo}"><!-- comment --> 
                    </td><!-- comment -->
                    
                </tr>
                 <tr>
                    <td>Categoria</td>
                    <td>
                        <input type="text" name="categoria" value="${lib.categoria}"><!-- comment --> 
                    </td><!-- comment -->
                    
                </tr>
                 <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Enviar"><!-- comment --> 
                    </td><!-- comment -->
                    
                </tr>

            </table>   
        </form>

    </body>
</html>
