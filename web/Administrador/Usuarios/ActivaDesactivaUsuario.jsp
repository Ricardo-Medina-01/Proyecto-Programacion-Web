<%-- 
    Document   : ActivaDesactivaUsuario
    Created on : 18-may-2026, 3:18:22
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
        <h2>Activar / Desacativar Usuario</h2>
        <form action="../../Control" method="post">
            Usuario:
            <select name = "usuario">
                <%
                    bean.Usuarios u = new bean.Usuarios();
                    u.llenarSelector();
                    out.print(u.getRespuesta());
                %>
            </select>
            <br><br>    
            Estado: 
                <select name="accion">
                    <option value="activarUsuario">
                        Activar
                    </option>
                    
                    <option value="desactivarUsuario">
                        Desactivar
                    </option>
                </select>
            <br><br>
            <input type="submit" value="Guardar Cambios">
        </form>
            <a href="../Usuarios.html">Volver</a>
    </body>
</html>
