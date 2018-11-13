<%-- 
    Document   : resultadobusqueda
    Created on : 05-nov-2018, 20:31:31
    Author     : Guillermo
--%>

<%@page import="beans.Tour"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados de la busqueda</title>
        <%
          List<Tour> resultList = (List<Tour>) session.getAttribute("resultList");
        %>    
    </head>
    <body>
        <h2>El resultado de la busqueda es el siguiente:</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Idioma</th>
                <th></th>
            </tr>
            <%
              for(Tour t : resultList) {  
            %>  
            <tr>
                <td><%=t.getIdTour()%></td>
                <td><%=t.getFecha().toString()%></td>
                <td><%=t.getHora()%></td>
                <td><%=t.getIdioma()%></td>
                <td>
                    <form action="IrATourServlet" method="get">
                        <input type="button" value="Ver" onclick="location.href='/IrATourServlet?id=<%=t.getIdTour()%>'">
                    </form>
                </td>
            </tr>
            <%
              }  
            %>
        </table>
    </body>
</html>
