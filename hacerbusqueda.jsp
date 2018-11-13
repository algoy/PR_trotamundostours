<%-- 
    Document   : hacerbusqueda
    Created on : 05-nov-2018, 20:14:31
    Author     : Borjita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar tour</title>
    </head>
    <body>
        <h4>Introduce los siguientes datos para la busqueda:</h4>
        <form action="/BusquedaServlet" method="post">
            <p>Ciudad:<input type="text" maxlength="20" size="20" name="ciudad"></p>
            <p>Idioma:</p>
            <input type="checkbox" name="idioma" value="ESP" checked="true">Español<br/>
            <input type="checkbox" name="idioma" value="ENG">Ingl&eacute;s<br/>
            <input type="checkbox" name="idioma" value="ITA">Italiano<br/>
            <input type="checkbox" name="idioma" value="GER">Aleman<br/>
            <input type="checkbox" name="idioma" value="FRA">Franc&eacute;s<br/>
            <input type="checkbox" name="idioma" value="POR">Portugu&eacute;s<br/>
            <input type="checkbox" name="idioma" value="CHI">Chino<br/>
            <input type="checkbox" name="idioma" value="RUS">Ruso<br/>
            <input type="submit" value="Buscar">
            <input type="Reset" value="Borrar">
        </form>
    </body>
</html>
