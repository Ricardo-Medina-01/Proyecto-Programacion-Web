<%-- 
    Document   : Registro
    Created on : 27-abr-2026, 9:54:13
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
                String n = request.getParameter("nombre");
                String p = request.getParameter("paterno");
                String m= request.getParameter("materno");
                String i = request.getParameter("email");
                %>
                
                <jsp:useBean id="db" class="bean.Usuarios">
                    <jsp:setProperty name="db" property="nombre" value="<%=n%>"/>
                    <jsp:setProperty name="db" property="paterno" value="<%=p%>"/>
                    <jsp:setProperty name="db" property="materno" value="<%=m%>"/>
                    <jsp:setProperty name="db" property="email" value="<%=i%>"/>
                    <jsp:setProperty name="db" property="opcion" value="registro"/>
                    <jsp:getProperty name="db" property="respuesta"/>
                    
                </jsp:useBean> 
                <br><br>
                <a href="index.html">Inicio</a>
    </body>
</html>
