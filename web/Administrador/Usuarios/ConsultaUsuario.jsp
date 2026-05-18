<%-- 
    Document   : ConsultaUsuario
<<<<<<< Upstream, based on origin/master
    Created on : 16/05/2026, 04:37:59 PM
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
            
            <input type="submit" value="Consultar Usuario" name="boton">
            </form>
    </body>
</html>
 
=======
    Created on : 18-may-2026, 2:05:27
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
        <h1>Consulta de Usuarios</h1>
        <%
            bean.Usuarios u = new bean.Usuarios();
            u.consultarUsuarios();
            out.print(u.getRespuesta());
        %>
        <a href="../Usuarios.html">Volver</a>
    </body>
</html>
>>>>>>> 6574c46 Finalización de Proyecto parte logica
