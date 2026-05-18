<%-- 
    Document   : ModificaUsuario
    Created on : 27-abr-2026, 11:47:09
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body><h1>
        <form action="../../Control" method="post">
            <input type="hidden" name="accion" value="modificarTipo">
            Usuario
                <select name = "usuario">
                    <%
                        bean.Usuarios u = new bean.Usuarios();
                        u.llenarSelector();
                        out.print(u.getRespuesta());
                    %>
                </select>
            <br>
            Tipo<select name="tipo">
                <option value="A">Administrador</option>
                <option value="I">Invitado</option>
                <option value="M">Medico</option>
                <option value="E">Empleado</option>
            </select>
            <br>
            <input type="submit" value="Modificar">
        </form>
            </h1>
            
            <a href="../Usuarios.html">Volver</a> // <a href="../index.html">Inicio</a>
    </body>
</html>