<%-- 
    Document   : Login
    Created on : 27-abr-2026, 7:39:59
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String u=request.getParameter("usuario");
            String p=request.getParameter("password");
        %>
        <jsp:useBean id="bd" class="bean.Usuarios" >
            <jsp:setProperty name="bd" property="usuario" value="<%=u%>" />
            <jsp:setProperty name="bd" property="password" value="<%=p%>" />
            <jsp:setProperty name="bd" property="opcion" value="verifica" />
            <jsp:getProperty name="bd" property="respuesta" />
            <%response.sendRedirect(bd.getRespuesta());%>
        </jsp:useBean>
    </body>
</html>
