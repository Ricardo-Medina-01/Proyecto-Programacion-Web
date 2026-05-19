<%-- 
    Document   : RegistrarConsulta
    Created on : 18-may-2026, 7:53:47
    Author     : Admin
--%>

<%@page contentType="text/html"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Registrar Consulta</title>
    </head>
    <body>

        <h2>Registrar Consulta</h2>

        <form action="../../Control" method="post">

            <input type="hidden" name="accion" value="registrarConsulta">

            ID Cita:
                <select name="idCita">
                    <%
                        bean.Consultas c =
                        new bean.Consultas();

                        c.llenarCitas();

                        out.print(c.getRespuesta());
                    %>
                </select>
                <br><br>

            Medicamento:
                <input type="text" name="medicamento" required>
                <br><br>

            Observaciones:
                <textarea name="observaciones"></textarea>
                <br><br>

            <input type="submit" value="Guardar Consulta">

        </form>
        <br><br>

        <a href="../Consultas.html">Volver</a>
    </body>
</html>
