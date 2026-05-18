<%-- 
    Document   : Error
    Created on : 18-may-2026, 15:22:03
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
        <h2>
            <%= request.getParameter("respuesta") %>
        </h2>
            <br><br>
            <a href="index.html">Inicio</a>
    </body>
</html>
