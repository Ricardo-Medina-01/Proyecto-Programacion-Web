<%-- 
    Document   : EliminarUsuario
<<<<<<< Upstream, based on origin/master
    Created on : 16/05/2026, 06:02:34 PM
    Author     : pauye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Control" method="post">
            Usuario
                <jsp:useBean id="bd" class="bean.Usuarios">
                    <jsp:setProperty name="bd" property="opcion" value="llenaS" />
                    <jsp:getProperty name="bd" property="respuesta" />
                </jsp:useBean>     
            <br><br>
            
            <input type="submit" value="Eliminar Usuario" name="boton">
            </form>
=======
    Created on : 18-may-2026, 3:13:08
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>DESACTIVAR USUARIO</h2>
        <form action="../../Control" method="post">
            <input type="hidden" name="accion" value="desactivarUsuario">
            Usuario:
            <select name="usuario"> 
                <%
                    bean.Usuarios u = new bean.Usuarios();
                    u.llenarSelector();
                    out.print(u.getRespuesta());
                %>
            </select>
            <br><br>
            <input type="submit" value="Desactivar Usuario">
            <br><br>
            <a href="../Usuarios.html">Volver</a>
        </form>
>>>>>>> 6574c46 Finalización de Proyecto parte logica
    </body>
</html>
