<%-- 
    Document   : ConsultaPaciente
    Created on : 18-may-2026, 6:02:25
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta Pacientes</title>
</head>
<body>

<h2>Consulta de Pacientes</h2>

<%
    bean.Pacientes p = new bean.Pacientes();
    p.consultarPacientes();
    out.print(p.getRespuesta());
%>

<br><br>

<a href="../Pacientes.html">Volver</a>

</body>
</html>
