<%-- 
    Document   : vistatour
    Created on : 12-nov-2018, 12:47:56
    Author     : Guillermo
--%>

<%@page import="beans.Reserva"%>
<%@page import="beans.Persona"%>
<%@page import="beans.Tour"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            Tour actualTour = (Tour) session.getAttribute("actualTour");
            Persona guia = (Persona) session.getAttribute("guiaTour");
            Persona actualUser = (Persona) session.getAttribute("actualUser");
            int participantes = 0;
            for(Reserva r : actualUser.getReservaList()){
                participantes+=r.getParticipantes().intValue();
            }
        %>
        <title>Tour</title>
    </head>
    <body>
        <h1>Tour por <%=actualTour.getCiudad()%></h1>
        <table border="0">
            <tr>
                <td>
                    <p>Idioma: </p>
                </td>
                <td>
                    <p><%=actualTour.getIdioma()%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Duraci&oacute;n: </p> 
                </td>
                <td>
                    <p>Aqui hay un problema</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Fecha y hora: </p>
                </td>
                <td>
                    <p><%=actualTour.getFecha().toString()%> <%=actualTour.getHora().toString()%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Lugar de encuentro: </p>
                </td>
                <td>
                    <p><%=actualTour.getLugarEncuentro()%></p>
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
                    <p><%=participantes%></p>
                </td>
            </tr>
            <tr>
                <td>
                    <%
                      boolean reservado = false;
                      for(Reserva v : actualUser.getReservaList()){
                          if(v.getIdTourTour().getIdTour().equals(actualTour.getIdTour())) reservado = true;
                      }
                      if(!reservado){
                    %>    
                    <form action="ReservarTourServlet" method="post">
                        <table border="0">
                                <tr>
                                    <td>¿Cu&aa&aacute;tos vais a venir?</td>
                                    <td><input type="text" name="participantes"></td>
                                    <td><input type="submit" value="Reservar"></td>
                                </tr>
                        </table>
                    </form>
                    <%
                      }
                      else { 
                    %>
                    <form action="CancelarReservaServlet" method="post">
                        <input type="submit" value="Cancelar reserva">
                    </form>
                    <%
                      }  
                    %>
                </td>
                <td>
                    <input type="button" value="Volver a b&uacute;squeda" onclick="location.href='/resultadobusqueda.jsp'">
                </td>
                <td>
                    <input type="button" value="Buscar" onclick="location.href='/hacerbusqueda.jsp'">
                </td>
            </tr>
            <%if(actualUser.getRol.equalsIgnoreCase("turista")){%>
            <tr>
                <td>
                    <input type="button" value="Puntuar" onclick="location.href='/comentar.jsp'">
                </td>
                <%
                      boolean favorito = false;
                      for(Tour t : actualUser.getTourList1()){
                          if(t.getIdTour().equals(actualTour.getIdTour())) favorito = true;
                      }
                      if(!favorito){
                    %>  
                <td>
                    <form action="AnyadirTourAFavoritosServlet" method="post">
                        <input type="submit" value="A&ntilde;adir a favoritos">
                    </form>
                </td>
                <%
                    } 
                    else {
                %>
                <td>
                    <form action="EliminarTourFavoritosServlet" method="post">
                        <input type="submit" value="Eliminar de favoritos">
                    </form>
                </td>
                <%
                  }  
                %>
            </tr>
            <%}%>
        </table>
    </body>
</html>
