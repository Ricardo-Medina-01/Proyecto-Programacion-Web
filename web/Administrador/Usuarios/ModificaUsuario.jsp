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
        <form action="ModificaUsuario.jsp" method="get">
            Usuario
                <jsp:useBean id="bd" class="bean.Usuarios">
                    <jsp:setProperty name="bd" property="opcion" value="llenaS" />
                    <jsp:getProperty name="bd" property="respuesta" />
                </jsp:useBean>     
            <br>
            Tipo<select name="tipo">
                <option value="A">Administrador</option>
                <option value="I">Invitado</option>
                <option value="M">Medico</option>
                <option value="E">Empleado</option>
            </select>
            <input type="hidden" name="opcion" value="modificaT">
            
            <input type="submit" value="Modificar">
        </form>
            <jsp:setProperty name="bd" property="*"/>
            <jsp:getProperty name="bd" property="respuesta"/>
            </h1>
            
            <a href="Usuarios.html">Volver</a> // <a href="Inicio">Inicio</a>
    </body>
</html>