/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;
import com.itextpdf.text.BaseColor;
import java.sql.*;
import java.io.File;
import java.io.FileWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Admin
 */
public class Usuarios {
    private String usuario, password;
    private String respuesta, opcion;
    private String nombre, materno, paterno;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
        if (opcion.equals("verifica")) {
            buscaUsuario();
        }
        if (opcion.equals("registro")) {
            obtenerUsuario();
        }
    }
    
    
    public void buscaUsuario() {
        try {
            Connection con = Conexion.conectar();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement("select * from Usuarios where usuario=? and pass=?");
                ps.setString(1, usuario);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    if (tipo.equals("I")) {
                        respuesta = "Invitado/MenuInvitado.html";
                    }
                    if (tipo.equals("A")) {
                        respuesta = "Administrador/MenuAdmin.html";
                    }
                    if (tipo.equals("M")) {
                        respuesta = "Medico/MenuMedico.html";
                    }
                    if (tipo.equals("E")) {
                        respuesta = "Empleado/MenuEmpleado.html";
                    }
                } else {
                    respuesta = "Error.jsp?respuesta='No encontro dato'";
                }
            } else {
                respuesta = "Error.jsp?respuesta='No hay conexion en la base'";
            }
        } catch (Exception e) {
            respuesta = "Error.jsp?respuesta='Error al buscar'" + e;
        }
    }
    public int aleatorio(){
        return((int)(Math.random()*100));
    }
    
    public void obtenerUsuario(){
        int y= aleatorio();

        String r = nombre.substring(0,3);
        r+= paterno.substring(0,3);
        r+= materno.substring(0,3);
        if(y<10) r+= "00"+y;
        else if(y<100) r+= "0"+y;

        else r+= y;
        usuario = r;
        password = EncriptarMD5.encripta(r);
        respuesta = "<H1> Tu usuario es " + usuario +" y tu password es "+password+"<br></H1>";
        guardarRegistro();
    }
    
    public void guardarRegistro(){
        
    }
}
