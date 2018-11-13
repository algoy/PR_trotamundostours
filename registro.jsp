<%-- 
    Document   : registro
    Created on : 17-oct-2018, 16:07:45
    Author     : Guillermo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
        <h1>Registro de usuario</h1>
        <form action="/RegistrarUsuarioServlet" method="post">
            <table border=0>
                <tr>
                    <td>
                        <p class="registro">Nombre: </p>
                    </td>
                    <td>
                        <input type="text" name="nombre">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p class="registro">Apellido 1: </p>
                    </td>
                    <td>
                        <input type="text" name="ap1">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p class="registro">Apellido 2: </p>
                    </td>
                    <td>
                        <input type="text" name="ap2">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p class="registro">Usuario: </p>
                    </td>
                    <td>
                        <input type="text" name="usuario">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p class="registro">Contraseña: </p>
                    </td>
                    <td>
                        <input type="password" name="password">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p class="registro">Email: </p>
                    </td>
                    <td>
                        <input type="text" name="email">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p class="registro">Tel&eacute;fono: </p>
                    </td>
                    <td>
                        <input type="text" name="telefono">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p class="registro">Idiomas: </p>
                        
                    </td>
                    <td>
                        <input type="checkbox" name="idioma" value="ESP" checked="true">Español<br/>
                            <input type="checkbox" name="idioma" value="ENG">Ingl&eacute;s<br/>
                            <input type="checkbox" name="idioma" value="ITA">Italiano<br/>
                            <input type="checkbox" name="idioma" value="GER">Aleman<br/>
                        <td>
                            <input type="checkbox" name="idioma" value="FRA">Franc&eacute;s<br/>
                            <input type="checkbox" name="idioma" value="POR">Portugu&eacute;s<br/>
                            <input type="checkbox" name="idioma" value="CHI">Chino<br/>
                            <input type="checkbox" name="idioma" value="RUS">Ruso<br/>
                        </td>
                    </td>
                </tr>
                <tr>    
                    <td>
                        <p class="registro">Rol: </p>
                    </td>
                    <td>
                        <input type="radio" name="rol" value="turista"> Turista<br/>
                        <input type="radio" name="rol" value="guia"> Gu&iacute;a<br/>
                    </td>
                </tr>
            </table>
            <br/>
            <input type="submit" value="Continuar">
            <button type="button" onclick="location.href='/inicio.jsp'">Cancelar</button>
        </form>
    </body>
</html>
