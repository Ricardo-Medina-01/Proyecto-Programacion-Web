<%-- 
    Document   : ModificarPaciente
    Created on : 18-may-2026, 14:09:09
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar Paciente</title>
</head>

<body>

<h2>Modificar Paciente</h2>

<%
    bean.Pacientes p = new bean.Pacientes();

    String id = request.getParameter("idPaciente");

    if(id != null && !id.equals("")){
        p.setIdPaciente(Integer.parseInt(id));
        p.buscarPaciente();
    }
%>

<form action="ModificarPaciente.jsp" method="get">

    Paciente:

    <select name="idPaciente" onchange="this.form.submit()">

        <option value="">Seleccione paciente</option>

        <%
            bean.Pacientes lista = new bean.Pacientes();
            lista.llenarPacientes();
            out.print(lista.getRespuesta());
        %>

    </select>

</form>

<br><br>

<% if(id != null && !id.equals("")){ %>

<form action="../../Control" method="post">

    <input type="hidden" name="accion" value="modificarPaciente">

    <input type="hidden" name="idPaciente" value="<%= id %>">

    Nombre:
    <input type="text" name="nombre" value="<%= p.getNombre() %>" required>
    <br><br>

    Paterno:
    <input type="text" name="aPaterno" value="<%= p.getPaterno() %>" required>
    <br><br>

    Materno:
    <input type="text" name="aMaterno" value="<%= p.getMaterno() %>" required>
    <br><br>

    Teléfono:
    <input type="text" name="telefono" value="<%= p.getTelefono() %>" required>
    <br><br>

    Email:
    <input type="email" name="email" value="<%= p.getEmail() %>" required>
    <br><br>

    Dirección:
    <input type="text" name="direccion" value="<%= p.getDireccion() %>" required>
    <br><br>

    <input type="submit" value="Modificar Paciente">

</form>

<% } %>

<br><br>

<a href="../Pacientes.html">Volver</a>

</body>
</html>
