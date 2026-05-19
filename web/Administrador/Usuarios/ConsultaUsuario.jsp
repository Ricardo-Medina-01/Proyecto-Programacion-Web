<%-- 
    Document   : ConsultaUsuario
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
        <h1>Consulta de Usuarios</h1>
        <%
            bean.Usuarios u = new bean.Usuarios();
            u.consultarUsuarios();
            out.print(u.getRespuesta());
        %>
        <a href="../Usuarios.html">Volver</a>
    </body>
</html>

