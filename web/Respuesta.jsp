<%-- 
    Document   : Respuesta
    Created on : 17-may-2026, 23:18:07
    Author     : Admin
--%>

<%@page contentType="text/html"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
    <meta charset="UTF-8">
    <title>Respuesta</title>
    </head>
        <body>
            <h2>
                <%= request.getAttribute("respuesta") %>
            </h2>
            <br><br>
            <a href="index.html">Inicio</a>
        </body>
</html>
