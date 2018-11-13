<%-- 
    Document   : login
    Created on : 08-nov-2018, 12:38:34
    Author     : Guillermo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trotamundo Tour</title>
    </head>
    <body>
        <h1>Trotamundo Tours</h1>
        <form action="/LoginServlet" method="post"> 
            <table border="0">
                <th>
                    <td>
                        <p>Usuario: </p>
                    </td>
                    <td>
                        <input type="text" name="usuario">
                    </td>
                </th>
                <th>
                    <td>
                        <p>Contraseña: </p>
                    </td>
                    <td>
                        <input type="text" name="password"
                    </td>
                </th>
                <th>
                    <input type="submit" value="Login">
                </th>
                <th>
                    <p>¿No tienes cuenta? <a href="location.href='/registro.jsp'">Registrate</a></p>
                </th>
        </form>
    </body>
</html>
