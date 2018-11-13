<%-- 
    Document   : usuario
    Created on : 08-nov-2018, 13:04:21
    Author     : Guillermo
--%>

<%@page import="beans.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            Persona user = (Persona) session.getAttribute("actualUser");
        %>
        <title>Usuario</title>
    </head>
    <body>
        <h1><%=user.getNombre()%> <%=user.getApellido1()%> <%=user.getApellido2()%></h1>
        <table border='0'>
            <tr>
                <td>
                    <p>Email: </p>
                </td>
                <td>
                    <p><%=user.getEmail()%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Tel&eacute;fono:</p>
                </td>
                <td>
                    <p><%=user.getTelefono()%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>ROL: </p>
                </td>
                <td>
                    <p><%=user.getRol()%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="Buscar" onclick="location.href='/hacerbusqueda.jsp'">
                </td>
                <td>
                    <form action="/LogoutServlet" method="post">
                        <input type="submit" value="Salir">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
