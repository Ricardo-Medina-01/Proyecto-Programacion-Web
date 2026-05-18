<%-- 
    Document   : AltaCita
    Created on : 18-may-2026, 6:11:42
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Alta Cita</title>
</head>

<body>

<h2>Agendar Cita</h2>

<form action="../../Control" method="post">

    <input type="hidden"
           name="accion"
           value="altaCita">

    ID Paciente:
    <input type="number"
           name="idPaciente"
           required>

    <br><br>

    Usuario Médico:
        <select name="usuarioMedico" required>
            <%
                bean.Citas c = new bean.Citas();
                c.llenarMedicos();
                out.print(c.getRespuesta());
            %>
        </select>

    <br><br>

    Fecha:
    <input type="date"
           name="fecha"
           required>

    <br><br>

    Hora:
    <input type="time"
           name="hora"
           required>

    <br><br>

    Motivo:
    <input type="text"
           name="motivo"
           required>

    <br><br>

    <input type="submit"
           value="Registrar Cita">

</form>

<br><br>

<a href="../Citas.html">
    Volver
</a>

</body>
</html>
