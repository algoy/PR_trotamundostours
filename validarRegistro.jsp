<%-- 
    Document   : confirmacionRegistro
    Created on : 17-oct-2018, 16:58:18
    Author     : Guillermo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Validacion de registro</title>
    </head>
    <body>
        <h1>Registro</h1>
        <br/>
        <form action="/Servlet/ValidarCodigoServlet" method="post">
            <p>No falta casi nada para que estes 100% registrado en nuestra web y </p>
            <p>puedas disfrutar de todas las opciones de tours que tenemos.</p><br/>
            <p>Solo falta que abras el email que has especificado</p>
            <p>y escribas el codigo que te hemos mandado aqui: <input type="text" name="code"> </p>
            <input type="submit" value="Continuar"> 
            <button type="button" onclick="location.href='/inicio.jsp'">Volver a Inicio </button> <br/>
        </form>
        <p><br/>Si no ha recibido el email haga clic <a href="location.href='/ReenviarEmailServlet'">aqui</a></p>
    </body>
</html>
