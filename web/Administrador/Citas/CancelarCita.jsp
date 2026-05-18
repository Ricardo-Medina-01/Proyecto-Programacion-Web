<%-- 
    Document   : CancelarCita
    Created on : 18-may-2026, 7:46:17
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cancelar Cita</title>
</head>
<body>

<h2>Cancelar Cita</h2>

<form action="../../Control" method="post">

    <input type="hidden" name="accion" value="cancelarCita">

    ID Cita:
    <select name="idCita">
        <%
            bean.Citas c = new bean.Citas();
            c.llenarCitasActivas();
            out.print(c.getRespuesta());
        %>
    </select>

    <br><br>

    <input type="submit" value="Cancelar Cita">

</form>

<br><br>

<a href="../Citas.html">Volver</a>

</body>
</html>
