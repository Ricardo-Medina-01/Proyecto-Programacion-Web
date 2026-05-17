<%-- 
    Document   : EliminarUsuario
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
    </body>
</html>
