/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.Usuarios;
import bean.Pacientes;
import bean.Citas;
import bean.Consultas;
import bean.Reportes;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Control", urlPatterns = {"/Control"})
public class Control extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Control</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Control at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        //  INICIAR SESION  
        if(accion.equals("login")){
            Usuarios u = new Usuarios();
            u.setUsuario(request.getParameter("usuario"));
            u.setPassword(request.getParameter("password"));
            u.setOpcion("verifica");
            System.out.println(u.getRespuesta());
            response.sendRedirect(u.getRespuesta());
        }
        //  REGISTRAR USUARIO
        if(accion.equals("registro")){
            Usuarios u = new Usuarios();
            u.setNombre(request.getParameter("nombre"));
            u.setPaterno(request.getParameter("paterno"));
            u.setMaterno(request.getParameter("materno"));
            u.setEmail(request.getParameter("email"));
            u.setOpcion("registro");
            request.setAttribute("respuesta",u.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request,response);
        }
        //  MODIFICAR USUARIO
        if(accion.equals("modificarTipo")){
            Usuarios u = new Usuarios();
            u.setUsuario(request.getParameter("usuario"));
            u.setTipo(request.getParameter("tipo"));
            u.modificarTipo();
            request.setAttribute("respuesta", u.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
        }
        if(accion.equals("activarUsuario")){
            Usuarios u = new Usuarios();
            u.setUsuario(request.getParameter("usuario"));
            u.activarUsuario();
            request.setAttribute("respuesta", u.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
        }
        if(accion.equals("desactivarUsuario")){
            Usuarios u = new Usuarios();
            u.setUsuario(request.getParameter("usuario"));
            u.desactivarUsuario();
            request.setAttribute("respuesta", u.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
        }
        //  CONSULTAR USUARIO
        if(accion.equals("consultarUsuarios")){
            Usuarios u = new Usuarios();
            u.consultarUsuarios();
            request.setAttribute("tabla", u.getRespuesta());
            request.getRequestDispatcher("Administrador/Usuarios/ConsultaUsuario.jsp").forward(request, response);
        }
        //  ALTA PACIENTES
        if(accion.equals("altaPaciente")){
            Pacientes p = new Pacientes();
            p.setNombre(request.getParameter("nombre"));
            p.setPaterno(request.getParameter("paterno"));
            p.setMaterno(request.getParameter("materno"));
            p.setTelefono(request.getParameter("telefono"));
            p.setEmail(request.getParameter("email"));
            p.setFechaNacimiento(request.getParameter("fechaNacimiento"));
            p.setDireccion(request.getParameter("direccion"));
            p.altaPaciente();
            request.setAttribute("respuesta", p.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
        }
        if(accion.equals("altaCita")){
            Citas c = new Citas();
            c.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
            c.setUsuarioMedico(request.getParameter("usuarioMedico"));
            c.setFecha(request.getParameter("fecha"));
            c.setHora(request.getParameter("hora"));
            c.setMotivo(request.getParameter("motivo"));
            c.altaCita();
            request.setAttribute("respuesta",c.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request,response);
        }
        if(accion.equals("cancelarCita")){
            Citas c = new Citas();
            c.setIdCita(Integer.parseInt(request.getParameter("idCita")));
            c.cancelarCita();
            request.setAttribute("respuesta", c.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
        }
        if(accion.equals("modificarCita")){
            Citas c = new Citas();
            c.setIdCita(Integer.parseInt(request.getParameter("idCita")));
            c.setFecha(request.getParameter("fecha"));
            c.setHora(request.getParameter("hora"));
            c.setMotivo(request.getParameter("motivo"));
            c.modificarCita();
            request.setAttribute("respuesta", c.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
        }
        if(accion.equals("registrarConsulta")){
            Consultas c = new Consultas();
            c.setIdCita(Integer.parseInt(request.getParameter("idCita")));
            c.setMedicamento(request.getParameter("medicamento"));
            c.setObservaciones(request.getParameter("observaciones"));
            c.registrarConsulta();
            request.setAttribute("respuesta",c.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request,response);
        }
        if(accion.equals("pdfBarrasConsultas")){
            Reportes r = new Reportes();
            r.setPeriodo(request.getParameter("periodo"));
            r.crearPdfBarras();
            request.setAttribute("respuesta", r.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
        }
        if(accion.equals("pdfPieConsultas")){
            Reportes r = new Reportes();
            r.setPeriodo(request.getParameter("periodo"));
            r.crearPdfPie();
            request.setAttribute("respuesta", r.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
        }
        if(accion.equals("excelConsultas")){
            Reportes r = new Reportes();
            r.setPeriodo(request.getParameter("periodo"));
            r.exportarExcel();
            request.setAttribute("respuesta", r.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
        }
        if(accion.equals("modificarPaciente")){
            Pacientes p = new Pacientes();
            p.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
            p.setNombre(request.getParameter("nombre"));
            p.setaPaterno(request.getParameter("aPaterno"));
            p.setaMaterno(request.getParameter("aMaterno"));
            p.setTelefono(request.getParameter("telefono"));
            p.modificarPaciente();
            request.setAttribute("respuesta",p.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request,response);
        }
        if(accion.equals("desactivarPaciente")){
            Pacientes p = new Pacientes();
            p.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
            p.desactivarPaciente();
            request.setAttribute("respuesta",p.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request,response);
        }
        if(accion.equals("activarPaciente")){
            Pacientes p = new Pacientes();
            p.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
            p.activarPaciente();
            request.setAttribute("respuesta",p.getRespuesta());
            request.getRequestDispatcher("Respuesta.jsp").forward(request,response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

