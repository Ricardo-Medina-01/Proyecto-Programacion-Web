<%-- 
    Document   : ModificarPaciente
    Created on : 18-may-2026, 14:09:09
    Author     : Admin
--%>

<%@page contentType="text/html"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar Paciente</title>
</head>

<body>

<h2>Modificar Paciente</h2>

<form action="../../Control"
      method="post">

    <input type="hidden"
           name="accion"
           value="modificarPaciente">

    Paciente:

    <select name="idPaciente">

        <%
            bean.Pacientes p =
            new bean.Pacientes();

            p.llenarPacientes();

            out.print(p.getRespuesta());
        %>

    </select>

    <br><br>

    Nombre:
    <input type="text"
           name="nombre"
           required>

    <br><br>

    Paterno:
    <input type="text"
           name="aPaterno"
           required>

    <br><br>

    Materno:
    <input type="text"
           name="aMaterno"
           required>

    <br><br>

    Teléfono:
    <input type="text"
           name="telefono"
           required>

    <br><br>

    <input type="submit"
           value="Modificar Paciente">

</form>

</body>
</html>
