<%-- 
    Document   : ModificarCita
    Created on : 18-may-2026, 7:47:03
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar Cita</title>
</head>
<body>

<h2>Modificar Cita</h2>

<form action="../../Control" method="post">

    <input type="hidden" name="accion" value="modificarCita">

    ID Cita:
    <select name="idCita">
        <%
            bean.Citas c = new bean.Citas();
            c.llenarCitasActivas();
            out.print(c.getRespuesta());
        %>
    </select>

    <br><br>

    Nueva fecha:
    <input type="date" name="fecha" required>

    <br><br>

    Nueva hora:
    <input type="time" name="hora" required>

    <br><br>

    Motivo:
    <input type="text" name="motivo" required>

    <br><br>

    <input type="submit" value="Modificar Cita">

</form>

<br><br>

<a href="../Citas.html">Volver</a>

</body>
</html>
