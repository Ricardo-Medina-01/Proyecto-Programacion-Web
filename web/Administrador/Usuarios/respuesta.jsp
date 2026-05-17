<%-- 
    Document   : respuesta
    Created on : 16/05/2026, 04:50:05 PM
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
          <%= request.getParameter("respuesta") %>
    </body>
</html>
