<%-- 
    Document   : ActivaDesactivaPaciente
    Created on : 18-may-2026, 14:27:54
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Activar / Desactivar Paciente</title>
</head>
<body>

<h2>Activar / Desactivar Paciente</h2>

<form action="../../Control" method="post">

    Paciente:
    <select name="idPaciente">
        <%
            bean.Pacientes p = new bean.Pacientes();
            p.llenarPacientesTodos();
            out.print(p.getRespuesta());
        %>
    </select>

    <br><br>

    Acción:
    <select name="accion">
        <option value="activarPaciente">Activar</option>
        <option value="desactivarPaciente">Desactivar</option>
    </select>

    <br><br>

    <input type="submit" value="Guardar Cambio">

</form>

<br><br>

<a href="../Pacientes.html">Volver</a>

</body>
</html>
