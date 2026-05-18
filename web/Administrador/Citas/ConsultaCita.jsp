<%-- 
    Document   : ConsultaCita
    Created on : 18-may-2026, 7:45:39
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta Citas</title>
</head>
<body>

<h2>Consulta de Citas</h2>

<%
    bean.Citas c = new bean.Citas();
    c.consultarCitas();
    out.print(c.getRespuesta());
%>

<br><br>

<a href="../Citas.html">Volver</a>

</body>
</html>
