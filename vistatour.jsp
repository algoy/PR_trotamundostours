<%-- 
    Document   : vistatour
    Created on : 12-nov-2018, 12:47:56
    Author     : Guillermo
--%>

<%@page import="beans.Persona"%>
<%@page import="beans.Tour"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            Tour t = (Tour) session.getAttribute("tour");
            Persona guia = (Persona) session.getAttribute("guiaTour");
        %>
        <title>Tour</title>
    </head>
    <body>
        <h1>Tour por <%=t.getCiudad()%></h1>
        <table border="0">
            <tr>
                <td>
                    <p>Idioma: </p>
                </td>
                <td>
                    <p><%=t.getIdioma()%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Duraci&oacute;n: </p> 
                </td>
                <td>
                    <p><%=t.getDuracion()%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Fecha y hora: </p>
                </td>
                <td>
                    <p><%=t.getFecha().toString()%> <%=t.getHora().toString()%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Lugar de encuentro: </p>
                </td>
                <td>
                    <p><%=t.getLugarEncuentro()%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Nombre del gu&iacute;a: </p>
                </td>
                <td>
                    <p><%=guia.getNombre()%> <%=guia.getApellido1()%> <%=guia.getApellido2()%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Asistentes: </p>
                </td>
                <td>
                    <p><%=t.getPersonaList().size()%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <form action="ReservarTourServlet" method="post">
                        <input type="submit" value="Reservar">
                    </form>
                </td>
                <td>
                    <input type="button" value="Volver a b&uacute;squeda" onclick="location.href='/resultadobusqueda.jsp'">
                </td>
                <td>
                    <input type="button" value="Buscar" onclick="location.href='/hacerbusqueda.jsp'">
                </td>
            </tr>
        </table>
                
    </body>
</html>
