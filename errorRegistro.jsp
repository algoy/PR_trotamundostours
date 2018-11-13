<%-- 
    Document   : errorRegistro
    Created on : 17-oct-2018, 17:24:41
    Author     : Guillermo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h2>Codigo incorrecto</h2>
        <p>El codigo que has introducido no es el mismo que te hemos mandado al email.</p>
        <form action="/Servlet/ValidarCodigoServlet" method="post">
            <p>Porfavor introduzca de nuevo su codigo: <input type="text" name="code"></p>
            <p>Si no ha recibido el email haga clic <a href="location.href='/ReenviarEmailServlet'">aqui</a></p><br/>
            <input type="submit" value="Continuar">
            <button type="button" onclick="location.href='/inicio.jsp'">Volver a Inicio</button>
        </form>
    </body>
</html>
